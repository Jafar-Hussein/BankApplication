package com.example.BankApp.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Table(name = "account_types")
public class AccountType {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private String type;
    @OneToMany(mappedBy = "accountType")
    private Set<BankAccount> bankAccounts;

    public AccountType(Long id, String type) {
        this.id = id;
        this.type = type;
        this.bankAccounts = new HashSet<>();
    }

    public AccountType() {
        super();
        this.bankAccounts = new HashSet<>();
    }
}
