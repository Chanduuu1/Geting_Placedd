class BinaryTreess{
    // unless and until the main class is not static you need not worry!!
    static class Node{
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
}

public class BinaryTreesB{  

    public static void main(String args[]){
        int nodes[] = {1,2,4,-1,-1,5,-1,-1,3,-1,6,-1,-1};
        BinaryTreess bT1 = new BinaryTreess();
        bT1.preorderTra(bT1.buildTree(nodes));
        
    }
    
}