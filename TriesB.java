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



    public static void main(String[] args){
        String words[] = {"the", "a", "there", "their", "any", "thee"};
        for(int i = 0; i < words.length; i++){
            insert(words[i]);
        }
    }
}