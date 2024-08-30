package com.aj.course_enrollment_service;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.web.reactive.function.client.WebClient;

@OpenAPIDefinition(
		info = @Info(
				title = "Course Enrollment Service",
				version = "1.0",
				description = "Course Enrollment MicroService for CRUD operations",
				summary = "Course Enrollment MicroService",
				contact = @Contact(
						name = "Akanksha Joshi",
						email = "joshi.akanksha@ukg.com",
						url = "https://www.example.com"
				)
		)
)
@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditorAwareImpl")
public class CourseEnrollmentServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CourseEnrollmentServiceApplication.class, args);
	}
	@Bean
	public WebClient webClient(){
		return WebClient.builder().build();
	}

}
