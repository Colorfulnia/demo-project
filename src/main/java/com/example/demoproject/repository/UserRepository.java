package com.example.demoproject.repository;

import com.example.demoproject.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
    User findByUsername(String username);

    List<User> findByAgeGreaterThan(Integer age);
    List<User> findByAgeBetween(Integer minAge, Integer maxAge);
    List<User> findByUsernameContaining(String keyword);
    List<User> findByEmailEndingWith(String domain);
    List<User> findAllByOrderByAgeDesc();

    @Query("SELECT u FROM User u WHERE u.age > :age")
    List<User> findUsersOlderThan(@Param("age") Integer age);

    @Query(value = "SELECT * FROM users WHERE age BETWEEN :minAge AND :maxAge", nativeQuery = true)
    List<User>findUserByAgeRange(@Param("minAge") Integer minAge, @Param("maxAge") Integer maxAge);

    @Query("SELECT u FROM User u WHERE u.username LIKE %:keyword% OR u.email LIKE %:keyword%")
    List<User> searchByUsernameOrEmail(@Param("keyword") String keyword);

    @Query("SELECT COUNT(u) FROM User u WHERE u.age > :age")
    Long countUsersOlderThan(@Param("age") Integer age);
}
