package com.abes.hrms.repository;

import org.springframework.stereotype.Component;

@Component 
public class EmpRepimp implements EmpRepo {
    @Override
    public double squareRoot(double d) {
        double result = Math.sqrt(d);
        return result;

    }
    @Override
    public String empData() {
        return "";
    }

    @Override 
    public int add (int a, int b) {
        return a + b;
    }

    @Override
    public int greaterNumber(int a, int b) {
        return Math.max(a, b);
    }
}
