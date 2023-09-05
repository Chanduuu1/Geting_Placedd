import java.util.*;
public class HashMapB{

    public static void main(String args[]){
        HashMap<String, Integer> hm = new HashMap<>();
        hm.put("India",100);
        hm.put("US",50);
        hm.put("Nepal",30);
        hm.put("Pakistan",20);
        hm.put("Japan",80);

        //to iterate
        Set<String> keys = hm.keySet();
        System.out.println(keys);
        

        //for each iterator
        for(String k : keys){
            System.out.println("key:"+k+", value:"+hm.get(k));
            
        }
    }
}