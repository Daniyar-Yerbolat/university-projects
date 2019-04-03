import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class CollectionTest {

	Collection c;

	@Before
	public void setup() {
		c = new Collection();
	

	}

	/*
	 * To be completed in part 1
	 */
	@Test
	public void testEmptyMaxAge() {
		assertEquals(-1, c.maxAge());
	}

	@Test
	public void testOneElementMaxAge() {
		c.addPerson("Donald", "Duck", 19);
		assertEquals(19, c.maxAge());
	}

	@Test
	public void testThreeElementsMaxAge() {
		c.addPerson("John", "Connor", 11);
		c.addPerson("Jack", "Daniels", 25);
		c.addPerson("Donald", "Duck", 19);
		System.out.println(c.maxAge());
		assertEquals(25, c.maxAge());

	}

}
