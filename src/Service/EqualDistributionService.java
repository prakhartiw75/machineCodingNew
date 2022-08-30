package Service;

import Entity.ExpenseTracker;
import Entity.User;

import java.util.List;
import java.util.Map;
import java.util.Objects;

public class EqualDistributionService extends ExpensesDistributionService {

    public EqualDistributionService(String paidBy, int totalAmount, int userNumber, List<String> debtUser, Map<String, User> userMap, ExpenseTracker tracker) {
        super(paidBy, totalAmount, userNumber, debtUser, userMap, tracker);
    }

    @Override
    public void distribute() {
        double debtAmount=(getTotalAmount())/(getUserNumber()*1.0);
        Map<String, User>userMap=getUserMap();
        Map<User,Map<User,Double>>expenseMap=getTracker().getOwnMap();
        User paidBy=userMap.get(getPaidBy());
        for(String id:getDebtUser()){
            User paidTo=userMap.get(id);
            if(paidTo==paidBy){
                continue;
            }
            double totAmount=0.0;
            Object ob=expenseMap.get(paidBy).get(paidTo);
            if(Objects.nonNull(ob)){
                totAmount=(double) ob;
            }
            if(totAmount!=0.0&&totAmount+debtAmount==0.0){
                expenseMap.get(paidBy).remove(paidTo);
                expenseMap.get(paidTo).remove(paidBy);
            } else{
                totAmount+=debtAmount;
                expenseMap.get(paidBy).put(paidTo,totAmount);
                expenseMap.get(paidTo).put(paidBy,-1*totAmount);
            }
        }
    }
}
