package com.hcl.library;

import com.hcl.library.exceptions.BorrowLimitExceededException;
import com.hcl.library.exceptions.ItemNotAvailableException;
import com.hcl.library.exceptions.ItemNotFoundException;
import com.hcl.library.exceptions.LibraryException;
import com.hcl.library.exceptions.MemberNotFoundException;
import com.hcl.library.model.Book;
import com.hcl.library.model.DVD;
import com.hcl.library.model.LibraryItem;
import com.hcl.library.model.Magazine;
import com.hcl.library.model.Member;
import com.hcl.library.service.LibraryService;

import java.util.Scanner;

public class Main {
    private final Scanner scanner = new Scanner(System.in);
    private final LibraryService library = new LibraryService();

    public static void main(String[] args) {
        new Main().run();
    }

    private void run() {
        boolean running = true;
        while (running) {
            printMenu();
            try {
                int choice = readInt("Choose an option: ");
                switch (choice) {
                    case 1:
                        addItem();
                        break;
                    case 2:
                        registerMember();
                        break;
                    case 3:
                        issueItem();
                        break;
                    case 4:
                        library.returnItem(readInt("Item ID to return: "));
                        break;
                    case 5:
                        library.joinWaitlist(readInt("Item ID: "), readInt("Member ID: "));
                        break;
                    case 6:
                        listItems();
                        break;
                    case 7:
                        listMembers();
                        break;
                    case 8:
                        library.exportReport(readText("Report file path: "));
                        break;
                    case 0:
                        running = false;
                        System.out.println("Goodbye.");
                        break;
                    default:
                        System.out.println("Please select a listed menu option.");
                }
            } catch (ItemNotFoundException exception) {
                System.out.println("Item error: " + exception.getMessage());
            } catch (MemberNotFoundException exception) {
                System.out.println("Member error: " + exception.getMessage());
            } catch (ItemNotAvailableException exception) {
                System.out.println("Availability error: " + exception.getMessage());
            } catch (BorrowLimitExceededException exception) {
                System.out.println("Borrowing error: " + exception.getMessage());
            } catch (LibraryException exception) {
                System.out.println("Library error: " + exception.getMessage());
            } catch (IllegalArgumentException exception) {
                System.out.println("Input error: " + exception.getMessage());
            } finally {
                if (running) {
                    System.out.println();
                }
            }
        }
    }

    private void printMenu() {
        System.out.println("=== College Library ===");
        System.out.println("1. Add library item");
        System.out.println("2. Register member");
        System.out.println("3. Issue item");
        System.out.println("4. Return item");
        System.out.println("5. Join item waitlist");
        System.out.println("6. List items");
        System.out.println("7. List members");
        System.out.println("8. Export item report");
        System.out.println("0. Exit");
    }

    private void addItem() throws LibraryException {
        System.out.println("1. Book  2. Magazine  3. DVD");
        int type = readInt("Item type: ");
        int id = readInt("Item ID: ");
        String title = readText("Title: ");
        LibraryItem item;
        switch (type) {
            case 1:
                item = new Book(id, title, readText("ISBN: "));
                break;
            case 2:
                item = new Magazine(id, title);
                break;
            case 3:
                item = new DVD(id, title);
                break;
            default:
                throw new IllegalArgumentException("Unknown item type.");
        }
        library.addItem(item);
        System.out.println("Added: " + item);
    }

    private void registerMember() throws LibraryException {
        int id = readInt("Member ID: ");
        String name = readText("Name: ");
        String email = readText("Email: ");
        Member member = new Member(id, name, email);
        library.registerMember(member);
        System.out.println("Registered: " + member);
    }

    private void issueItem() throws LibraryException {
        library.issueItem(readInt("Item ID: "), readInt("Member ID: "));
    }

    private void listItems() {
        if (library.getItems().isEmpty()) {
            System.out.println("No library items have been added.");
            return;
        }
        for (LibraryItem item : library.getItems()) {
            System.out.println(item);
        }
    }

    private void listMembers() {
        if (library.getMembers().isEmpty()) {
            System.out.println("No members have been registered.");
            return;
        }
        for (Member member : library.getMembers()) {
            System.out.println(member);
        }
    }

    private int readInt(String prompt) {
        System.out.print(prompt);
        String value = scanner.nextLine().trim();
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException exception) {
            throw new IllegalArgumentException("Please enter a whole number.");
        }
    }

    private String readText(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }
}
