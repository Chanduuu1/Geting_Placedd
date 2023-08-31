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

    public static void main(String args[]){
        Heap h = new Heap();
        h.add(3);
        h.add(4);
        h.add(1);
        h.add(5);
        h.add(2);

        while(!h.isEmpty()){
            System.out.println(h.peek());
            h.remove();
            
        }
    }
}