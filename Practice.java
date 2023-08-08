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
    public static void main(String args[]){
        /*Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int arr[] = new int[N];
        for(int i = 0; i < N; i++){
            arr[i] = sc.nextInt();
        }*/
        //BinSearchRec(arr, 34, 0, N-1);
        int arr[] = {1,2,3,4,5};
        allSubarr(arr);
    }
}