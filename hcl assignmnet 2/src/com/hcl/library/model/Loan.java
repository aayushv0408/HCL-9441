package com.hcl.library.model;

import java.time.LocalDate;

public class Loan {
    private final int itemId;
    private final int memberId;
    private final LocalDate checkoutDate;
    private final LocalDate dueDate;

    public Loan(int itemId, int memberId, LocalDate checkoutDate, LocalDate dueDate) {
        this.itemId = itemId;
        this.memberId = memberId;
        this.checkoutDate = checkoutDate;
        this.dueDate = dueDate;
    }

    public int getItemId() {
        return itemId;
    }

    public int getMemberId() {
        return memberId;
    }

    public LocalDate getCheckoutDate() {
        return checkoutDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }
}
