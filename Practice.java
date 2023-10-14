import java.util.*;

public class Practice{

    public static int BinSearch(int arr[], int val){
        int si = 0, ei = arr.length - 1, mid;
        while(si<=ei){
            mid = (si + ei)/2;
            if(val < arr[mid]){
                ei = mid - 1;
            }
            else if(val > arr[mid]){
                si = mid + 1;
            }
            else if(val == arr[mid]){
                return mid;
            }
        }
        return -1;


    }
    public static void allSubarr(int[] arr){
        for(int i = 0; i < arr.length; i++){
            for(int j = i; j < arr.length; j++){
                System.out.print("{");
                for(int k = i; k <= j; k++){
                    if(j-i == 0){
                        System.out.print(arr[k]);
                    }
                    else if(k == j){
                        System.out.print(arr[k]);
                    }
                    else{
                        System.out.print(arr[k] + ", ");
                    }
                }
                System.out.print("}");
            }
            System.out.println();
        }
    }
    public static void BinSearchRec(int arr[], int val, int st, int end){
        if(st >= end){
            System.out.println("key not found");
            return ;
        }
        int mid = (st+end)/2;

        if(val < arr[mid]){
            BinSearchRec(arr,val,st,mid-1);
        }
        else if(val > arr[mid]){
            BinSearchRec(arr,val,mid+1,end);
        }
        else if(val == arr[mid]){
            System.out.println("The key value is in index " + mid);
            return;
        }  
    }
    public static void maxSubarr(int[] arr){
        // brute force, subarrays saare sum find and return max
        // prefix sum array
        int N = arr.length;
        int sum = 0;
        int[] psumArr = new int[N];
        for(int i = 0; i < arr.length; i++){
            sum += arr[i];
            psumArr[i] = sum;

        }
        sum = 0;
        int maxSum = Integer.MIN_VALUE;
        for(int i = 0; i < psumArr.length; i++){
            for(int j = i; j < psumArr.length; j++){
                if(i - j == 0){
                    sum = arr[j];
                    if(sum > maxSum){
                        maxSum = sum;
                    }
                }
                if(i == 0){
                    sum = psumArr[j];
                    if(sum > maxSum){
                        maxSum = sum;
                    }
                }
                else{
                    sum = psumArr[j] - psumArr[i];
                    if(sum > maxSum){
                        maxSum = sum;
                    }
                }
            }
        }
        System.out.println("Max sum of subarray is "+ maxSum);
    }
    public static void printArr(int[] arr){
        // arr = height array
        for(int i = 0; i < arr.length; i++){
            System.out.print(arr[i]+" ");
        }
        System.out.println();
    }
    public static void trappingRain(int[] arr){
        int N = arr.length;
        int MLB[] = new int[N];
        int MRB[] = new int[N];
        // for getting maxLeftBound
        int max = -1;
        for(int i = 0; i < arr.length; i++){
            for(int j = 0; j <= i; j++){
                if(arr[j] > max){
                    max = arr[j];
                }
            }
            MLB[i] = max;
            max = 0;
        }

        // for getting maxRightBound
        max = -1;
        for(int i = 0; i < arr.length; i++){
            for(int j = arr.length - 1; j >= i; j--){
                if(arr[j] > max){
                    max = arr[j];
                }
            }
            MRB[i] = max;
            max = 0;
        }
        // Optimized way O(n) for calculating MLB and MRB written in rough book.
        printArr(MRB);
        printArr(MLB);        


        // finding area
        int area = 0,wl,bl,width = 1;
        for(int i = 0; i < arr.length; i++){
            wl = Math.min(MLB[i], MRB[i]);
            area += (wl - arr[i]) * width;
        }
        System.out.println(area);
    }
    public static void spiralArr(int[][] matrix){
        int sc = 0,sr = 0, ec = matrix[0].length-1, er = matrix.length-1;
        while(sc <= ec && sr <= er){
            //top
            for(int i = sc; i <= ec; i++){
                System.out.print(matrix[sr][i] + " -> ");
            }
            //right
            for(int i = sr + 1; i <= er; i++){
                System.out.print(matrix[i][ec] + " -> ");
            }
            //bottom
            for(int i = ec - 1; i >= sr; i--){
                System.out.print(matrix[er][i] + " -> ");
            }
            //left
            for(int i = er - 1; i >= sr + 1; i--){
                System.out.print(matrix[i][sc] + " -> ");
            }
            sc++;
            sr++;
            ec--;
            er--;
        }
        System.out.print(" END ");

    }
    public static void printArr2D(int[][] mat){
        for(int i = 0; i< mat.length; i++){
            for(int j = 0; j < mat[0].length; j++){
                System.out.print(mat[i][j] + " ");
            }
            System.out.println();
        }

    }
    public static void stairCaseSearch(int[][] sortedMat,int key){
        int i = 0;
        int j = sortedMat[0].length - 1;
        while(i < sortedMat.length && j < sortedMat[0].length){
            if(sortedMat[i][j] > key){
                j--;
            }
            else if(sortedMat[i][j] < key){
                i++;
            }
            if(i < sortedMat.length && j < sortedMat[0].length && sortedMat[i][j] == key){
                System.out.println("row ->" + i + " Column ->" + j);
                return;
            }
            
        }
        System.out.println("key not present");
    }
    public static void strCompress(String s){
        int count = 1;
        String newStr = " ";
        int i = 0;
        int j = 1;
        while(j <= s.length()){
            if(j == s.length()){
                if(count > 1){
                    newStr += s.charAt(i) + Integer.toString(count);
                    break;
                } else if(count == 1){
                    newStr += s.charAt(i);
                    break;
                }    
            }
            if(s.charAt(i) == s.charAt(j)){
                j++;
                count++;
            } else{
                if(count == 1){
                    newStr += s.charAt(i);
                } else if(count > 1){
                    newStr += s.charAt(i) + Integer.toString(count);
                }
                i = j;
                j++;
                count = 1;
            }
        }
        System.out.println(newStr);
    }
    public static int removeElement(int[] nums, int val) {
        int count = 0;
        int i = 0;
        while(i < nums.length){
            if(nums[i] == val){
                count++;
                for(int j = i; j < nums.length-1; j++){
                    nums[j] = nums[j+1];
                }
                nums[nums.length-count] = -1;
            }
            else{
                i++;
            }
        }
        return count;
    }
    public static int removeDuplicates(int[] nums) {
        int n=nums.length;
        if(n==0){
            return 0;
        }
        else{
            int a=0;
            for(int i=0;i<n;i++){
                if(nums[a]!=nums[i]){
                    a++;
                    nums[a]=nums[i];
                }
            }
            return a+1;
        } 
    }

