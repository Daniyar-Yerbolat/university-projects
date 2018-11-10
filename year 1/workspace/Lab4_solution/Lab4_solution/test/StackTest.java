import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;


public class StackTest {
	
	Stack st;
	@Before
	public void setup()
	{
		st = new Stack();
	}

	/*
	 * Part 3: implement these methods (see your solution to the test your wrote in lab 2)
	 */
	@Test
	public void testPush() {
		assertEquals(0,st.size());
		st.push(10);
		assertEquals(1,st.size());
		assertEquals(10,st.top());
	}


	@Test
	public void testTop() {
		st.push(10);
		st.push(20);
		assertEquals(20,st.top());
		st.pop();
		assertEquals(10,st.top());
	}

	
	@Test
	public void testPop() {
		st.push(10);
		st.push(20);
		assertEquals(20,st.pop());
		assertEquals(1,st.size());	
		assertEquals(10,st.pop());
		assertEquals(0,st.size());
	}
	
	@Test(expected = StackException.class)  
	public void testEmptyPop() {  
	  st.pop();
	}
	
	
}
