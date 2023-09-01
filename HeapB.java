import java.util.*;
public class HeapB{

    static class Heap{
        ArrayList<Integer> aL = new ArrayList<>();

        public void add(int data){
            // adding the data at last pos O(1)
            aL.add(data);

            int x = aL.size()-1; // index of the element just entered
            int par = (x-1)/2; // index of the parent

            while(aL.get(x) < aL.get(par)){
                // we need to swap
                int temp = aL.get(x);
                aL.set(x, aL.get(par));
                aL.set(par, temp);

                // upadtation
                x = par;
                par = (x-1)/2;
            }
        }

        public int peek(){
            return aL.get(0);
        }

        private void heapify(int i){
            // i is the idx of root
            int left = 2*i+1;
            int right = 2*i+2;
            int minInd = i;  // since its a min heap, we asssume that the top most ele the root is the minimum. nahi ho to dekh lenge bhai tnns not

            if(left < aL.size() /* try to find children of  leaf node */ && aL.get(minInd) > aL.get(left)){
                minInd = left;
            }

            if(right < aL.size() && aL.get(minInd) > aL.get(right)){
                minInd = right;
            }

            // by the above two if stmts I ensured ki top me min ele hi rahe
            if(minInd == i){
                // mtlb jo pehle minInd humne assume kia tha ki i hai abh bhi wahi hai! 
                // so tree is already min heap
                // full set no problem what so ever
            }

            else if(minInd != i){
                // swap krna padega ith index ko min index ke value se
                int temp = aL.get(i);
                aL.set(i,aL.get(minInd)); // abh i pr min element hi hai swap kardia
                aL.set(minInd, temp); // abh minInd pr min value nahi, prev i ki valie h

                heapify(minInd); // wapas abh is minInd ke sath dekh aage ke lie

            }
        }

        public int remove(){ //O(log n)
            int data = aL.get(0);
            //step 1 - swapping 1st and last
            int temp = aL.get(0);
            aL.set(0,aL.get(aL.size()-1));
            aL.set(aL.size()-1, temp);

            //step 2 - delete last
            aL.remove(aL.size()-1);

            //step 3 - heapify
            heapify(0);

            return data;
        }

        public boolean isEmpty(){
            return aL.size() == 0;
        }
    }



    public static void heapSort(int arr[]){
        // step 1 - build max Heap
        int n = arr.length;
        for(int i = n/2 ; i >= 0; i--){
            maxHeapify(arr,i,n); // pushing ke time size ka zarurat padega to abhi se hi daal raha hu, although yaha n ka mahotwa nahi hai

        }


        //step 2 - push largest at end
        for(int i = n-1; i > 0; i--){
            //swap largest to last 
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;

            maxHeapify(arr,0,i); // (0,i) 0 - yeh pass kanre se wapas pura maxHeap banega | i - last element ignore marenge iss se, notes ka dry run dekh samjga

        }

    }
    public static void maxHeapify(int arr[], int i, int size){
        int left = 2*i+1;
        int right = 2*i+2;
        int maxIdx = i;

        if(left < size && arr[maxIdx] < arr[left]){
            maxIdx = left;
        }
        if(right < size && arr[maxIdx] < arr[right]){
            maxIdx = right;
        }

        if(maxIdx != i){
            // iss if ka mtlb hai ki filal i jo max ele hoga samja tha unke children se, woh nahi hai so swap
            int temp = arr[i];
            arr[i] = arr[maxIdx];
            arr[maxIdx] = temp;

            maxHeapify(arr,maxIdx,size);    // ye sirf heapify ka logic hai pichle q ne clear hogaya hoga
                                            // yaha masala sirf heapSort function ke code m h
        }
    }



    static class Points implements Comparable<Points>{
        int x;
        int y;
        int distSq;
        int idx;

        public Points(int x,int y,int distSq,int idx){
            this.x = x;
            this.y = y;
            this.distSq = distSq;
            this.idx = idx;
        }

        @Override
        public int compareTo(Points p2){
            return this.distSq - p2.distSq; // +ve mtlb this bada, -ve mtlb this chota ...... bada integer mtlb less priority
        }


    }

    public static void main(String args[]){
        int pts[][] = {{3,3}, {5,-1}, {-2,4}};
        int k = 2;

        PriorityQueue<Points> p = new PriorityQueue<>();
        for(int i = 0; i < pts.length; i++){
            int distSq = pts[i][0]*pts[i][0] + pts[i][1]*pts[i][1];
            p.add(new Points(pts[i][0],pts[i][1],distSq,i));
        }

        //nearest k restraunts
        for(int i = 0; i < k; i++){
            System.out.println("Restraunt"+ p.remove().idx);
            
        }
    }
}