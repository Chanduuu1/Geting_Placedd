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






// lasrget BST Q
    static class Info{
        boolean isBST;
        int size;
        int min;
        int max;
        public Info(boolean isBST, int size, int min, int max){
            this.isBST = isBST;
            this.size = size;
            this.min = min;
            this.max = max;
        }
        
    }
    public static int maxSize = 0;
    public static Info largestBST(Node root){
        if(root == null){
            return new Info(true, 0, Integer.MAX_VALUE, Integer.MIN_VALUE);
        }


        Info leftInfo = largestBST(root.left);
        Info rightInfo = largestBST(root.right);

        int size = leftInfo.size + rightInfo.size + 1;
        int min = Math.min(root.data, Math.min(leftInfo.min, rightInfo.min));
        int max = Math.max(root.data, Math.max(leftInfo.max, rightInfo.max));

        // now cecking isValid BST
        if(root.data <= leftInfo.max || root.data >= rightInfo.min){
            return new Info(false,size,min,max);
        }

        if(leftInfo.isBST && rightInfo.isBST){
            maxSize = Math.max(maxSize,size);
            return new Info(true,size,min,max);
        }

        
        return new Info(false,size,min,max); 
    }






    
    
    public static void main(String[] args){
        /*
                        50                 expected largest BST size = 5        
                      /    \
                    30     60                 60                 
                   /  \   /   \              /   \
                  5   20  45   70           45   70
                              /   \             /   \
                             65   80           65   80 
                       
               
         */
        Node root = new Node(50);
        root.right = new Node(60);
        root.right.right = new Node(70);
        root.right.right.right = new Node(80);
        root.left = new Node(30);
        root.left.left = new Node(5);
        root.left.right = new Node(20);
        root.right.left = new Node(45);
        root.right.right.left = new Node(65);

        System.out.println(maxSize);
        Info information = largestBST(root);
        System.out.println(maxSize);
        
    
        
        
    }
}