import java.util.*;
public class GreedyAlgos{

    public static void activitySelection(int[] start, int[] end){
        int activities[][] = new int[end.length][3];
        for(int i = 0; i < start.length; i++){
            activities[i][0] = i;  // indices stored so later can retrive the correct act no after sorting
            activities[i][1] = start[i];
            activities[i][2] = end[i];
        }

        // Sorting this 2d-array on the basis of end time
        //lambda function
        Arrays.sort(activities, Comparator.comparingDouble(o -> o[2]));

        //now once they have been sorted apna normal greedy approach
        int maxAct = 0;
        ArrayList<Integer> ans = new ArrayList<>(); // iss arraylist me will store activities ke number

        // we always peek the first act acc to approach 
        maxAct = 1;
        ans.add(activities[0][0]);
        int lastEnded = activities[0][2];
        for(int i = 1; i < activities.length; i++){
            if(activities[i][1] >= lastEnded){
                maxAct++;
                ans.add(activities[i][0]);
                lastEnded = activities[i][2];
            }
        }
        System.out.println("Max activites that can be done " + maxAct);
        for(int i = 0; i < ans.size(); i++){
            System.out.println("A"+ans.get(i)+" Selected ");
            
        }
        System.out.println();
        
        
        
    }

    public static void main(String[] args){
        int start[] = {0,8,1,3,5,5};
        int end[] = {6,9,2,4,7,9};

        // for a sorted end array
        activitySelection(start,end);
        
        
    }
}