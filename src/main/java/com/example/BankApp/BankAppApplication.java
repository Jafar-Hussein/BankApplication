package com.example.BankApp;

import com.example.BankApp.models.AccountType;
import com.example.BankApp.models.Role;
import com.example.BankApp.repository.AccountTypeRepository;
import com.example.BankApp.repository.RoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class BankAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(BankAppApplication.class, args);
	}

	@Bean
	CommandLineRunner run(RoleRepository roleRepository, AccountTypeRepository accountTypeRepository) {
		return args -> {
			if (roleRepository.findByAuthority("ADMIN").isEmpty()) {
				Role adminRole = roleRepository.save(new Role("ADMIN"));
				Set<Role> roles = new HashSet<>();
				roles.add(adminRole);
			}
			if (accountTypeRepository.findById(1L).isEmpty()) {
				accountTypeRepository.save(new AccountType(1L, "Savings"));
				accountTypeRepository.save(new AccountType(2L, "Checking"));
			}
		};
	}

}
