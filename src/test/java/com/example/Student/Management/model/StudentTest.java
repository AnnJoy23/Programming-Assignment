package com.example.Student.Management.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class StudentTest {

    private Student student;

    @BeforeEach
    public void setUp() {
        // Initialize the student object before each test
        student = new Student(1, "John Doe", "Mathematics");
    }

    @Test
    public void testConstructor() {
        // Test that the constructor sets the fields correctly
        Student student = new Student(1, "John Doe", "Mathematics");

        assertEquals(1, student.getId());          // Verify ID is set correctly
        assertEquals("John Doe", student.getName()); // Verify name is set correctly
        assertEquals("Mathematics", student.getCourse()); // Verify course is set correctly
    }

    @Test
    public void testGetId() {
        // Test the getter for the ID
        assertEquals(1, student.getId());  // Verify ID is 1
    }

    @Test
    public void testSetId() {
        // Test the setter for the ID
        student.setId(2);
        assertEquals(2, student.getId());  // Verify ID is updated to 2
    }

    @Test
    public void testGetName() {
        // Test the getter for the name
        assertEquals("John Doe", student.getName());  // Verify name is "John Doe"
    }

    @Test
    public void testSetName() {
        // Test the setter for the name
        student.setName("Jane Smith");
        assertEquals("Jane Smith", student.getName());  // Verify name is updated to "Jane Smith"
    }

    @Test
    public void testGetCourse() {
        // Test the getter for the course
        assertEquals("Mathematics", student.getCourse());  // Verify course is "Mathematics"
    }

    @Test
    public void testSetCourse() {
        // Test the setter for the course
        student.setCourse("Computer Science");
        assertEquals("Computer Science", student.getCourse());  // Verify course is updated to "Computer Science"
    }

}
