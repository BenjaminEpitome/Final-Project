package org.alvee;

import java.util.ArrayList;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Student {
    private String studentId;
    private String studentName;
    private Gender gender;
    private Address address;
    private Department department;
    private ArrayList<Course> registeredCourses;

    private static int nextId = 1;

    // Constructor
    public Student(String studentName, Gender gender, Address address, Department department) {
        this.studentName = studentName;
        this.gender = gender;
        this.address = address;
        this.department = department;

        this.studentId = String.format("S%06d", nextId);
        nextId++;

        this.registeredCourses = new ArrayList<>();
    }

}
