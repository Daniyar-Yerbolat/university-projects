import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class ArraySortTest {

	
	@Test
	public void testIsSortedEmpty() {
		int[] arr = {};
		assertTrue(ArraySort.isSorted(arr));
	}
	
	@Test
	public void testIsSortedTrue() {
		int[] arr = {1,2,3,67,78};
		assertTrue(ArraySort.isSorted(arr));
	}
	
	@Test
	public void testIsSortedFalse() {
		int[] arr = {1,2,3,7,5};
		assertFalse(ArraySort.isSorted(arr));
	}	
	
	@Test
	public void testIsSortedArrayListEmpty() {
		ArrayList<Integer> arr = new ArrayList<Integer>();
		assertTrue(ArraySort.isSorted(arr));
	}
	
	@Test
	public void testIsSortedArrayListTrue() {
		ArrayList<Integer> arr = new ArrayList<Integer>();
		arr.add(1);
		arr.add(2);
		arr.add(3);
		arr.add(5);
		assertTrue(ArraySort.isSorted(arr));
	}
	
	@Test
	public void testIsSortedArrayListFalse() {
		ArrayList<Integer> arr = new ArrayList<Integer>();
		arr.add(1);
		arr.add(2);
		arr.add(3);
		arr.add(5);
		arr.add(4);
		assertFalse(ArraySort.isSorted(arr));
	}		
	
	
	@Test
	public void testBubbleSortEmpty() {
		ArraySort arraysort = new ArraySort();
		int[] arr = {};
		arraysort.bubbleSort(arr);
		boolean sorted = arraysort.isSorted(arr);
		assertEquals(true, sorted);	
	}

	@Test
	public void testBubbleSortRandom() {
		ArraySort arraysort = new ArraySort();
		int[] arr = {6,2,22,33,18};
		arraysort.bubbleSort(arr);
		boolean sorted = arraysort.isSorted(arr);
		assertEquals(true, sorted);
	}
	
	@Test
	public void testBubbleSortOrdered() {
		ArraySort arraysort = new ArraySort();
		int[] arr = {1,2,3,4,5};
		arraysort.bubbleSort(arr);
		boolean sorted = arraysort.isSorted(arr);
		assertEquals(true, sorted);	
	}

	@Test
	public void testQuickSortSorted() {
		ArraySort arraysort = new ArraySort();
		ArrayList<Integer> arr = new ArrayList<Integer>();
		arr.add(1);
		arr.add(2);
		arr.add(3);
		arr.add(4);
		arr.add(5);
		arraysort.quickSort(arr);
		boolean sorted = arraysort.isSorted(arr);
		assertEquals(true, sorted);
	}

	@Test
	public void testQuickSortRandom() {
		ArraySort arraysort = new ArraySort();
		ArrayList<Integer> arr = new ArrayList<Integer>();
		arr.add(6);
		arr.add(22);
		arr.add(3);
		arr.add(18);
		arr.add(99);
		arraysort.quickSort(arr);
		boolean sorted = arraysort.isSorted(arr);
		assertEquals(true, sorted);
	}
	
	@Test
	public void testQuickSortEmpty() {
		ArraySort arraysort = new ArraySort();
		ArrayList<Integer> arr = new ArrayList<Integer>();
		arraysort.quickSort(arr);
		boolean sorted = arraysort.isSorted(arr);
		assertEquals(true, sorted);
	}
}
