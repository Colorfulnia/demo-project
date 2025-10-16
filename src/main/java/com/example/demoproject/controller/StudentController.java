package com.example.demoproject.controller;

import com.example.demoproject.common.Result;
import com.example.demoproject.dto.StudentDTO;
import com.example.demoproject.dto.StudentDetailDTO;
import com.example.demoproject.entity.Student;
import com.example.demoproject.repository.StudentRepository;
import com.example.demoproject.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @Autowired
    private StudentRepository studentRepository;

    @PostMapping
    public Result<StudentDTO> createStudent(@Valid @RequestBody Student student) {
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

    @GetMapping("/page")
    public Result<Page<StudentDTO>> getStudentsPage(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "desc") String direction
    ) {
        Sort sort = direction.equalsIgnoreCase("asc")
                ?Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(page, size, sort);

        Page<Student> studentPage = studentRepository.findAll(pageable);

        Page<StudentDTO> dtoPage = studentPage.map(student ->
                new StudentDTO(
                        student.getId(),
                        student.getName(),
                        student.getStudentNumber(),
                        student.getMajor()
                )
        );
        return Result.success(dtoPage);
    }
}
