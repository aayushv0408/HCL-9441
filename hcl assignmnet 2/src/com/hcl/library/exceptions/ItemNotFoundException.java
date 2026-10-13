package com.hcl.library.exceptions;

public class ItemNotFoundException extends LibraryException {
    private static final long serialVersionUID = 1L;

    public ItemNotFoundException(int itemId) {
        super("No library item exists with ID " + itemId + ".");
    }
}
