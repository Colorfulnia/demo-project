package com.example.demoproject.repository;

import com.example.demoproject.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student,Long> {
    Student findByStudentNumber(String studentNumber);
    Student findByName(String name);
}
