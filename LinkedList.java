class LinkedListCreator{
    public class Node{
        int data;
        Node next; // this will be a reference variable which right now is not pointing anywhere

        // making node constructor
        public Node(int data){
            this.data = data;
            this.next = null;
        }
    }
    public Node head; // head is a reference variable with initial null value with data type as Node.
    public Node tail; // tail is a reference variable with initial null value with data type as Node.
    
    public void addFirst(int data){
        Node newNode = new Node(data);
        if(head == null){
            head = tail = newNode;
            return;
        } else{
            newNode.next = head;
            head = newNode;
            return;
        }  
    }

    public void printLL(){
        Node temp;
        temp = head;
        while(temp != null){
            System.out.print(temp.data + "->");
            temp = temp.next;
        }
        System.out.print("null");
        System.out.println();
    }

    public void addLast(int data){
        Node newNode = new Node(data);
        if(head == null){
            head = tail = newNode;
            return;
        } else{
            tail.next = newNode;
            tail = newNode;
            return; 
        }
    }

}

public class LinkedList{
    public static void main(String[] args){
        LinkedListCreator ll1 = new LinkedListCreator();
        ll1.printLL();
        System.out.println("head of LL1 is" + ll1.head);
        ll1.addFirst(4);
        ll1.printLL();
        System.out.println("head of LL1 is" + ll1.head +" and the value of head is " + ll1.head.data);
        ll1.addFirst(3);
        System.out.println("head of LL1 is" + ll1.head);
        ll1.addFirst(2);
        System.out.println("head of LL1 is" + ll1.head);
        ll1.addFirst(1);
        System.out.println("head of LL1 is" + ll1.head);
        //ll1.printLL();
        ll1.addLast(5);
        System.out.println("head of LL1 is" + ll1.head);

        LinkedListCreator ll2 = new LinkedListCreator();
        ll2.addLast(10);
        System.out.println("tail of LL2 is" + ll2.tail);
        ll2.addLast(9);
        System.out.println("tail of LL2 is" + ll2.tail);
        ll2.addLast(8);
        System.out.println("tail of LL2 is" + ll2.tail);
        ll2.addLast(7);
        System.out.println("tail of LL2 is" + ll2.tail);
        ll2.addLast(6);
        System.out.println("tail of LL2 is" + ll2.tail);
        ll2.addLast(5);
        System.out.println("tail of LL2 is" + ll2.tail);
    }
}