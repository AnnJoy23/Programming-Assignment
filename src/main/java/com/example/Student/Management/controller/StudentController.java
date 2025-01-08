package com.example.Student.Management.controller;

import com.example.Student.Management.model.Student;
import com.example.Student.Management.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@Tag(name = "Student Management", description = "APIs for managing students")
@RestController
@RequestMapping("/api/students")
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    // GET /api/students: Retrieve all students
    @Operation(summary = "Get all students", description = "Fetches the list of all students")
    @GetMapping
    public List<Student> getStudents() {
        return studentService.getAllStudents();  // Returns a list of students in JSON format
    }

    // GET /api/students/{id}: Retrieve a student by ID
    @Operation(summary = "Get a student by ID", description = "Fetch a student by their unique ID")
    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable int id) {
        Student student = studentService.getStudentById(id);
        return student != null
                ? new ResponseEntity<>(student, HttpStatus.OK)  // Student found, return 200 OK
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);  // Student not found, return 404 Not Found
    }

    // POST /api/students: Add a new student
    @Operation(summary = "Add a new student", description = "Creates a new student in the system")
    @PostMapping
    public ResponseEntity<Student> createStudent(@RequestBody Student student) {
        Student createdStudent = studentService.createStudent(student.getName(), student.getCourse());
        return new ResponseEntity<>(createdStudent, HttpStatus.CREATED);  // Return 201 Created status
    }

    // PUT /api/students/{id}: Update student details by ID
    @Operation(summary = "Update a student", description = "Updates the details of an existing student")
    @PutMapping("/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable int id, @RequestBody Student updatedStudent) {
        Student student = studentService.updateStudent(id, updatedStudent.getName(), updatedStudent.getCourse());
        return student != null
                ? new ResponseEntity<>(student, HttpStatus.OK)  // Student updated, return 200 OK
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);  // Student not found, return 404 Not Found
    }

    // DELETE /api/students/{id}: Delete a student by ID
    @Operation(summary = "Delete a student", description = "Deletes a student by their unique ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable int id) {
        boolean isDeleted = studentService.deleteStudent(id);
        return isDeleted
                ? new ResponseEntity<>(HttpStatus.NO_CONTENT)  // Return 204 No Content on successful delete
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);  // Student not found, return 404 Not Found
    }
}
