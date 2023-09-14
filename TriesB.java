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

    public static void main(String[] args){
        String words[] = {"i","like","sam","samsung","mobile","ice"};
        for(int i = 0; i < words.length; i++){
            insert(words[i]);
        }

        String key = "ilikesamung";
        System.out.println(wordBreak(key));
        

        
        
    }
}