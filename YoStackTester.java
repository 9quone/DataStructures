import java.util.EmptyStackException;
import java.util.LinkedList;

/**
 * Tester class for YoStack.
*/
public class YoStackTester {
	/**
	 * Tests the YoStack by calling methods on a YoStack<String>
	 */
	public static void main(String[] args) {
		YoStack<String> dishes = new YoStack<String>();
		System.out.println("Is it empty: " + dishes.isEmpty());
		System.out.println("Now pushing H, E, L, L, O");
		dishes.push("H");
		dishes.push("E");
		dishes.push("L");
		dishes.push("L");
		dishes.push("O");

		System.out.println("The top element is: " + dishes.peek());

		while (!dishes.isEmpty()){
			System.out.println("Popping: "+dishes.pop());
		}
		System.out.println("Is it empty: " + dishes.isEmpty());
		System.out.println("Now pushing 1");
		dishes.push("1");
		System.out.println("Is it empty: " + dishes.isEmpty());
		System.out.println("Now pushing 2, 3, 4, 5");
		dishes.push("2");
		dishes.push("3");
		dishes.push("4");
		dishes.push("5");
		System.out.println("The top element is: " + dishes.peek());
		System.out.println("Removing " + dishes.pop() );
		System.out.println("Removing "+ dishes.pop() );
		System.out.println("Now pushing Last");
		dishes.push("Last");
		System.out.println("The top element is: " + dishes.peek());

		while (!dishes.isEmpty()){
			System.out.println("Popping: " + dishes.pop());
		}
	}
}

/**
 * Creates a generic stack using LinkedList.
 */
class YoStack<E> {
	private LinkedList<E> list = new LinkedList<E> ();
	
	/**
	 * Adds an object to the top of the stack.
	 */
	@SuppressWarnings("unchecked")
	public void push(Object item) {
		list.addFirst((E) item);
	}
	
	/**
	 * Removes the top value of the stack or throws an EmptyStackException if the stack is empty.
	 */
    public Object pop() {
    	if (list.size() == 0) {
    		throw new EmptyStackException();
    	} else {
    		return list.removeFirst();
    	}
    }
    
    /**
     * Returns the top value of the stack without modifying the stack. 
     * An EmptyStackException is thrown if the stack if empty.
     */
    public Object peek() {
    	if (list.size() == 0) {
    		throw new EmptyStackException();
    	} else {
    		return list.getFirst();
    	}
    }
    
    /**
     * Returns the size of the stack.
     */
    public int size() {
    	return list.size();
    }
    
    /**
     * Checks if the stack is empty.
     */
    public boolean isEmpty() {
    	return list.isEmpty();
    }
}