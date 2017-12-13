package hash_table;

import interfaces.*;

public class StringHashCode implements IHashCode {

	@Override
	public int giveCode(Object o) {
		String word = (String) o;
		char[] char_array = word.toCharArray();
		int hash = 7;
		for (int x = 0; x < char_array.length; x++) {
		    hash = hash*31 + char_array[x];
		}
		return Math.abs(hash);
	}

}
