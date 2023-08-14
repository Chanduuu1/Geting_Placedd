import java.util.*;

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
        int peek = list.get(list.size() - 1);
        list.remove(list.size() - 1);
        return peek;  // returns the element that has been just removed
    }

    public int peek(){
        if(isEmpty()){
            return -1;
        }   
        return list.get(list.size() - 1);
    }
}

class stackUsingLL{
    public class Node{
        int data;
        Node next;
        public Node(int data){
            this.data = data;
            this.next = null;
        }
    }
    public Node head;
    public Node tail;

    //push operation using add first
    public boolean isEmpty(){
        return head == null;
    }
    public void push(int data){
        Node newNode = new Node(data);
        if(head == null){
            head = tail = newNode;
            return;
        }
        newNode.next = head;
        head = newNode;
        // right now head is pointing at the peek of the stack
    }

    public int pop(){
        if(head == null){
            System.out.println("the stack is empty");
            return -1;
        }
        int val = head.data;
        head = head.next;
        return val;
    }

    public int peek(){
        if(head == null){
            System.out.println("the stack is empty");
            return -1;
        }
        return head.data;
    }
}

public class StackB{
    public static void pushAtBottom(Stack<Integer> s, int data){
        if(s.isEmpty()){
            s.push(data);
            return;
        }
        int val = s.pop();
        pushAtBottom(s,data);
        s.push(val);
    }

    public static String printReverseString(String str){
        Stack<Character> s = new Stack<>();
        int i = 0;
        while(i < str.length()){
            s.push(str.charAt(i));
            i++;
        }
        StringBuilder result = new StringBuilder("");
        while(!s.isEmpty()){
            char ch = s.pop();
            result.append(ch);
        }

        return result.toString();

    }

    public static void reverseStack(Stack<Integer> s){
        // base case
        if(s.isEmpty()){
            return;
        }
        //main step
        int val = s.pop();
        reverseStack(s);
        pushAtBottom(s,val);
    }

    public static void showStack(Stack<Integer> s){
        while(!s.isEmpty()){
            System.out.println(s.peek());
            s.pop();    
        }
    }

    public static void printarr(int[] arr){
        for(int i = 0; i < arr.length; i++){
            System.out.print(arr[i] + " ");
        }
    }
    public static void stockSpanProb(int[] price){
        Stack<Integer> s = new Stack<>();
        int span[] = new int[price.length];
        span[0] = 1;
        s.push(0);
        for(int i = 1; i < price.length; i++){
            if(price[i] < price[s.peek()]){
                span[i] = i - s.peek();
                s.push(i);
            }
            else if(price[i] >= price[s.peek()]){
                while(!s.isEmpty() && price[i] >= price[s.peek()]){
                    s.pop();
                }
                if(s.isEmpty()){
                    span[i] = i + 1;
                }else{
                    span[i] = i - s.peek();
                    s.push(i);
                }
                
            }

        }
        printarr(span);
        
    }

    public static void main(String[] args){
        int[] price = {100,80,60,70,60,85,100};
        stockSpanProb(price);
         
    }
}
