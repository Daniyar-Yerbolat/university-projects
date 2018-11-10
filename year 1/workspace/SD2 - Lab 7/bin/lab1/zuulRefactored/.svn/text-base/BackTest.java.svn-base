/**
 * 
 */
package lab1.zuulRefactored;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * @author vrieser
 *
 */
public class BackTest
{

	/**
     * Default constructor for test class SalesItemTest
     */
    public BackTest()
    {
    }

	@Test
	public void testBack()
	{
		Game game = new Game();
		Room start= game.getCurrentRoom();
		
		Command command = new Command("go", "east");
		game.goRoom( command );
		
		assertTrue(!start.equals( game.getCurrentRoom()));
		
		Command back = new Command("back", null);
		
		// You will need to uncomment the following two lines to test your goBack() method!
		//game.goBack(back );
		//assertTrue(start.equals( game.getCurrentRoom()));
		
	}
	
	
	
	
}