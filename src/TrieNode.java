import java.util.HashMap;

public class TrieNode {
    HashMap<Character, TrieNode> children;

    public TrieNode() {
        children = new HashMap<>();
    }
}
