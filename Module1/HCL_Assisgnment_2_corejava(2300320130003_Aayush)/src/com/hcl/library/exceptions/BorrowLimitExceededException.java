package com.hcl.library.exceptions;

public class BorrowLimitExceededException extends LibraryException {
    private static final long serialVersionUID = 1L;

    public BorrowLimitExceededException(int limit) {
        super("Borrowing limit reached. A member may hold at most " + limit + " active items.");
    }
}
