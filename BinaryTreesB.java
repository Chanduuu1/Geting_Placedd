import java.util.*;
class BinaryTreess{
    // unless and until the main class is not static you need not worry!!
    public static class Node{
        int data;
        Node left;
        Node right;
        Node(int data){
            this.data = data;
            left = null;
            right = null;
        }
    }

    static int idx = -1;
    public static Node buildTree(int nodes[]){
        idx++;
        if(nodes[idx] == -1){
            return null; // you could also have created a node and made it null but why to do that?
        }
        Node newNode = new Node(nodes[idx]);
        newNode.left = buildTree(nodes);
        newNode.right = buildTree(nodes);

        return newNode; // dhyaan se soch this newNode is the root node of the tree
    }

    public static void preorderTra(Node root){
        if(root == null){
            return;
        }
        System.out.print(root.data + "->");
        preorderTra(root.left);
        preorderTra(root.right);
    }

    public static void inorderTra(Node root){
        if(root == null){
            return;
        }
        inorderTra(root.left);
        System.out.print(root.data + "->");
        inorderTra(root.right);
    }


    public static void postorderTra(Node root){
        if(root == null){
            return;
        }
        postorderTra(root.left);
        postorderTra(root.right);
        System.out.print(root.data + "->");
    }

    public static void lvlOrderTra(Node root){
        Queue<Node> q = new LinkedList<>();
        q.add(root);
        q.add(null);
        while(!q.isEmpty()){
            Node currNode = q.remove();
            if(currNode == null){
                System.out.println();
                if(q.isEmpty()){
                    break;
                }
                else{
                    q.add(null);
                }
            }
            else{
                System.out.print(currNode.data + "->");
                if(currNode.left != null){
                    q.add(currNode.left);
                }
                if(currNode.right != null){
                    q.add(currNode.right);
                }
            }
            

            
        }
    }

    public static int heightOfTree(Node root){
        Node currNode = root;
        if(root == null){
            return 0;
        }
        int hL = heightOfTree(currNode.left);
        int hR = heightOfTree(currNode.right);

        int ht = (Math.max(hL,hR) + 1);

        return ht;
    }

    public static int countNodes(Node root){
        if(root == null){
            return 0;
        }

        int lC = countNodes(root.left);
        int rC = countNodes(root.right);

        return (lC + rC + 1);
    }

    public static int sumOfNodes(Node root){
        if(root == null){
            return 0;
        }

        int lS = sumOfNodes(root.left);
        int rS = sumOfNodes(root.right);

        int sum = lS + rS + root.data;
        
        
        return sum; 
    }



    public static class Info{ // isme store karunga node aur uska hd
        Node node;
        int hd;
        public Info(Node node,int hd){
            this.node = node;
            this.hd = hd;
        }
    }
    public static void topView(Node root){
        // level order traversal
        Queue<Info> q = new LinkedList<>();  // hum lvl order traversal, but instead of storing node, we will be storing 
                                             // "Info" which contains node ka sab kuch + additionally hd of that node
        
        HashMap<Integer, Node> map = new HashMap<>();  // hash table me store karenge hd aur uss hd ka node
        q.add(new Info(root,0));  
        q.add(null);
        
        int max = 0, min = 0; // this is just to store range kaha se kaha tak
        
        while(!q.isEmpty()){
            Info currInfo = q.remove();
            if(currInfo == null){
                if(q.isEmpty()){
                    break; // q khaali
                }
                else{
                    q.add(null); 
                }
            }
            else{
                if(!map.containsKey(currInfo.hd)){ // check kar hash map me yeh hd hai ke nahi
                    map.put(currInfo.hd,currInfo.node);
                }

                if(currInfo.node.left != null){     // basic, level order tra step , bass we are adding info instead of data
                    q.add(new Info(currInfo.node.left, currInfo.hd - 1));
                    min = Math.min(min, currInfo.hd-1);
                }

                if(currInfo.node.right != null){
                    q.add(new Info(currInfo.node.right, currInfo.hd+1));
                    max = Math.max(max, currInfo.hd+1);
                }
            }
        }
        // for prinntint the top view
            for(int i = min; i <= max; i++){
                System.out.print(map.get(i).data+" ");
            }
            System.out.println();
    }
}



public class BinaryTreesB extends BinaryTreess{  
    public static void main(String args[]){
        int nodes[] = {1,2,4,-1,-1,5,-1,-1,3,6,-1,-1,7,-1,-1};
        BinaryTreess bT1 = new BinaryTreess();
        Node root1 = bT1.buildTree(nodes);
        
        /* 
                   0
                 /   \
                1     2
              /  \   / \
             3   4  5   6
        
         */

        BinaryTreess bT2 = new BinaryTreess();
        Node root = new Node(0);
        root.left = new Node(1);
        root.right = new Node(2);
        root.left.left = new Node(3);
        root.left.right = new Node(4);
        root.right.left = new Node(5);
        root.right.right = new Node(6);
        lvlOrderTra(root);
        topView(root);
    }
    
}