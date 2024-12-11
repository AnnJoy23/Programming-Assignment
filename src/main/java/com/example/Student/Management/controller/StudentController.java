package com.example.Student.Management.controller;

import com.example.Student.Management.model.Student;
import com.example.Student.Management.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController  // This annotation combines @Controller and @ResponseBody for REST APIs
@RequestMapping("/api/students")  // Base URL for student-related APIs
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    // GET /api/students: Retrieve all students
    @GetMapping
    public List<Student> getStudents() {
        return studentService.getAllStudents(); // Returns a list of students in JSON format
    }

    // GET /api/students/{id}: Retrieve a student by ID
    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable int id) {
        Student student = studentService.getStudentById(id);
        if (student != null) {
            return new ResponseEntity<>(student, HttpStatus.OK);  // Return student details with 200 OK status
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);  // Return 404 Not Found if student not found
        }
    }

    // POST /api/students: Add a new student
    @PostMapping
    public ResponseEntity<Student> createStudent(@RequestBody Student student) {
        Student createdStudent = studentService.createStudent(student.getName(), student.getCourse());
        return new ResponseEntity<>(createdStudent, HttpStatus.CREATED);  // Return 201 Created status
    }

    // PUT /api/students/{id}: Update student details by ID
    @PutMapping("/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable int id, @RequestBody Student updatedStudent) {
        Student student = studentService.updateStudent(id, updatedStudent.getName(), updatedStudent.getCourse());
        if (student != null) {
            return new ResponseEntity<>(student, HttpStatus.OK);  // Return updated student with 200 OK status
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);  // Return 404 if student not found
        }
    }

    // DELETE /api/students/{id}: Delete a student by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable int id) {
        boolean isDeleted = studentService.deleteStudent(id);
        if (isDeleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);  // Return 204 No Content on successful delete
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);  // Return 404 if student not found
        }
    }
}
