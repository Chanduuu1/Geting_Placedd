import java.util.*;

public class BST{



    static class Node{
        int data;
        Node left;
        Node right;

        Node(int data){
            this.data = data;
        }
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
    public static Node inertIntoBST(Node root, int val){
        if(root == null){
            // the first value comes in
            root = new Node(val);
            return root;
        }

        if(val > root.data){
            root.right = inertIntoBST(root.right,val); // yeh root.right and root.left ka significance hai! issi waja se end me real root of tree return kar paenge
        }
        if(val < root.data){
            root.left = inertIntoBST(root.left,val);
        }

        return root;

    }
    public static void inorder(Node root){
        if(root == null){
            return;
        }
        inorder(root.left);
        System.out.print(root.data + " ");
        inorder(root.right);
    }

    public static boolean search(Node root,int key){
        if(root == null){
            return false;
        }

        if(root.data == key){
            return true;
        }

        if(key > root.data){
            return search(root.right, key);
        }else{
            return search(root.left, key);
        }

    }

    public static void printInRage(Node root, int k1, int k2){
        if(root == null){
            return;
        }

        // agar k1 <= rootval <= k2
        if(k1 <= root.data && root.data <= k2){
            printInRage(root.left,k1,k2);
            System.out.print(root.data + " ");
            printInRage(root.right,k1,k2);
        }else if(root.data < k1){
            printInRage(root.right,k1,k2);
        }else if(root.data > k2){
            printInRage(root.left,k1,k2);
        }



    }


    public static void printPath(ArrayList<Integer> path){
        for(int i = 0; i < path.size(); i++){
            System.out.print(path.get(i) + "->");
        }
        System.out.println();
    }
    public static void rootToLeaf(Node root, ArrayList<Integer> path){
        if(root == null){
            return;
        }

        path.add(root.data);

        if(root.left == null  && root.right == null){
            // print path and return
            printPath(path);
        }

        
        rootToLeaf(root.left,path);
        rootToLeaf(root.right,path);
        path.remove(path.size()-1);
    }


    public static void main(String[] args){
        int[] val = {8,5,3,1,4,6,10,11,14};
        Node root = null;
        for(int i = 0; i < val.length; i++){
            root = inertIntoBST(root,val[i]);
        }

        //lvlOrderTra(root);
        ArrayList<Integer> path = new ArrayList<>();
        rootToLeaf(root,path);
        
        
    }
}