package lab1.zuulRefactored;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 * @author vrieser
 *
 */
public class BackTest {

	/**
	 * Default constructor for test class SalesItemTest
	 */

	@Test
	public void testBack() {
		Game game = new Game();
		game.play();
		Room start = game.getCurrentRoom();

		Command command = new Command("go", "east");
		game.goRoom(command);

		assertTrue(!start.equals(game.getCurrentRoom()));
		Command back = new Command("back", null);

		// You will need to uncomment the following two lines to test your
		// goBack() method!
		game.back();
		assertTrue(start.equals(game.getCurrentRoom()));

	}
	@Test
	public void testPickUp(){
		Game game = new Game();
		game.play();
		Room start = game.getCurrentRoom();

		Command command = new Command("go", "west");
		game.goRoom(command);
		
		Command command2 = new Command ("pickup", "wine");
		game.pickUp(command2);
		
		assertEquals(game.getCurrentRoom().getItems(), game.player.getItems());
		
		
	}
	@Test
	public void testDrop(){
		
	}

}