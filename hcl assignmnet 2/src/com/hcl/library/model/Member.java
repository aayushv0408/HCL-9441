package com.hcl.library.model;

public class Member {
    private final int id;
    private final String name;
    private final String email;

    public Member(int id, String name, String email) {
        if (id <= 0) {
            throw new IllegalArgumentException("Member ID must be a positive integer.");
        }
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Member name cannot be blank.");
        }
        if (email == null || email.trim().isEmpty()) {
            throw new IllegalArgumentException("Member email cannot be blank.");
        }
        this.id = id;
        this.name = name.trim();
        this.email = email.trim();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return id + " | " + name + " | " + email;
    }
}
