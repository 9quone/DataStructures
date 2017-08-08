import java.util.Arrays;

/**
 * Replica of ArrayList.
 * Exploration of a Data Structure.
 * @author Abhi.Arya18
 */
public class YoArrayListTester {
	public static void main(String[] args) {
		YoArrayList<Integer> list = new YoArrayList<Integer>(5);
		
		list.add(10);
		list.add(20);
		list.add(30);
		list.add(40);
		list.add(50);
		
		System.out.println("Integer YoArrayList\n");
		
		printList(list);
		System.out.println("Size: " + list.size());
		System.out.println("Index of 30: " + list.indexOf(30));
		System.out.println("Index of 25: " + list.indexOf(25));
		System.out.println("Value to be Removed: " + list.remove(0) + "\n");
		
		list.add(1,25);
		list.set(3,35);
		list.add(55);
		
		printList(list);
		System.out.println("Size: " + list.size());
		System.out.println("Index of 25: " + list.indexOf(25));
		System.out.println("Value to be Set to 60: " + list.set(5,60) + "\n");
		
		list.add(100);
		list.add(6,75);
		
		printList(list);
		System.out.println("Size: " + list.size());
		System.out.println("Index of 60: " + list.indexOf(60) + "\n");
		
		list.remove(new Integer(25));
		list.remove(new Integer(35));
		list.remove(new Integer(75));
		list.remove(new Integer(60));
		
		list.remove(list.size()-1);
		list.remove(new Integer(70));
		
		list.add(0,10);
		list.add(3,40);
		
		printList(list);
		System.out.println("Size: " + list.size());
		System.out.println("Index of 40: " + list.indexOf(40) + "\n");
		
		System.out.println("String YoArrayList\n");
		
		YoArrayList<String> strList = new YoArrayList<String>();
		strList.add("Hello");
		strList.add("!");
		strList.add("World");
		
		printList(strList);
		System.out.println("Size: " + strList.size());
		System.out.println("Index of !: " + strList.indexOf("!") + "\n");
		
		String temp = strList.get(1);
		strList.set(1, strList.get(2));
		strList.set(2, temp);
		
		printList(strList);
		System.out.println("Index of !: " + strList.indexOf("!") + "\n");
		
		strList.remove("World");
		
		printList(strList);
		System.out.println("Size: " + strList.size());
	}
	
	// Prints the YoArrayList
	public static void printList(YoArrayList l) {
		for (int i = 0 ; i < l.size() ; i++) {
			System.out.print(l.get(i) + " ");
		}
		System.out.println();
		
	}
}

/**
 * Replica of ArrayList<E>
 * Stores Objects in an array, modifies size as more elements are added.
 * @author Abhi.Arya18
 */
class YoArrayList<E> {
	private Object[] list;
	private int index;
	
	/**
	 * Constructor. Ensures starter array has a length of size. 
	 * @param size
	 */
	public YoArrayList(int size) {
		list = new Object[size];
	}
	
	/**
	 * Default constructor. Creates starter array of length 10.
	 */
	public YoArrayList() {
		list = new Object[10];
	}
	
	/**
	 * Adds an Object value to the end of the array.
	 * @param value
	 */
	public void add(E value) {
		if (index == list.length)
			extend();
		
		list[index] = value;
		index++;
	}
	
	/**
	 * Adds an Object value to a specified location.
	 * @param value
	 * @param location
	 * @throws IndexOutOfBoundsException
	 */
	public void add(int location, E value) {
		if (location < 0 || location > size())
			throw new IndexOutOfBoundsException();
		
		if (size() == list.length)
			extend();
		
		for (int i = size() ; i > location ; i--)
			list[i] = list[i - 1];

		list[location] = value;
		index++;
	}
	
	/*
	 * Doubles the size of the array.
	 */
	private void extend() {
		list = Arrays.copyOf(list, list.length * 2);
	}
	
	/**
	 * Returns the Object at the specified location.
	 * @param location
	 * @return Object at location
	 * @throws IndexOutOfBoundsException
	 */
	public E get(int location) {
		if (location < 0 || location >= size())
			throw new IndexOutOfBoundsException();
		
		return (E)list[location];
	}
	
	/**
	 * Returns the index of Object in the YoArrayList, -1 if not found.
	 * @param value
	 * @return index of Object
	 */
	public int indexOf(E value) {
		for (int i = 0 ; i < size() ; i++) {
			if (value.equals(list[i]))
				return i;
		}
		return -1;
	}
	
	/**
	 * Replaces the Object at location with the specified value. 
	 * @param location
	 * @param value
	 * @return the Object being replaced
	 */
	public E set(int location, E value) {
		if (location < 0 || location >= size())
			throw new IndexOutOfBoundsException();
		
		Object previous = list[location];
		list[location] = value;
		return (E)previous;
	}
	
	/**
	 * Removes the element at the specified location. 
	 * Shifts subsequent elements to the left.
	 * @param location
	 * @return the original element that was removed
	 */
	public E remove(int location) {
		if (location < 0 || location >= size())
			throw new IndexOutOfBoundsException();
		
		if (list.length == size())
			extend();
		
		Object previous = list[location];
		list[location] = null;
		
		for (int i = location ; i < size() ; i++)
			list[i] = list[i+1];
		
		index--;
		return (E)previous;
	}
	
	/**
	 * Removes the first occurrence of the specified element.
	 * Shifts subsequent elements to the left.
	 * @param value
	 * @return true if element removed, false if element not found in the YoArrayList
	 */
	public boolean remove(E value) {
		for (int i = 0 ; i < size() ; i++) {
			if (value.equals(list[i])) {
				remove(i);
				return true;
			}	
		}
		return false;
	}
	
	/**
	 * Returns the number of Objects in the list.
	 * @return number of elements
	 */
	public int size() {
		return index;
	}
}
