package com.example.Student.Management.model;

import lombok.AllArgsConstructor;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter             // Lombok generates getters, setters, equals, hashCode, toString methods
@AllArgsConstructor  // Lombok generates a constructor with all fields (optional if you already have one)
public class Student {

    private int id;
    private String name;
    private String course;

}
