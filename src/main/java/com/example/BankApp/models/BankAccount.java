package com.example.BankApp.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Data
@Table(name = "bank_accounts")
@AllArgsConstructor
@NoArgsConstructor
public class BankAccount {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private String accountNumber;
    private Double balance;
    @ManyToOne
    @JoinColumn(name = "account_type_id")
    private AccountType accountType;

    @OneToMany(mappedBy = "bankAccount")
    private Set<User> user;
}
