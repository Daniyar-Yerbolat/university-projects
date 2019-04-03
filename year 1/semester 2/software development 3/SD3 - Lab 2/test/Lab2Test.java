import static org.junit.Assert.*;

import org.junit.Test;


public class Lab2Test {
	
	/*
	 *  Tests for part 2
	 */
	
	@Test
	public void testReverse() {
		String[] empty = {};
		String[] one = {"A"};
		String[] three = {"A","B","C"};
		
		String[] rev_empty = {};
		String[] rev_one = {"A"};
		String[] rev_three = {"C","B","A"};
		
		Lab2.reverse(rev_empty);
		Lab2.reverse(rev_one);
		Lab2.reverse(rev_three);
		
		assertArrayEquals(empty,rev_empty);
		assertArrayEquals(one,rev_one);
		assertArrayEquals(three,rev_three);

	}
	
	/*
	 *  Tests for part 3
	 */
	
	@Test
	public void testCalculate() {
		String [] cmds1 = {"+","-","3","5","2"};
		String [] cmds2 = {"-","3","+","5","2"};
		
		assertEquals(0,Lab2.calculate(cmds1));
		assertEquals(-4,Lab2.calculate(cmds2));	
	}

	@Test
	public void testConvert() {
		assertEquals(1,Lab2.convert("1"));
		assertEquals(0,Lab2.convert("0"));
		assertEquals(-5,Lab2.convert("-5"));
	}

	@Test
	public void testIsNumber() {
		assertTrue(Lab2.isNumber("-1"));
		assertFalse(Lab2.isNumber("A"));
	}

	@Test
	public void testApplyOp() {
		assertEquals(3,Lab2.applyOp("1","+","2"));
		assertEquals(-5,Lab2.applyOp("2","-","7"));
		assertEquals(6,Lab2.applyOp("3","+","3"));
		assertEquals(3,Lab2.applyOp("5","-","2"));
	}
	

}
