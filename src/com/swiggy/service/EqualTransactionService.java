package com.swiggy.service;

import com.swiggy.model.MoneyBorrower;
import com.swiggy.model.User;
import com.swiggy.utils.Utils;

import java.util.List;

public class EqualTransactionService extends TransactionService{
    @Override
    public void distribute(double amountPaid, List<MoneyBorrower> borrowers) {
        List<Double>equalDivision= Utils.equalDivision(amountPaid,borrowers);
        boolean isFirstBorrower=true;
        for(MoneyBorrower borrower:borrowers){
            if(isFirstBorrower){
                borrower.setAmount(equalDivision.get(0));
            } else{
                borrower.setAmount(equalDivision.get(1));
            }
            isFirstBorrower=false;
        }
    }

    @Override
    public void validate() {

    }
}
