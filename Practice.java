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




    public static void main(String args[]){
        /*Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int arr[] = new int[N];
        for(int i = 0; i < N; i++){
            arr[i] = sc.nextInt();
        }
        BinSearchRec(arr, 34, 0, N-1);
        */
        int arr[] = {4,2,0,6,3,2,5,7};
        trappingRain(arr);


    }
}