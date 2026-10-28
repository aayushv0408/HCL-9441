package com.abes.hrmsdatabase.service;

import com.abes.hrmsdatabase.model.Employee;
import com.abes.hrmsdatabase.repository.EmployeeRepository;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    public EmployeeService(EmployeeRepository employeeRepository) { this.employeeRepository = employeeRepository; }

    public String createNewEmployee(Employee employee) {
        if (employee == null || employee.getEmpId() == null) throw new IllegalArgumentException("Employee ID is required");
        if (employee.getEmail() == null || employee.getEmail().isBlank()) throw new IllegalArgumentException("Email is required");
        if (employeeRepository.existsById(employee.getEmpId())) throw new IllegalArgumentException("Employee ID already exists");
        employeeRepository.save(employee);
        return "Employee saved successfully";
    }
    public String getById(Long id) {
        if (id == null) return "Employee ID is required";
        return employeeRepository.existsById(id) ? "User exists with ID: " + id : "Not Found";
    }
    public Employee login(Long empId, String email, String password) {
        if (empId == null || email == null || password == null) throw new IllegalArgumentException("Invalid credentials");
        Employee emp = employeeRepository.findById(empId).orElseThrow(() -> new IllegalArgumentException("Invalid credentials"));
        if (!email.equals(emp.getEmail()) || !password.equals(emp.getPassword())) throw new IllegalArgumentException("Invalid credentials");
        return emp;
    }
    public String deleteById(Long id) {
        if (id == null || !employeeRepository.existsById(id)) return "User does not exist";
        employeeRepository.deleteById(id); return "User deleted successfully";
    }
    public String updateEmployee(Long id, Employee incoming) {
        Optional<Employee> found = employeeRepository.findById(id);
        if (found.isEmpty()) return "Employee not found with ID: " + id;
        Employee employee = found.get();
        if (incoming.getEmp_name() != null) employee.setEmp_name(incoming.getEmp_name());
        if (incoming.getStack() != null) employee.setStack(incoming.getStack());
        if (incoming.getDesignation() != null) employee.setDesignation(incoming.getDesignation());
        if (incoming.getPassword() != null && !incoming.getPassword().isBlank()) employee.setPassword(incoming.getPassword());
        if (incoming.getEmail() != null) employee.setEmail(incoming.getEmail());
        employeeRepository.save(employee); return "Employee updated successfully";
    }
}
