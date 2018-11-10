import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class Q2 {

	/*
	 * Part 2: complete method
	 */
	public static void sort(int[] arr) {
		PriorityQueue pri_que = new PriorityQueue(arr.length);
		for(int x=0; x<arr.length;x++){
			pri_que.insert(arr[x]);
		}
		for(int y=0; y<pri_que.getHeap().length-1; y++){
			arr[y]=pri_que.min();
			pri_que.removeMin();
		}
	}

	public static void main(String[] args) {
		int[] arr = { 53, 3, 5, 2, 4, 67 };
		Q2.sort(arr);
		// should be printed in order
		System.out.println(arr[0]);
		System.out.println(arr[1]);
		System.out.println(arr[2]);
		System.out.println(arr[3]);
		System.out.println(arr[4]);
		System.out.println(arr[5]);
	}

}
