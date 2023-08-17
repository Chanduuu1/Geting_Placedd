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

    public static double fractKnapsack(double[] profit, double[]wt, int W){
        //sorting on the basis of pro/wt
        double ratio[] = new double[profit.length];
        for(int i = 0; i < profit.length; i++){
            ratio[i] = profit[i]/(double)wt[i];
        }

        // making a 2D array
        double itemData[][] = new double[profit.length][3];
        for(int i = 0; i < profit.length; i++){
            itemData[i][0] = ratio[i];
            itemData[i][1] = profit[i];
            itemData[i][2] = wt[i];
        }

        // sorting this wrt ratio(this will do it in asc order)
        Arrays.sort(itemData, Comparator.comparingDouble(o -> o[0]));

        // greedy approch
        double capacity = W;
        double val = 0;
        for(int i = profit.length-1; i >= 0 ; i--){
            if(capacity >= itemData[i][2]){
                capacity -= itemData[i][2];
                val += itemData[i][1];                
            }
            else if(capacity < itemData[i][2] && capacity > 0){
                val += ((capacity)/(itemData[i][2])) * itemData[i][1];
                capacity = 0;
                break;
            }
        }
        return val;
    }

    public static void main(String[] args){
        double profit[] = {60,100,120};
        double wt[] = {10,20,30};

        System.out.println(fractKnapsack(profit,wt,50));
        
        
        
    }
}