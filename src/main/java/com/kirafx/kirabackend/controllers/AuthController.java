package com.kirafx.kirabackend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kirafx.kirabackend.dto.ApiResponseGenerator;
import com.kirafx.kirabackend.dto.AuthResponse;
import com.kirafx.kirabackend.dto.LoginRequest;
import com.kirafx.kirabackend.dto.RegisterRequest;
import com.kirafx.kirabackend.services.AuthService;
import static com.kirafx.kirabackend.utils.constants.AppMessages.USER_LOGGED_IN_SUCCESS;
import static com.kirafx.kirabackend.utils.constants.AppMessages.USER_REGISTERED_SUCCESS;

@RestController
@RequestMapping("/auth")
@CrossOrigin
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public ApiResponseGenerator<AuthResponse> register(@RequestBody RegisterRequest req) {
        AuthResponse authResponse = authService.register(req);
        return ApiResponseGenerator.success(USER_REGISTERED_SUCCESS, authResponse);
    }

    @PostMapping("/login")
    public ApiResponseGenerator<AuthResponse> login(@RequestBody LoginRequest req) {
        AuthResponse authResponse = authService.login(req);
        return ApiResponseGenerator.success(USER_LOGGED_IN_SUCCESS, authResponse);
    }
}
