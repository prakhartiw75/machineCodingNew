package com.swiggy.service;

import com.swiggy.model.MoneyBorrower;

import java.util.List;
import java.util.Objects;

public class ExactTransactionService extends TransactionService{

    private static ExactTransactionService exactTransactionService;
    private ExactTransactionService(){
        super();
    }
    @Override
    public void distribute(double amountPaid, List<MoneyBorrower> borrowers) {
    }

    @Override
    public void validate() {

    }

    public static ExactTransactionService getExactTransactionServiceObject(){
        if(Objects.isNull(exactTransactionService)){
            exactTransactionService=new ExactTransactionService();
        }
        return exactTransactionService;
    }
}
