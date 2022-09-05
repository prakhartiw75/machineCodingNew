package com.swiggy.repository;

import com.swiggy.model.MoneyBorrower;
import com.swiggy.model.User;
import com.swiggy.model.Transaction;

import java.util.*;

public class ExpenseRepository {
    private final Map<User, Map<User, Double>> trackerMap;
    private final Map<String,User>userMap;

    private static ExpenseRepository expenseRepository;

    private ExpenseRepository() {
        this.trackerMap=new HashMap<>();
        this.userMap=new HashMap<>();
    }
    public void addUser(User user){
        userMap.put(user.getUserId(),user);
        trackerMap.put(user,new HashMap<>());
    }

    public User getUser(String userId){
        return userMap.get(userId);
    }

    public void addTransaction(Transaction transaction){
        User user=this.getUser(transaction.getPayer());
        for(MoneyBorrower moneyBorrower:transaction.getBorrowers()){
            if(moneyBorrower.getAmount()!=0.0) {
                this.findAllTransactionOfUser(user).put(moneyBorrower.getBorrower(), moneyBorrower.getAmount());
                this.findAllTransactionOfUser(moneyBorrower.getBorrower()).put(user,-1*moneyBorrower.getAmount());
            }
        }
    }

    public Map<User,Double> findAllTransactionOfUser(User user){
        return trackerMap.get(user);
    }

    public void removeUserFromRecord(User payer,User borrower){
        this.trackerMap.get(payer).remove(borrower);
    }

    public double amountGiven(User payer,User borrower){
        Double val=this.trackerMap.get(payer).get(borrower);
        return val==null?0.0:val;
    }

    public List<String> showAllTransaction(){
        List<String>output=new ArrayList<>();
        for (Map.Entry<User, Map<User, Double>> userTransactionsMap : trackerMap.entrySet()) {
            for (Map.Entry<User, Double> userTransaction : userTransactionsMap.getValue().entrySet()) {
                if (userTransaction.getValue() < 0) {
                    output.add(userTransactionsMap.getKey().getUserId() + " owns " + userTransaction.getKey().getUserId() + ": " + -1 * userTransaction.getValue());
                }
            }
        }
        return output;
    }

    public List<String>showUserTransaction(User user){
        List<String>output=new ArrayList<>();
        for (Map.Entry<User, Double> userTransactionsMap : trackerMap.get(user).entrySet()) {
            User key = userTransactionsMap.getKey();
            Double value = userTransactionsMap.getValue();
            if (value < 0) {
                output.add(user.getUserId() + " owes " + key.getUserId() + ": " + -1 * value);
            } else {
                output.add(key.getUserId() + " owes " + user.getUserId() + ": " + value);
            }
        }
        return output;
    }

    public static ExpenseRepository getRepositoryObject(){
       if(Objects.nonNull(expenseRepository)){
           return expenseRepository;
       }
       expenseRepository=new ExpenseRepository();
       return expenseRepository;
    }
}
