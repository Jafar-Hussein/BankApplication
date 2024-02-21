package com.example.BankApp.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Data
@Table(name = "transactions")
public class Transaction {
    @Id
    private Long id;
    @Enumerated(EnumType.STRING)
    private TransactionType transactionType;

    private Date transactionDate;

    @ManyToOne
    private BankAccount bankAccount;

    double amount;
}
