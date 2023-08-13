import java.util.ArrayList;

class stackUsingAL{
    ArrayList<Integer> list= new ArrayList<>();
    
    public boolean isEmpty(){
        return list.size() == 0;
    }
    
    public void push(int data){
        list.add(data);
    }

    public int pop(){
        if(isEmpty()){
            return -1;
        }
        int top = list.get(list.size() - 1);
        list.remove(list.size() - 1);
        return top;  // returns the element that has been just removed
    }

    public int peek(){
        if(isEmpty()){
            return -1;
        }
        return list.get(list.size() - 1);
    }
}
public class Stack{
    public static void main(String[] args){
        stackUsingAL s1 = new stackUsingAL();
        s1.push(1);
        s1.push(2);
        s1.push(3);
        s1.push(4);
        while(!s1.isEmpty()){
            System.out.println(s1.peek());
            s1.pop();
            
        }

    }
}