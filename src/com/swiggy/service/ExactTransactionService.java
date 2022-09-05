package com.swiggy.service;

import com.swiggy.model.MoneyBorrower;

import java.util.List;

public class ExactTransactionService extends TransactionService{
    @Override
    public void distribute(double amountPaid, List<MoneyBorrower> borrowers) {
    }

    @Override
    public void validate() {

    }
}
