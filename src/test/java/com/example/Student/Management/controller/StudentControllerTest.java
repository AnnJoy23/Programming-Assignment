package com.example.Student.Management.controller;

import com.example.Student.Management.model.Student;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class StudentControllerTest {

    @LocalServerPort
    private int port;

    private static final String BASE_URL = "http://localhost";

    @BeforeAll
    public static void setup() {
        // Ensure RestAssured uses the correct base URI
        RestAssured.baseURI = BASE_URL;
    }

    // Test for GET /api/students - Get all students
    @Test
    public void testGetAllStudents() {
        given()
                .port(port)
                .when()
                .get("/api/students")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("$", hasSize(greaterThan(0)))
                .body("[0].id", notNullValue())
                .body("[0].name", notNullValue())
                .body("[0].course", notNullValue());
    }

    // Test for GET /api/students/{id} when student not found
    @Test
    public void testGetStudentByIdNotFound() {
        given()
                .port(port)
                .when()
                .get("/api/students/999")  // Non-existing ID
                .then()
                .statusCode(404);
    }

    // Test for POST /api/students - Create a new student
    @Test
    public void testCreateStudent() {
        Student newStudent = new Student(0, "Mark Twain", "Literature");

        given()
                .port(port)
                .contentType(ContentType.JSON)
                .body(newStudent)
                .when()
                .post("/api/students")
                .then()
                .statusCode(201)
                .contentType(ContentType.JSON)
                .body("name", equalTo("Mark Twain"))
                .body("course", equalTo("Literature"));
    }

    // Test for PUT /api/students/{id} - Update an existing student
    @Test
    public void testUpdateStudent() {
        Student updatedStudent = new Student(0, "John Doe", "Physics");

        given()
                .port(port)
                .contentType(ContentType.JSON)
                .body(updatedStudent)
                .when()
                .put("/api/students/1")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("id", equalTo(1))
                .body("name", equalTo("John Doe"))
                .body("course", equalTo("Physics"));
    }

    // Test for PUT /api/students/{id} when student not found
    @Test
    public void testUpdateStudentNotFound() {
        Student updatedStudent = new Student(0, "Unknown Student", "Unknown Course");

        given()
                .port(port)
                .contentType(ContentType.JSON)
                .body(updatedStudent)
                .when()
                .put("/api/students/999")  // Non-existing ID
                .then()
                .statusCode(404);
    }

    // Test for DELETE /api/students/{id} - Delete a student
    @Test
    public void testDeleteStudent() {
        given()
                .port(port)
                .when()
                .delete("/api/students/1")
                .then()
                .statusCode(204);  // Status code 204 No Content
    }

    // Test for DELETE /api/students/{id} when student not found
    @Test
    public void testDeleteStudentNotFound() {
        given()
                .port(port)
                .when()
                .delete("/api/students/999")  // Non-existing ID
                .then()
                .statusCode(404);
    }
}
