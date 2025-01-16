package com.example.Student.Management;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication

@OpenAPIDefinition(
		info = @Info(
				title = "Student Management API",  // API title
				description = "This API allows management of students, courses, and enrollments.",  // API description
				version = "1.0.0"  // API version
		)
)


public class StudentManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(StudentManagementApplication.class, args);
	}

}
