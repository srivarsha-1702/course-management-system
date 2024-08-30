package com.aj.employee.service;

import com.aj.employee.service.dto.EmployeeDto;
import com.aj.employee.service.service.IEmployeeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
public class EmployeeControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private IEmployeeService iEmployeeService;

    @Test
    @DisplayName("Test EmployeeController")

    public void fetchEmployee() throws Exception {
        EmployeeDto RECORD_1 = new EmployeeDto("John", "Doe", "8790372722", "ENGINEER", "SOFTWARE");
//        Mockito.when
    }
}
