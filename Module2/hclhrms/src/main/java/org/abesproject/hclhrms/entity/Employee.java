package org.abesproject.hclhrms.entity;

public class Employee {
    private String empid;
    private String empname;
    private String designation;
    private double salary;
    private String progStack;

    public Employee() {
    }

    public void setEmpid(String empid) {
        this.empid = empid;
    }

    public void setEmpname(String empname) {
        this.empname = empname;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public void setProgStack(String progStack) {
        this.progStack = progStack;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getEmpid() {
        return empid;
    }

    public String getEmpname() {
        return empname;
    }

    public String getDesignation() {
        return designation;
    }

    public String getProgStack() {
        return progStack;
    }

    public double getSalary() {
        return salary;
    }



    public Employee(String empid, String empname, String designation, String progStack, double salary) {
        this.empid = empid;
        this.empname = empname;
        this.designation = designation;
        this.progStack = progStack;
        this.salary = salary;
    }


}
