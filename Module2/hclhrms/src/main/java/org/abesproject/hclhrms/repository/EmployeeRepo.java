package org.abesproject.hclhrms.repository;
import org.abesproject.hclhrms.entity.Employee;
import java.util.List;

public interface EmployeeRepo {
    Employee addemp(Employee employee);
    List<Employee> getemp();
    Employee getEmpById(long empid);
    boolean deleteById(long empid);
    Employee updateById(long empid, Employee updatedEmployee);

}
