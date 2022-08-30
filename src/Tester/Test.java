package Tester;

import Entity.ExpenseTracker;
import ExpensesDistribution.ExpensesDistribution;
import ExpensesDistribution.EqualDistribution;
import ExpensesDistribution.ExactDistribution;
import Entity.User;

import java.util.*;

public class Test {

    public static void main(String[] args) {
        ExpenseTracker tracker=new ExpenseTracker();

        // this map will be used to track expenses. Request will involve these users only.
        Map<User, Map<User, Double>>ownMap=new HashMap<User, Map<User, Double>>();
        User first=new User("u1");
        User second=new User("u2");
        User third=new User("u3");
        User fourth=new User("u4");

        ownMap.put(first,new HashMap<>());
        ownMap.put(second,new HashMap<>());
        ownMap.put(third,new HashMap<>());
        ownMap.put(fourth,new HashMap<>());
        tracker.setOwnMap(ownMap);

        // In general this value will be fetched from database
        Map<String,User>userMap=new HashMap<>();
        userMap.put("u1",first);
        userMap.put("u2",second);
        userMap.put("u3",third);
        userMap.put("u4",fourth);

        Scanner scanner=new Scanner(System.in);
        boolean test=true;
        boolean isValueInserted=false;
        while(test){
            System.out.print("Enter your query: ");
            String input=scanner.nextLine();
            String[] words=input.split(" ");
            switch(words[0]){
                case "SHOW":
                    if(!isValueInserted){
                        System.out.println("No balances");
                        break;
                    } else if(words.length==1){
                         if(!isValueInserted){
                             System.out.println("No balances");
                             break;
                         }
                        for(Map.Entry<User,Map<User,Double>>map:ownMap.entrySet()){
                            for(Map.Entry<User,Double>map2:map.getValue().entrySet()){
                                if(map2.getValue()<0){
                                    System.out.println(map.getKey().getUserId()+" owns "+map2.getKey().getUserId()+": "+-1*map2.getValue());
                                }
                            }
                        }
                    } else{
                        try {
                            User user = userMap.get(words[1]);
                            for(Map.Entry<User,Double>map:ownMap.get(user).entrySet()){
                                User key=map.getKey();
                                Double value=map.getValue();
                                if(value<0){
                                    System.out.println(words[1]+" owes "+ key.getUserId()+": "+-1*value);
                                } else{
                                    System.out.println(key.getUserId()+" owes "+ words[1]+": "+ value);
                                }
                            }
                        } catch (NullPointerException e){
                            System.out.println("User is not present in system!");
                            break;
                        }
                    }
                    break;
                case "EXPENSE":
                    isValueInserted=true;
                    int numUser=Integer.parseInt(words[3]);
                    String paidBy=words[1];
                    List<String>borrower=new ArrayList<>();
                    for(int i=4;i<4+numUser;i++){
                        borrower.add(words[i]);
                    }
                    if(words[numUser+4].equals("EXACT")){
                        List<Double>moneyDistribution=new ArrayList<>();
                        for(int i=numUser+5;i<words.length;i++){
                            moneyDistribution.add(Double.parseDouble(words[i]));
                        }
                        ExpensesDistribution expensesDistribution=new ExactDistribution(paidBy,Integer.parseInt(words[2]),numUser,borrower,userMap,tracker,moneyDistribution);
                        expensesDistribution.distribute();
                        System.out.println("OK");
                    } else{
                        ExpensesDistribution expensesDistribution=new EqualDistribution(paidBy,Integer.parseInt(words[2]),numUser,borrower,userMap,tracker);
                        expensesDistribution.distribute();
                        System.out.println("OK");
                    }
                    break;
                default:
                    System.out.println("Wrong Input! Please check your input");
            }
            System.out.println("Do you want to test again? Enter y for YES and n for NO ");
            test=scanner.nextLine().charAt(0)=='y';
        }
    }
}
