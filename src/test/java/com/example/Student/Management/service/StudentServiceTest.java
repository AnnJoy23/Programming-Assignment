package com.example.Student.Management.service;

import com.example.Student.Management.model.Student;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class StudentServiceTest {

    private StudentService studentService;

    @BeforeEach
    public void setUp() {
        // Initialize the service before each test
        studentService = new StudentService();
    }

    @Test
    public void testGetAllStudents() {
        List<Student> students = studentService.getAllStudents();

        assertNotNull(students);
        assertEquals(3, students.size(), "Expected 3 students");
        assertStudent(students.get(0), 1, "John Doe", "Mathematics");
    }

    @Test
    public void testCreateStudent() {
        Student newStudent = studentService.createStudent("Mark Twain", "Literature");

        assertNotNull(newStudent);
        assertEquals(4, newStudent.getId());
        assertStudent(newStudent, 4, "Mark Twain", "Literature");

        List<Student> students = studentService.getAllStudents();
        assertEquals(4, students.size(), "Student list should contain 4 students after creation");
    }

    @Test
    public void testUpdateStudent() {
        Student updatedStudent = studentService.updateStudent(1, "Johnathan Doe", "Physics");

        assertNotNull(updatedStudent);
        assertStudent(updatedStudent, 1, "Johnathan Doe", "Physics");

        List<Student> students = studentService.getAllStudents();
        assertEquals(3, students.size(), "List size should still be 3 after update");
        assertEquals("Johnathan Doe", students.get(0).getName(), "The first student's name should be updated");
    }

    @Test
    public void testUpdateStudentNotFound() {
        Student updatedStudent = studentService.updateStudent(999, "Non Existent", "Unknown");
        assertNull(updatedStudent, "Updating non-existent student should return null");
    }

    @Test
    public void testDeleteStudent() {
        boolean isDeleted = studentService.deleteStudent(2);
        assertTrue(isDeleted, "Student should be deleted successfully");

        List<Student> students = studentService.getAllStudents();
        assertEquals(2, students.size(), "List should contain 2 students after deletion");

        Student student = studentService.getStudentById(2);
        assertNull(student, "Deleted student should not be found");
    }

    @Test
    public void testDeleteStudentNotFound() {
        boolean isDeleted = studentService.deleteStudent(999);
        assertFalse(isDeleted, "Attempt to delete a non-existent student should return false");
    }

    @Test
    public void testGetStudentById() {
        Student student = studentService.getStudentById(1);
        assertStudent(student, 1, "John Doe", "Mathematics");
    }

    @Test
    public void testGetStudentByIdNotFound() {
        Student student = studentService.getStudentById(999);
        assertNull(student, "Non-existent student should return null");
    }

    // Helper method for verifying student details
    private void assertStudent(Student student, int expectedId, String expectedName, String expectedCourse) {
        assertNotNull(student);
        assertEquals(expectedId, student.getId(), "Student ID should match");
        assertEquals(expectedName, student.getName(), "Student name should match");
        assertEquals(expectedCourse, student.getCourse(), "Student course should match");
    }
}
