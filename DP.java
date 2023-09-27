public class DP{
    

    //climbing stairs
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


    // 0-1 knapsack normal recursion (bohot kuch documentation likh diya hai, sirf code dekhna ho  toh comments remove kardena kahi aur copy paste karkr)
    public static int knapsack(int val[], int wt[], int W, int n){
        // base case
        if(W == 0 || n == 0){
            return 0; // (feel nahi aaya toh) samaj initially hi knapsack ka capacity 0 diya hai, ya shuru se hi koi items hi nahi hai jisme hum yeh sab perform kare, so donno
                      // hi casses me total profit 0 hi aaega na, toh usko yaha mimic karwaya hia
        }

        if(wt[n-1] <= W){ // n-1 isilye because 1st item 0th index me stored hoga,similarly last item n-1th index me stored hoga
            //include kar rahe hai toh mera value + aage jo bhi dalega woh
            int ans1 = val[n-1] + knapsack(val, wt, W - wt[n-1], n-1);
            
            // me exclude bhi kar sakta hu, agar exclude kiya toh jo exclude hua hai (current element) uska value count nahi hoga instead jo aage aaega woh
            int ans2 = knapsack(val, wt, W, n-1);


            // abh dekhna hai , include kara toh faida hua ki exclude kara toh
            return Math.max(ans1,ans2);
        }

        else{ // mtlb item ko kanpsack me jaga nahi, tabh toh exclude k=hi karna padega
            return knapsack(val, wt, W, n-1); //kuch nahi aage badho esa
        }       
    }

    

    public static void main(String args[]){
        int val[] = {15,14,10,45,30};
        int wt[] = {2,5,1,3,4};
        int W = 7;
        System.out.println(knapsack(val,wt,W,val.length));
        
    }
}