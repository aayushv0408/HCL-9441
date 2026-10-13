package com.hcl.library.model;

import java.util.Objects;

public abstract class LibraryItem implements Borrowable {
    private final int id;
    private final String title;
    private final ItemType type;
    private boolean available = true;

    protected LibraryItem(int id, String title, ItemType type) {
        if (id <= 0) {
            throw new IllegalArgumentException("Item ID must be a positive integer.");
        }
        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("Item title cannot be blank.");
        }
        this.id = id;
        this.title = title.trim();
        this.type = Objects.requireNonNull(type, "Item type cannot be null.");
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public ItemType getType() {
        return type;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    @Override
    public final boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof LibraryItem)) {
            return false;
        }
        LibraryItem that = (LibraryItem) other;
        return id == that.id;
    }

    @Override
    public final int hashCode() {
        return Integer.hashCode(id);
    }

    @Override
    public String toString() {
        return id + " | " + type + " | " + title + " | "
                + (available ? "Available" : "Borrowed");
    }
}
