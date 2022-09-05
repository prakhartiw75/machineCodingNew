package com.swiggy.model;

import java.util.List;

public class Transaction {
    private final String payer;
    private final List<MoneyBorrower>borrowers;
    private final double amountPaid;
    private final String transactionType;

    public Transaction(String payer, List<MoneyBorrower> borrowers, double amountPaid, String transactionType) {
        this.payer = payer;
        this.borrowers = borrowers;
        this.amountPaid = amountPaid;
        this.transactionType = transactionType;
    }

    public String getPayer() {
        return payer;
    }

    public List<MoneyBorrower> getBorrowers() {
        return borrowers;
    }

    public double getAmountPaid() {
        return amountPaid;
    }

    public String getTransactionType() {
        return transactionType;
    }
}
