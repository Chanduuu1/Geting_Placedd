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

    public static void main(String args[]){
        /*
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int arr[] = new int[N];
        for(int i = 0; i < N; i++){
            arr[i] = sc.nextInt();
        }
        BinSearchRec(arr, 34, 0, N-1);
        int arr[] = {4,2,0,6,3,2,5,7};
        trappingRain(arr);
        int[][] mat = {{10,20,30,40},{15,25,35,45},{27,29,27,48},{32,33,39,50}};
        printArr2D(mat);
        stairCaseSearch(mat,36);
       strCompress("aaabbcccddefffffgh");
       */
        int arr[] = {0,0,1,1,1,2,2,3,3,4};
        System.out.println(removeDuplicates(arr));
        printArr(arr);


    }
}