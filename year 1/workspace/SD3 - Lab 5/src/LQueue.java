public class LQueue {
	
	private class Node{
		Object element;
		Node next;
		
		public Node(Object e, Node n){
			element = e;
			next = n;
		}
		
		public Node(Object e){
			element = e;
			next = null;
		}
	}
	
	private Node head;
	private Node tail;
	private int size;
	
	public LQueue(){
		head = null;
		tail = null;	
	}
	
	/*
	 * Part 3: complete the following methods
	 */
	
	// Part 3: complete	
	public boolean isEmpty(){
		return false; // dummy value
	}
	
	// Part 3: complete
	public int size(){
		return -1; // dummy value 
	}
	
	// Part 3: complete
	public void enqueue(Object o){

	}
	
	// Part 3: complete	
	public Object dequeue() throws QueueException{
		return null; // dummy value
	}
	
	// Part 3: complete
	public Object front() throws QueueException{
		return null; // dummy value
	}
	
}
