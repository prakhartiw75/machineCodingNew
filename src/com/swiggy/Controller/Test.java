package com.swiggy.Controller;

import com.swiggy.model.MoneyBorrower;
import com.swiggy.model.Transaction;
import com.swiggy.model.User;
import com.swiggy.repository.ExpenseRepository;
import com.swiggy.service.*;

import java.util.*;

public class Test {

    public static void main(String[] args) {
        UserService userService=new UserService();
        ExpenseRepository expenseRepository=ExpenseRepository.getRepositoryObject();

        User first = new User("u1","Prakhar Tiwari","tiwariprakhar75@gmail.com","8123456780");
        User second = new User("u2","Rohan Verma","rohan23@gmail.com","9644552132");
        User third = new User("u3","Dhruv Sharma","dhruvnew100@gmail.com","9080706022");
        User fourth = new User("u4","Tanay Kumar","tanaykumar34@gmail.com","9971234567");

        userService.addUser(first);
        userService.addUser(second);
        userService.addUser(third);
        userService.addUser(fourth);

        Scanner scanner = new Scanner(System.in);
        boolean test = true;
        boolean isInserted = false;
        while (test) {
            System.out.print("Enter your query: ");
            String input = scanner.nextLine();
            String[] words = input.split(" ");
            switch (words[0]) {
                case "SHOW":
                    if (!isInserted) {
                        System.out.println("No balances");
                        break;
                    } else if (words.length == 1) {
                        List<String>output=expenseRepository.showAllTransaction();
                        for(String transaction:output){
                            System.out.println(transaction);
                        }
                    } else {
                        List<String>output=expenseRepository.showUserTransaction(userService.getUser(words[1]));
                        for(String transaction:output){
                            System.out.println(transaction);
                        }
                    }
                    break;
                case "EXPENSE":
                    isInserted = true;
                    TransactionService transactionService;
                    int numUser = Integer.parseInt(words[3]);
                    String payer = words[1];
                    List<String> borrowers = new ArrayList<>(Arrays.asList(words).subList(4, 4 + numUser));
                    Transaction transaction;
                    List<MoneyBorrower>moneyBorrowers=new ArrayList<>();
                    if (words[numUser + 4].equals("EXACT")) {
                        for (int i = numUser + 5; i < words.length; i++) {
                            MoneyBorrower moneyBorrower=new MoneyBorrower(userService.getUser(borrowers.get(i-5-numUser)),Double.parseDouble(words[i]));
                            moneyBorrowers.add(moneyBorrower);
                        }
                        transactionService=new ExactTransactionService();
                    } else {
                        for (int i = 4; i < 4+numUser; i++) {
                            MoneyBorrower moneyBorrower=new MoneyBorrower(userService.getUser(words[i]),100.0);
                            moneyBorrowers.add(moneyBorrower);
                        }
                        transactionService=new EqualTransactionService();
                    }
                    transaction=new Transaction(payer,moneyBorrowers,Double.parseDouble(words[2]),words[numUser+4]);
                    transactionService.addTransaction(transaction);
                    System.out.println("OK");
                    break;
                default:
                    System.out.println("Wrong Input! Please check your input");
            }
            System.out.println("Do you want to test again? Enter y for YES and anything else for NO ");
            test = scanner.nextLine().charAt(0) == 'y';
        }
    }
}
