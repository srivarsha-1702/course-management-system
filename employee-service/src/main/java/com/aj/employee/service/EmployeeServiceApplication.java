package com.aj.employee.service;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@OpenAPIDefinition(
		info = @Info(
				title = "Employee Service",
				version = "1.0",
				description = "Employee MicroService for CRUD operations",
				summary = "Employee MicroService",
				contact = @Contact(
						name = "Akanksha Joshi",
						email = "joshi.akanksha@ukg.com",
						url = "https://www.example.com"
		)
)
)
@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditorAwareImpl")
public class EmployeeServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmployeeServiceApplication.class, args);
	}

}
