package com.example.demoproject.dto;

import java.util.ArrayList;
import java.util.List;

public class StudentDetailDTO {
    private Long id;
    private String name;
    private String studentNumber;
    private String major;

    private List<CourseDTO> courses = new ArrayList<>();

    public StudentDetailDTO() {
    }

    public StudentDetailDTO(Long id, String name, String studentNumber, String major, List<CourseDTO> courses) {
        this.id = id;
        this.name = name;
        this.studentNumber = studentNumber;
        this.major = major;
        this.courses = courses;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStudentNumber() {
        return studentNumber;
    }

    public void setStudentNumber(String studentNumber) {
        this.studentNumber = studentNumber;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public List<CourseDTO> getCourses() {
        return courses;
    }

    public void setCourses(List<CourseDTO> courses) {
        this.courses = courses;
    }
}
