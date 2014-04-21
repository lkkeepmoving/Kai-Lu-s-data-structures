
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

// TrieNode is like TreeNode in Tree
class TrieNode {
    private char value;
    private HashMap<Character, TrieNode> children;
    private boolean isEnd;
    
    TrieNode(char c) {
        value = c;
        children = new HashMap<Character, TrieNode>();
        isEnd = false;
    }
    
    public HashMap<Character, TrieNode> getChildren() {
        return children;
    }
    
    public char getValue() {
        return value;
    }
    
    public void setIsEnd(boolean val) {
        isEnd = val;
    }
    
    public boolean isEnd() {
        return isEnd;
    }
}

// The Trie data structure
public class Trie {
    private TrieNode root;
    
    // Constructor
    public Trie() {
        root = new TrieNode((char)0);
    }
    
    // Method to insert a new word to Trie
    public void insert(String word) {
        int len = word.length();
        TrieNode crawl = root;
        // Traverse through all characters of given word
        for (int level = 0; level < len; level++) {
            HashMap<Character, TrieNode> children = crawl.getChildren();
            char c = word.charAt(level);
            // If there is already a child for current character of the given word
            if (children.containsKey(c)) {
                crawl = children.get(c);
            } else { // Else need to create a child
                TrieNode newChild = new TrieNode(c);
                children.put(c, newChild);
                crawl = newChild;
            }
        }
        // Set isEnd true for the last character
        crawl.setIsEnd(true);
    }
    
    // find if the input word is in the trie or not
    public boolean find(String word) {
        int len = word.length();
        TrieNode crawl = root;
        for (int level = 0; level < len; level++) {
            HashMap<Character, TrieNode> children = crawl.getChildren();
            char c = word.charAt(level);
            if (children.containsKey(c)) {
                crawl = children.get(c);
            } else {
                return false;
            }
        }
        if (!crawl.isEnd()) {
            return false;
        }
        return true;
    }
    
    // works like the automatically correction or filling
    public ArrayList<String> findPrefix(String prefix) {
        int len = prefix.length();
        TrieNode crawl = root;
        StringBuilder sb = new StringBuilder();
        for (int level = 0; level < len; level++) {
            HashMap<Character, TrieNode> children = crawl.getChildren();
            char c = prefix.charAt(level);
            if (children.containsKey(c)) {
                sb.append(c);
                crawl = children.get(c);
            } else {
                return null;
            }
        }
        ArrayList<String> res = new ArrayList<String>();
        sb.deleteCharAt(sb.length() - 1);
        helper(crawl, sb, res);
        return res;
    }
    public void helper(TrieNode crawl, StringBuilder sb, ArrayList<String> res) {
        if (crawl != null) {
            sb.append(crawl.getValue());
            if (crawl.isEnd()) {
                res.add(sb.toString());
            }
            HashMap<Character, TrieNode> children = crawl.getChildren();
            for (Map.Entry<Character, TrieNode> entry : children.entrySet()) {
                helper(entry.getValue(), sb, res);
            }
            sb.deleteCharAt(sb.length() - 1);
        }
    }
    
    // The method to find our the longest prefix of the input
    public String getMatchingPrefix(String input) {
        StringBuilder sb = new StringBuilder();
        int len = input.length();
        TrieNode crawl = root;
        
        // Iterate through all characters of the input string and traverse down the Trie
        int prevMatch = 0;
        for (int level = 0; level < len; level++) {
            char c = input.charAt(level);
            HashMap<Character, TrieNode> children = crawl.getChildren();
            if (children.containsKey(c)) {
                sb.append(c);
                crawl = children.get(c);
                if (crawl.isEnd()) {
                    prevMatch = level + 1;
                }
            } else {
                break;
            }
        }
        String result = sb.toString();
        if (!crawl.isEnd()) {
            return result.substring(0, prevMatch);
        }
        return result;
    }
}
