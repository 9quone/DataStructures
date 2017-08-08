// Abhinav Arya
// Heaps-A4: Remove Root

import java.util.Arrays;

/* 
 * Notes: 
 * Sample data tested: 71 43 65 36 41 22 38 35 17 6 10 16 4 14 22
 * Test results are as expected. Max-heap property maintained even after removing the root.
 * Program works as desired. Rigorous testing: Removed all elements in the heap.
 * Testing helped me uncover a bug when removing the penultimate element in the heap,
 * which was due to the if statement in the removeRoot method being off by 1.
 * 
 * removeRoot returns null if the heap is empty. However, nothing is printed
 * due to the design of the print method. Included an isEmpty method for testing purposes
 * (to iterate until all elements have been removed).
 */

/**
 * Tester for the Heap class.
 * @author abhi
 */
public class YoHeapTester {
	/**
	 * Adds values to the heap to check if max-Heap property is maintained.
	 * Removing root value and ensuring max-Heap property is preserved.
	 */
	public static void main(String[] args) {
		Heap heap = new Heap();
		heap.add(36);
		heap.add(41);
		heap.add(22);
		heap.add(35);
		heap.add(6);
		heap.add(16);
		heap.add(14);
		heap.add(65);
		heap.add(17);
		heap.add(10);
		heap.add(43);
		heap.add(38);
		heap.add(4);
		heap.add(22);
		heap.add(71);
		heap.print();
		
		while (!heap.isEmpty()) {
			Comparable root = heap.removeRoot();
			System.out.println("\nRemoving root (" + root + ") ...\n");
			heap.print();
		}
	}
}

/**
 * Represents a max Heap data structure.
 * @author abhi
 */
class Heap {
	private Comparable[] elements;
	private int end;
	
	/**
	 * Constructs an empty heap.
	 */
	public Heap() {
		final int defaultSize = 10;
		elements = new Comparable[defaultSize];
		end = 1;
	}
	
	/**
	 * Adds a new element to this heap.
	 */
	public void add(Comparable newElement) {
		end++;
		if (end >= elements.length) {
			grow();
		}
		
		int pos = end - 1;
		while (pos > 1 && getParent(pos).compareTo(newElement) < 0) {
			elements[pos] = getParent(pos);
			pos = getParentIndex(pos);
		}
		
		elements[pos] = newElement;
	}
	
	/**
	 * Removes the root of the max-Heap.
	 * @return the maximum element (root) or null if the heap is empty.
	 */
	public Comparable removeRoot() {
		Comparable root = elements[1];
		Comparable last = elements[end - 1];
		
		elements[end - 1] = null;
		end--;
		
		if (end > 1) {
			elements[1] = last;
			fixHeap();
		}
		
		return root;
	}
	
	/**
	 * Returns true if the heap is empty.
	 */
	public boolean isEmpty() {
		return (elements[1] == null);
	}
	
	/**
	 * Restores the max-Heap property after the root is removed.
	 */
	private void fixHeap() {
		Comparable root = elements[1];
		int index = 1;
		boolean hasMore = true;
		
		while (hasMore) {
			// Assume Left child is larger
			int childIndex = getLeftChildIndex(index);
			if (childIndex <= end - 1) {
				Comparable child = getLeftChild(index);
				
				// Use Right child if larger than Left child
				if (getRightChildIndex(index) <= end - 1
						&& getRightChild(index).compareTo(child) > 0) {
					childIndex = getRightChildIndex(index);
					child = getRightChild(index);
				}
				
				// Check if child is larger than root
				if (child.compareTo(root) > 0) {
					// Promote child
					elements[index] = child;
					index = childIndex;
				} else {
					// Root is larger
					hasMore = false;
				}
			} else {
				// No more children
				hasMore = false;
			}
		}
		// Restoring root
		elements[index] = root;
	}
	
	/**
	 * Prints out the heap.
	 */
	public void print() {
		int pos = 1;
		int power = 0;
		while (pos <= end - 1) {
			for (int i = 0; i < Math.pow(2, power); i++) {
				if (elements[pos] != null) {
					System.out.print(elements[pos] + " ");
					pos++;
				}
			}
			System.out.println();
			power++;
		}
	}
	
	/**
	 * Grows the base array by copying values to larger array.
	 */
	private void grow() {
		elements = Arrays.copyOf(elements, elements.length * 2);
	}
	
	/**
	 * Returns the index of the left child.
	 */
	private static int getLeftChildIndex(int index) {
		return 2 * index;
	}
	
	/**
	 * Returns the index of the right child.
	 */
	private static int getRightChildIndex(int index) {
		return 2 * index + 1;
	}
	
	/**
	 * Returns the index of the parent.
	 */
	private static int getParentIndex(int index) {
		return index / 2;
	}
	
	/**
	 * Returns the value of the left child.
	 */
	private Comparable getLeftChild(int index) {
		return elements[2 * index];
	}
	
	/**
	 * Returns the value of the right child.
	 */
	private Comparable getRightChild(int index) {
		return elements[2 * index + 1];
	}
	
	/**
	 * Returns the value of the parent.
	 */
	private Comparable getParent(int index) {
		return elements[index / 2];
	}
}
