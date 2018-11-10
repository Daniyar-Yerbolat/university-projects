public class LinkedList {

	private class Node {
		private int value;
		private Node nextNode;

		public Node(int i) {
			value = i;
			nextNode = null; 
		}

		// returns the value stored in the node
		public int getValue() {
			return value;
		}

		// sets the value stored in the node
		public void setValue(int i) {
			value = i;
		}

		// Returns the Node that this Node links to
		// Note this may return null
		public Node getNextNode() {
			return nextNode;
		}

		// Sets the NextNode to the given Node
		public void setNextNode(Node n) {
			nextNode = n;
		}

		// adds Node n to the tail of the list
		public void addNodeAtTail(Node n) {
			if (this.nextNode == null) {
				this.nextNode = n;
			} else {
				this.nextNode.addNodeAtTail(n);
			}
		}

		public Node removeAtTail(Node valueAtTail) {
			if (this.nextNode == null) { // if this is the last node
				valueAtTail.setValue(this.getValue()); // copy out the value
				return null; // return null
			} else { // move onto the next Node
				this.nextNode = this.nextNode.removeAtTail(valueAtTail);
				return this;
			}

		}
	} // End of Node Class implementation

	private Node headNode; // Holds a reference to the head of the list

	public LinkedList() {
		headNode = null;
	}

	public void addAtHead(int i) {
		Node newNode = new Node(i); 
		newNode.setNextNode(headNode); 
		headNode = newNode; 
	}

	public void addAtTail(int i) {
		Node newNode = new Node(i);
		if (headNode == null) { 
			headNode = newNode;
		} else {
			headNode.addNodeAtTail(newNode);
		}
	}

	/*
	 * removes and returns the value at the head of the list
	 * note, this may return -1 if the list is empty
	 */ 
	public int removeAtHead() {
		if (headNode == null) {
			return -1; // return -1 if the list is empty
		} else {
			Node returnedNode = headNode;
			headNode = headNode.getNextNode();
			return returnedNode.getValue();
		}
	}

	public int removeAtTail() {
		if (headNode == null) {
			return -1;
		} else {
			Node returnedNode = new Node(-1);
			headNode = headNode.removeAtTail(returnedNode);
			return returnedNode.getValue();
		}
	}

	/*
	 * PART 2: complete the following methods
	 */
	
	// recursive solution
	public int calculateSizeRec(Node curr){
		if (curr == null)
			return 0;
		else 
			return 1 + calculateSizeRec(curr.nextNode);
	}

	public int calculateSize(){
		return calculateSizeRec(headNode);
	}
	
	// iterative solution
	public int calculateTotal(){ 
		int total = 0;
		Node curr = headNode;
		while (curr != null){
			total += curr.getValue();
			curr = curr.nextNode;
		}
		return total;
	}

	/*
	 * Optional: reverse the linked lists so that the first element becomes
	 * the last, the second becomes the second last, and so on.
	 */
	public void reverse(){
		// reverse of singleton or empty is the same
		if (headNode == null || headNode.nextNode == null)
			return;
		Node prev = headNode;
		Node curr = headNode.nextNode;
		Node tmp;
		headNode.nextNode = null;
		while(curr != null){
			tmp = curr.nextNode;
			curr.nextNode = prev;
			prev = curr;
			curr = tmp;		
		}
		headNode = prev;
	}

}
