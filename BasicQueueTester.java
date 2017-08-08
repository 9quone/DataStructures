import java.util.NoSuchElementException;

/**
 * Tester class for BasicQueue
 */
public class BasicQueueTester {
	/**
	 * Various tests to validate if the BasicQueue works properly.
	 */
	public static void main(String[] args) {
		BasicQueue<String> q = new BasicQueue<String>();
		System.out.println("Q is empty: "+ q.isEmpty());
		System.out.println("Now adding H, E, L, L, O, B, C, P");
		q.add("H");
		q.add("E");
		q.add("L");
		q.add("L");
		q.add("O");
		q.add("B");
		q.add("C");
		q.add("P");

		System.out.println("The top element is: " + q.peek());

		while (!q.isEmpty()){
		  System.out.println("removing: " + q.remove());
		}
		System.out.println("Is it empty: " + q.isEmpty());
		System.out.println("Now adding 1");
		q.add("1");
		System.out.println("Is it empty: " + q.isEmpty());
		System.out.println("Now adding 2, 3, 4, 5");
		q.add("2");
		q.add("3");
		q.add("4");
		q.add("5");
		System.out.println("The top element is: " + q.peek());
		System.out.println("Removing " + q.remove() );
		System.out.println("Removing " + q.remove() );
		System.out.println("Now adding 7, 8, 9, 10, 11, 12");
		q.add("7");
		q.add("8");
		q.add("9");
		q.add("10");
		q.add("11");
		q.add("12"); 
		System.out.println("Now adding Last");
		q.add("Last");
		System.out.println("The top element is: " + q.peek());
		System.out.println("The size is: " + q.size());

		while (!q.isEmpty()){
		  System.out.println("removing: " + q.remove());
		}
		
		/* Causes an error because my BasicQueue has a max capacity of 10.
		for(int i = 0; i < 10000; i++ )
		q.add( "" + i );
		*/ System.out.println();
		
		BasicQueue<Integer> qInt = new BasicQueue<Integer>();
		System.out.println("The Q is empty: " + qInt.isEmpty());
		System.out.println("Now adding 3, 1, 4, 1, 5, 9, 2, 6");
		qInt.add(3);
		qInt.add(1);
		qInt.add(4);
		qInt.add(1);
		qInt.add(5);
		qInt.add(9);
		qInt.add(2);
		qInt.add(6);
		System.out.println("The top element is: " + qInt.peek());
		System.out.println("Removing: " + qInt.remove());
		System.out.println("Removing: " + qInt.remove());
		System.out.println("Removing: " + qInt.remove());
		System.out.println("The top element is: " + qInt.peek());
		System.out.println("Current size: " + qInt.size());
		System.out.println("Addding 5, 3, 5, 8, 9");
		qInt.add(5);
		qInt.add(3);
		qInt.add(5);
		qInt.add(8);
		qInt.add(9);
		System.out.println("Current size: " + qInt.size());
		System.out.println("Adding 2 to overflow queue and cause an error");
		qInt.add(2);
	}
}

/**
 * Basic queue implemented as a circular array.
 */
class BasicQueue<T> {
	private Object[] elements;
	private int size;
	private int head;
	private int tail;
	
	/**
	 * Constructs an empty queue.
	 */
	public BasicQueue() {
		final int initSize = 10;
		
		elements = new Object[initSize];
		size = 0;
		head = 0;
		tail = 0;
	}
	
	/**
	 * Returns true if the queue is empty.
	 */
	public boolean isEmpty() {
		return (size == 0);
	}
	
	/**
	 * Adds an element to the tail of this queue.
	 */
	public boolean add(T newElement) {
		if (size == 10) {
			throw new Error("Queue is full");
		}
		size++;
		elements[tail] = newElement;
		tail = (tail + 1) % elements.length;
		
		return true;
	}
	
	/**
	 * Removes an element from the head of the queue and returns it.
	 */
	@SuppressWarnings("unchecked")
	public T remove() {
		if (size == 0) {
			throw new NoSuchElementException();
		}
		Object removed = elements[head];
		head = (head + 1) % elements.length;
		size--;
		
		return (T)removed;
	}
	
	/**
	 * Returns an element at the end of the queue.
	 */
	@SuppressWarnings("unchecked")
	public T peek() {
		return (T)elements[head];
	}
	
	/**
	 * Returns the size of the queue.
	 */
	public int size() {
		return size;
	}
}