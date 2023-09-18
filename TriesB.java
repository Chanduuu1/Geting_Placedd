public class TriesB{

    static class Node{
        Node children[] = new Node[26];
        boolean eow = false; // by default rakhte esa.

        Node(){
            for(int i = 0; i < 26; i++){
                children[i] = null; //by default storring all address as null in the array later unme as per needed address store
            }
        }
    }
    public static Node root = new Node(); // root node create kar diya


    public static void insert(String word){ //O(L) length of word
        Node curr = root;
        for(int lvl = 0; lvl < word.length(); lvl++){
            int idx = word.charAt(lvl) - 'a';
            if(curr.children[idx] == null){
                curr.children[idx] = new Node();
            }
            curr = curr.children[idx];
        }

        // after reaching end of word
        curr.eow = true;
    }

    public static boolean search(String key){ // O(L)
        Node curr = root;
        for(int lvl = 0; lvl < key.length(); lvl++){
            int idx = key.charAt(lvl) - 'a';
            if(curr.children[idx] == null){
                // this means ch doeasnt exists
                return false;
            }
            curr = curr.children[idx];
        }
        
        return curr.eow == true; // if its trur it will ret true else...

    }


    public static boolean wordBreak(String key){
        if(key.length() == 0){
            return true;
        }
        
        for(int i = 1; i<= key.length(); i++){
            if(search(key.substring(0,i)) && wordBreak(key.substring(i))){
                return true;
            }
        }

        return false;
    }

    // for prefix Problem

    public static class Node2{
        Node2 children[] = new Node2[26];
        boolean eow = false;
        int freq;

        public Node2(){
            for(int i = 0; i < 26; i++){
                children[i] = null;
            }
            freq = 1;
        }
    }
    public static Node2 root2 = new Node2();

    public static void insert2(String word){
        Node2 curr = root2;
        for(int lvl = 0; lvl < word.length(); lvl++){
            int idx = word.charAt(lvl) - 'a';
            if(curr.children[idx] == null){
                curr.children[idx] = new Node2();
            }
            else{ // this is the new thing in town
                curr.children[idx].freq++;
            }
            curr = curr.children[idx];
        }

        curr.eow = true; // just following the tradition yaha koi use nahi hai eow ka iss sawal me
    }

    public static void findPrefix(Node2 root, String ans){
        if(root == null){
            return;
        }
        if(root.freq == 1){
            System.out.println(ans);
            return;
        }
        for(int i = 0; i < root.children.length ; i++){  /*=26 thoda style mar raha bass */
            if(root.children[i] != null){
                findPrefix(root.children[i], ans + (char)(i+'a')); // the 2nd argument i+a is converting the int value of idx to char and appendinding to ans
            }
        }
    }


    public static void main(String[] args){
        String words[] = {"zebra", "dog", "duck", "dove"};
        for(int i = 0; i < words.length; i++){
            insert2(words[i]);
        }
        root2.freq = -1;
        findPrefix(root2, "");    

    }
}

