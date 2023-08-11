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
    public int sizeOfLL = 0;
    public void addFirst(int data){
        Node newNode = new Node(data);
        if(head == null){
            head = tail = newNode;
            sizeOfLL++;
            return;
        } else{
            newNode.next = head;
            head = newNode;
            sizeOfLL++;
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
            sizeOfLL++;
            return;
        } else{
            tail.next = newNode;
            tail = newNode;
            sizeOfLL++;
            return; 
        }
    }

    public void addAtIdx(int data, int idx){
        if(head == null){
            addFirst(data);
        } 
        else {
            int idxAt = 0;
            Node newNode = new Node(data);
            Node temp = head;
            while(idxAt < idx-1){
                temp = temp.next;
                idxAt++;
            }
            if(idxAt == sizeOfLL - 1){
                addLast(data);
            }
            else{
                //temp => idx - 1
                newNode.next = temp.next;
                temp.next = newNode;
                sizeOfLL++;
            }
        }
    }

    public void removeFirst(){
        if(head == null){
            // do nothing
            System.out.println("The LL is already empty");
        }
        else{
            head = head.next;
            return;
        }
    }

    public void removeLast(){
        Node temp;
        if(head == null){
            System.out.println("the ll is empty already");
            
        } else{
            temp = head;
        while(temp.next.next != null){
            temp = temp.next;
        }
        // temp => penultimate node
        tail = temp;
        temp.next = null;
        }
    }
}

public class LinkedList{
    public static void main(String[] args){
        LinkedListCreator ll1 = new LinkedListCreator();
        LinkedListCreator ll2 = new LinkedListCreator();
        ll1.addFirst(1);
        ll1.addLast(2);
        ll1.addLast(3);
        ll1.addLast(5);
        ll1.addLast(6);
        ll1.addLast(7);
        ll1.printLL();
        ll1.addAtIdx(4,3);
        System.out.println("Tail of ll1 is before appending at last "+ll1.tail);
        ll1.addAtIdx(8,7);
        System.out.println("Tail of ll1 is after appending at last "+ll1.tail);
        // hence it is important to update the tail variable in case of add at index function, so better when you are appending at last index make a call to addLast from the add at index function.It will take care of tail function.
        ll1.printLL();
        System.out.println(ll1.sizeOfLL);
        System.out.println(ll2.sizeOfLL);
        ll1.printLL();
        ll1.removeFirst();
        ll1.printLL();
        ll2.removeLast();
        ll1.printLL();

    }
}