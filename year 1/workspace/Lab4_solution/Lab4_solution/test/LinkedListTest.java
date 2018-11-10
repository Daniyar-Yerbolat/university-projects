import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;


public class LinkedListTest {

	LinkedList l;
	
	@Before
	public void setup(){
		l = new LinkedList();
	}
	/*
	 * Part 1: implement these methods
	 */
	@Test
	public void testCalculateSizeEmpty() {
		assertEquals(0, l.calculateSize());
	}

	@Test
	public void testCalculateSizeMany() {
		l.addAtHead(5);
		l.addAtHead(2);
		l.addAtTail(10);
		assertEquals(3, l.calculateSize());
	}

	
	@Test
	public void testCalculateTotalEmpty() {
		assertEquals(0, l.calculateTotal());
	}

	@Test
	public void testCalculateTotalMany() {
		l.addAtHead(5);
		l.addAtHead(2);
		l.addAtTail(10);
		assertEquals(17, l.calculateTotal());
	}
	
	/*
	 * Optional part
	 */
	
	@Test
	public void testReverse() {
		l.addAtHead(5);
		l.addAtHead(2);
		l.addAtHead(10);
		l.reverse();
		assertEquals(5, l.removeAtHead());
		assertEquals(2, l.removeAtHead());	
		assertEquals(10, l.removeAtHead());	
	}

}
