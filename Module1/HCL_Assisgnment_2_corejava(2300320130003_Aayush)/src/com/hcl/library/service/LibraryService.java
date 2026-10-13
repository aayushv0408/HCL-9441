package com.hcl.library.service;

import com.hcl.library.exceptions.BorrowLimitExceededException;
import com.hcl.library.exceptions.DuplicateIsbnException;
import com.hcl.library.exceptions.ItemNotAvailableException;
import com.hcl.library.exceptions.ItemNotFoundException;
import com.hcl.library.exceptions.LibraryException;
import com.hcl.library.exceptions.MemberNotFoundException;
import com.hcl.library.model.Book;
import com.hcl.library.model.LibraryItem;
import com.hcl.library.model.Loan;
import com.hcl.library.model.Member;
import com.hcl.library.repository.InMemoryRepository;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Queue;
import java.util.Set;

public class LibraryService {
    private static final int BORROW_LIMIT = 3;

    private final InMemoryRepository<LibraryItem, Integer> items =
            new InMemoryRepository<LibraryItem, Integer>(LibraryItem::getId);
    private final InMemoryRepository<Member, Integer> members =
            new InMemoryRepository<Member, Integer>(Member::getId);
    private final Map<Integer, Loan> activeLoans = new HashMap<Integer, Loan>();
    private final List<Loan> loanHistory = new ArrayList<Loan>();
    private final Map<Integer, Queue<Integer>> waitlists = new HashMap<Integer, Queue<Integer>>();
    private final Set<String> bookIsbns = new HashSet<String>();

    public void addItem(LibraryItem item) throws LibraryException {
        if (items.findById(item.getId()).isPresent()) {
            throw new LibraryException("An item with ID " + item.getId() + " already exists.");
        }
        if (item instanceof Book) {
            String isbn = ((Book) item).getIsbn();
            if (!bookIsbns.add(isbn)) {
                throw new DuplicateIsbnException(isbn);
            }
        }
        items.save(item);
    }

    public void registerMember(Member member) throws LibraryException {
        if (members.findById(member.getId()).isPresent()) {
            throw new LibraryException("A member with ID " + member.getId() + " already exists.");
        }
        members.save(member);
    }

    public void issueItem(int itemId, int memberId) throws LibraryException {
        LibraryItem item = requireItem(itemId);
        Member member = requireMember(memberId);
        if (!item.isAvailable()) {
            throw new ItemNotAvailableException(itemId);
        }
        if (countLoansFor(memberId) >= BORROW_LIMIT) {
            throw new BorrowLimitExceededException(BORROW_LIMIT);
        }

        LocalDate checkoutDate = LocalDate.now();
        Loan loan = new Loan(itemId, member.getId(), checkoutDate,
                checkoutDate.plusDays(item.getLoanDays()));
        activeLoans.put(itemId, loan);
        item.setAvailable(false);
        System.out.println("Issued \"" + item.getTitle() + "\" to " + member.getName()
                + ". Due date: " + loan.getDueDate() + ".");
    }

    public double returnItem(int itemId) throws LibraryException {
        LibraryItem item = requireItem(itemId);
        Loan loan = activeLoans.remove(itemId);
        if (loan == null) {
            throw new LibraryException("Item " + itemId + " is not currently on loan.");
        }

        LocalDate returnDate = LocalDate.now();
        long overdueDays = Math.max(0, ChronoUnit.DAYS.between(loan.getDueDate(), returnDate));
        double lateFee = overdueDays * item.getLateFeePerDay();
        loanHistory.add(loan);
        item.setAvailable(true);
        System.out.printf("Returned \"%s\". Late fee: $%.2f (%d overdue day(s)).%n",
                item.getTitle(), lateFee, overdueDays);

        assignToNextWaitlistedMember(item);
        return lateFee;
    }

    public void joinWaitlist(int itemId, int memberId) throws LibraryException {
        LibraryItem item = requireItem(itemId);
        requireMember(memberId);
        if (item.isAvailable()) {
            throw new LibraryException("Item " + itemId
                    + " is available; issue it directly instead of joining its waitlist.");
        }
        Queue<Integer> waitlist = waitlists.get(itemId);
        if (waitlist == null) {
            waitlist = new ArrayDeque<Integer>();
            waitlists.put(itemId, waitlist);
        }
        if (waitlist.contains(memberId)) {
            throw new LibraryException("Member " + memberId + " is already on the waitlist for item "
                    + itemId + ".");
        }
        waitlist.add(memberId);
        System.out.println("Member " + memberId + " added to the waitlist for item " + itemId
                + " (position " + waitlist.size() + ").");
    }

    public Collection<LibraryItem> getItems() {
        return items.findAll();
    }

    public Collection<Member> getMembers() {
        return members.findAll();
    }

    public void exportReport(String path) throws LibraryException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path))) {
            writer.write("College Library Item Status Report");
            writer.newLine();
            writer.write("Generated: " + LocalDate.now());
            writer.newLine();
            writer.newLine();
            for (LibraryItem item : items.findAll()) {
                writer.write(item.toString());
                Loan loan = activeLoans.get(item.getId());
                if (loan != null) {
                    Optional<Member> member = members.findById(loan.getMemberId());
                    writer.write(" | Due: " + loan.getDueDate()
                            + " | Borrower: " + member.map(Member::getName).orElse("Unknown"));
                }
                Queue<Integer> waitlist = waitlists.get(item.getId());
                writer.write(" | Waitlist: " + (waitlist == null ? 0 : waitlist.size()));
                writer.newLine();
            }
        } catch (IOException exception) {
            throw new LibraryException("Could not export the library report to " + path + ".",
                    exception);
        }
        System.out.println("Report exported to " + path + ".");
    }

    private LibraryItem requireItem(int itemId) throws ItemNotFoundException {
        return items.findById(itemId).orElseThrow(() -> new ItemNotFoundException(itemId));
    }

    private Member requireMember(int memberId) throws MemberNotFoundException {
        return members.findById(memberId).orElseThrow(() -> new MemberNotFoundException(memberId));
    }

    private int countLoansFor(int memberId) {
        int count = 0;
        for (Loan loan : activeLoans.values()) {
            if (loan.getMemberId() == memberId) {
                count++;
            }
        }
        return count;
    }

    private void assignToNextWaitlistedMember(LibraryItem item) {
        Queue<Integer> waitlist = waitlists.get(item.getId());
        while (waitlist != null && !waitlist.isEmpty()) {
            int nextMemberId = waitlist.remove();
            if (!members.findById(nextMemberId).isPresent()
                    || countLoansFor(nextMemberId) >= BORROW_LIMIT) {
                continue;
            }

            LocalDate checkoutDate = LocalDate.now();
            Loan nextLoan = new Loan(item.getId(), nextMemberId, checkoutDate,
                    checkoutDate.plusDays(item.getLoanDays()));
            activeLoans.put(item.getId(), nextLoan);
            item.setAvailable(false);
            Member member = members.findById(nextMemberId).get();
            System.out.println("Waitlist fulfilled: \"" + item.getTitle() + "\" is now issued to "
                    + member.getName() + ". Due date: " + nextLoan.getDueDate() + ".");
            break;
        }
        if (waitlist != null && waitlist.isEmpty()) {
            waitlists.remove(item.getId());
        }
    }
}
