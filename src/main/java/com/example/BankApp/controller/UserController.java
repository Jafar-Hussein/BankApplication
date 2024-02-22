package com.example.BankApp.controller;

import com.example.BankApp.models.RegistrationPayload;
import com.example.BankApp.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bank/user")
@RequiredArgsConstructor
public class UserController {
    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegistrationPayload registrationPayload) {
        if (registrationPayload.getUsername() == null || registrationPayload.getPassword() == null || registrationPayload.getAccountType() == null) {
            return ResponseEntity.badRequest().body("Error: Username, password, or account type is null!");
        }
        return authService.register(registrationPayload);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody RegistrationPayload registrationPayload) {
        if (registrationPayload.getUsername() == null || registrationPayload.getPassword() == null) {
            return ResponseEntity.badRequest().body("Error: Username or password is null!");
        }
        return authService.login(registrationPayload.getUsername(), registrationPayload.getPassword());
    }



}
