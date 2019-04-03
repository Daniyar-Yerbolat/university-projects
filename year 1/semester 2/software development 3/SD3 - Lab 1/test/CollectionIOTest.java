import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;


public class CollectionIOTest {

	Person[] p;
	Collection c;
	
	@Before
	public void setup() {
		c = new Collection();
		c.readFromFile();
		p = c.getCollection();
	}
	
	@Test
	public void testReadFromFile() {
		assertNotNull(p);
		assertEquals(200,c.getSize());
		assertEquals("Age for second person incorrect",59,p[1].getAge());
		assertEquals("First name of third person incorrect","Bartley",p[2].getFirstName().trim());		
		assertEquals("Last name of last person incorrect","Anderson",p[199].getLastName().trim());
	}


}
