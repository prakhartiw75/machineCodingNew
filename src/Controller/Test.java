package Controller;

import Entity.ExpenseTracker;
import Service.DistributionService;
import Service.EqualDistributionService;
import Service.ExactDistributionService;
import Entity.User;

import java.util.*;

public class Test {

    public static void main(String[] args) {
        ExpenseTracker tracker = new ExpenseTracker();

        // this map will be used to track expenses. Request will involve these users only.
        Map<User, Map<User, Double>> expenseTrackerMap = new HashMap<User, Map<User, Double>>();
        User first = new User("u1");
        User second = new User("u2");
        User third = new User("u3");
        User fourth = new User("u4");

        //Entity, will be fetched from DB
        expenseTrackerMap.put(first, new HashMap<>());
        expenseTrackerMap.put(second, new HashMap<>());
        expenseTrackerMap.put(third, new HashMap<>());
        expenseTrackerMap.put(fourth, new HashMap<>());
        tracker.setOwnMap(expenseTrackerMap);

        // In general this value will be fetched from DB
        Map<String, User> userMap = new HashMap<>();
        userMap.put("u1", first);
        userMap.put("u2", second);
        userMap.put("u3", third);
        userMap.put("u4", fourth);

        Scanner scanner = new Scanner(System.in);
        boolean test = true;
        boolean isInserted = false;
        while (test) {
            System.out.print("Enter your query: ");
            String input = scanner.nextLine();
            String[] words = input.split(" ");
            switch (words[0]) {
                case "SHOW":
                    if (!isInserted) {
                        System.out.println("No balances");
                        break;
                    } else if (words.length == 1) {
                        if (!isInserted) {
                            System.out.println("No balances");
                            break;
                        }
                        for (Map.Entry<User, Map<User, Double>> map : expenseTrackerMap.entrySet()) {
                            for (Map.Entry<User, Double> map2 : map.getValue().entrySet()) {
                                if (map2.getValue() < 0) {
                                    System.out.println(map.getKey().getUserId() + " owns " + map2.getKey().getUserId() + ": " + -1 * map2.getValue());
                                }
                            }
                        }
                    } else {
                        try {
                            User user = userMap.get(words[1]);
                            for (Map.Entry<User, Double> map : expenseTrackerMap.get(user).entrySet()) {
                                User key = map.getKey();
                                Double value = map.getValue();
                                if (value < 0) {
                                    System.out.println(words[1] + " owes " + key.getUserId() + ": " + -1 * value);
                                } else {
                                    System.out.println(key.getUserId() + " owes " + words[1] + ": " + value);
                                }
                            }
                        } catch (NullPointerException e) {
                            System.out.println("User is not present in system!");
                            break;
                        }
                    }
                    break;
                case "EXPENSE":
                    isInserted = true;
                    int numUser = Integer.parseInt(words[3]);
                    String payer = words[1];
                    List<String> borrowers = new ArrayList<>();
                    for (int i = 4; i < 4 + numUser; i++) {
                        borrowers.add(words[i]);
                    }
                    DistributionService distributionService=null;
                    if (words[numUser + 4].equals("EXACT")) {
                        List<Double> moneyDistribution = new ArrayList<>();
                        for (int i = numUser + 5; i < words.length; i++) {
                            moneyDistribution.add(Double.parseDouble(words[i]));
                        }
                        distributionService = new ExactDistributionService(payer, Integer.parseInt(words[2]), numUser, borrowers, userMap, tracker, moneyDistribution);
                    } else {
                        distributionService = new EqualDistributionService(payer, Integer.parseInt(words[2]), numUser, borrowers, userMap, tracker);
                    }
                    distributionService.distribute();
                    System.out.println("OK");
                    break;
                default:
                    System.out.println("Wrong Input! Please check your input");
            }
            System.out.println("Do you want to test again? Enter y for YES and n for NO ");
            test = scanner.nextLine().charAt(0) == 'y';
        }
    }
}
