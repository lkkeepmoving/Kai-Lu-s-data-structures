/* Kai Lu */

import java.util.Stack;

class TrinaryNode {
    int val;
    TrinaryNode left;
    TrinaryNode mid;
    TrinaryNode right;
    TrinaryNode(int val) {
        this.val = val;
        this.left = null;
        this.mid = null;
        this.right = null;
    }
}

public class TrinaryTree {
    private TrinaryNode root;
    
    /* Constructor */
    public TrinaryTree() {
        this.root = null;
    }
    
    /* Insert a value to the tree */
    public void insert(int val) {
        insertHelper(this.root, val);
    }
    
    public TrinaryNode insertHelper(TrinaryNode node, int val) {
        if (node == null) {
            if (node == this.root) {
                this.root = new TrinaryNode(val);
            } else {
                node = new TrinaryNode(val);
            }
        } else if (node.val > val) {
            node.left = insertHelper(node.left, val);
        } else if (node.val == val) {
            node.mid = insertHelper(node.mid, val);
        } else if (node.val < val) {
            node.right = insertHelper(node.right, val);
        }
        return node;
    }
    
    /* Delete a value in the tree. 
     * First find the node with the value, then replace in the following order: 
     * (1) if it has mid, replace it with mid 
     * (2) find the node with max value in its left subtree, replace it with the found node
     * (3) replace it with its right node
     */
    public void delete(int val) {
        deleteHelper(this.root, val);
    }
    
    public TrinaryNode deleteHelper(TrinaryNode node, int val) {
        if (node == null) {
            return node;
        }
        if (val < node.val) {
            node.left = deleteHelper(node.left, val);
        } else if (val > node.val) {
            node.right = deleteHelper(node.right, val);
        } else { /* find the node, let's do delete */
            if (node.mid != null) {
                node.mid = deleteHelper(node.mid, val);
            } else if (node.left != null) {
                TrinaryNode leftMax = findLeftMax(node.left);
                node.val = leftMax.val; /* replace the value */
                node.left = deleteHelper(node.left, leftMax.val); /* then delete the duplicate value */
            } else {
                if (node == this.root) {
                    this.root = this.root.right;
                } else {
                    node = node.right;
                }
            }
        }
        return node;
    }
    
    /* Find the max value of left child subtree */
    private TrinaryNode findLeftMax(TrinaryNode node) {
        if (node != null) {
            while (node.right != null) {
                node = node.right;
            }
        }
        return node;
    }
    
    /* In-order traversal. Print all values. Should always be ascending order */
    public void inorder() {
        Stack<TrinaryNode> s = new Stack<TrinaryNode>();
        TrinaryNode cur = this.root;
        while (!s.isEmpty() || cur != null) {
            if (cur != null) {
                s.push(cur);
                cur = cur.left;
            } else {
                cur = s.pop();
                TrinaryNode temp = cur;
                while (temp != null) {
                    System.out.print(temp.val);
                    temp = temp.mid;
                }
                cur = cur.right;
            }
        }
        System.out.println();
    }
}
