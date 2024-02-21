package com.example.BankApp.models;

import lombok.Data;

@Data
public class LoggResponse {
    private User user;
    private String jwt;

    public LoggResponse() {
        super();
    }

    public LoggResponse(User user, String jwt) {
        this.user = user;
        this.jwt = jwt;
    }
}
