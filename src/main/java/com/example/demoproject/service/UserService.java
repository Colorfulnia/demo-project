package com.example.demoproject.service;

import com.example.demoproject.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserService {
    User createUser(User user);
    List<User> getAllUsers();

    Page<User> getUsersPage(Pageable pageable);

    User getUserById(Long id);
    User updateUser(Long id, User userDetails);
    void deleteUser(Long id);

}
