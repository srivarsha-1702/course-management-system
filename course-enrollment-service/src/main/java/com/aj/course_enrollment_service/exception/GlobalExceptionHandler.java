package com.aj.course_enrollment_service.exception;

import com.aj.course_enrollment_service.dto.ErrorResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(CourseAlreadyExistsException.class)
    public ResponseEntity<ErrorResponseDto> handleEmployeeAlreadyExistsException(
            CourseAlreadyExistsException ex, WebRequest webRequest
    ){
        ErrorResponseDto errorResponseDto = new ErrorResponseDto(
                webRequest.getDescription(false),
                ex.getMessage(),
                HttpStatus.ALREADY_REPORTED,
                LocalDateTime.now()
        );
        return new ResponseEntity<>(errorResponseDto, HttpStatus.ALREADY_REPORTED);
    }
}
