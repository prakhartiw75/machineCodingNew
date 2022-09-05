package com.swiggy.service;

import com.swiggy.model.MoneyBorrower;
import com.swiggy.model.Transaction;
import com.swiggy.model.User;
import com.swiggy.repository.ExpenseRepository;

import java.util.List;

public abstract class TransactionService {
    private final ExpenseRepository expenseRepository;

    public TransactionService(){
        expenseRepository=ExpenseRepository.getRepositoryObject();
    }

    public void addTransaction(Transaction transaction){
        this.distribute(transaction.getAmountPaid(),transaction.getBorrowers());
        this.adjust(transaction.getPayer(),transaction.getBorrowers());
        this.expenseRepository.addTransaction(transaction);
    }

    public void adjust(String payer, List<MoneyBorrower>borrowers){
        User user=this.getExpenseRepository().getUser(payer);
        for(MoneyBorrower borrower:borrowers){
            if(borrower.getBorrower()==user){
                borrower.setAmount(0.0);
                continue;
            }
            double currentDebt=borrower.getAmount();
            double prevTransactionAmount=this.getExpenseRepository().amountGiven(user,borrower.getBorrower());
            if(prevTransactionAmount+currentDebt==0.0){
                this.getExpenseRepository().removeUserFromRecord(user,borrower.getBorrower());
                this.getExpenseRepository().removeUserFromRecord(borrower.getBorrower(),user);
                borrower.setAmount(0.0);
            } else{
                borrower.setAmount(prevTransactionAmount+currentDebt);
            }
        }
    }

    public abstract void distribute(double amountPaid, List<MoneyBorrower> borrowers);

    public abstract void validate();

    public ExpenseRepository getExpenseRepository() {
        return expenseRepository;
    }
}
