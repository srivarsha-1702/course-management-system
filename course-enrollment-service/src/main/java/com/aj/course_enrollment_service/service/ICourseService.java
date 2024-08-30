package com.aj.course_enrollment_service.service;

import com.aj.course_enrollment_service.dto.CourseDto;
import com.aj.course_enrollment_service.dto.EmployeeDto;
import com.aj.course_enrollment_service.dto.EnrollRequestDto;

import java.util.List;

public interface ICourseService {
    void createCourse(CourseDto courseDto);
    CourseDto fetchCourse(String title);
    boolean updateCourse(CourseDto courseDto);
    boolean deleteCourse(String title);
    List<CourseDto> fetchAllCourse();

    CourseDto fetchCourseById(Integer id);

    void enrollCourse(EnrollRequestDto enrollRequestDto);

    boolean updateEnroll(Integer enrollmentId);

//    boolean deleteEnroll(EnrollRequestDto enrollRequestDto);

//    boolean deleteEnroll(Integer empId, Integer courseId);

//    List<EmployeeDto> getEmployeesByCourseId(Integer courseId);
//    List<EmployeeDto> getEmployeesByCourseId(Integer course_id);
}
