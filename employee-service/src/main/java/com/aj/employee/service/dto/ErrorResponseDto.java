package com.aj.employee.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponseDto {
    private String apiPath;
    private String errorMessage;
    private HttpStatus statusCode;
    private LocalDateTime timestamp;

}
