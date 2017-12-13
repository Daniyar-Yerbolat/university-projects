package hash_table;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

import exceptions.MapException;
import interfaces.IHashCode;
import interfaces.IHashTableMonitor;
import interfaces.IMap;

public class HashTableMap implements IHashTableMonitor, IMap {
	private String[] array;
	private int indexes_taken = 0;
	private float load_factor;
	private float max_load_factor;
	private int array_size = 7;
	private int number_of_probes = 0;
	private int number_of_operations = 0;
	private IHashCode hashcode;
	private Prime prime;
	private final int  MAX_PROBES = 20;

	public HashTableMap() throws MapException {
		throw new MapException("Hash function not given and load factor not set.");
	}

	public HashTableMap(IHashCode inputCode, float maxLoadFactor) {
		hashcode = inputCode;
		prime = new Prime();
		array = new String[array_size];
		max_load_factor = maxLoadFactor;
	}

	public void insert(String value) throws MapException {
		if (getLoadFactor() >= max_load_factor) {
			array_size = prime.getNextPrime(array_size);
			rehash();
		}
		number_of_operations++;
		value = value.toLowerCase().trim();
		int hash = hashcode.giveCode(value);
		int index = hash % array.length;
		if (array[index] == null) {
			array[index] = value;
			indexes_taken++;
			number_of_probes++;
		} else {
			int new_index = collision(index, hash, false, value);
			if (array[new_index] == null) {
				array[new_index] = value;
				indexes_taken++;
			}

		}
	}
	
	public void remove(String value) throws MapException {
		number_of_operations++;
		value = value.toLowerCase().trim();
		int hash = hashcode.giveCode(value);
		int index = hash % array.length;
		if (array[index] != null && array[index].equals(value)) {
			array[index] = null;
			indexes_taken--;
			number_of_probes++;
		} else {
			int new_index = collision(index, hash, true, value);
			if (array[new_index] != null && array[new_index].equals(value)) {
				array[new_index] = null;
				indexes_taken--;
			} else {
				throw new MapException("Value not found " + value);
			}

		}
	}

	private int collision(int index, int hash, boolean check, String value) {
		int j = 0;
		int x = index;
		int i = 0;

		// should return the index that matches the value - basically a
		// duplicate. Used for finding collided values. Increases the time
		// significantly.
		while (check == true && i<MAX_PROBES && j < array.length) {
			number_of_probes++;
			x = index;
			x = (index + (j * (7 - (hash % 7)))) % array.length;
			j++;
			i++;
			if (array[x] != null && array[x].equals(value)) {
				return x;
			}
		}
		// should return the first null index.
		while (check == false && array[x] != null && j < array.length) {
			number_of_probes++;
			x = index;
			x = (index + (j * (7 - (hash % 7)))) % array.length;
			j++;
		}
		return x;
	}

	public boolean find(String word) {
		number_of_operations++;
		word = word.toLowerCase().trim();
		int hash = hashcode.giveCode(word);
		int index = hash % array.length;
		if (array[index] != null && array[index].equals(word)) {
			number_of_probes++;
			return true;
		} else {
			int x = collision(index, hash, true, word);
			if (array[x] != null && array[x].equals(word)) {
				return true;
			} else {
				return false;
			}
		}
	}

	public float getLoadFactor() {
		load_factor = ((float) indexes_taken / (float) array.length);
		return load_factor;
	}

	public Iterator<String> elements() {
		int x = 0;
		ArrayList<String> l = new ArrayList<String>();
		while (x < array.length) {
			if (array[x] != null && array[x].equals("") == false) {
				l.add(array[x]);
			}
			x++;
		}
		Iterator<String> it = l.iterator();
		return it;

	}

	private void rehash() {
		String[] old_array = Arrays.copyOf(array, array.length);
		indexes_taken = 0;
		array = new String[array_size];
		int x = 0;
		while (x < old_array.length) {
			if (old_array[x] != null && (old_array[x].equals("") == false)) {
				try {
					insert(old_array[x]);
				} catch (MapException e) {
					e.printStackTrace();
				}
			}
			x++;
		}
	}

	public int numberOfElements() {
		return indexes_taken;
	}

	public float getMaxLoadFactor() {
		return max_load_factor;
	}

	public float averNumProbes() {
		return (float) number_of_probes / (float) number_of_operations;
	}
}
