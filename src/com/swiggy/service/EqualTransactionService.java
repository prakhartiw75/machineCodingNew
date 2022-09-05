package com.swiggy.service;

import com.swiggy.model.MoneyBorrower;
import com.swiggy.utils.Utils;

import java.util.List;
import java.util.Objects;

public class EqualTransactionService extends TransactionService{

    private static EqualTransactionService equalTransactionService;
    private EqualTransactionService(){
        super();
    }
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

    public static EqualTransactionService getEqualTransactionServiceObject(){
        if(Objects.isNull(equalTransactionService)){
            equalTransactionService=new EqualTransactionService();
        }
        return equalTransactionService;
    }
}
