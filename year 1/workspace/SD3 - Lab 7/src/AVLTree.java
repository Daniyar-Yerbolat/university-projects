public class AVLTree {

	private class AVLNode{ //private class to hold a tree node
		
		private int height; // the height of a node
		private int value;
		private AVLNode leftChild; //left subtree
		private AVLNode rightChild; //right subtree
		
		public AVLNode(int v){
			value = v;
			leftChild = null;
			rightChild = null;
			height = 1;
		}
		
		public AVLNode getLeftChild(){
			return leftChild;
		}
		public AVLNode getRightChild(){
			return rightChild;
		}
		
		public void setLeftChild(AVLNode n){
			leftChild = n;
		}
		public void setRightChild(AVLNode n){
			rightChild = n;
		}
		public int getValue(){
			return value;
		}	
		public void setValue(int v){
			value = v;
		}	
		
		public int getHeight(){
			return height;
		}	
		
		public void setHeight(int h){
			height = h;		
		}
	}
	
	private AVLNode root;
	
	
	private void setHeight(AVLNode n){
		int h = max(n.getLeftChild(),n.getRightChild()) + 1;
		n.setHeight(h);
	}
	
	private int max(AVLNode n1,AVLNode n2){
		if (n1 == null && n2 == null)
			return 0;
		if (n1 == null)
			return n2.height;
		if (n2 == null)
			return n1.height;
		return Math.max(n1.height,n2.height); 		
	}
	
	private int height(AVLNode n){
		if(n == null)
			return 0;
		else
			return n.getHeight();
	}
	
	private int getBalance(AVLNode n){
		if (n == null)
			return 0;
		return height(n.getLeftChild()) - height(n.getRightChild());	
	}
	
	public AVLNode getLargestValueNode(AVLNode n){ 
		if (n.getRightChild() == null)
			return n;
		else
			return getLargestValueNode(n.getRightChild());
	}
	
	// Part 3: complete
	private AVLNode rotateRight(AVLNode c){
		return null; // dummy: complete
	}
	
	// Part 3: complete
	private AVLNode rotateLeft(AVLNode a){
		return null; // dummy: complete
	}	

	private AVLNode doublerotateRight(AVLNode a){	
		a.rightChild = rotateRight(a.rightChild);
		return rotateLeft(a);
	}
				
	private AVLNode doublerotateLeft(AVLNode c){	
		c.leftChild = rotateLeft(c.leftChild);
		return rotateRight(c);
	}	
	
	public AVLNode insert(int value,AVLNode n){
		if (n == null)
			return new AVLNode(value);
		if (value < n.value)
			n.leftChild = insert(value,n.leftChild);
		else 
			n.rightChild = insert(value,n.rightChild);
		
		n.height = max(n.leftChild,n.rightChild) + 1;
		int balance = getBalance(n);
		
		// SINGLE ROTATION (OUTHER CASES)
		// left-left
		if(balance > 1 && value < n.getLeftChild().getValue())
			return rotateRight(n);
		//right-right
		if(balance < -1 && value > n.getRightChild().getValue())
			return rotateLeft(n);	
		// DOUBLE ROTATIONS INNER CASES 
		// left-right
		if(balance > 1 && value > n.getLeftChild().getValue())
			return doublerotateLeft(n);
		//right-left
		if(balance < -1 && value < n.getRightChild().getValue())
			return doublerotateRight(n);
		
		// return unchanged if no rotation
		return n;
	}
	
	// Optional part: complete
	public AVLNode delete(int value,AVLNode n){
		return n; // dummy
	}
	public void delete(int value){
		root = delete(value,root);
	}
	public void insert(int value){
		root = insert(value,root);
	}
	
	public void printTree(String s,AVLNode n){
		if (n == null)
			return;
		System.out.println(s + n.getValue() + " height : " + height(n) + " balance: " + getBalance(n));
		printTree(s + "-",n.getLeftChild());
		printTree(s + "-",n.getRightChild());		
	}
	
	public void printTree(){
		printTree("",root);
	}
	
	
	public static void main(String[] args){
		AVLTree avl = new AVLTree();
		avl.insert(1);
		avl.insert(2);
		avl.insert(3);
		avl.insert(4);
		avl.insert(5);
		avl.insert(6);
		avl.insert(7);
		avl.insert(8);
		avl.insert(9);
		avl.insert(10);
		avl.insert(11);
		avl.insert(12);
		avl.insert(13);
		avl.insert(14);
		avl.insert(15);
		avl.insert(16);
		avl.insert(17);
		avl.insert(18); 
		avl.printTree();
	}
	
}
