package Service;

import Helper.ExpenseTracker;
import Entity.User;

import java.util.List;
import java.util.Map;

public abstract class DistributionService {
    private final String payer;
    private final int amountPaid;
    private final int userNumber;
    private final List<String> borrowers;
    private final Map<String, User> userMap;
    private final ExpenseTracker tracker;

    public DistributionService(String payer, int amountPaid, int userNumber, List<String> borrower, Map<String, User> userMap, ExpenseTracker tracker) {
        this.payer = payer;
        this.amountPaid = amountPaid;
        this.userNumber = userNumber;
        this.borrowers = borrower;
        this.userMap = userMap;
        this.tracker = tracker;
    }

    public String getPayer() {
        return payer;
    }

    public int getAmountPaid() {
        return amountPaid;
    }

    public int getUserNumber() {
        return userNumber;
    }

    public List<String> getBorrowers() {
        return borrowers;
    }

    public ExpenseTracker getTracker() {
        return tracker;
    }

    public Map<String, User> getUserMap() {
        return userMap;
    }

    public abstract void distribute();

}
