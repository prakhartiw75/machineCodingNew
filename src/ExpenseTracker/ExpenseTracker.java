package ExpenseTracker;

import Entity.User;

import java.util.HashMap;
import java.util.Map;

public class ExpenseTracker {

    private Map<User,Map<User,Double>>ownMap;
    public ExpenseTracker() {
        ownMap=new HashMap<User, Map<User, Double>>();
    }
    public Map<User, Map<User, Double>> getOwnMap() {
        return ownMap;
    }
    public void setOwnMap(Map<User, Map<User, Double>> ownMap) {
        this.ownMap = ownMap;
    }
}
