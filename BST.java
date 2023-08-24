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
    public static void preorderTra(Node root){
        if(root == null){
            return;
        }
        System.out.print(root.data + "->");
        preorderTra(root.left);
        preorderTra(root.right);
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


    public static boolean isValidBST(Node root, Node min, Node max){
        if(root == null){
            return true;
        }

        if(min != null && root.data <= min.data){
            return false;
        }

        if(max != null && root.data >= max.data){
            return false;
        }

        return isValidBST(root.left,min,root) && 
                    isValidBST(root.right,root,max);
                    // because koi ek bhi false ho gaya then it will return false!
    }


    public static Node mirror(Node root){
        if(root == null){
            return null; // null ka toh koi mirror hota nai hai so uska mirror node also null
        }

        Node mirroredLeftSubTree = mirror(root.left);
        Node mirroredRightSubTree = mirror(root.right);
        root.right = mirroredLeftSubTree;
        root.left = mirroredRightSubTree;

        return root;
    }

    public static Node createBST(int[] arr,int start,int end){
        if(start > end){
            return null;
        }

        int mid = (start+end)/2;
        Node root = new Node(arr[mid]);
        root.left = createBST(arr,start,mid-1);
        root.right = createBST(arr,mid+1,end);

        return root;
    }


    public static Node conertToBBST(Node root){
        // inorder sequence generation
        ArrayList<Integer> inorderAL = new ArrayList<>();
        inorder2(root,inorderAL);

        // Create BBST using this inorder seq.
        root = createBST2(inorderAL,0,inorderAL.size()-1);

        return root;
    }
    public static Node createBST2(ArrayList<Integer> a,int start,int end){
        if(start > end){
            return null;
        }

        int mid = (start+end)/2;
        Node root = new Node(a.get(mid));
        root.left = createBST2(a,start,mid-1);
        root.right = createBST2(a,mid+1,end);

        return root;
    }
    public static void inorder2(Node root,ArrayList<Integer> a){
        if(root == null){
            return;
        }
        inorder2(root.left,a);
        a.add(root.data);
        inorder2(root.right,a);
    }
    
    
    public static void main(String[] args){
        int[] val = {8,5,3,1,4,6,10,11,14,15};
        Node root = null;
        for(int i = 0; i < val.length; i++){
            root = inertIntoBST(root,val[i]);
        }


        /*
                8
               / \
              4   9
             /     \
            3       10
           /          \
          1            15

          to be converted to
                 8
               /   \
              3     10
             / \   /  \
            1  4   9   15

         */

        Node root2 = new Node(8);
        root2.right = new Node(9);
        root2.right.right = new Node(10);
        root2.right.right.right = new Node(15);
        root2.left = new Node(4);
        root2.left.left = new Node(3);
        root2.left.left.left = new Node(1);

        lvlOrderTra(root2);
        conertToBBST(root2);
        lvlOrderTra(root2);
        
        
    }
}