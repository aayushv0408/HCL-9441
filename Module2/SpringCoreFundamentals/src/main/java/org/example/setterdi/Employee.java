package org.example.setterdi;

public class Employee {
    private Address address;

    public void setAddress(Address address) {
        this.address = address;
    }

    public void displayAddress() {
        address.getAddress();
    }
}