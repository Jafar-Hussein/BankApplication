package com.example.BankApp.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Entity
@Data
@Table(name = "bank_accounts")
public class BankAccount {
    @Id
    private Long id;
    private String accountNumber;
    private Double balance;
    @ManyToOne
    private AccountType accountType;

    @OneToMany(mappedBy = "bankAccount")
    private Set<User> user;
}
