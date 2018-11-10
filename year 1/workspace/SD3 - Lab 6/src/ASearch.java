public class ASearch {

	private Entry[] catalogue;
	private int current;

	/*
	 * Assume 10 entries
	 */
	public ASearch() {
		catalogue = new Entry[10];
		current = 0;
	}

	/*
	 * Ignores adding if full (should really be handled by exception...)
	 */
	public void addEntry(Entry e) {
		if (current < 10) {
			catalogue[current++] = e;
		}
	}

	/*
	 * Part 2: complete implementation
	 */
	public int linearSearch(String name) {
		for (int i = 0; i < catalogue.length; i++) {
			if (name.equals(catalogue[i].getName())) {
				return 1;
			}
		}
		return -1;
	}

	/*
	 * Part 4: complete implementation
	 */
	public int binarySearch(int first, int last, String name) {
		if (catalogue[(last + first) / 2].getName().compareTo(name) > 0) {
			if (name != catalogue[(last + first) / 2].getName()) {
				binarySearch(first, (last + first) / 2, name);
			} else if (name ==catalogue[(last + first) / 2].getName()){
				return 1;
			}
		} else {
			if (name != catalogue[(last + first) / 2].getName()) {
				binarySearch((last + first) / 2, last, name);
			} else if(name ==catalogue[(last + first) / 2].getName()) {
				return 1;
			}
		}

		return -1;
	}

	// helper method
	public int bSearch(String name) {
		return binarySearch(0, current, name);
	}

}
