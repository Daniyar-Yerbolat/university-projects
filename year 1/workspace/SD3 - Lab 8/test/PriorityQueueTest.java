import static org.junit.Assert.*;

import org.junit.Test;

public class PriorityQueueTest {

	/*
	 * Part 1: complete
	 */
	
	@Test
	public void insertTest() {
		PriorityQueue pri_que = new PriorityQueue(10);
		int[] heap =pri_que.getHeap();
		int[] array = new int[2];
		array[0] = 1;
		array[1] = 2;
		pri_que.insert(array[0]);
		pri_que.insert(array[1]);
		assertEquals(2,heap[pri_que.last]);
	}

	@Test
	public void removeMinTest() {
		PriorityQueue pri_que = new PriorityQueue(10);
		int[] heap =pri_que.getHeap();
		int[] array = new int[2];
		array[0] = 1;
		array[1] = 2;
		pri_que.insert(array[0]);
		pri_que.insert(array[1]);
		pri_que.removeMin();
		assertEquals(2,heap[pri_que.last]);
	}
	
}
