package top.okay3r.service;

import top.okay3r.entity.Employee;

public interface EmployeeService {
    void addEmployee(Employee employee);

    Employee findEmployeeById(Integer id);

    Integer findEmployeeCount();
}
