package com.hcl.library.exceptions;

public class ItemNotAvailableException extends LibraryException {
    private static final long serialVersionUID = 1L;

    public ItemNotAvailableException(int itemId) {
        super("Item " + itemId + " is currently borrowed. Join its waitlist to be notified on return.");
    }
}
