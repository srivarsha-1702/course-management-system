package com.aj.employee.service.service.impl;

import com.aj.employee.service.dto.EmployeeDto;
import com.aj.employee.service.entity.Employee;
import com.aj.employee.service.exception.EmployeeAlreadyExistsException;
import com.aj.employee.service.exception.EmployeeNotFoundException;
import com.aj.employee.service.mapper.EmployeeMapper;
import com.aj.employee.service.repository.EmployeeRepository;
import com.aj.employee.service.service.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements IEmployeeService {
    @Autowired
    private EmployeeRepository repository;
    @Override
    public void createEmployee(EmployeeDto employeeDto){
        Optional<Employee> employeeOptional = repository.findByMobileNumber(employeeDto.getMobileNumber());
        if(employeeOptional.isPresent()){
            throw new EmployeeAlreadyExistsException("Employee Already exists with the number - " + employeeDto.getMobileNumber());
        }
        Employee employee = EmployeeMapper.mapToEmployee(employeeDto, new Employee());
        repository.save(employee);
    }

    @Override
    public EmployeeDto fetchEmployee(String mobileNumber){
        Employee employee = repository.findByMobileNumber(mobileNumber).orElseThrow(
                ()-> new EmployeeNotFoundException("Employee does not exists for mobile number - " + mobileNumber )
        );
        EmployeeDto employeeDto = EmployeeMapper.mapToEmployeeDto(employee, new EmployeeDto());
        return employeeDto;
    }

    @Override
    public EmployeeDto fetchEmployeeById(Integer id){
        Employee employee = repository.findById(id).orElseThrow(
                ()-> new EmployeeNotFoundException("Employee does not exists for id - " + id )
        );
        EmployeeDto employeeDto = EmployeeMapper.mapToEmployeeDto(employee, new EmployeeDto());
        return employeeDto;
    }
    @Override
    public boolean updateEmployee(EmployeeDto employeeDto){
        boolean isUpdated = false;
        if(employeeDto.getMobileNumber() == null){
            return isUpdated;
        }
        Employee employee = repository.findByMobileNumber(employeeDto.getMobileNumber()).orElseThrow(
                ()-> new EmployeeNotFoundException("Employee does not exists for mobile number - " + employeeDto.getMobileNumber() )
        );
        Employee updatedEmployee = EmployeeMapper.mapToEmployee(employeeDto, employee);
        repository.save(updatedEmployee);
        isUpdated = true;
        return isUpdated;
    }

    @Override
    public boolean deleteEmployee(String mobileNumber){
        boolean isDeleted = false;
        if(mobileNumber == null){
            return isDeleted;
        }
        repository.deleteByMobileNumber(mobileNumber);
        isDeleted = true;
        return isDeleted;
    }

    @Override
    public List<EmployeeDto> fetchAllEmployee() {
        List<Employee> employees = repository.findAll();
        List<EmployeeDto> employeeDtos = new ArrayList<>();
        for(Employee employee:employees){
            EmployeeDto employeeDto = EmployeeMapper.mapToEmployeeDto(employee, new EmployeeDto());
            employeeDtos.add(employeeDto);
        }
//        List<EmployeeDto> employeeDtos = employees.stream()
//                .map(employee -> EmployeeMapper.mapToEmployeeDto(employee, new EmployeeDto()))
//                .collect(Collectors.toList());
        return employeeDtos;

    }
}
