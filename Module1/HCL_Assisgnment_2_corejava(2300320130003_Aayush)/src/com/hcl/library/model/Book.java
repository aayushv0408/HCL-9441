package com.hcl.library.model;

public class Book extends LibraryItem {
    private final String isbn;

    public Book(int id, String title, String isbn) {
        super(id, title, ItemType.BOOK);
        if (isbn == null || isbn.trim().isEmpty()) {
            throw new IllegalArgumentException("ISBN cannot be blank.");
        }
        this.isbn = isbn.trim();
    }

    public String getIsbn() {
        return isbn;
    }

    @Override
    public int getLoanDays() {
        return 14;
    }

    @Override
    public double getLateFeePerDay() {
        return 0.50;
    }

    @Override
    public String toString() {
        return super.toString() + " | ISBN: " + isbn;
    }
}
