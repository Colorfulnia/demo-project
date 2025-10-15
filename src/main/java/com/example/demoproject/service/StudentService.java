package com.example.demoproject.service;

import com.example.demoproject.dto.StudentDTO;
import com.example.demoproject.dto.StudentDetailDTO;
import com.example.demoproject.entity.Student;

import java.util.List;

public interface StudentService {
    StudentDTO createStudent(Student student);
    List<StudentDTO> getAllStudents();
    StudentDetailDTO getStudentById(Long id);

    StudentDetailDTO enrollCourse(Long studentId, Long courseId);
    StudentDetailDTO dropCourse(Long studentId, Long courseId);

//    Student createStudent(Student student);
//    List<Student> getAllStudents();
//    Student getStudentById(Long id);

//    Student enrollCourse(Long studentId,Long courseId);
//    Student dropCourse(Long studentId,Long courseId);
}
