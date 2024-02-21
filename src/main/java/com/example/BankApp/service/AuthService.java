package com.example.BankApp.service;

import com.example.BankApp.models.*;
import com.example.BankApp.repository.AccountRepository;
import com.example.BankApp.repository.AccountTypeRepository;
import com.example.BankApp.repository.RoleRepository;
import com.example.BankApp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final RoleRepository roleRepository;
    private final TokenService tokenService;
    private final AccountTypeRepository accountTypeRepository;
    private final AccountRepository bankAccountRepository;

    // auto generate string accountNumber
    public String generateAccountNumber() {
        String accountNumber = "";
        for (int i = 0; i < 4; i++) {
            accountNumber += (int) (Math.random() * 10);
        }
        return accountNumber;
    }
    public ResponseEntity<?> register(RegistrationPayload payload) {
        try {
            // Check if the username already exists
            if (userRepository.findByUsername(payload.getUsername()).isPresent()) {
                return ResponseEntity.badRequest().body("Error: Username is already taken!");
            }

            // Find the role for a regular user (not specified in the payload)
            Role userRole = roleRepository.findByAuthority("ADMIN").orElseThrow(() -> new RuntimeException("USER role not found"));

            // Create a new BankAccount based on the specified account type
            AccountType accountType = accountTypeRepository.findByType(payload.getAccountType())
                    .orElseThrow(() -> new RuntimeException("Account type not found"));
            BankAccount bankAccount = new BankAccount();
            bankAccount.setAccountNumber(generateAccountNumber());
            bankAccount.setBalance(0.0);
            bankAccount.setAccountType(accountType);

            // Save the BankAccount to the database
            bankAccountRepository.save(bankAccount);

            // Create a new User
            User user = new User();
            user.setUsername(payload.getUsername());
            user.setPassword(passwordEncoder.encode(payload.getPassword()));
            user.setAuthorities(Set.of(userRole)); // Set the user role
            user.setBankAccount(bankAccount); // Associate the BankAccount with the User

            // Save the User to the database
            userRepository.save(user);

            return ResponseEntity.ok("User registered successfully!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
        }
    }



    public ResponseEntity<?> login(String username, String password) {
        try {
            //autentiserar användaren
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
            //skapar en variabel som hämtar användaren från databasen
            User user = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));
            //skapar en variabel som innehåller JWT
            String jwt = tokenService.generateJwt(new UsernamePasswordAuthenticationToken(username, password));
            //returnerar användaren och JWT
            return ResponseEntity.ok(new LoggResponse(user, jwt));
        } catch (AuthenticationException e) {
            return ResponseEntity.ok(new LoggResponse(null,""));
        }
    }
}
