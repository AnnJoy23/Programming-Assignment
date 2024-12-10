package com.example.Student.Management.service;
import com.example.Student.Management.model.Student;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {


    private List<Student> students = new ArrayList<>();

    // Mock data initialization
    public StudentService() {
        students.add(new Student(1, "John Doe", "Mathematics"));
        students.add(new Student(2, "Jane Smith", "Computer Science"));
        students.add(new Student(3, "Alice Brown", "Physics"));
    }

    // Get all students
    public List<Student> getAllStudents() {
        return students;
    }

    // Create a new student
    public String createStudent(String name, String course) {
        Student newStudent = new Student(students.size() + 1, name, course);
        students.add(newStudent);
        return "Student created: " + newStudent.getName() + ", " + newStudent.getCourse();
    }

    // Update an existing student by ID
    public String updateStudent(int id, String name, String course) {
        Optional<Student> studentOptional = students.stream()
                .filter(student -> student.getId() == id)
                .findFirst();

        if (studentOptional.isPresent()) {
            Student student = studentOptional.get();
            student.setName(name);
            student.setCourse(course);
            return "Student updated: " + student.getName() + ", " + student.getCourse();
        } else {
            return "Student not found.";
        }
    }

    // Delete a student by ID
    public String deleteStudent(int id) {
        Optional<Student> studentOptional = students.stream()
                .filter(student -> student.getId() == id)
                .findFirst();

        if (studentOptional.isPresent()) {
            students.remove(studentOptional.get());
            return "Student deleted: " + studentOptional.get().getName();
        } else {
            return "Student not found.";
        }
    }

    public Student getStudentById(int id) {
        Optional<Student> studentOptional = students.stream()
                .filter(student -> student.getId() == id)
                .findFirst();

        return studentOptional.orElse(null);
    }
}
