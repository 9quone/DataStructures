/**
 * Tester class for BSTree. Specifically testing the removeNode method.
 */
public class TreeTester {
	/**
	 * Tests the functionality of BSTree
	 */
	public static void main(String[] args) {
		BSTree<Integer> tree = new BSTree<Integer>();
		System.out.println("Trying to print tree...");
		tree.print();
		
		// Empty tree handled
		System.out.println("Trying to remove a node...");
		tree.removeNode(7);
		System.out.println();
		
		System.out.println("Adding 5 to the tree...");
		tree.insert(5);
		tree.print();
		
		// 1 node tree handled
		System.out.println("Removing 5 from the tree...");
		tree.removeNode(5);
		tree.print();
		
		System.out.println("Adding values to the tree...");
		tree.insert(20);
		tree.insert(50);
		tree.insert(10);
		tree.insert(5);
		tree.insert(40);
		tree.insert(30);
		tree.insert(12);
		tree.print();
		
		// Sub-Tree with no successor nodes (but does have predecessor nodes) is handled
		System.out.println("Removing 40 from the tree...");
		tree.removeNode(40);
		tree.print();
		
		// Tree where successor node to the deleted node is a leaf node is handled
		System.out.println("Removing 50 from the tree...");
		tree.removeNode(50);
		tree.print();
		
		// Tree where successor node to the deleted node has a child is handled
		System.out.println("Removing 20 from the tree...");
		tree.removeNode(20);
		tree.print();
	}
}

/**
 * Represents a basic tree data structure.
 */
class BSTree<E> implements Comparable {
	private TreeNode root;
	
	/**
	 * Initializes the BSTree.
	 */
	public BSTree() {
		root = null;
	}
	
	/**
	 * Inserts an item into the tree.
	 */
	public void insert(Comparable comp) {
		if (root == null) {
			root = new TreeNode(comp, null, null);
		} else {
			insert(root, comp);
		}

	}
	
	/**
	 * Recursive helper method for inserting an element into the BSTree.
	 */
	private void insert(TreeNode root, Comparable comp) {
		if(comp.compareTo(root.getValue()) < 0 ) {
			if (root.getLeft() == null) {
				root.setLeft(new TreeNode(comp, null, null));
			} else {
				insert(root.getLeft(), comp);
			}
		} else {
			if (root.getRight() == null) {
				root.setRight(new TreeNode(comp, null, null) );
			} else {
				insert(root.getRight(), comp);
			}
		}
	}
	
	/**
	 * Inorder traversal printing: prints all the items in the tree to which each root points.
	 */
	public void print() {
		if (root != null) {
			printHelper(root);
		} else {
			System.out.println("Tree is empty!");
		}
		System.out.println();
	}
	
	/**
	 * Recursive helper method to print out the tree.
	 */
	private void printHelper(TreeNode node) {
		if (node.getLeft() != null) {
			printHelper(node.getLeft());
		}
		System.out.println(node.getValue());
		if (node.getRight() != null) {
			printHelper(node.getRight());
		}

	}
	
	/**
	 * Removes datum from the BSTree.
	 */
	public E removeNode(E datum) {
		root = removeNode(root, datum);
		return datum;
	}
	
	/**
	 * Recursive helper method to remove datum from the BSTree.
	 */
	private TreeNode removeNode(TreeNode tree, E datum) {
		if (tree == null) {
			System.out.println("Tree is empty!");
			return null;
		}
		if (((Comparable) datum).equals(tree.getValue())) {
			if (tree.getLeft() == null && tree.getRight() == null)
				return null;
			if (tree.getLeft() == null)
				return tree.getRight();
			if (tree.getRight() == null)
				return tree.getLeft();

			TreeNode tmp = smallestNode(tree.getRight());
			tree.setValue(tmp.getValue());

			tree.setRight(removeNode(tree.getRight(), (E) tmp.getValue()));
			return tree;

		} else if (((Comparable) datum).compareTo(tree.getValue()) < 0) {
			tree.setLeft(removeNode(tree.getLeft(), datum));
			return tree;
		} else {
			tree.setRight(removeNode(tree.getRight(), datum));
			return tree;
		}
	}
	
	/**
	 * Returns the smallest node of the tree or subtree.
	 */
	private TreeNode smallestNode(TreeNode tree) {
		if (tree.left == null)
			return tree;
		else
			return smallestNode(tree.left);
	}
	
	/**
	 * Compares the values of two TreeNodes.
	 */
	public int compareTo(Object newNode) {
		return ((Comparable) root.getValue()).compareTo(newNode);
	}
	
	/**
	 * Represents a node in the BSTree
	 */
	private class TreeNode<E> {
		private Object value;
		private TreeNode left;
		private TreeNode right;

		public TreeNode(Object initValue) {
			value = initValue;
			left = null;
			right = null;
		}

		public TreeNode(Object initValue, TreeNode initLeft, TreeNode initRight) {
			value = initValue;
			left = initLeft;
			right = initRight;
		}

		public Object getValue() { return value; }
		public TreeNode getLeft() { return left; }
		public TreeNode getRight() { return right; }
		public void setValue(Object theNewValue) { value = theNewValue; }
		public void setLeft(TreeNode theNewLeft) { left = theNewLeft; }
		public void setRight(TreeNode theNewRight) { right = theNewRight; }
	}
}
