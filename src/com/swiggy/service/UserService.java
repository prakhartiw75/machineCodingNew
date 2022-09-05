package com.swiggy.service;

import com.swiggy.model.User;
import com.swiggy.repository.ExpenseRepository;

public class UserService {
    private final ExpenseRepository expenseRepository;

    public UserService() {
        expenseRepository=ExpenseRepository.getRepositoryObject();
    }

    public void addUser(User user){
        expenseRepository.addUser(user);
    }

    public User getUser(String userId){
        return expenseRepository.getUser(userId);
    }
}
