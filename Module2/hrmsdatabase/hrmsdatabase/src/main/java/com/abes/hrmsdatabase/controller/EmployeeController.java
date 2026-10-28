package com.abes.hrmsdatabase.controller;

import com.abes.hrmsdatabase.model.Employee;
import com.abes.hrmsdatabase.service.EmployeeService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@CrossOrigin(origins = {"http://localhost:5173", "http://127.0.0.1:5173"})
@RequestMapping("/v1/user")
public class EmployeeController {
    private final EmployeeService employeeService;
    public EmployeeController(EmployeeService employeeService) { this.employeeService = employeeService; }
    @GetMapping("/") public String welcome() { return "Welcome to HRMS Database"; }
    @PostMapping("/addNewEmployee")
    public String addNewEmployee(@RequestBody Employee employee) {
        try { return employeeService.createNewEmployee(employee); }
        catch (IllegalArgumentException e) { throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage()); }
        catch (DataIntegrityViolationException e) { throw new ResponseStatusException(HttpStatus.CONFLICT, "Employee ID already exists"); }
    }
    @PostMapping("/login")
    public Employee login(@RequestBody Employee emp) {
        try { return employeeService.login(emp.getEmpId(), emp.getEmail(), emp.getPassword()); }
        catch (IllegalArgumentException e) { throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid credentials"); }
    }
    @GetMapping("/findById/{empid}") public String getById(@PathVariable Long empid) { return employeeService.getById(empid); }
    @DeleteMapping("/deleteById/{empid}") public String deleteById(@PathVariable Long empid) { return employeeService.deleteById(empid); }
    @PutMapping("/updateEmployee/{empid}")
    public String updateEmployee(@PathVariable Long empid, @RequestBody Employee emp) { return employeeService.updateEmployee(empid, emp); }
}
