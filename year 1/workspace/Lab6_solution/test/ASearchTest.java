import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class ASearchTest {

	ASearch as;
	/*
	 * Part 1: complete unit tests
	 */
	@Before
	public void setup(){
		as = new ASearch();
		as.addEntry(new Entry("Andrew",111));
		as.addEntry(new Entry("Ewen",321));
		as.addEntry(new Entry("Peter",123));
		as.addEntry(new Entry("Roger",222));
		
	}
	
	@Test
	public void testLinearSearchOK() {
		assertEquals(321,as.linearSearch("Ewen"));
	}

	@Test
	public void testLinearSearchFail() {
		assertEquals(-1,as.linearSearch("Andres"));
	}
	
	@Test
	public void testBinarySearchOK() {
		assertEquals(321,as.bSearch("Ewen"));
	}
	
	@Test
	public void testBinarySearchFail() {
		assertEquals(-1,as.bSearch("Andres"));
	}

}
