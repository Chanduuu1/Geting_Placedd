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
}

public class BinaryTreesB extends BinaryTreess{  
    public static void main(String args[]){
        int nodes[] = {1,2,4,-1,-1,5,-1,-1,3,-1,6,-1,-1};
        BinaryTreess bT1 = new BinaryTreess();
        Node root = bT1.buildTree(nodes);
        bT1.preorderTra(root);
        System.out.println();
        bT1.inorderTra(root);
        System.out.println();
        bT1.postorderTra(root);
        System.out.println();
        bT1.lvlOrderTra(root);
        
    }
    
}