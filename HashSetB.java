import java.util.*;
public class HashSetB{

    public static String getStartPt(HashMap<String, String> tickets){
        HashMap<String, String> revMap = new HashMap<>();

        for(String tkt :tickets.keySet()){
            revMap.put(tickets.get(tkt), tkt);
        }

        for(String tkt :tickets.keySet()){
            if(!revMap.containsKey(tkt)){
                return tkt; // milgaya start pt
            }
        }

        return null;
    }


    public static void main(String args[]){
        HashMap<String,String> tickets = new HashMap<>();
        tickets.put("Chennai", "Bengalauru");
        tickets.put("mumbai", "delhi");
        tickets.put("goa", "Chennai");
        tickets.put("delhi", "goa");

        String start = getStartPt(tickets);
        System.out.print(start);
        for(String tkt : tickets.keySet()){
            System.out.print(" -> " + tickets.get(start));
            start = tickets.get(start);
            
        }
    }
}