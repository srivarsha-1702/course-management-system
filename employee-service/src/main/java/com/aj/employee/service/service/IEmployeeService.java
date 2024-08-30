package com.aj.employee.service.service;

import com.aj.employee.service.dto.EmployeeDto;
import com.aj.employee.service.mapper.EmployeeMapper;

import java.util.List;

public interface IEmployeeService {
    void createEmployee(EmployeeDto employeeDto);
    EmployeeDto fetchEmployee(String employeeDto);
    boolean updateEmployee(EmployeeDto employeeDto);
    boolean deleteEmployee(String mobileNumber);
    List<EmployeeDto> fetchAllEmployee();

    EmployeeDto fetchEmployeeById(Integer id);
}
