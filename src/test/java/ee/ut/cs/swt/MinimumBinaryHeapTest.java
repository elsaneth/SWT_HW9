package ee.ut.cs.swt;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class MinimumBinaryHeapTest {

	private MinimumBinaryHeap heap;
	private List<Integer> result;

	@BeforeEach
	public void setUp() {
		heap = new MinimumBinaryHeap();
		result = new ArrayList<>();
		result.add(10);
		result.add(5);
		result.add(4);
		result.add(8);
		result.add(12);
	}

	@Test
	public void minHeapifyTest() {
		heap.minHeapify(result);
		assertEquals(4, heap.getArray().get(0), 0);
		assertEquals(5, heap.getArray().get(1), 0);
		assertEquals(10, heap.getArray().get(2), 0);

	}

	@Test
	public void extractMinTest() {
		heap.add(7);
		heap.add(1);
		heap.add(6);
		int min = heap.exractMin();
		assertEquals(1, min);
	}

	@Test
	public void swapTest() {
		heap.add(5);
		heap.add(8);
		heap.add(13);
		heap.swap(1, 2);
		assertEquals(5, heap.getArray().get(0), 0);
		assertEquals(13, heap.getArray().get(1), 0);
		assertEquals(8, heap.getArray().get(2), 0);
	}

	@Test
	public void addTest() {
		heap.add(10);
		heap.add(5);
		heap.add(4);
		heap.add(8);
		heap.add(12);
		assertEquals(4, heap.getArray().get(0), 0);
		assertEquals(5, heap.getArray().get(1), 0);
		assertEquals(10, heap.getArray().get(2), 0);
	}

	@Test
	public void removeTest() {
		heap.add(5);
		heap.add(3);
		heap.add(2);
		boolean b1 = heap.remove(3);

        assertTrue(b1);
	}

	@Test
	public void isEmptyTest() {
		heap = new MinimumBinaryHeap();
        assertTrue(heap.isEmpty());
	}

	@Test
	public void removeMinGetNewMin() {
		heap.add(10);
		heap.add(5);
		heap.add(15);
		heap.add(4);
		heap.add(8);

		heap.remove(4);

		assertEquals(5, heap.getArray().get(0), "The minimum value after removal should be 5");
	}

	@Test
	public void isEmptyAfterRemovingAll() {
		heap.add(10);
		heap.add(5);

		heap.remove(5);
		heap.remove(10);

        assertTrue(heap.isEmpty(), "Should be empty after removing");
	}

	@Test
	public void extractMinMultipleTimes() {
		heap.add(10);
		heap.add(20);
		heap.add(5);
		heap.add(15);

		int firstMin = heap.exractMin();  // Extracts 5
		int secondMin = heap.exractMin();  // Extracts 10
		int thirdMin = heap.exractMin();  // Extracts 15

		assertEquals(5, firstMin, "First minimum should be 5");
		assertEquals(10, secondMin, "Second minimum should be 10");
		assertEquals(15, thirdMin, "Third minimum should be 15");
	}

	@Test
	public void isParentSmallerThanChild() {
		heap.add(10);
		heap.add(20);
		heap.add(5);
		heap.add(15);
		heap.add(40);

		heap.remove(5);
		heap.add(3);
		heap.add(50);
		heap.add(15);
		heap.add(15);

		List<Integer> heapArray = heap.getArray();
		int size = heapArray.size();
		assertEquals(8, size);

		for (int i = 0; i < size; i++) {
			int leftChildIndex = 2 * i + 1;
			int rightChildIndex = 2 * i + 2;

			if (leftChildIndex < size) {
				assertTrue(heapArray.get(i) <= heapArray.get(leftChildIndex), "Parent should be smaller than the left child");
			}
			if (rightChildIndex < size) {
				assertTrue(heapArray.get(i) <= heapArray.get(rightChildIndex), "Parent should be smaller than the right child");
			}
		}
	}

	@Test
	public void doesMinHeapifyAlterOriginalList() {
		List<Integer> original = List.of(20, 30, 10, 40, 50, 15);
		assertTrue(heap.isEmpty(), "Heap should be empty before minHeapify");
		heap.minHeapify(original);

		List<Integer> expectedOriginal = List.of(20, 30, 10, 40, 50, 15);
		assertEquals(expectedOriginal, original, "Original list should not be altered");

		assertFalse(heap.isEmpty(), "Heap should not be empty after minHeapify");
	}

	@Test
	public void isTrueAfterRemoving() {
		heap.add(5);
		heap.add(2);
		assertTrue(heap.remove(5));
	}

	@Test
	public void extractMinFromEmptyHeap() {
		assertThrows(IllegalArgumentException.class, () -> {
			heap.exractMin();
		});
	}

//	@Test
//	public void testSwap() {
//		heap.add(9);
//		heap.add(10);
//		List<Integer> heapArr = heap.getArray();
//		heap.swap(0, 1);
//		assertEquals(10, heapArr.get(0));
//		assertEquals(9, heapArr.get(1));
//	}

}

