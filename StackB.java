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


    public static boolean open(char ch){
        switch(ch){
            case '(' : return true;
            case '[' : return true;
            case '{' : return true;
            default : return false;        
        }
    }
    public static boolean close(char ch){
        switch(ch){
            case ')' : return true;
            case ']' : return true;
            case '}' : return true;
            default : return false;        
        }
    }
    public static char conjugateOf(char ch){
        switch(ch){
            case '(' : return ')';
            case '[' : return ']';
            case '{' : return '}';
            default : return '-';        
        }
    }
    public static boolean validParentheses(String str){
        Stack<Character> s = new Stack<>();
        for(int i = 0; i < str.length(); i++){
            char ch = str.charAt(i);
            if(s.isEmpty() && close(ch)){
                return false;
            }
            if(open(ch)){
                s.push(ch);
            }
            if(close(ch)){
                if( ch == conjugateOf(s.peek()) ) {
                    s.pop();
                }
                else{
                    return false;
                }
            }
        }
        if(s.isEmpty()){
            return true;    
        }
        return false;
    }


    public static boolean dupliParentheses(String str){
        Stack<Character> s = new Stack<>();
        for(int i = 0; i < str.length(); i++){
            char ch = str.charAt(i);
            // check if it is a closing char
            if(ch == ')'){
                int count = 0;
                while(s.peek() != '('){
                    count++;
                    s.pop();
                }
                if(count < 1){
                    return true;
                }
                s.pop();
            }
            else{
                s.push(ch);
            }
        }
        return false;
    }


    public static void nextGreaterEle(int[] arr){
        Stack<Integer> s = new Stack<>();
        int maxa[] = new int[arr.length];

        int nextGreater = 0;
        for(int i = arr.length - 1; i >= 0; i--){
            if(s.isEmpty()){
                nextGreater = -1; 
                s.push(arr[i]);   
            }

            else if(!s.isEmpty() && arr[i] < s.peek()){
                nextGreater = s.peek();
                s.push(arr[i]);
            }

            else if(!s.isEmpty() && arr[i] > s.peek()){
                while(!s.isEmpty() && arr[i] > s.peek()){
                    s.pop();
                }
                if(s.isEmpty()){
                    nextGreater = -1;
                    s.push(arr[i]); 
                }else{
                    nextGreater = s.peek();
                    s.push(arr[i]);
                }
            }
            
            maxa[i] = nextGreater;
        }
        printarr(maxa);
    }


    public static int maxInArr(int[] arr){
        int max = 0;
        for(int i = 0; i < arr.length; i++){
            if(arr[i] > max){
                max = arr[i];
            }
        }
        return max;
    }
    public static int maxAreaHistogram(int[] arr){
        Stack<Integer> s = new Stack<>();
        int nsr[] = new int[arr.length];
        int nsl[] = new int[arr.length];
        int areaArr[] = new int[arr.length];
        
        // next smaller right
        int nextSmaller = 0;
        for(int i = arr.length - 1; i >= 0; i--){
            if(s.isEmpty()){
                nextSmaller = -1; 
                s.push(i);   
            }

            else if(!s.isEmpty() && arr[i] > arr[s.peek()]){
                nextSmaller = s.peek();
                s.push(i);
            }
            else{
                while(!s.isEmpty() && arr[i] < arr[s.peek()]){
                    s.pop();
                }
                if(s.isEmpty()){
                    nextSmaller = -1;
                    s.push(i); 
                }else{
                    nextSmaller = s.peek();
                    s.push(i);
                }
            }
            
            nsr[i] = nextSmaller;
        }

        // next smaller left
        nextSmaller = 0; 
        s = new Stack<>();      
        for(int i = 0; i < arr.length; i++){
            if(s.isEmpty()){
                nextSmaller = -1; 
                s.push(i);   
            }

            else if(!s.isEmpty() && arr[i] > arr[s.peek()]){
                nextSmaller = s.peek();
                s.push(i);
            }
            else{
                while(!s.isEmpty() && arr[i] < arr[s.peek()]){
                    s.pop();
                }
                if(s.isEmpty()){
                    nextSmaller = -1;
                    s.push(i); 
                }else{
                    nextSmaller = s.peek();
                    s.push(i);
                }
            }
            
            nsl[i] = nextSmaller;
        }

        // calculating area
        int width = 0, height = 0,area = 0;
        for(int i = 0; i < arr.length; i++){
            width = nsr[i] = nsl[i] - 1;
            height = arr[i];
            area = height * width;
            areaArr[i] = area;
        }
        return maxInArr(areaArr);
        
    }

    public static void main(String[] args){
        int arr[] =  {2,1,5,6,2,3};
        System.out.println(maxAreaHistogram(arr));
        
    }
}
