package lab1.zuulRefactored;

import java.util.HashSet;

public class Player {
	private String name;
	private Room currentRoom;
	private HashSet<Item> player_items;
	public final double max_weight = 29;

	public HashSet<Item> getItems() {
		return player_items;
	}

	public Player(String name, Room room) {
		this.name = name;
		currentRoom = room;
		player_items = new HashSet<Item>();
	}

	public void addItem(Item item) {
		player_items.add(item);
	}

	public void removeItem(Item item) {
		player_items.remove(item);
	}
	
	public String getName() {
		return name;
	}

	public Room getCurrentRoom() {
		return currentRoom;
	}

	public void setCurrentRoom(Room currentRoom) {
		this.currentRoom = currentRoom;
	}

}
