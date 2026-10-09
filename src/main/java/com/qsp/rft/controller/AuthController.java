package com.qsp.rft.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qsp.rft.dto.LoginResponse;
import com.qsp.rft.dto.PhoneRequest;
import com.qsp.rft.dto.RegisterRequest;
import com.qsp.rft.dto.VerifyRequest;
import com.qsp.rft.service.AuthService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public Map<String, String> register(@Valid @RequestBody RegisterRequest request) {
        authService.register(request);
        return Map.of("message", "OTP sent");
    }

    @PostMapping("/request-otp")
    public Map<String, String> requestOtp(@Valid @RequestBody PhoneRequest request) {
        authService.requestOtp(request.phone());
        return Map.of("message", "OTP sent");
    }

    @PostMapping("/verify-otp")
    public LoginResponse verifyOtp(@Valid @RequestBody VerifyRequest request) {
        return authService.verify(request.phone(), request.otp());
    }
}