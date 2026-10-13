package com.hcl.library.exceptions;

public class MemberNotFoundException extends LibraryException {
    private static final long serialVersionUID = 1L;

    public MemberNotFoundException(int memberId) {
        super("No library member exists with ID " + memberId + ".");
    }
}
