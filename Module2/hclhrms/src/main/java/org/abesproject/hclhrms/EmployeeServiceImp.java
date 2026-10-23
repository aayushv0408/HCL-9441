package org.abesproject.hclhrms;

import org.abesproject.hclhrms.entity.Employee;
import org.abesproject.hclhrms.repository.EmployeeRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImp {
    private final EmployeeRepo employeeRepo;

    public EmployeeServiceImp(EmployeeRepo employeeRepo) {
        this.employeeRepo = employeeRepo;
    }

    public Employee addemp(Employee employee) {
        return employeeRepo.addemp(employee);
    }

    public List<Employee> getemp() {
        return employeeRepo.getemp();
    }

    public Employee getEmpById(long empid) {
        return employeeRepo.getEmpById(empid);
    }

    public boolean deleteById(long empid) {
        return employeeRepo.deleteById(empid);
    }

    public Employee updateById(long empid, Employee updatedEmployee) {
        return employeeRepo.updateById(empid, updatedEmployee);
    }
}
