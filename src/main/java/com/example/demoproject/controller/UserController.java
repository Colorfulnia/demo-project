package com.example.demoproject.controller;

import com.example.demoproject.common.Result;
import com.example.demoproject.entity.User;
import com.example.demoproject.repository.UserRepository;
import com.example.demoproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @PostMapping
    public Result<User> createUser(@RequestBody User user){
//        return userRepository.save(user);
        return Result.success(userService.createUser(user));
    }

    @GetMapping
    public Result<List<User>> getAllUsers(){
//        return userRepository.findAll();
        return Result.success(userService.getAllUsers());
    }

    @GetMapping("/{id}")
    public Result<User> getUserById(@PathVariable Long id) {
//        return userRepository.findById(id).orElse(null);
        return Result.success(userService.getUserById(id));
    }

    @PutMapping("/{id}")
    public Result<User> updateUser(@PathVariable Long id, @RequestBody User userDetails){
//        User user = userRepository.findById(id).orElse(null);
//        if(user != null){
//            user.setUsername(userDetails.getUsername());
//            user.setEmail(userDetails.getEmail());
//            user.setAge(userDetails.getAge());
//            return userRepository.save(user);
//        }
//        return null;
        return Result.success(userService.updateUser(id, userDetails));
    }

    @DeleteMapping("/{id}")
    public Result<String> deleteUser(@PathVariable Long id){
//        userRepository.deleteById(id);
        userService.deleteUser(id);
        return Result.success("用户已删除");
    }

    @GetMapping("/age/greater-than/{age}")
    public Result<List<User>> findByAgeGreaterThan(@PathVariable Integer age){
        return Result.success(userRepository.findByAgeGreaterThan(age));
    }

    @GetMapping("/age/between")
    public Result<List<User>> getUserByAgeBetween(@RequestParam Integer min, @RequestParam Integer max){
        return Result.success(userRepository.findByAgeBetween(min,max));
    }

    @GetMapping("/search")
    public Result<List<User>> searchUsers(@RequestParam String keyword){
        return Result.success(userRepository.findByUsernameContaining(keyword));
    }

    @GetMapping("/sorted-by-age")
    public Result<List<User>> getSortedByAge(){
        return Result.success(userRepository.findAllByOrderByAgeDesc());
    }

    @GetMapping("/query/older-than/{age}")
    public Result<List<User>> getUsersOlderThan(@PathVariable Integer age){
        return Result.success(userRepository.findUsersOlderThan(age));
    }

    @GetMapping("/query/age-range")
    public Result<List<User>> getUserByAgeRangeNative(@RequestParam Integer min, @RequestParam Integer max){
        return Result.success(userRepository.findUserByAgeRange(min,max));
    }

    @GetMapping("/query/search")
    public Result<List<User>> searchInUsernameOrEmail(@RequestParam String keyword){
        return Result.success(userRepository.searchByUsernameOrEmail(keyword));
    }

    @GetMapping("/query/count-older-than/{age}")
    public Result<Long> countOlderThan(@PathVariable Integer age){
        return Result.success(userRepository.countUsersOlderThan(age));
    }
}
