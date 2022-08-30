package Service;

import Entity.ExpenseTracker;
import Entity.User;

import java.util.List;
import java.util.Map;
import java.util.Objects;

public class ExactDistributionService extends ExpensesDistributionService {

    private List<Double>exactDistribute;
    public ExactDistributionService(String paidBy, int totalAmount, int userNumber, List<String> debtUser, Map<String, User> userMap, ExpenseTracker tracker, List<Double>exactDistribute) {
        super(paidBy, totalAmount, userNumber, debtUser, userMap,tracker);
        this.exactDistribute=exactDistribute;
    }

    @Override
    public void distribute() {
        Map<String, User>userMap=getUserMap();
        Map<User,Map<User,Double>>expenseMap=getTracker().getOwnMap();
        User paidBy=userMap.get(getPaidBy());
        List<String>borrower=getDebtUser();
        for(int i=0;i<borrower.size();i++){
            String borrowerId=borrower.get(i);
            if(Objects.isNull(userMap.get(borrowerId))){
                System.out.println("User with id="+borrowerId+" is not present in system. Please check your request again");
                return;
            }
        }
        for(int i=0;i<borrower.size();i++){
            String borrowerId=borrower.get(i);
            User paidTo=userMap.get(borrowerId);
            if(paidTo==paidBy){
                continue;
            }
            double debtAmount=exactDistribute.get(i);
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
