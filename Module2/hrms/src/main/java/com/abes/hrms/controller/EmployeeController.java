
package com.abes.hrms.controller;

import com.abes.hrms.model.DemoEmployee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeController {

    private final DemoEmployee demoEmployee;

    @Autowired
    public EmployeeController(DemoEmployee demoEmployee) {
        this.demoEmployee = demoEmployee;
    }

    @GetMapping("/")
    public String welcome() {
        return "Welcome to HRMS";
    }

    @GetMapping("/add")
    public int sum() {
        System.out.println("Inside sum method");
        int result = demoEmployee.add(10,80);
        System.out.println("Result of addition: " + result);
        return result;
    }

    @GetMapping("/gtrnum")
    public int greaterNumber(@RequestParam int a, @RequestParam int b) {
        return demoEmployee.greaterNumber(a,b);
    }

    @GetMapping("/sqrt")
    public double squareRoot() {
        System.out.println("Inside squareRoot method");
        return demoEmployee.squareRoot();
    }
}
