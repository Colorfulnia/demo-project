package com.example.demoproject.controller;

import com.example.demoproject.common.Result;
import com.example.demoproject.dto.AuthResponse;
import com.example.demoproject.dto.LoginRequest;
import com.example.demoproject.dto.RegisterRequest;
import com.example.demoproject.entity.User;
import com.example.demoproject.repository.UserRepository;
import com.example.demoproject.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/register")
    public Result<AuthResponse> register(@Valid @RequestBody RegisterRequest request){
        AuthResponse response = authService.register(request);
        return Result.success(response);
    }
    @PostMapping("/login")
    public Result<AuthResponse> login(@Valid @RequestBody LoginRequest request){
        AuthResponse response = authService.login(request);
        return Result.success(response);
    }

    @GetMapping("/me")
    public Result<User> getCurrentUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        User user = userRepository.findByUsername(username);
        return Result.success(user);
    }
}
