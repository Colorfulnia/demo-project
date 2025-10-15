package com.example.demoproject.dto;

public class CourseDTO {
    private Long id;
    private String courseName;
    private String teacher;
    private Integer credits;

    public CourseDTO() {
    }

    public CourseDTO(Long id, String courseName, String teacher, Integer credits) {
        this.id = id;
        this.courseName = courseName;
        this.teacher = teacher;
        this.credits = credits;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public Integer getCredits() {
        return credits;
    }

    public void setCredits(Integer credits) {
        this.credits = credits;
    }
}
