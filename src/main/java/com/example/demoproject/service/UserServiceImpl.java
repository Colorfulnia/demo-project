package com.example.demoproject.service;

import com.example.demoproject.annotation.Log;
import com.example.demoproject.entity.User;
import com.example.demoproject.exception.BusinessException;
import com.example.demoproject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    @Log("用户创建操作")
    @CacheEvict(value= "users",allEntries = true)
    public User createUser(User user) {
        User existingUserByEmail = userRepository.findByEmail(user.getEmail());
        if(existingUserByEmail != null){
            throw new BusinessException("邮箱已被注册: "+ user.getEmail());
        }

        User existingUserByUsername = userRepository.findByUsername(user.getUsername());
        if(existingUserByUsername != null){
            throw new BusinessException("用户名已存在: "+  user.getUsername());
        }

        if(user.getAge() != null && user.getAge() < 0){
            throw new BusinessException("年龄不能为负数");
        }
        return userRepository.save(user);
    }

    @Override
    @Log("查询所有用户")
    @CacheEvict(value = "users",key = "'all'")
    public List<User> getAllUsers() {
        System.out.println("从数据库查询所有用户...");
        return userRepository.findAll();
    }

    @Override
    @Cacheable(value = "users",key = "#id")
    public User getUserById(Long id) {
        System.out.println("从数据库查询用户 ID: "+id);
        return userRepository.findById(id).orElse(null);
    }

    @Override
    @Log("更新用户信息")
    @CachePut(value = "users",key = "#id")
    @CacheEvict(value = "users",key = "'all'")
    public User updateUser(Long id, User userDetails){
        User user = userRepository.findById(id).orElse(null);
        if(user != null){
            user.setUsername(userDetails.getUsername());
            user.setEmail(userDetails.getEmail());
            user.setAge(userDetails.getAge());
            return  userRepository.save(user);
        }
        return null;
    }

    @Override
    @CacheEvict(value = "users",allEntries = true)
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public Page<User> getUsersPage(Pageable pageable){
        return userRepository.findAll(pageable);
    }
}
