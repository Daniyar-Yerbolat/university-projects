package lab1.zuulRefactored;

import java.util.Iterator;
import java.util.Scanner;

/**
 * This class is the main class of the "World of Zuul" application.
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
 * @author Michael Kolling and David J. Barnes
 * @version 1.0 (February 2002)
 */

public class Game {
	private Parser parser;
	private Room currentRoom;
	private Room previousRoom;
	protected Player player;
	private double weight = 0;

	/**
	 * Create the game and initialise its internal map.
	 */
	public Game() {
		Room startRoom = createRooms();
		currentRoom = startRoom;
		parser = new Parser();
	}

	/**
	 * Create all the rooms and link their exits together.
	 * 
	 * @return Returns the starting room
	 */
	private Room createRooms() {
		Room outside, theatre, pub, lab, office;

		// create the rooms
		outside = new Room("outside the main entrance of the university");
		theatre = new Room("in a lecture theatre");
		pub = new Room("in the campus pub");
		lab = new Room("in the computing lab");
		office = new Room("in the computing admin office");

		// put items in the room
		pub.addItem(new Item("beer", 0.5));
		pub.addItem(new Item("wine", 0.75));
		lab.addItem(new Item("computer", 30));
		theatre.addItem(new Item("violin", 5));
		office.addItem(new Item("papers", 1));
		outside.addItem(new Item("trash", 0.1));

		// initialise room exits
		outside.setExit("east", theatre);
		outside.setExit("south", lab);
		outside.setExit("west", pub);

		theatre.setExit("west", outside);

		pub.setExit("east", outside);

		lab.setExit("north", outside);
		lab.setExit("east", office);

		office.setExit("west", lab);

		return outside;
	}

	private Player createPlayer() {
		Scanner scan = new Scanner(System.in);
		System.out.print("Enter your name: ");
		String x = scan.next();
		player = new Player(x, currentRoom);
		return player;

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
		createPlayer();
		System.out.println();
		System.out.println("Welcome to the World of Zuul, " + player.getName() + ".");
		System.out.println("World of Zuul is a new, incredibly boring adventure game.");
		System.out.println("Currently you are " + player.getCurrentRoom().getShortDescription() + ".");
		System.out.println("Type 'help' if you need help.");
		System.out.println();
	}

	/**
	 * Given a command, process (that is: execute) the command. If this command
	 * ends the game, true is returned, otherwise false is returned.
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
		} else if (commandWord.equals("back")) {
			back();
		} else if (commandWord.equals("items")) {
			items();
		} else if (commandWord.equals("pickup")) {
			pickUp(command);
		} else if (commandWord.equals("drop")) {
			drop(command);
		} else if (commandWord.equals("where")) {
			where();
		}
		return wantToQuit;
	}

	private void items() {
		String returnString = "Items:";
		for (Iterator<Item> iter = player.getItems().iterator(); iter.hasNext();) {
			returnString += " " + iter.next().getDescription();
		}
		System.out.println(returnString + "\n" + "Weight: " + weight);
	}

	private void where() {
		System.out.println(player.getName() + " is now " + currentRoom.getLongDescription());
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
		parser.showCommands();
	}

	/**
	 * Try to in to one direction. If there is an exit, enter the new room,
	 * otherwise print an error message.
	 */

	protected void pickUp(Command command) {
		if (!command.hasSecondWord()) {
			// if there is no second word, we don't know where to go...
			System.out.println("Pick up what?");
			return;
		}

		String item_name = command.getSecondWord();
		Iterator<Item> iter = currentRoom.getItems().iterator();
		if (iter.hasNext()) {
			while (iter.hasNext()) {
				Item temp = iter.next();
				if (temp.getDescription().equals(item_name) && (temp.getWeight() + weight) < player.max_weight) {
					weight = weight + temp.getWeight();
					player.addItem(temp);
					currentRoom.removeItem(temp);
					System.out.println("You have acquired " + item_name + ".");
					break;
				}
				else{
					System.out.println("You don't have enough space in your iventory.");
				}
			}
		} else if (iter.hasNext() == false) {
			System.out.println("There is no such item.");
		}
	}

	protected void drop(Command command) {
		if (!command.hasSecondWord()) {
			// if there is no second word, we don't know where to go...
			System.out.println("Drop what?");
			return;
		}

		String item_name = command.getSecondWord();
		Iterator<Item> iter = player.getItems().iterator();
		if (iter.hasNext()) {
			while (iter.hasNext()) {
				Item temp = iter.next();
				if (temp.getDescription().equals(item_name)) {
					weight = weight - temp.getWeight();
					player.removeItem(temp);
					currentRoom.addItem(temp);
					System.out.println("You have dropped " + item_name + ".");
					break;
				}
			}
		} else if (!iter.hasNext()) {
			System.out.println("There is no such item in your inventory.");
		}
	}

	protected void goRoom(Command command) {
		if (!command.hasSecondWord()) {
			// if there is no second word, we don't know where to go...
			System.out.println("Go where?");
			return;
		}

		String direction = command.getSecondWord();

		// Try to leave current room.
		Room nextRoom = currentRoom.getExit(direction);

		if (nextRoom == null) {
			System.out.println("There is no door!");
		} else {
			previousRoom = currentRoom;
			currentRoom = nextRoom;
			player.setCurrentRoom(currentRoom);
			System.out.println(player.getName() + " is now " + currentRoom.getLongDescription());
		}
	}

	/**
	 * "Quit" was entered. Check the rest of the command to see whether we
	 * really quit the game. Return true, if this command quits the game, false
	 * otherwise.
	 */
	private boolean quit(Command command) {
		if (command.hasSecondWord()) {
			System.out.println("Quit what?");
			return false;
		} else
			return true; // signal that we want to quit
	}

	public void back() {
		if (previousRoom != null) {
			currentRoom = previousRoom;
			System.out.println(player.getName() + " is now " + currentRoom.getLongDescription());
			previousRoom = null;
		} else {
			System.out.println("You haven't moved yet.");
		}
	}

	public Room getCurrentRoom() {
		return this.currentRoom;
	}

	public static void main(String[] args) {
		Game game = new Game();
		game.play();

	}
}
