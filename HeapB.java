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
            }
        }

        public int peek(){
            return aL.get(0);
        }
    }

    public static void main(String args[]){

    }
}