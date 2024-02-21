package com.example.BankApp.service;

import com.example.BankApp.models.User;
import com.example.BankApp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
 @RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    /**
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));
    }

    public User getCurrentUser() { //Hämtar inloggad användare
        //skapar en variabel som hämtar inloggad användare
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        //hämtar användaren från databasen
        Optional<User> userOptional = userRepository.findByUsername(username);
        //returnerar användaren
        return userOptional.orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));
    }

}
