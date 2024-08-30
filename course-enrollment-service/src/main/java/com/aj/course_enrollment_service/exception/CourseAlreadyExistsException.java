// CourseAlreadyExistsException.java
package com.aj.course_enrollment_service.exception;

public class CourseAlreadyExistsException extends RuntimeException {
    public CourseAlreadyExistsException(String message) {
        super(message);
    }
}