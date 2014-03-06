class LCA {
	public static TreeNode LCA(TreeNode root, TreeNode a, TreeNode b) {
		if (root == null) {
			return null;
		}
		
		// If the root is one of a or b, then it is the LCA
		if (root == a || root == b) {
			return root;
		}
		
		TreeNode left = LCA(root.left, a, b);
		TreeNode right = LCA(root.right, a, b);
		
		// If both nodes lie in left subtree, then their LCA is in left subtree
		// If both nodes lie in right subtree, then their LCA is in right subtree
		// Otherwise root is their LCA
		if (left != null && right != null) {
			return root;
		}
		return left != null ? left : right;
	}
}