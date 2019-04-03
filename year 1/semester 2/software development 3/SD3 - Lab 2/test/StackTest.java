import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class StackTest {

	Stack st;

	@Before
	public void setup() {
		st = new Stack(2);
	}

	/*
	 * Part1: complete the following test methods as specified.
	 * 
	 * Note that you will need to add new methods in order to test that
	 * exceptions are thrown for the special cases
	 */

	@Test
	public void testPush() {
		st.push(5);
		st.push(1);
		assertEquals(2, st.size());
		assertEquals(1, st.top());

	}

	@Test
	public void testPop() {
		st.push(3);
		st.pop();
		st.push(3);
		assertEquals(1, st.size());
		assertEquals(3, st.top());

	}

	@Test(expected=StackException.class)
	public void checkFull() {
		st.push(1);
		st.push(1);
		st.push(1);
		

	}

	@Test(expected=StackException.class)
	public void checkEmpty() {
		st.pop();
	}
}
