import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;


public class PersonTest {

	Person p;
	
	@Before
	public void testMakePerson() {
		p = new Person("john","bla",10);
	}
	
	@Test
	public void testSetFirstName() {
		assertNotNull(p);
		p.setFirstName("andrew");
		assertEquals("andrew",p.getFirstName());
	}

	@Test
	public void testSetLastName() {
		p.setLastName("Jones");
		assertEquals("Jones",p.getLastName());
	}

	// Part 1
	@Test
	public void testSetAge() {
		p.setAge(19);
		assertEquals(19, p.getAge());
	}

}
