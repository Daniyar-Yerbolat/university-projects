class StackException extends RuntimeException{    
		public StackException(String err) {
			super(err);
		}
}

public class Stack {
	
	private class Node{
		int element;
		Node next;
		
		public Node(int e, Node n){
			element = e;
			next = n;
		}
	}
	
	private Node top;
	private int size;
	
	public Stack(){
		top = null;
		size = 0;
	}
	
	public boolean isEmpty(){
		return top == null;
	}
	
	public int size(){
		return size; 
	}
	
	// part 3: complete
	public void push(int o){
		Node newNode = new Node(o,top);
		top = newNode; 
		size++;
	}
	
	// part 3: complete
	public int pop() throws StackException{
		if (top == null) {
			throw new StackException("stack is full");
		} else {
			int res = top.element;
			top = top.next;
			size--;
			return res;
		}
	}
	
	// part 3: complete
	public int top() throws StackException{
		return top.element;
	}
	
	
	

}
