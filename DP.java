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
    

    // Target sum tabulation
    public static boolean targetSumTabulation(int arr[], int sum){ // )(n * Targetsum)
        int n = arr.length;
        boolean dp[][] = new boolean[n+1][sum+1];
        // base case initialization. *Note - sirf trye wala initialzation karna, because the entire 2d array is by default initializaed with false so wapas false ke jaga pr false initialze karna iss....
        // i = items, j = tS
        for(int i = 1; i < n+1; i++){
            dp[i][0] = true;
        }

        for(int i = 1; i < n+1; i++){
            for(int j = 1; j < sum+1; j++){
                int v = arr[i-1];
                //incld
                if(v <= j && dp[i-1][j-v] == true){
                    dp[i][j] = true;
                }
                //excld
                else if(dp[i-1][j] == true){
                    dp[i][j] = true;
                }

            }
        }

        printDP2(dp);
        return dp[n][sum];
    }
    public static void printDP2(boolean dp[][]){
        for(int i = 0; i < dp.length; i++){
            for(int j = 0; j < dp[0].length; j++){
                System.out.print(dp[i][j]+ " ");
            }
            System.out.println();
            
        }
    }



    // coin change 
    public static int coinChange(int coins[], int sum){
        int n = coins.length;
        int dp[][] = new int[n+1][sum+1];

        //initializse - sum/billAmt is 0
        // i -> coins; j -> sum/change
        for(int i = 0; i < n+1; i++){
            dp[i][0] = 1;
        }
        //by default 0 assigned karta hai java we need not do it but still kayda maintain
        for(int j = 1; j < sum+1; j++){
            dp[0][j] = 0;
        }

        // filling!
        //O(n*SUM)
        for(int i = 1; i < n+1; i++){
            for(int j = 1; j < sum+1; j++){
                if(coins[i-1] <= j){ //valid
                    dp[i][j] = dp[i][j-coins[i-1]] + dp[i-1][j];
                }
                else{ //invalid
                    dp[i][j] = dp[i-1][j];
                }

            }
        }

        return dp[n][sum];
    }


    //Rod cutting
    public static int rodCutting(int lengthOfRod[], int price[], int rodLength){
        int n = price.length;
        int dp[][] = new int[n+1][rodLength+1];
        // initilaize step me 0 se hi initialize karna hai jojava alsready kr bheta hai so need to do it again
        for(int i = 1; i<n+1; i++){
            for(int j = 1; j < rodLength+1; j++){
                //valid
                if(lengthOfRod[i-1] <= j){
                    dp[i][j] = Math.max(price[i-1] + dp[i][j-lengthOfRod[i-1]], dp[i-1][j]);
    
                }
                //invalid
                else{
                    dp[i][j] = dp[i-1][j];
               }
            }
        }   
        return dp[n][rodLength];
    }


    //lcs
    public static int lcs(String str1,  int n, String str2, int m){
        if(n == 0 || m == 0){
            return 0;
        }

        if(str1.charAt(n-1) == str2.charAt(m-1)){
            return lcs(str1,n-1,str2,m-1) + 1; // same wali case
        }
        else{
            int ans1 = lcs(str1, n-1, str2,m);
            int ans2 = lcs(str1, n, str2,m-1);
            return Math.max(ans1,ans2);
        }
    }
    //lcs memoization
    public static int lcsMemoization(String str1,  int n, String str2, int m, int dp[][]){
        if(n == 0 || m == 0){
            return 0;
        }

        if(dp[n][m] != -1){
            return dp[n][m];
        }
        if(str1.charAt(n-1) == str2.charAt(m-1)){
            dp[n][m] = lcsMemoization(str1,n-1,str2,m-1,dp) + 1; // same wali case
            return dp[n][m];
        }
        else{
            int ans1 = lcsMemoization(str1, n-1, str2,m,dp);
            int ans2 = lcsMemoization(str1, n, str2,m-1,dp);
            dp[n][m] = Math.max(ans1,ans2);
            return dp[n][m];
        }
    }

    public static void main(String args[]){
        String str1 = "abcdge";
        String str2 = "abedg";
        int n = str1.length();
        int m = str2.length();

        int dp[][] = new int[n+1][m+1];
        // initialize with -1
        for(int i = 0; i < n+1; i++){
            for(int j = 0; j < m+1; j++){
                dp[i][j] = -1;
            }
        }

        System.out.println(lcsMemoization(str1, str1.length(), str2, str2.length(),dp));        
    }
}