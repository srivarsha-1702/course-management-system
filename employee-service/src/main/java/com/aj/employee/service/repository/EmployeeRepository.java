package com.aj.employee.service.repository;

import com.aj.employee.service.entity.Employee;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
     Optional<Employee> findByMobileNumber(String mobileNumber);
     @Transactional
     @Modifying
     void deleteByMobileNumber(String MobileNumber);
}
