package com.example.demoproject.repository;

import com.example.demoproject.entity.Student;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student,Long> {
    Student findByStudentNumber(String studentNumber);
    Student findByName(String name);

    @Query("SELECT distinct s from Student s left join fetch s.courses")
    List<Student> findAllWithCourses();

    @EntityGraph(attributePaths = {"courses"})
    List<Student> findByMajor(String major);
}
