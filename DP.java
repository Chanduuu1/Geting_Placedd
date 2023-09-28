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
    public static int knapsackMemoization(int val[], int wt[], int W, int n, int dp[][]){ // O(n * W)
        // base case
        if(W == 0 || n == 0){
            return 0; 
        }

        if(dp[n][W] != -1){
            return dp[n][W];
        }

        if(wt[n-1] <= W){    
            int ans1 = val[n-1] + knapsackMemoization(val, wt, W - wt[n-1], n-1,dp);

            int ans2 = knapsackMemoization(val, wt, W, n-1,dp);

            dp[n][W] =  Math.max(ans1,ans2);
            return dp[n][W];
        }

        else{ 
            dp[n][W] = knapsackMemoization(val, wt, W, n-1,dp); 
            return dp[n][W];
        }       
    }
    public static int knapsackTabulation(int val[], int wt[], int W){
        int n = val.length;
        int dp[][] = new int[n+1][W+1];

        //step1 initialization
        for(int i = 0; i < dp.length; i++){
            dp[i][0] = 0;
        }
        for(int j = 0; j < dp[0].length; j++){
            dp[0][j] = 0;
        }

        // tabulating
        for(int i = 1; i < n+1; i++){
            for(int j = 1; j < W+1; j++){
                // find value and wieght 
                int v = val[i-1]; // value of ith element
                int w = wt[i-1]; // wt of " " " "
                if(w <= j){  //valid
                
                // apna toh include hi karlo + aage dekh i-1 elemets ko j-w capacity se max profit kese nikale? uske liye uska table pr search kr details and buid
                int incldProfit = v + dp[i-1][j-w];
                // me excld kar kr baaki possibilites explore kr raha
                int excldProfit = dp[i-1][j];


                //anth me jo zyada profitable!
                dp[i][j] = Math.max(incldProfit,excldProfit);
                }

                else{
                    int excldProfit = dp[i-1][j];
                    dp[i][j] = excldProfit;
                }
            }
        }


        // just for clarity dp matrix ko dekhle 
        printDP(dp);

        return dp[n][W]; // as expected, ans ekdum nichle right corner me hoga
    }
    public static void printDP(int dp[][]){
        for(int i = 0; i < dp.length; i++){
            for(int j = 0; j < dp[0].length; j++){
                System.out.print(dp[i][j]+ " ");
            }
            System.out.println();
            
        }
    }
    

    public static void main(String args[]){
        int val[] = {15,14,10,45,30};
        int wt[] = {2,5,1,3,4};
        int W = 7;
        int dp[][] = new int[val.length + 1][W+1];
        // also initiailizig with -1
        for(int i = 0; i < dp.length; i++){
            for(int j = 0; j < dp[0].length; j++){
                dp[i][j] = -1;
            }
        }
        System.out.println(knapsackTabulation(val,wt,W));
        
    }
}