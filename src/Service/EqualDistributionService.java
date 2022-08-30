package Service;

import Entity.ExpenseTracker;
import Entity.User;

import java.util.List;
import java.util.Map;
import java.util.Objects;

public class EqualDistributionService extends DistributionService {

    public EqualDistributionService(String payer, int amountPaid, int userNumber, List<String> borrowers, Map<String, User> userMap, ExpenseTracker tracker) {
        super(payer, amountPaid, userNumber, borrowers, userMap, tracker);
    }

    @Override
    public void distribute() {
        double debtAmountForOthers = ((double)getAmountPaid()) / ((double)getUserNumber());
        debtAmountForOthers=Math.round(debtAmountForOthers*100.0)/100.0;
        System.out.println(debtAmountForOthers);
        Map<String, User> userMap = getUserMap();
        Map<User, Map<User, Double>> expenseMap = getTracker().getOwnMap();
        User payer = userMap.get(getPayer());
        boolean first=true;
        double debtAmountForFirst=getAmountPaid()-(getUserNumber()-1)*debtAmountForOthers;
        debtAmountForFirst=Math.round(debtAmountForFirst*100.0)/100.0;
        for (String id : getBorrowers()) {
            double debtAmount=0.0;
            if(first)
                debtAmount=debtAmountForFirst;
            else {
                debtAmount=debtAmountForOthers;
            }
            first=false;
            User borrower = userMap.get(id);
            if (borrower == payer) {
                continue;
            }
            double totAmount = 0.0;
            Object ob = expenseMap.get(payer).get(borrower);
            if (Objects.nonNull(ob)) {
                totAmount = (double) ob;
            }
            if (totAmount + debtAmount == 0.0) {
                expenseMap.get(payer).remove(borrower);
                expenseMap.get(borrower).remove(payer);
            } else {
                totAmount += debtAmount;
                expenseMap.get(payer).put(borrower, totAmount);
                expenseMap.get(borrower).put(payer, -1 * totAmount);
            }
        }
    }
}
