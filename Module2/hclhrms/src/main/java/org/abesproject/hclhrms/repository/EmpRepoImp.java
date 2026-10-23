package org.abesproject.hclhrms.repository;

import org.abesproject.hclhrms.entity.Employee;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class EmpRepoImp implements EmployeeRepo {

    private final Map<Long, Employee> employees = new ConcurrentSkipListMap<>();
    private final AtomicLong nextEmployeeId = new AtomicLong(1);

    @Override
    public Employee addemp(Employee employee) {
        long employeeId = nextEmployeeId.getAndIncrement();
        employee.setEmpid(String.valueOf(employeeId));
        employees.put(employeeId, employee);
        return employee;
    }

    @Override
    public List<Employee> getemp() {
        return List.copyOf(employees.values());
    }

    @Override
    public Employee getEmpById(long empid) {
        return employees.get(empid);
    }

    @Override
    public boolean deleteById(long empid) {
        return employees.remove(empid) != null;
    }

    @Override
    public Employee updateById(long empid, Employee updatedEmployee) {
        if (updatedEmployee == null || !employees.containsKey(empid)) {
            return null;
        }

        updatedEmployee.setEmpid(String.valueOf(empid));
        employees.put(empid, updatedEmployee);
        return updatedEmployee;
    }
}
