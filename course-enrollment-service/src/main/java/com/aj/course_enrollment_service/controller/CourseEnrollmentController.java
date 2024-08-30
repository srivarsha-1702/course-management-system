package com.aj.course_enrollment_service.controller;

import com.aj.course_enrollment_service.dto.CourseDto;
import com.aj.course_enrollment_service.dto.EmployeeDto;
import com.aj.course_enrollment_service.dto.EnrollRequestDto;
import com.aj.course_enrollment_service.dto.ResponseDto;
import com.aj.course_enrollment_service.service.ICourseService;
import org.hibernate.annotations.processing.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CourseEnrollmentController {
    @Value("${build.version}")
    private String buildVersion;

    @GetMapping("/hello")
    public String sayHello(){
        return "Hello World!";
    }

    @Autowired
    private ICourseService iCourseService;

    @PostMapping("/create-course")
    public ResponseEntity<ResponseDto> createCourse(@RequestBody CourseDto courseDto ){
//        return "Course Created!";
        iCourseService.createCourse(courseDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(
                new ResponseDto("Created Successfully", "201")
        );

    }
    @GetMapping("/fetch-course")
    public ResponseEntity<CourseDto> fetchCourse(@RequestParam String title){
        CourseDto courseDto = iCourseService.fetchCourse(title);
        return ResponseEntity.status(HttpStatus.OK).body(courseDto);

    }

    @GetMapping("/fetch-by-id")
    public ResponseEntity<CourseDto> fetchCourseById(@RequestParam Integer id){
        CourseDto courseDto = iCourseService.fetchCourseById(id);
        return ResponseEntity.status(HttpStatus.OK).body(courseDto);

    }
    @GetMapping("/fetchall-course")
    public List<CourseDto> fetchAllCourse(){
        List<CourseDto> CourseDtos = iCourseService.fetchAllCourse();
        return CourseDtos;
    }
    @PutMapping("/update-course")
    public ResponseEntity<ResponseDto> updateCourse(@RequestBody CourseDto courseDto){
        boolean isUpdated = iCourseService.updateCourse(courseDto);
        if(isUpdated){
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseDto("Updated Successfully", "203")
            );
        }
        else{
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    new ResponseDto("Not Updated", "501")
            );
        }
    }
    @DeleteMapping("/delete-course")
    public ResponseEntity<ResponseDto> deleteEmployee(@RequestParam  String title){
        boolean isDeleted = iCourseService.deleteCourse(title);
        if(isDeleted){
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseDto("Deleted Successfully", "203")
            );
        }
        else{
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    new ResponseDto("Not Deleted", "501")
            );
        }
    }

//    @PostMapping("/enroll-course")
//    public ResponseEntity<ResponseDto> enrollCourse(@RequestBody ){
//        boolean isEnrolled = iCourseService.enrollCourse(title, studentName);
//        if(isEnrolled){
//            return ResponseEntity.status(HttpStatus.OK).body(
//                    new ResponseDto("Enrolled Successfully", "203")
//            );
//        }
//        else{
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
//                    new ResponseDto("Not Enrolled", "501")
//            );
//        }
//    }

    @PostMapping("/enroll-course")
    public ResponseEntity<ResponseDto> enrollCourse(@RequestBody EnrollRequestDto enrollRequestDto) {
        try {
            iCourseService.enrollCourse(enrollRequestDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(
                    new ResponseDto("Created Successfully", "201")
            );
        } catch (RuntimeException e) {
            if (e.getMessage().contains("Employee doesn't exist")) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        new ResponseDto("Employee doesn't exist", "404")
                );
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        new ResponseDto("Course doesn't exist", "500")
                );
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    new ResponseDto("Internal Server Error", "500")
            );
        }
    }

    @PutMapping("/update-enroll")
    public ResponseEntity<ResponseDto> updateEnroll(@RequestParam Integer enrollmentId){
        boolean isUpdated = iCourseService.updateEnroll(enrollmentId);
        if(isUpdated){
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseDto("Updated Successfully", "203")
            );
        }
        else{
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    new ResponseDto("Not Updated", "501")
            );
        }
    }

    @GetMapping("/build-info")
    public String buildInfo(){
        return "Build Version: " + buildVersion;
    }

//    @GetMapping("/fetch-enroll")
//@GetMapping("/employees-by-course")
//public ResponseEntity<List<EmployeeDto>> getEmployeesByCourseId(@RequestParam Integer course_id) {
//    List<EmployeeDto> employees = iCourseService.getEmployeesByCourseId(course_id);
//    return ResponseEntity.ok(employees);
//}

}
