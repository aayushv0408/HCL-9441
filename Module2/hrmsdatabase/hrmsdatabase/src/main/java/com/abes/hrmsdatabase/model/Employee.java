package com.abes.hrmsdatabase.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Id;

@Entity
public class Employee {
    @Id
    private Long empId;
    private String emp_name;
    private String email;
    private String stack;
    private String designation;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    public Employee(){
    }
    public Employee(Long empId, String emp_name, String email, String stack, String designation, String password) {
        this.empId = empId;
        this.emp_name = emp_name;
        this.email = email;
        this.stack = stack;
        this.designation = designation;
        this.password = password;
    }

    public Long getEmpId() {
        return empId;
    }

    public void setEmpId(Long empId) {
        this.empId = empId;
    }

    public String getEmp_name() {
        return emp_name;
    }

    public void setEmp_name(String emp_name) {
        this.emp_name = emp_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStack() {
        return stack;
    }

    public void setStack(String stack) {
        this.stack = stack;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
