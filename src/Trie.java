import java.util.ArrayList;

public class Trie {
    TrieNode root;
    public Trie() {
        root = new TrieNode();
    }

    public void insert (String word) {
        TrieNode currentNode = root;
        for(int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if(!currentNode.children.containsKey(c)) {
                currentNode.children.put(c, new TrieNode());
            }
            currentNode = currentNode.children.get(c);
        }
        currentNode.children.put('*', null);
    }

    public TrieNode search (String word) {
        TrieNode currentNode = root;
        for(int i = 0; i < word.length(); i ++){
            char c = word.charAt(i);
            if(!currentNode.children.containsKey(c)) {
                return null;
            }
            currentNode = currentNode.children.get(c);
        }
        return currentNode;
    }

    public void collectAllWords (TrieNode node, String word, ArrayList<String> words) {
        if(node == null) {
            return;
        }

        if(node.children.containsKey('*')) {
            words.add(word);
        }
        for(char c : node.children.keySet()) {
            if(c != '*') {
                collectAllWords(node.children.get(c), word + c, words);
            }
        }
    }

    public ArrayList<String> autoComplete (String prefix) {
        TrieNode currentNode = search(prefix);
        if(currentNode == null)
            return null;

        ArrayList<String> words = new ArrayList<>();
        collectAllWords(currentNode, prefix, words);
        return words;
    }
}

