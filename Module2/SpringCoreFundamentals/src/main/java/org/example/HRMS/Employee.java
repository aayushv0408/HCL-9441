package org.example.HRMS;

public class Employee {
    private int employeeId;
    private String employeeName;
    private Address address;
    private Department department;
    private Salary salary;

    public Employee(int employeeId, String employeeName, Address address, Department department, Salary salary){
        this.employeeId = employeeId;
        this.employeeName = employeeName;
        this.address = address;
        this.salary = salary;
        this.department = department;
    }
    public void display() {
        System.out.println("Employee ID: " + employeeId);
        System.out.println("Employee Name: " + employeeName);
        System.out.println("City: " + address.getCity());
        System.out.println("State: " + address.getState());
        System.out.println("Department: " + department.getDepartmentName());
        System.out.println("Salary Category: " + salary.getCategory());
        System.out.println("Salary Amount: " + salary.getAmount());
    }

}
