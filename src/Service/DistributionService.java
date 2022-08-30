package Service;

import Entity.ExpenseTracker;
import Entity.User;

import java.util.List;
import java.util.Map;

public abstract class DistributionService {
    private String payer;
    private int amountPaid;
    private int userNumber;
    private List<String> borrowers;
    private Map<String, User> userMap;
    private ExpenseTracker tracker;

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

    public void setPayer(String payer) {
        this.payer = payer;
    }

    public int getAmountPaid() {
        return amountPaid;
    }

    public void setAmountPaid(int amountPaid) {
        this.amountPaid = amountPaid;
    }

    public int getUserNumber() {
        return userNumber;
    }

    public void setUserNumber(int userNumber) {
        this.userNumber = userNumber;
    }

    public List<String> getBorrowers() {
        return borrowers;
    }

    public void setBorrowers(List<String> borrowers) {
        this.borrowers = borrowers;
    }

    public ExpenseTracker getTracker() {
        return tracker;
    }

    public void setTracker(ExpenseTracker tracker) {
        this.tracker = tracker;
    }

    public Map<String, User> getUserMap() {
        return userMap;
    }

    public void setUserMap(Map<String, User> userMap) {
        this.userMap = userMap;
    }

    public abstract void distribute();

}
