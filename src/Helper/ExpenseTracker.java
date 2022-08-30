package Helper;

import Entity.User;

import java.util.HashMap;
import java.util.Map;

public class ExpenseTracker {

    private Map<User, Map<User, Double>> trackerMap;

    public ExpenseTracker() {
        trackerMap = new HashMap<>();
    }

    public Map<User, Map<User, Double>> getTrackerMap() {
        return trackerMap;
    }

    public void setTrackerMap(Map<User, Map<User, Double>> trackerMap) {
        this.trackerMap = trackerMap;
    }
}
