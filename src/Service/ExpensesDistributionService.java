package Service;

import Entity.ExpenseTracker;
import Entity.User;

import java.util.List;
import java.util.Map;

public abstract class ExpensesDistributionService {
    private String paidBy;
    private int totalAmount;
    private int userNumber;
    private List<String> debtUser;
    private Map<String,User> userMap;
    private ExpenseTracker tracker;
    public ExpensesDistributionService(String paidBy, int totalAmount, int userNumber, List<String> debtUser, Map<String, User> userMap, ExpenseTracker tracker) {
        this.paidBy = paidBy;
        this.totalAmount = totalAmount;
        this.userNumber = userNumber;
        this.debtUser = debtUser;
        this.userMap = userMap;
        this.tracker=tracker;
    }

    public String getPaidBy() {
        return paidBy;
    }

    public void setPaidBy(String paidBy) {
        this.paidBy = paidBy;
    }

    public int getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(int totalAmount) {
        this.totalAmount = totalAmount;
    }

    public int getUserNumber() {
        return userNumber;
    }

    public void setUserNumber(int userNumber) {
        this.userNumber = userNumber;
    }

    public List<String> getDebtUser() {
        return debtUser;
    }

    public void setDebtUser(List<String> debtUser) {
        this.debtUser = debtUser;
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
