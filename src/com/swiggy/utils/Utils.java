package com.swiggy.utils;

import com.swiggy.model.MoneyBorrower;

import java.util.ArrayList;
import java.util.List;

public class Utils {

    public static List<Double> equalDivision(double amount, List<MoneyBorrower> borrowers){
        List<Double>equalDivision=new ArrayList<>();
        double debtAmountForOthers = (amount) / (borrowers.size()*1.0);
        debtAmountForOthers = Math.round(debtAmountForOthers * 100.0) / 100.0;
        double debtAmountForFirst = amount - (borrowers.size() - 1) * debtAmountForOthers;
        debtAmountForFirst = Math.round(debtAmountForFirst * 100.0) / 100.0;
        equalDivision.add(debtAmountForFirst);
        equalDivision.add(debtAmountForOthers);
        return equalDivision;
    }
}
