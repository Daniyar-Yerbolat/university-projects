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

	}
	
	// part 3: complete
	public int pop() throws StackException{
		return -1; // dummy remove
	}
	
	// part 3: complete
	public int top() throws StackException{
		return -1; // dummy remove
	}
	
	
	

}
