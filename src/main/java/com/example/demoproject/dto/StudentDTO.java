package com.example.demoproject.dto;

public class StudentDTO {
    private Long id;
    private String name;
    private String studentNumber;
    private String major;

    public StudentDTO() {
    }

    public StudentDTO(Long id, String name, String studentNumber, String major) {
        this.id = id;
        this.name = name;
        this.studentNumber = studentNumber;
        this.major = major;
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
}
