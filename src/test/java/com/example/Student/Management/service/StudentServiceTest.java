package com.example.Student.Management.service;

import com.example.Student.Management.model.Student;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

public class StudentServiceTest {

    private StudentService studentService;

    @BeforeEach
    public void setUp() {
        // Initialize the service before each test
        studentService = new StudentService();
    }

    @Test
    public void testGetAllStudents() {
        // When calling getAllStudents
        List<Student> students = studentService.getAllStudents();

        // Verify the list is not empty and contains exactly 3 students as per mock data
        assertNotNull(students);
        assertEquals(3, students.size());

        // Verify the details of the first student
        assertEquals(1, students.get(0).getId());
        assertEquals("John Doe", students.get(0).getName());
        assertEquals("Mathematics", students.get(0).getCourse());
    }

    @Test
    public void testCreateStudent() {
        // When creating a new student
        Student newStudent = studentService.createStudent("Mark Twain", "Literature");

        // Verify the student is created with correct details
        assertNotNull(newStudent);
        assertEquals(4, newStudent.getId());  // ID should be 4 as it is the next available ID
        assertEquals("Mark Twain", newStudent.getName());
        assertEquals("Literature", newStudent.getCourse());

        // Verify the new student is added to the list
        List<Student> students = studentService.getAllStudents();
        assertEquals(4, students.size());  // Now there should be 4 students
    }

    @Test
    public void testUpdateStudent() {
        // Given a student with ID 1 exists in the mock data
        Student updatedStudent = studentService.updateStudent(1, "Johnathan Doe", "Physics");

        // Verify the student is updated correctly
        assertNotNull(updatedStudent);
        assertEquals(1, updatedStudent.getId());
        assertEquals("Johnathan Doe", updatedStudent.getName());
        assertEquals("Physics", updatedStudent.getCourse());

        // Verify the updated student details in the list
        List<Student> students = studentService.getAllStudents();
        assertEquals(3, students.size());  // Still 3 students
        assertEquals("Johnathan Doe", students.get(0).getName());  // First student should now be updated
    }

    @Test
    public void testUpdateStudentNotFound() {
        // Try to update a non-existing student (ID 999)
        Student updatedStudent = studentService.updateStudent(999, "Non Existent", "Unknown");

        // Verify the result is null since the student does not exist
        assertNull(updatedStudent);
    }

    @Test
    public void testDeleteStudent() {
        // When deleting an existing student (ID 2)
        boolean isDeleted = studentService.deleteStudent(2);

        // Verify the student was deleted successfully
        assertTrue(isDeleted);

        // Verify the list size is now 2
        List<Student> students = studentService.getAllStudents();
        assertEquals(2, students.size());

        // Verify the student with ID 2 no longer exists
        Student student = studentService.getStudentById(2);
        assertNull(student);
    }

    @Test
    public void testDeleteStudentNotFound() {
        // Try to delete a non-existing student (ID 999)
        boolean isDeleted = studentService.deleteStudent(999);

        // Verify the result is false since the student does not exist
        assertFalse(isDeleted);
    }

    @Test
    public void testGetStudentById() {
        // When retrieving a student by ID (ID 1)
        Student student = studentService.getStudentById(1);

        // Verify the student exists and has the correct details
        assertNotNull(student);
        assertEquals(1, student.getId());
        assertEquals("John Doe", student.getName());
        assertEquals("Mathematics", student.getCourse());
    }

    @Test
    public void testGetStudentByIdNotFound() {
        // When retrieving a non-existing student by ID (ID 999)
        Student student = studentService.getStudentById(999);

        // Verify the student is not found (should be null)
        assertNull(student);
    }
}
