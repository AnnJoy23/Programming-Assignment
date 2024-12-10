package com.example.Student.Management.controller;

import com.example.Student.Management.model.Student;
import com.example.Student.Management.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api")
public class StudentController {

    // Injecting the StudentService class using constructor injection
    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    // GET /api/students: Retrieve all students
    @GetMapping("/students")
    @ResponseBody
    public String getStudents() {
        List<Student> students = studentService.getAllStudents();
        StringBuilder studentList = new StringBuilder();
        for (Student student : students) {
            studentList.append("ID: ").append(student.getId())
                    .append(", Name: ").append(student.getName())
                    .append(", Course: ").append(student.getCourse())
                    .append("\n");
        }
        return studentList.toString(); // Returning plain text
    }

    @GetMapping("/students/{id}")
    @ResponseBody
    public String getStudentById(@PathVariable int id) {
        Student student = studentService.getStudentById(id);
        if (student != null) {
            return "ID: " + student.getId() + ", Name: " + student.getName() + ", Course: " + student.getCourse();
        } else {
            return "Student with ID " + id + " not found.";
        }
    }

    // POST /api/students: Add a new student
    @PostMapping("/students")
    @ResponseBody
    public String createStudent(@RequestBody String studentInfo) {
        String[] studentDetails = studentInfo.split(",");
        if (studentDetails.length == 2) {
            return studentService.createStudent(studentDetails[0].trim(), studentDetails[1].trim());
        }
        return "Invalid student data. Please provide name and course.";
    }

    // PUT /api/students/{id}: Update student details
    @PutMapping("/students/{id}")
    @ResponseBody
    public String updateStudent(@PathVariable int id, @RequestBody String updatedInfo) {
        String[] studentDetails = updatedInfo.split(",");
        if (studentDetails.length == 2) {
            return studentService.updateStudent(id, studentDetails[0].trim(), studentDetails[1].trim());
        }
        return "Invalid update data. Please provide name and course.";
    }

    // DELETE /api/students/{id}: Delete a student
    @DeleteMapping("/students/{id}")
    @ResponseBody
    public String deleteStudent(@PathVariable int id) {
        return studentService.deleteStudent(id);
    }
}
