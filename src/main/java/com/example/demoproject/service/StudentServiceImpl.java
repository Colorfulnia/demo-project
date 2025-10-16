package com.example.demoproject.service;

import com.example.demoproject.dto.CourseDTO;
import com.example.demoproject.dto.StudentDTO;
import com.example.demoproject.dto.StudentDetailDTO;
import com.example.demoproject.entity.Course;
import com.example.demoproject.entity.Student;
import com.example.demoproject.exception.BusinessException;
import com.example.demoproject.repository.CourseRepository;
import com.example.demoproject.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Override
    public StudentDTO createStudent(Student student) {
        if (studentRepository.findByStudentNumber(student.getStudentNumber()) != null) {
            throw new BusinessException("学号已存在: " + student.getStudentNumber());
//            throw new RuntimeException("学号已存在: " + student.getStudentNumber());
        }
        Student saved = studentRepository.save(student);
        return convertToDTO(saved);
    }

    @Override
    public List<StudentDTO> getAllStudents() {
        return studentRepository.findAll().stream().
                map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public StudentDetailDTO getStudentById(Long id) {
        Student student = studentRepository.findById(id).orElse(null);
        if (student == null) {
            return null;
        }
        return convertToDetailDTO(student);
    }

    @Override
    public StudentDetailDTO enrollCourse(Long studentId, Long courseId) {
        Student student = studentRepository.findById(studentId).orElse(null);
        if (student == null) {
            throw new BusinessException("学生不存在, ID: " + studentId);
//            throw new RuntimeException("学生不存在, ID: " + studentId);
        }

        Course course = courseRepository.findById(courseId).orElse(null);
        if (course == null) {
            throw new BusinessException("课程不存在, ID: " + courseId);
//            throw new RuntimeException("课程不存在, ID: " + courseId);
        }

        student.getCourses().add(course);
        Student saved = studentRepository.save(student);
        return convertToDetailDTO(saved);
    }

    @Override
    public StudentDetailDTO dropCourse(Long studentId, Long courseId) {
        Student student = studentRepository.findById(studentId).orElse(null);
        if (student == null) {
            throw new BusinessException("学生不存在, ID: " + studentId);
//            throw new RuntimeException("学生不存在, ID: " + studentId);
        }


        Course course = courseRepository.findById(courseId).orElse(null);
        if (course == null) {
            throw new BusinessException("课程不存在, ID: " + courseId);
//            throw new RuntimeException("课程不存在, ID: " + courseId);
        }

        student.getCourses().remove(course);
        Student saved = studentRepository.save(student);
        return convertToDetailDTO(saved);
    }

    private StudentDTO convertToDTO(Student student) {
        return new StudentDTO(
                student.getId(),
                student.getName(),
                student.getStudentNumber(),
                student.getMajor()
        );
    }

    @Override
    public Page<Student> getStudentsPage(Pageable pageable){
        return studentRepository.findAll(pageable);
    }

    private StudentDetailDTO convertToDetailDTO(Student student) {
        List<CourseDTO> courseDTOS = student.getCourses().stream()
                .map(course -> new CourseDTO(
                        course.getId(),
                        course.getCourseName(),
                        course.getTeacher(),
                        course.getCredits()
                ))
                .collect(Collectors.toList());

        return new StudentDetailDTO(
                student.getId(),
                student.getName(),
                student.getStudentNumber(),
                student.getMajor(),
                courseDTOS
        );
    }

}
