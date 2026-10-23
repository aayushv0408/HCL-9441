package org.abesproject.hclhrms.controller;

import org.abesproject.hclhrms.EmployeeServiceImp;
import org.abesproject.hclhrms.entity.Employee;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class EmployeeController {

	private final EmployeeServiceImp employeeService;

	public EmployeeController(EmployeeServiceImp employeeService) {
		this.employeeService = employeeService;
	}

	@GetMapping("/")
	public String healthCheck() {
		return "HRMS Employee API is running. Use /api/employees to manage employees.";
	}

	@PostMapping("/api/employees")
	public ResponseEntity<Employee> addEmployee(@RequestBody Employee employee) {
		return ResponseEntity.status(HttpStatus.CREATED).body(employeeService.addemp(employee));
	}

	@GetMapping("/api/employees")
	public List<Employee> getEmployees() {
		return employeeService.getemp();
	}

	@GetMapping("/api/employees/{empid}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable long empid) {
		return ResponseEntity.of(Optional.ofNullable(employeeService.getEmpById(empid)));
	}

	@PutMapping("/api/employees/{empid}")
	public ResponseEntity<Employee> updateEmployee(
			@PathVariable long empid,
			@RequestBody Employee employee
	) {
		return ResponseEntity.of(Optional.ofNullable(employeeService.updateById(empid, employee)));
	}

	@DeleteMapping("/api/employees/{empid}")
	public ResponseEntity<Void> deleteEmployee(@PathVariable long empid) {
		return employeeService.deleteById(empid)
				? ResponseEntity.noContent().build()
				: ResponseEntity.notFound().build();
	}
}
