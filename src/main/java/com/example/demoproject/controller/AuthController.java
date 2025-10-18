package com.example.demoproject.controller;

import com.example.demoproject.common.Result;
import com.example.demoproject.dto.AuthResponse;
import com.example.demoproject.dto.LoginRequest;
import com.example.demoproject.dto.RegisterRequest;
import com.example.demoproject.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

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
}
