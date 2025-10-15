package com.example.demoproject.controller;

import com.example.demoproject.common.Result;
import com.example.demoproject.dto.StudentDTO;
import com.example.demoproject.dto.StudentDetailDTO;
import com.example.demoproject.entity.Student;
import com.example.demoproject.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @PostMapping
    public Result<StudentDTO> createStudent(@RequestBody Student student) {
        return Result.success(studentService.createStudent(student));
    }

    @GetMapping
    public Result<List<StudentDTO>> getAllStudents() {
        return Result.success(studentService.getAllStudents());
    }

    @GetMapping("/{id}")
    public Result<StudentDetailDTO> getStudentById(@PathVariable Long id) {
        return Result.success(studentService.getStudentById(id));
    }

    @PostMapping("/{studentId}/enroll/{courseId}")
    public Result<StudentDetailDTO> enrollCourse(@PathVariable Long studentId, @PathVariable Long courseId) {
        return Result.success(studentService.enrollCourse(studentId, courseId));
    }

    @DeleteMapping("/{studentId}/drop/{courseId}")
    public Result<StudentDetailDTO> dropCourse(@PathVariable Long studentId, @PathVariable Long courseId) {
        return Result.success(studentService.dropCourse(studentId, courseId));
    }
}
