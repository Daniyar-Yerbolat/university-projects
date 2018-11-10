public class ASearch {


	private Entry[] catalogue;
	private int current;
	
	/*
	 * Assume 10 entries
	 */
	public ASearch(){
		catalogue = new Entry[10];
		current = 0;
	}
	
	/*
	 * Ignores adding if full (should really be handled by exception...)
	 */
	public void addEntry(Entry e){
		if(current < 10){
			catalogue[current++] = e;
		}
	}
	
	/*
	 * Part 2: complete implementation
	 */
	public int linearSearch(String name){
		for(int i = 0; i < current; i++){
			if(catalogue[i].getName().equals(name))
		        return catalogue[i].getNumber();
		  	}
		return -1;
	}

	/*
	 * Part 4: complete implementation
	 */
	public int binarySearch(int first,int last,String name){
		if (first > last) // failure case
			return -1;
		else {
			int middle = (first + last) / 2;
			String middle_name = catalogue[middle].getName();
			if (middle_name.equals(name)) // success case
				return catalogue[middle].getNumber(); 
			else if (middle_name.compareTo(name) > 0) // bottom half
				return binarySearch(first,middle-1,name); 
			else // top half
				return binarySearch(middle+1,last,name); 
		}
	}

	public int bSearch(String name){
		return binarySearch(0,current,name);
	}
	
	
}
