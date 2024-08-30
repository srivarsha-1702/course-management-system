package com.aj.employee.service.controller;

import com.aj.employee.service.dto.EmployeeDto;
import com.aj.employee.service.dto.ResponseDto;
import com.aj.employee.service.service.IEmployeeService;
//import org.hibernate.annotations.processing.Pattern;
import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(
        name = "Employee Controller for CRUD Operations",
        description = "This controller is responsible for CRUD operations on Employee",
        externalDocs = @ExternalDocumentation(description = "For more information, visit the official documentation", url = "https://www.example.com")
)
@Validated
@RestController
@RequestMapping("/api")

public class EmployeeController {
    @Value("${build.version}")
    private String buildVersion;


    @Autowired
    private IEmployeeService iEmployeeService;

    @Operation(
            description = "Create new employee operations",
            summary = "Post API to Create new employee in the system"
    )

    @ApiResponse(
            responseCode = "201",
            description = "Employee Created Successfully"
    )
    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createEmployee(@RequestBody EmployeeDto employeeDto){
        iEmployeeService.createEmployee(employeeDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(
                new ResponseDto("Created Successfully", "201")
        );
    }
    @GetMapping("/fetch")
    public ResponseEntity<EmployeeDto> fetchEmployee(@RequestParam @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile Number should have 10 digits") String mobileNumber){
        EmployeeDto employeeDto = iEmployeeService.fetchEmployee(mobileNumber);
        return ResponseEntity.status(HttpStatus.OK).body(employeeDto);

    }
    @GetMapping("/fetchbyid")
    public ResponseEntity<EmployeeDto> fetchEmployeeById(@RequestParam Integer id){
        EmployeeDto employeeDto = iEmployeeService.fetchEmployeeById(id);
        return ResponseEntity.status(HttpStatus.OK).body(employeeDto);
    }
    @GetMapping("/fetchall")
    public List<EmployeeDto> fetchAllEmployee(){
        List<EmployeeDto> employeeDtos = iEmployeeService.fetchAllEmployee();
        return employeeDtos;
    }
    @PutMapping("/update")
    public ResponseEntity<ResponseDto> updateEmployee(@RequestBody EmployeeDto employeeDto){
        boolean isUpdated = iEmployeeService.updateEmployee(employeeDto);
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
    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDto> deleteEmployee(@RequestParam @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile Number should have 10 digits") String mobileNumber){
        boolean isDeleted = iEmployeeService.deleteEmployee(mobileNumber);
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
    @GetMapping("/greet")
    public String greet(){
        return "Hello World!";
    }

    @GetMapping("/build-info")
    public String buildInfo(){
        return "Build Version: " + buildVersion;
    }
}
