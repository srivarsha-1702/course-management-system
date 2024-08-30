package com.aj.employee.service.mapper;

import com.aj.employee.service.dto.EmployeeDto;
import com.aj.employee.service.entity.Employee;

public class EmployeeMapper {

    public static Employee mapToEmployee(EmployeeDto employeeDto, Employee employee){
        employee.setName(employeeDto.getName());
        employee.setDesignation(employeeDto.getDesignation());
        employee.setDepartment(employeeDto.getDepartment());
        employee.setEmail(employeeDto.getEmail());
        employee.setMobileNumber(employeeDto.getMobileNumber());
        return employee;
    }
    public static EmployeeDto mapToEmployeeDto(Employee employee, EmployeeDto employeeDto) {

        employeeDto.setName(employee.getName());
        employeeDto.setEmail(employee.getEmail());
        employeeDto.setDepartment(employee.getDepartment());
        employeeDto.setDesignation(employee.getDesignation());
        employeeDto.setMobileNumber(employee.getMobileNumber());

        return employeeDto;
    }

}
