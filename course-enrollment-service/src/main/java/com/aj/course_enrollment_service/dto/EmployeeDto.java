package com.aj.course_enrollment_service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDto {
    private String name;
    private String email;
    private String mobileNumber;
    private String designation;
    private String department;

}
