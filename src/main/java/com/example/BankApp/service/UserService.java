package com.example.BankApp.service;

import com.example.BankApp.models.RegistrationPayload;
import com.example.BankApp.models.User;
import com.example.BankApp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
 @RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private PasswordEncoder encoder;

    public User registerUser(RegistrationPayload user) {
        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            throw new RuntimeException("Username already taken");
        }
        String encodedPassword = encoder.encode(user.getPassword());
        User newUser = new User();
        newUser.setUsername(user.getUsername());
        newUser.setPassword(encodedPassword);
        return userRepository.save(newUser);
    }
}
