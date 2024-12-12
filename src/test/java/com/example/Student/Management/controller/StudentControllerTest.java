package com.example.Student.Management.controller;

import com.example.Student.Management.model.Student;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.springframework.boot.test.web.server.LocalServerPort;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class StudentControllerTest {

    @LocalServerPort
    private int port;


    @BeforeAll
    public static void setup() {
        // Ensure RestAssured uses the correct base URI and port
        RestAssured.baseURI = "http://localhost";

    }

    // Test for GET /api/students - Get all students
    @Test
    public void testGetAllStudents() {
        given()
                .baseUri("http://localhost")
                .port(port)
                .when()
                .get("/api/students")
                .then()
                .statusCode(200)  // Status code 200 OK
                .contentType(ContentType.JSON)  // Ensure the content is in JSON format
                .body("$", hasSize(greaterThan(0)))  // Verify there is at least one student
                .body("[0].id", notNullValue())  // Check that the first student has an ID
                .body("[0].name", notNullValue())  // Check that the first student has a name
                .body("[0].course", notNullValue());  // Check that the first student has a course
    }


    // Test for GET /api/students/{id} when student not found
    @Test
    public void testGetStudentByIdNotFound() {
        given()
                .baseUri("http://localhost")
                .port(port)
                .when()
                .get("/api/students/999")  // Use a non-existing ID
                .then()
                .statusCode(404);  // Status code 404 Not Found
    }

    // Test for POST /api/students - Create a new student
    @Test
    public void testCreateStudent() {
        Student newStudent = new Student(0, "Mark Twain", "Literature");

        given()
                .baseUri("http://localhost")
                .port(port)
                .contentType(ContentType.JSON)
                .body(newStudent)
                .when()
                .post("/api/students")
                .then()
                .statusCode(201)  // Status code 201 Created
                .contentType(ContentType.JSON)
                .body("name", equalTo("Mark Twain"))
                .body("course", equalTo("Literature"));
    }

    // Test for PUT /api/students/{id} - Update an existing student
    @Test
    public void testUpdateStudent() {
        Student updatedStudent = new Student(0, "John Doe", "Physics");

        given()
                .baseUri("http://localhost")
                .port(port)
                .contentType(ContentType.JSON)
                .body(updatedStudent)
                .when()
                .put("/api/students/1")
                .then()
                .statusCode(200)  // Status code 200 OK
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
                .baseUri("http://localhost")
                .port(port)
                .contentType(ContentType.JSON)
                .body(updatedStudent)
                .when()
                .put("/api/students/999")  // Use a non-existing ID
                .then()
                .statusCode(404);  // Status code 404 Not Found
    }

    // Test for DELETE /api/students/{id} - Delete a student
    @Test
    public void testDeleteStudent() {
        given()
                .baseUri("http://localhost")
                .port(port)
                .when()
                .delete("/api/students/1")
                .then()
                .statusCode(204);  // Status code 204 No Content (successful deletion)
    }

    // Test for DELETE /api/students/{id} when student not found
    @Test
    public void testDeleteStudentNotFound() {
        given()
                .baseUri("http://localhost")
                .port(port)
                .when()
                .delete("/api/students/999")  // Use a non-existing ID
                .then()
                .statusCode(404);  // Status code 404 Not Found
    }

}
