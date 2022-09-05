package com.swiggy.model;

public class MoneyBorrower {
    private final User borrower;
    private double amount;

    public MoneyBorrower(User borrower, double amount) {
        this.borrower = borrower;
        this.amount = amount;
    }

    public User getBorrower() {
        return borrower;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
