package com.hcl.library.exceptions;

public class DuplicateIsbnException extends LibraryException {
    private static final long serialVersionUID = 1L;

    public DuplicateIsbnException(String isbn) {
        super("A book with ISBN " + isbn + " is already in the library.");
    }
}
