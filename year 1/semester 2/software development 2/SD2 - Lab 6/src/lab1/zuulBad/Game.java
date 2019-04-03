package lab1.zuulBad;

/**
 * This class is the main class of the "World of Zuul" application. ORIGINAL
 * VERSION
 * 
 * "World of Zuul" is a very simple, text based adventure game. Users can walk
 * around some scenery. That's all. It should really be extended to make it more
 * interesting!
 * 
 * To play this game, create an instance of this class and call the "play"
 * method.
 * 
 * This main class creates and initialises all the others: it creates all rooms,
 * creates the parser and starts the game. It also evaluates and executes the
 * commands that the parser returns.
 * 
 * @author Michael Kölling and David J. Barnes
 * @version 2011.07.31
 */

public class Game {
	private Parser parser;
	private Room currentRoom;
	private Room previousRoom;

	/**
	 * Create the game and initialise its internal map.
	 */
	public Game() {
		createRooms();
		parser = new Parser();
	}

	/**
	 * Create all the rooms and link their exits together.
	 */
	private void createRooms() {
		Room outside, theater, pub, lab, office, library, tower, dungeon;

		// create the rooms
		outside = new Room("outside the main entrance of the university");
		theater = new Room("in a lecture theater");
		pub = new Room("in the campus pub");
		lab = new Room("in a computing lab");
		office = new Room("in the computing admin office");
		library = new Room("in the library");
		tower = new Room("in the tower");
		dungeon = new Room("in the dungeon");

		// initialise room exits
		outside.setExits(library, theater, lab, pub, null, null);
		theater.setExits(null, null, null, outside, null, null);
		pub.setExits(null, outside, null, null, tower, dungeon);
		lab.setExits(outside, office, null, null, null, null);
		office.setExits(null, null, null, lab, null, null);
		library.setExits(null, null, outside, null, null, null);
		tower.setExits(null, null, null, null, null, pub);
		dungeon.setExits(null, null, null, null, pub, null);

		currentRoom = outside; // start game outside
	}

	/**
	 * Main play routine. Loops until end of play.
	 */
	public void play() {
		printWelcome();

		// Enter the main command loop. Here we repeatedly read commands and
		// execute them until the game is over.

		boolean finished = false;
		while (!finished) {
			Command command = parser.getCommand();
			finished = processCommand(command);
		}
		System.out.println("Thank you for playing.  Good bye.");
	}

	/**
	 * Print out the opening message for the player.
	 */
	private void printWelcome() {
		System.out.println();
		System.out.println("Welcome to the World of Zuul!");
		System.out.println("World of Zuul is a new, incredibly boring adventure game.");
		System.out.println("Type 'help' if you need help.");
		System.out.println();
		System.out.println("You are " + currentRoom.getDescription());
		System.out.print("Exits: ");

		if (currentRoom.getNorthExit() != null) {
			System.out.print("north ");
		}
		if (currentRoom.getEastExit() != null) {
			System.out.print("east ");
		}
		if (currentRoom.getSouthExit() != null) {
			System.out.print("south ");
		}
		if (currentRoom.getWestExit() != null) {
			System.out.print("west ");
		}
		if (currentRoom.getUpExit() != null) {
			System.out.print("up ");
		}
		if (currentRoom.getDownExit() != null) {
			System.out.print("down ");
		}
		System.out.println();
	}

	/**
	 * Given a command, process (that is: execute) the command.
	 * 
	 * @param command
	 *            The command to be processed.
	 * @return true If the command ends the game, false otherwise.
	 */
	private boolean processCommand(Command command) {
		boolean wantToQuit = false;

		if (command.isUnknown()) {
			System.out.println("I don't know what you mean...");
			return false;
		}

		String commandWord = command.getCommandWord();
		if (commandWord.equals("help")) {
			printHelp();
		} else if (commandWord.equals("go")) {
			goRoom(command);
		} else if (commandWord.equals("quit")) {
			wantToQuit = quit(command);
		} else if (commandWord.equals("where")) {
			where();
		} else if (commandWord.equals("back")) {
			back();
		}

		return wantToQuit;
	}

	// implementations of user commands:

	/**
	 * Print out some help information. Here we print some stupid, cryptic
	 * message and a list of the command words.
	 */
	private void printHelp() {
		System.out.println("You are lost. You are alone. You wander");
		System.out.println("around at the university.");
		System.out.println();
		System.out.println("Your command words are:");
		System.out.println("   go quit help");
	}

	/**
	 * Try to go in one direction. If there is an exit, enter the new room,
	 * otherwise print an error message.
	 */

	private void goRoom(Command command) {
		if (!command.hasSecondWord()) {
			// if there is no second word, we don't know where to go...
			System.out.println("Go where?");
			return;
		}

		String direction = command.getSecondWord();

		// Try to leave current room.
		Room nextRoom = null;
		previousRoom = currentRoom;
		if (direction.equals("north")) {
			nextRoom = currentRoom.getNorthExit();
		} else if (direction.equals("east")) {
			nextRoom = currentRoom.getEastExit();
		} else if (direction.equals("south")) {
			nextRoom = currentRoom.getSouthExit();
		} else if (direction.equals("west")) {
			nextRoom = currentRoom.getWestExit();
		} else if (direction.equals("up")) {
			nextRoom = currentRoom.getUpExit();
		} else if (direction.equals("down")) {
			nextRoom = currentRoom.getDownExit();
		}

		if (nextRoom == null) {
			System.out.println("There is no door!");
		} else {
			currentRoom = nextRoom;
			System.out.println("You are " + currentRoom.getDescription());
			System.out.print("Exits: ");
			if (currentRoom.getNorthExit() != null) {
				System.out.print("north ");
			}
			if (currentRoom.getEastExit() != null) {
				System.out.print("east ");
			}
			if (currentRoom.getSouthExit() != null) {
				System.out.print("south ");
			}
			if (currentRoom.getWestExit() != null) {
				System.out.print("west ");
			}
			if (currentRoom.getUpExit() != null) {
				System.out.print("up ");
			}
			if (currentRoom.getDownExit() != null) {
				System.out.print("down ");
			}
			System.out.println();
		}
	}

	/**
	 * "Quit" was entered. Check the rest of the command to see whether we
	 * really quit the game.
	 * 
	 * @return true, if this command quits the game, false otherwise.
	 */
	private boolean quit(Command command) {
		if (command.hasSecondWord()) {
			System.out.println("Quit what?");
			return false;
		} else {
			return true; // signal that we want to quit
		}
	}

	private void back() {
		if (previousRoom != null) {
			currentRoom = previousRoom;
			System.out.println("You are " + currentRoom.getDescription());
		} else {
			System.out.println("You haven't moved yet.");
		}
	}

	private void where() {
		System.out.println("You are " + currentRoom.getDescription());
	}

	public static void main(String[] args) {
		Game game = new Game();
		game.play();

	}
}
