import java.util.*;
public class HashMapB{

    // Valid anagram code
    public static boolean isAnagram(String s, String t){
        HashMap<Character, Integer> map = new HashMap<>();

        for(int i = 0; i < s.length(); i++){
            char ch = s.charAt(i);
            map.put(ch,map.getOrDefault(ch, 0) + 1); // aggar ch key get hua map me toh uska value return karega nahi toh instead of returning null, this function will return the default value of 0 which is passes! why 0 default soch khud; 
        }

        for(int i = 0; i < t.length(); i++){
            char ch = t.charAt(i);
            if(map.get(ch) != null){ // iska mtlb value map me exists karti hai tulip, lipid wala case nahi hai 'd' wala
                if(map.get(ch) == 1){
                    map.remove(ch);
                }
                else{
                    map.put(ch,map.get(ch) - 1);
                }
            }
            else{
                return false;
            }
        }

        return map.isEmpty();
    }
    
    
    // largest subbaray with sum = 0 problem
    public static void main(String args[]){ //O(n)
        int[] arr = {15,-2,2,-8,1,7,10};
        int sum = 0;
        int len = 0;

        HashMap<Integer,Integer> map = new HashMap<>();
        for(int j = 0; j < arr.length; j++){
            sum += arr[j];
            if(map.containsKey(sum)){
                int i = map.get(sum);
                len = Math.max(len,j - i);
            }
            else{
                map.put(sum,j);
            }
        }
        System.out.println(len);
        
    }
}