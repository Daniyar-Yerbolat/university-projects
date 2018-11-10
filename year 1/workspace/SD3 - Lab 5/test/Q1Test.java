import static org.junit.Assert.*;

import org.junit.Test;


public class Q1Test {

	@Test
	public void testQ1() {
		Stack st = new Stack(5);
		st.push("A");
		st.push("B");
		Q1.reverseStack(st);
		assertEquals("A",(String) st.top());
	}


}
