package com.hcl.library.model;

public interface Borrowable {
    int getLoanDays();

    double getLateFeePerDay();
}
