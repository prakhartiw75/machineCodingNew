package Service;

import Entity.ExpenseTracker;
import Entity.User;

import java.util.List;
import java.util.Map;
import java.util.Objects;

public class ExactDistributionService extends DistributionService {

    private List<Double> exactDistribute;

    public ExactDistributionService(String paidBy, int totalAmount, int userNumber, List<String> borrowers, Map<String, User> userMap, ExpenseTracker tracker, List<Double> exactDistribute) {
        super(paidBy, totalAmount, userNumber, borrowers, userMap, tracker);
        this.exactDistribute = exactDistribute;
    }

    @Override
    public void distribute() {
        Map<String, User> userMap = getUserMap();
        Map<User, Map<User, Double>> expenseMap = getTracker().getOwnMap();
        User payer = userMap.get(getPayer());
        List<String> borrowers = getBorrowers();
        for (String borrowerId : borrowers) {
            if (Objects.isNull(userMap.get(borrowerId))) {
                System.out.println("User with id=" + borrowerId + " is not present in system. Please check your request again");
                return;
            }
        }
        for (int i = 0; i < borrowers.size(); i++) {
            String borrowerId = borrowers.get(i);
            User borrower = userMap.get(borrowerId);
            if (borrower == payer) {
                continue;
            }
            double debtAmount = exactDistribute.get(i);
            double totAmount = 0.0;
            Object ob = expenseMap.get(payer).get(borrower);
            if (Objects.nonNull(ob)) {
                totAmount = (double) ob;
            }
            // Condition for neutralizing previous debt of payer
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
