package com.example.demoproject.service;

import com.example.demoproject.entity.Course;
import com.example.demoproject.exception.BusinessException;
import com.example.demoproject.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {
    @Autowired
    private CourseRepository courseRepository;

    @Override
    public Course createCourse(Course course) {
        if(courseRepository.findByCourseName(course.getCourseName()) != null){
            throw new BusinessException("课程已存在: "+course.getCourseName());
        }
        return courseRepository.save(course);
    }

    @Override
    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    @Override
    public Course getCourseById(Long id) {
        return courseRepository.findById(id).orElse(null);
    }
}
