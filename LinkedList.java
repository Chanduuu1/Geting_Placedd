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

    public boolean isCyclic(){
        Node slow = this.head;
        Node fast = this.head;
        while(fast != null && fast.next != null){
            fast = fast.next.next;
            slow = slow.next;
            if(fast == slow){ // cycle exsists
                return true;
            }
        }
        return false;
    }

    public void removeCycle(){
        Node slow = this.head;
        Node fast = this.head;
        Node prev = null;
        boolean cycle = false;
        // step1 - detect cycle;
        while(fast != null && fast.next != null && cycle == false){
            fast = fast.next.next;
            slow = slow.next;
            if(fast == slow){ // cycle exsists
                cycle = true;
                slow = this.head;
            }
        }
        if(cycle == false){
            System.out.println("No cycle bro!");
            
            return;
        }    
        // step2 - find the loop start node;
        while(fast != slow ){ // fast != head ek corner case ho jaega when the cycle is proper circular head is loop start so improvise, usually nahi puchte yeh
            prev= fast;
            fast = fast.next; //+1 ka incriment
            slow = slow.next; //+1 ka incri
        }
        // step3 - set its prev's next to null; if fast == slow reached, it means that loop starting reached so prev.next = null;
        prev.next = null;
    }

    private Node findMidVariation(Node head){
            Node slow = head;
            Node fast = head.next;
            while(fast != null && fast.next != null){
                slow = slow.next; //+1
                fast = fast.next.next; //+2
            }
            return slow; // slow is midNode
    }
    public Node mergerLL(Node head1, Node head2){
        Node mergedLL = new Node(-1);
        Node temp = mergedLL;
        while(head1 != null && head2 != null){
            if(head1.data <= head2.data){
                temp.next = head1;
                head1 = head1.next;
                temp = temp.next;
            }
            else if(head1.data >= head2.data){
                temp.next = head2;
                head2 = head2.next;
                temp = temp.next;
            }
        }
        while(head1 != null){
            temp.next = head1;
            head1 = head1.next;
            temp = temp.next;   
        }

        while(head2 != null){
            temp.next = head2;
            head2 = head2.next;
            temp = temp.next;   
        }

        return mergedLL.next;
        
    }
    public Node mergeSortLL(Node headOfLLtoSort){
        if(headOfLLtoSort == null || headOfLLtoSort.next == null){
            return headOfLLtoSort;
        }

        // step1 finding mid
        Node mid = findMidVariation(headOfLLtoSort);
        // step2 makking left and right head and further diveide them
        Node rightHead = mid.next;
        mid.next = null;
        Node newLeft = mergeSortLL(headOfLLtoSort);
        Node newRight = mergeSortLL(rightHead);
        
        //step3 merge them
        return mergerLL(newLeft,newRight);

    }


    public Node getMid(){
        Node slow = this.head;
        Node fast = this.head.next;
        while(fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }
    public Node zigZagLL(){
        // step1 find mid
        Node mid = getMid();
        // step2 divide the ll from mid and reverse the right side
        Node prev = null;
        Node curr = mid.next;
        mid.next = null;
        Node Next;
        while(curr != null){
            Next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = Next;
        }
        Node leftHead = this.head;
        Node rightHead = prev;

        // step3 merge in zig zag fashion
        Node newRight;
        Node newLeft;
        while(leftHead != null && rightHead != null){
            newLeft = leftHead.next;
            leftHead.next = rightHead;
            newRight = rightHead.next;
            rightHead.next = newLeft;

            rightHead = newRight;
            leftHead = newLeft;
        }
        return head;
    }
    
}



public class LinkedList{
    public static void main(String[] args){
        LinkedListCreator ll1 = new LinkedListCreator();
        ll1.addFirst(1);
        ll1.addLast(2);
        ll1.addLast(3);
        ll1.addLast(4);
        ll1.addLast(5);
        ll1.addLast(6);
        ll1.printLL();
        ll1.head = ll1.zigZagLL();
        ll1.printLL();
        
        
    }
}   