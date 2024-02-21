package com.example.BankApp.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

import java.util.Set;

@Entity
@Data
@Table(name = "account_types")
public class AccountType {
    @Id
    private Long id;
    private String type;
    @OneToMany(mappedBy = "accountType")
    private Set<BankAccount> bankAccount;
}
