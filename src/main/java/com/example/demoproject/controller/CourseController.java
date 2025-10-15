package com.example.demoproject.controller;

import com.example.demoproject.common.Result;
import com.example.demoproject.entity.Course;
import com.example.demoproject.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/courses")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @PostMapping
    public Result<Course> createCourse(@RequestBody Course course){
        return Result.success(courseService.createCourse(course));
    }

    @GetMapping
    public Result<List<Course>> getAllCourses(){
        return Result.success(courseService.getAllCourses());
    }

    @GetMapping("/{id}")
    public Result<Course> getCourseById(@PathVariable Long id){
        return Result.success(courseService.getCourseById(id));
    }
}
