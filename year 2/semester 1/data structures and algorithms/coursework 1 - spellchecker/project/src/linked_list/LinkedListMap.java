package linked_list;

import java.util.Iterator;
import java.util.LinkedList;

import exceptions.MapException;
import interfaces.IMap;

public class LinkedListMap implements IMap {
	private LinkedList<String> list;

	LinkedListMap() {
		list = new LinkedList<String>();
	}

	@Override
	public void insert(String key) throws MapException {
			list.add(key);
	}

	@Override
	public void remove(String key) throws MapException {
		if (find(key)) {
			list.remove(key);
		} else {
			throw new MapException("Value not found.");
		}

	}

	@Override
	public boolean find(String key) {
		return list.contains(key);
	}

	@Override
	public int numberOfElements() {
		return list.size();
	}

	@Override
	public Iterator<String> elements() {
		return list.listIterator();
	}

}
