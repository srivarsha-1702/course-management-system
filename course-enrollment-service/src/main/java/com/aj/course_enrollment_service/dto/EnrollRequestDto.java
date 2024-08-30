package com.aj.course_enrollment_service.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EnrollRequestDto {
    private Integer emp_id;
    private Integer course_id;
}