    static int maxIdx = 0;
    public static int lastOcc(int arr[], int i, int k){
        if(i == arr.length){
            return -1;
        }
        if(arr[i] == k){
           maxIdx = i;
        }
        
        return lastOcc(arr,i+1,k);


    }

    public static int powOf(int x, int n){
        if(n == 0){
            return 1;
        }
        int hp = powOf(x, n/2);

        if(n%2 == 0){
            return hp*hp;
        }
        else{
            return hp*hp*x;
        }


    }

    static int count = 1,count1 = 1;
    public static int tp(int n){
        if(n == 0 || n == 1){
            return 1;
        }
        int v = tp(n-1);
        int h = tp(n-2);
        System.out.println(v);
        System.out.println(h);
        System.out.println(v+h);
        return v+h;
    }

    public static double myPow(double x, int n) {
        if(n < 0){
            n = Math.abs(n);
            return myPow(1/x, n);
        }
        if(n == 0){
            return 1;
        }
        double hp = myPow(x,n/2);
        if(n%2 == 0){
            return hp*hp;
        }
        else{
            return hp*hp*x;
        }
    }


    public static void generateBinaryStrings(int n, String str, int lastplace) {
        if(n == 0){
            System.out.println(str);
            return;
        }
        
        if(lastplace == 0){
            generateBinaryStrings(n - 1, str+"0", 0);
            generateBinaryStrings(n - 1, str+"1", 1);
        }
        else{
            generateBinaryStrings(n-1, str+"0", 0);
        }  
    }




    public static void mS(int[] arr, int si, int ei){
        int mid = (si+ei)/2;
        mSHelper(arr,si,mid,ei);
    }
    public static void mSHelper(int arr[], int si, int mid, int ei){
        int i = si;
        int j = mid+1;
        int k = 0;
        int[] temp = new int[ei-si+1];
        while(i <= mid && j <= ei){
            if(arr[i] < arr[j]){
                temp[k] = arr[i];
                i++;
            }
            else{
                temp[k] = arr[j];
                j++;
            }
            k++;
        }

        printArr(temp);
    }


    public static void compress(char[] chars) {
        // brute force
        String s = "";
        int count = 1;
        int jump = 0;
        for(int i = 0; i < chars.length; i+= jump){
            char curr = chars[i];
            s += curr;
            for(int j = i+1; j < chars.length; j++){
                if(chars[j] != curr){
                    s += count;
                    jump = count;
                    count = 1;
                    break;
                }
                else{
                    count++;
                }
            }
            i = i + jump;
            jump = 0;
        }
        System.out.println(s);
        
        
        /*String str = "";
        int i = 0;
        int j = 1;
        int count = 1;
        while(i < chars.length && j < chars.length){
            if(chars[i] == chars[j]){
                count++;
                j++;
            }
            else{
                str += chars[i];
                if(count != 1){
                    str += count;
                }
                count = 1;
                i = j;
                j++;
            }
        }
        str += chars[i];
        if(count != 1){
            str += count;
        }
        System.out.println(str);*/
        
    }

    
    public static void findSubsets(String str, String ans, int i){
        if(i == str.length()){
            System.out.println(ans);
            return;
        }

        findSubsets(str, ans + str.charAt(i), i+1);
        findSubsets(str, ans, i+1);

    }



    public static void main(String args[]){
        String str = "abc";
        String ans = "";
        findSubsets(str,ans,0);
        
    }
}