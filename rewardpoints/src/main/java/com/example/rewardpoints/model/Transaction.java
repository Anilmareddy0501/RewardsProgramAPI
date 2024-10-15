package com.example.rewardpoints.model;

import java.time.LocalDate;
public class Transaction {
    private String customerId;
    private LocalDate date;
    private int points;

    public Transaction(String customerId, LocalDate date, int points) {
        this.customerId = customerId;
        this.date = date;
        this.points = points;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }
}
