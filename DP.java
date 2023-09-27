public class DP{
    
    public static int countWays(int n){
        // base case
        if(n==0){
            return 1;
        }
        if(n<0){
            return 0;
        }
        return countWays(n-1) + countWays(n-2); 
    }
    public static int countWaysMemoized(int n, int ways[]){
        // base case
        if(n==0){
            return 1;
        }
        if(n<0){
            return 0;
        }
        if(ways[n] != 0){
            return ways[n]; /// will return ways till nth step directly instead of rec
        }
        ways[n] = countWaysMemoized(n-1,ways) + countWaysMemoized(n-2,ways); 
        return ways[n];
    }


    public static int countWaysTabluation(int n, int ways[]){
        // intialization
        ways[0] = 1; // ground floor pr hi rehne ke 1 tareke hai wahi raho
        ways[1] = 1;
        for(int  i = 2; i <= n; i++){
            ways[i] = ways[i-1] + ways[i-2];
        }

        return ways[n];
    }

    public static void main(String args[]){
        int n=6;
        int ways[] = new int[n+1];
        
        System.out.println(countWaysTabluation(n,ways) + " ways");  // lett uss wenttt!
        
    }
}