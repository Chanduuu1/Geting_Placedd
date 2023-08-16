import java.util.*;
class QueueUsingArr{
        int arr[];
        int size;
        int rear;

        // constructor - jaise hi me Queue ka obj banaunga turant ek array ban jaegi size n ki;
        QueueUsingArr(int n){
            arr = new int[n];
            size = n;
            rear = -1;
        }


        //isEmpty function
        public boolean isEmpty(){
            return rear == -1;
        }

        //add function
        public void add(int val){
            if(rear == size - 1){
                System.out.println("stack is full");
                return;
            }
            rear += 1;
            arr[rear] = val;
        }

        //remove function
        public int remove(){
            if(isEmpty()){
                System.out.println("notheng can be removed stack empty");
                return -1;
            }
            int front = arr[0];
            for(int i = 1; i < size; i++){
                arr[i-1] = arr[i];
            }
            rear -= 1;
            return front;
            
        }

        //peek function
        public int peek(){
            if(isEmpty()){
                System.out.println("notheng can be removed stack empty");
                return -1;
            }
            return arr[0];
        }

}

class CirQueueUsingArr{
        int arr[];
        int size;
        int rear;
        int front;

        // constructor - jaise hi me Queue ka obj banaunga turant ek array ban jaegi size n ki;
        CirQueueUsingArr(int n){
            arr = new int[n];
            size = n;
            rear = -1;
            front = -1;
        }


        //isEmpty function
        public boolean isEmpty(){
            return rear == -1 && front == -1;
        }

        public boolean isFull(){
            return (rear+1) % size == front;
        }

        //add function
        public void add(int val){
            if(isFull()){
                System.out.println("stack is full");
                return;
            }

            //while adding first ele
            if(front == -1){
                front = 0;
            }
            rear = (rear+1) % size;
            arr[rear] = val;
        }

        //remove function
        public int remove(){
            if(isEmpty()){
                System.out.println("notheng can be removed stack empty");
                return -1;
            }
            int result = arr[front];
            front = (front+1) % size;
            
            //while removing last ele
            if(rear == front){
                rear = front = -1;
            } else{
                front = (front+1) % size;
            }
            return result;
            
        }

        //peek function
        public int peek(){
            if(isEmpty()){
                System.out.println("notheng can be removed stack empty");
                return -1;
            }
            return arr[front];
        }

}

class QueueUsingLL{
    class Node{
        int data;
        Node next;
        Node(int data){
            this.data = data;
            this.next = null;
        }
    }
    Node head;
    Node tail;

    // is empty funx
    public boolean isEmpty(){
        return head == null;
    }
    // add function
    public void add(int data){
        Node newNode = new Node(data);
        if(head == null){
            head = tail = newNode;
            return;
        }
        tail.next = newNode;
        tail = newNode;
    }

    // remove function
    public int remove(){
        if(head == null){
            return -1;
        }
        int temp = head.data;

        // if single el in LL
        if(head == tail){
            head = tail = null;
        } else{
            head = head.next;
        }
        
        return temp;
    }

    public int peek(){
            if(head == null){
                System.out.println("notheng can be removed stack empty");
                return -1;
            }
            return head.data;
        }

}

class QueueUsing2Stk{
    // here we are using O(n) for poping and peeking, adding with O(n) written in notes
    Stack<Integer> s1 = new Stack<>();
    Stack<Integer> s2 = new Stack<>();


    //isempty function for queeue
    public boolean isEmpty(){
        return s1.isEmpty();
    }

    // add fucntion for queue - O(1)
    public void add(int data){
        s1.push(data);
    }

    // remove operation - O(n)
    public int remove(){
        while(!s1.isEmpty()){
            s2.push(s1.pop());
        }

        // now poping from s2
        int val = s2.pop();

        while(!s2.isEmpty()){
            s1.push(s2.pop());
        }
        return val;

    }

    // peek function - O(n)
    public int peek(){
        while(!s1.isEmpty()){
            s2.push(s1.pop());
        }

        // now poping from s2
        int val = s2.peek();

        while(!s2.isEmpty()){
            s1.push(s2.pop());
        }
        return val;
    }

}

public class QueueB{
    

    public static void main(String args[]){
        QueueUsing2Stk q = new QueueUsing2Stk();
        q.add(1);
        q.add(2);
        q.add(3);
        while(!q.isEmpty()){
            System.out.println(q.peek());
            System.out.println(q.remove());
            
        }

    }
}