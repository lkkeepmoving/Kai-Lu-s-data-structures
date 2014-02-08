// Kai Lu's implementation of BST

class Node {
    int val;
    Node left; 
    Node right;
    Node(int val) {
        this.val = val;
        this.left = null;
        this.right = null;
    }
}

public class BST {
    private Node root;
    
    // constructor 1
    BST() {
        this.root = null;
    }
    
    // constructor 2
    BST(int val) {
        this.root = new Node(val);
    }
    
    // insert a value to BST
    public void insert(int val) {
        insertHelper(this.root, val);
    }
    
    private void insertHelper(Node node, int val) {
        if (node == null) {
            this.root = new Node(val);
            return;
        }
        if (val < node.val) {
            if (node.left == null) {
                node.left = new Node(val);
            } else {
                insertHelper(node.left, val);
            }
        } else {
            if (node.right == null) {
                node.right = new Node(val);
            } else {
                insertHelper(node.right, val);
            }
        }
    }
    
    // delete a value in BST (this version can delete only one value a time)
    public void delete(int val) {
        deleteHelper(this.root, val, null);
    }
    
    private void deleteHelper(Node node, int val, Node parent) {
        if (node == null) {
            return;
        }
        if (val < node.val) {
            deleteHelper(node.left, val, node);
        } else if (val > node.val) {
            deleteHelper(node.right, val, node);
        } else if (val == node.val) {
            if (node.left != null && node.right != null) {
                Node leftMax = findLeftMax(node.left);
                node.val = leftMax.val;
                deleteHelper(node.left, leftMax.val, node);
            } else if (node.left != null) {
                doDelete(parent, node, node.left);
            } else if (node.right != null) {
                doDelete(parent, node, node.right);
            } else {  // the node is a leaf
                doDelete(parent, node, null);
            }
        }
    }
    
    private Node findLeftMax(Node node) {
        if (node == null) {
            return null;
        }
        Node result = node;
        while (result.right != null) {
            result = result.right;
        }
        return result;
    }
    
    private void doDelete(Node parent, Node toDel, Node replace) {
        if (parent != null) {
            if (parent.left != null && parent.left == toDel) {
                parent.left = replace;
            } else {
                parent.right = replace;
            }
        } else {
            this.root = replace;
        }
    }
    
    // if BST contains this value, return the according node, else return null
    public Node contains(int val) {
        Node cur = this.root;
        while (cur != null) {
            if (cur.val == val) {
                return cur;
            } else if (val > cur.val) {
                cur = cur.right;
            } else {
                cur = cur.left;
            }
        }
        return cur;
    }
    
    // print all the values in BST based on the input traversal method
    public void traverse(String type) {
        if (type.equalsIgnoreCase("preorder")) {
            preorder(this.root);
        } else if (type.equalsIgnoreCase("inorder")) {
            inorder(this.root);
        } else if (type.equalsIgnoreCase("postorder")) {
            postorder(this.root);
        }
    }    
    
    private void preorder(Node node) {
        if (node == null) {
            return;
        }
        System.out.print(node.val + " ");
        preorder(node.left);
        preorder(node.right);
    }
    
    private void inorder(Node node) {
        if (node == null) {
            return;
        }
        inorder(node.left);
        System.out.print(node.val + " ");
        inorder(node.right);
    }

    private void postorder(Node node) {
        if (node == null) {
            return;
        }
        postorder(node.left);
        postorder(node.right);
        System.out.print(node.val + " ");
    }
}