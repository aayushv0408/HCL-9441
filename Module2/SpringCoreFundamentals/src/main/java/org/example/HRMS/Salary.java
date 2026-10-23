package org.example.HRMS;

public class Salary {
    private String category;
    private double amount;

    public Salary(String category, double amount){
        this.category=category;
        this.amount=amount;
    }
    public String getCategory() {
        return category;
    }

    public double getAmount() {
        return amount;
    }
}
