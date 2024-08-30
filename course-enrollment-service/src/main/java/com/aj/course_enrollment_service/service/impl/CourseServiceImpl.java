package com.aj.course_enrollment_service.service.impl;

import com.aj.course_enrollment_service.dto.CourseDto;
import com.aj.course_enrollment_service.dto.EmployeeDto;
import com.aj.course_enrollment_service.dto.ResponseDto;
import com.aj.course_enrollment_service.entity.Course;
import com.aj.course_enrollment_service.entity.Enrollment;
import com.aj.course_enrollment_service.exception.CourseAlreadyExistsException;
import com.aj.course_enrollment_service.exception.CourseNotFoundException;
import com.aj.course_enrollment_service.mapper.CourseMapper;
import com.aj.course_enrollment_service.repository.CourseRepository;
import com.aj.course_enrollment_service.repository.EnrollmentRepository;
import com.aj.course_enrollment_service.service.ICourseService;
import org.springframework.beans.factory.annotation.Autowired;
import com.aj.course_enrollment_service.dto.EnrollRequestDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CourseServiceImpl implements ICourseService {
    @Autowired
    private CourseRepository repository;
    @Autowired
    private EnrollmentRepository enrollmentRepository;
    @Autowired
    private WebClient webClient;
    @Override
    public void createCourse(CourseDto courseDto) {
        Optional<Course> courseOptional = repository.findByTitle(courseDto.getTitle());
        if(courseOptional.isPresent()){
            throw new CourseAlreadyExistsException("Course Already exists with the name - " + courseDto.getTitle());
        }
        Course course =  CourseMapper.mapToCourse(courseDto, new Course());
        repository.save(course);
    }

    @Override
    public CourseDto fetchCourse(String title){
        Course course = repository.findByTitle(title).orElseThrow(
                ()-> new CourseNotFoundException("Course does not exists for title - " + title )
        );
        CourseDto courseDto = CourseMapper.mapToCourseDto(course, new CourseDto());
        return courseDto;
    }
   @Override
   public CourseDto fetchCourseById(Integer id){
        Course course = repository.findById(id).orElseThrow(
                ()-> new CourseNotFoundException("Course does not exists for id - " + id )
        );
        CourseDto courseDto = CourseMapper.mapToCourseDto(course, new CourseDto());
        return courseDto;
    }
    @Override
    public boolean updateCourse(CourseDto courseDto){
        boolean isUpdated = false;
        if(courseDto.getTitle() == null){
            return isUpdated;
        }
        Course course = repository.findByTitle(courseDto.getTitle()).orElseThrow(
                ()-> new CourseNotFoundException("Course does not exists for title - " + courseDto.getTitle() )
        );
        Course updatedCourse = CourseMapper.mapToCourse(courseDto, course);
        repository.save(updatedCourse);
        isUpdated = true;
        return isUpdated;
    }
    @Override
    public boolean deleteCourse(String title){
        boolean isDeleted = false;
        if(title == null){
            return isDeleted;
        }
        repository.deleteByTitle(title);
        isDeleted = true;
        return isDeleted;
    }
    @Override
    public List<CourseDto> fetchAllCourse() {
        List<Course> courses = repository.findAll();
        List<CourseDto> courseDtos = new ArrayList<>();
        for(Course course:courses){
            CourseDto courseDto = CourseMapper.mapToCourseDto(course, new CourseDto());
            courseDtos.add(courseDto);
        }
//        List<EmployeeDto> employeeDtos = employees.stream()
//                .map(employee -> EmployeeMapper.mapToEmployeeDto(employee, new EmployeeDto()))
//                .collect(Collectors.toList());
        return courseDtos;

    }
    @Override
    public void enrollCourse(EnrollRequestDto enrollRequestDto) {
        try {
            EmployeeDto employeeDto = webClient.get()
                    .uri("http://localhost:8090/api/fetchbyid?id=" + enrollRequestDto.getEmp_id())
                    .retrieve()
                    .bodyToMono(EmployeeDto.class)
                    .block();

            CourseDto courseDto = fetchCourseById(enrollRequestDto.getCourse_id());

            Enrollment enrollment = new Enrollment();
            enrollment.setEmp_id(enrollRequestDto.getEmp_id());
            enrollment.setCourse_id(enrollRequestDto.getCourse_id());
            enrollment.setStatus(Boolean.FALSE);
            enrollmentRepository.save(enrollment);
        } catch (Exception e) {
            if (e.getMessage().contains("Course does not exists for id")) {
                throw new RuntimeException("Course doesn't exist", e);
            }
            throw new RuntimeException("Employee doesn't exist", e);
        }
    }
    @Override
    public boolean updateEnroll(Integer enrollmentId) {
        boolean isUpdated = false;
        if(enrollmentId == null) return isUpdated;
//        Enrollment enrollment = enrollmentRepository.findByEmpIdAndCourseId(enrollRequestDto.getEmp_id(), enrollRequestDto.getCourse_id();
        Enrollment enrollment = enrollmentRepository.findById(enrollmentId).orElseThrow(
                ()-> new CourseNotFoundException("Enrollment does not exists for id - " + enrollmentId )
        );
        enrollment.setStatus(Boolean.TRUE);
        enrollmentRepository.save(enrollment);
        isUpdated = true;
        return isUpdated;
    }


//    @Override
//    public List<EmployeeDto> getEmployeesByCourseId(Integer course_id) {
//        List<Enrollment> enrollments = enrollmentRepository.findByCourseId(course_id);
//        return enrollments.stream()
//                .map(enrollment -> webClient.get()
//                        .uri("http://localhost:8090/api/fetchbyid?id=" + enrollment.getEmp_id())
//                        .retrieve()
//                        .bodyToMono(EmployeeDto.class)
//                        .block())
//                .collect(Collectors.toList());
//    }

}


