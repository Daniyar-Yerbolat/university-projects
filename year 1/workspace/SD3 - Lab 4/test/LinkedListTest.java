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
		assertEquals(l.calculateSize(), null);
	}

	@Test
	public void testCalculateSizeMany() {
		l.addAtHead(3);
		l.addAtHead(2);
		l.addAtHead(1);
		assertEquals(l.calculateSize(), 3);
	}

	
	@Test
	public void testCalculateTotalEmpty() {
		assertEquals(l.calculateTotal(), null);
	}

	@Test
	public void testCalculateTotalMany() {
		l.addAtHead(3);
		l.addAtHead(2);
		l.addAtHead(1);
		assertEquals(l.calculateTotal(), 6);
	}
	
	/*
	 * Optional part
	 */
	
	@Test
	public void testReverse() {
		fail("Not yet implemented");
	}

}
