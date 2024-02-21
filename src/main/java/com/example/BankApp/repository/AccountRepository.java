package com.example.BankApp.repository;

import com.example.BankApp.models.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<BankAccount, Long> {
}
