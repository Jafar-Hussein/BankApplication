package com.example.BankApp.controller;

import com.example.BankApp.models.User;
import com.example.BankApp.service.BankAccountService;
import com.example.BankApp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("bank/transaction")
@RequiredArgsConstructor
public class BankAccountController {
   private final BankAccountService bankAccountService;
   private final UserService userService;

   @PostMapping("/deposit")
    public void deposit(@RequestBody double amount) {
       if (amount <= 0){
           throw new IllegalArgumentException("Deposit amount must be greater than 0");
       }
       User user = userService.getCurrentUser();
       bankAccountService.deposit(user.getBankAccount().getAccountNumber(), amount);
    }

}
