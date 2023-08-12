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

    public int iterSearch(int val){
        Node temp = head;
        int idx = 0;
        while(temp != null){
            if(temp.data == val){
                return idx;
            }
            else{
                temp = temp.next;
                idx++;
            }
        } 
        return -1;  

    }

    public int helperForRecSrch(Node tempHead , int val){
        int idx;
        if(tempHead == null){
            return -1;
        }
        if(tempHead.data == val){
            idx = 0;
            return idx;
        }
        idx = helperForRecSrch(tempHead.next, val);
        idx++;
        return idx;
    }
    public int recSearch(int val){
        return helperForRecSrch(this.head,val);
    }

    public void reverseLL(){
        Node prev = null;
        Node curr = this.head;
        Node next;  
        while(curr != null){
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        head = prev;
    }

    public void removeNthFromEnd(int idx){
        // nth index from end is sz - n + 1 from begining;
        int sz = 0;
        int idxAt = 0;
        Node temp = head;
        while(temp != null){
            temp = temp.next;
            sz++;
        }
        temp = head;
        // traverse till idx sz-n-1 idx and change its next to next's next
        while(idxAt < sz-idx-1){
            temp = temp.next;
            idx++;
        }

        temp.next = temp.next.next;
        return;
    }

    public Node findMid(){
        Node slow = this.head;
        Node fast = this.head;
        while(fast != null && fast.next != null){
            slow = slow.next; //+1
            fast = fast.next.next; //+2
        }
        return slow; // slow is midNode
    } 
    public boolean palindromeLlOrNot(){
        if(head == null || head.next == null){
            return true;
        }
        //step1 - find mid;
        Node midNode = this.findMid();

        //step2 - reverse the 2nd half
        Node prev = null;
        Node curr = midNode;
        Node next;  
        while(curr != null){
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        Node right = prev; // right half head dhyaan se soch
        Node left = head;

        //step3 - check left half and right half traversal
        while(right != null){
            if(left.data != right.data){
                return false;
            }
            left = left.next;
            right = right.next;
        }
        return true;

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
        ll1.addAtIdx(4,3);
        ll1.addAtIdx(8,7);
        ll1.printLL();
        System.out.println("head "+ll1.head);
        System.out.println("The inddex of the value is at "+ll1.recSearch(5));
        System.out.println("head "+ll1.head);
        System.out.println();
        
        ll1.reverseLL();
        ll1.printLL();
        ll1.removeNthFromEnd(3);
        ll1.reverseLL();
        ll1.printLL();
        ll2.addFirst(1);
        ll2.addFirst(2);
        ll2.addFirst(2);
        ll2.addFirst(3);
        ll2.addFirst(2);
        ll2.addFirst(2);
        ll2.addFirst(1);
        System.out.println(ll2.palindromeLlOrNot());
        
        // great success!
        

    }
}