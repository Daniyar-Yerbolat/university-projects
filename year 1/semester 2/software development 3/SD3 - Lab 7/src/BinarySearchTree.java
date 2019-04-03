import javax.xml.soap.Node;

public class BinarySearchTree {
	private class BSTNode { // private class to hold a tree node

		private int value;
		private BSTNode leftChild; // left subtree
		private BSTNode rightChild; // right subtree

		public BSTNode(int v) {
			value = v;
			leftChild = null;
			rightChild = null;
		}

		public BSTNode getLeftChild() {
			return leftChild;
		}

		public BSTNode getRightChild() {
			return rightChild;
		}

		public void setLeftChild(BSTNode n) {
			leftChild = n;
		}

		public void setRightChild(BSTNode n) {
			rightChild = n;
		}

		public int getValue() {
			return value;
		}

		public void insertNode(BSTNode n) {
			if (n.value < this.value) { // goes into the left subtree
				if (this.getLeftChild() == null) {
					this.setLeftChild(n);
				} else {
					this.getLeftChild().insertNode(n);
				}
			} else if (n.value > this.value) { // goes into the right subtree
				if (this.getRightChild() == null) {
					this.setRightChild(n);
				} else {
					this.getRightChild().insertNode(n);
				}
			} else { // Trying to insert a duplicate, which we don't allow:
						// could also raise exception
				return;
			}
		}

		public boolean containsNodeWithValue(int v) {
			if (v < this.value) {
				if (this.getLeftChild() == null) {
					return false;
				} else {
					return this.getLeftChild().containsNodeWithValue(v);
				}
			} else if (v > this.value) {
				if (this.getRightChild() == null) {
					return false;
				} else {
					return this.getRightChild().containsNodeWithValue(v);
				}
			}
			return true; // this.value == v
		}

		public BSTNode getLargestValueNode() {
			// decend down the right subtree until we get the largest value.
			// i.e. until we cannot continue to go down the right subtree
			if (this.getRightChild() == null) {
				return this;
			} else {
				return this.getRightChild().getLargestValueNode();
			}
		}

		public BSTNode deleteNodeWithValue(int v) {
			if (v == this.value) { // this is the node we want to remove
				// does it have two children?
				if (this.getLeftChild() != null && this.getRightChild() != null) {
					BSTNode largestLeft = this.getLeftChild().getLargestValueNode(); // find
																						// the
																						// largest
																						// value
																						// in
																						// the
																						// left
																						// subtree
					this.value = largestLeft.value; // set the current node
													// value to this node
					this.leftChild = this.getLeftChild().deleteNodeWithValue(largestLeft.value); // remove
																									// the
																									// largest
																									// left
																									// node
					return this;
					// otherwise we promote the only valid subtree node
				} else if (this.getLeftChild() != null) {
					return this.getLeftChild();
				} else if (this.getRightChild() != null) {
					return this.getRightChild();
				} else { // no children. this is a leaf node
					System.out.println("Deleting Node with value " + this.value + " Returning Null");
					return null;
				}
			} else { // this isn't the node we want to remove, so keep going
				if (v > this.value && this.getRightChild() != null) {
					this.rightChild = this.getRightChild().deleteNodeWithValue(v);
					return this;
				} else if (v < this.value && this.getLeftChild() != null) {
					this.leftChild = this.getLeftChild().deleteNodeWithValue(v);
					return this;
				} else { // We are trying to remove a non existent node
					return this;
				}
			}
		}

		public int numberOfNodes() {
			int valueToReturn = 1; // this is 1 to count this node
			if (leftChild != null)
				valueToReturn += leftChild.numberOfNodes(); // add the nodes in
															// the left subtree
			if (rightChild != null)
				valueToReturn += rightChild.numberOfNodes(); // add the nodes in
																// the right
																// subtree
			return valueToReturn; // return the number of nodes
		}

		// Part 1: complete
		public void inOrderTraversal(BSTNode parent) {
			if (parent != null) {
				inOrderTraversal(parent.leftChild);
				System.out.println(parent.value);
				inOrderTraversal(parent.rightChild);

			}

		}

	}

	BSTNode rootNode = null;

	public void insert(int v) {
		if (rootNode == null) {
			rootNode = new BSTNode(v);
		} else {
			rootNode.insertNode(new BSTNode(v));
		}
	}

	public void remove(int v) {
		if (rootNode != null) {
			rootNode = rootNode.deleteNodeWithValue(v);
		}
	}

	public boolean contains(int v) {
		if (rootNode != null) {
			return rootNode.containsNodeWithValue(v);
		} else {
			return false;
		}
	}

	// Part 1
	public void inOrderTraversal() {
		if (rootNode != null) {
			rootNode.inOrderTraversal(rootNode);
		}
	}
	DLinkedList list = new DLinkedList();
	// Part 2: complete
	public DLinkedList returnInOrderTraversal(BSTNode parent) {
		
		if (parent != null) {

			returnInOrderTraversal(parent.leftChild);
			list.addAtTail(parent.value);
			returnInOrderTraversal(parent.rightChild);

		}
		return list; // dummy: complete
	}

	public static void main(String[] args) {

		System.out.println("******* Tree 1 : 3 nodes ***********");
		BinarySearchTree bst1 = new BinarySearchTree();
		bst1.insert(1);
		bst1.insert(2);
		bst1.insert(3);
		bst1.insert(4);
		bst1.inOrderTraversal();

		System.out.println("******* Tree 2 : 1 node ***********");
		BinarySearchTree bst2 = new BinarySearchTree();
		bst2.insert(1);
		bst2.inOrderTraversal();

		System.out.println("******* Tree 3 : empty ***********");
		BinarySearchTree bst3 = new BinarySearchTree();
		bst3.inOrderTraversal();

	}
}
