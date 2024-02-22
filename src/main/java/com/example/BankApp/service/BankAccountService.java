package com.example.BankApp.service;

import com.example.BankApp.models.BankAccount;
import com.example.BankApp.models.Transaction;
import com.example.BankApp.models.TransactionType;
import com.example.BankApp.repository.AccountRepository;
import com.example.BankApp.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class BankAccountService {
    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository; // Assuming you have a TransactionRepository

    public void deposit(String accountNumber, double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Deposit amount must be greater than 0");
        } else if (accountNumber == null || accountNumber.isEmpty()) {
            throw new IllegalArgumentException("Account number must be provided");
        }

        // Retrieve BankAccount from repository
        BankAccount bankAccount = accountRepository.findByAccountNumber(accountNumber)
                .orElseThrow(() -> new IllegalArgumentException("Bank account not found"));

        // Update balance
        double newBalance = bankAccount.getBalance() + amount;
        bankAccount.setBalance(newBalance);

        // Save the updated BankAccount
        accountRepository.save(bankAccount);

        // Create and save a transaction record
        Transaction transaction = new Transaction();
        transaction.setTransactionType(TransactionType.DEPOSIT);
        transaction.setTransactionDate(new Date());
        transaction.setBankAccount(bankAccount);
        transaction.setAmount(amount);
        transactionRepository.save(transaction);
    }
}

