package com.hcl.library.model;

public class DVD extends LibraryItem {
    public DVD(int id, String title) {
        super(id, title, ItemType.DVD);
    }

    @Override
    public int getLoanDays() {
        return 3;
    }

    @Override
    public double getLateFeePerDay() {
        return 1.00;
    }
}
