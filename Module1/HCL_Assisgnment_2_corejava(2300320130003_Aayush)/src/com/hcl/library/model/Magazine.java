package com.hcl.library.model;

public class Magazine extends LibraryItem {
    public Magazine(int id, String title) {
        super(id, title, ItemType.MAGAZINE);
    }

    @Override
    public int getLoanDays() {
        return 7;
    }

    @Override
    public double getLateFeePerDay() {
        return 0.25;
    }
}
