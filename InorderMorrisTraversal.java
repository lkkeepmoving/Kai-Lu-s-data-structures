class InorderMorrisTraversal {
	// Function to in-order traverse binary tree without recursion and without stack
	// Morris Traversal works only when we have write permission
	public void inorderMorrisTraversal(TreeNode root) {
		if (root == null) {
			return;
		}
		TreeNode cur = root;
		TreeNode pre = null;
		while (cur != null) {
			if (cur.left == null) {
				System.out.println(cur.val);
				cur = cur.right;
			} else {
				// Find the inorder predecessor of current
				pre = cur.left;
				while (pre.right != null && pre.right != cur) {
					pre = pre.right;
				}
				
				// Make current as right child of its inorder predecessor
				if (pre.right == null) {
					pre.right = cur;
					cur = cur.left;
				} else {
					// Revert the changes made in if part to restore the original tree
					// i.e., fix the right child of predecessor
					pre.right = null;
					System.out.println(cur.val);
					cur = cur.right;
				}
			}
		}
	}
}
