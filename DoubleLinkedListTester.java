import java.util.NoSuchElementException;
import java.util.ListIterator; 

/**
 * Illustrates generic implementation of Linked List.
 * Tester class for DoubleLink<E>.
 */
public class DoubleLinkedListTester<E> {
	public static void main(String[] args) {
		DoubleLink<Integer> list = new DoubleLink<Integer>();
		ListIterator<Integer> iterator = list.iterator();
		
		list.add(10);
		list.add(20);
		list.add(30);
		list.add(40);
		list.add(50);
		System.out.println(list);
		
		list.reverse();
		System.out.println(list);
	}
}

/**
 * Generic Doubly Linked List class.
 */
class DoubleLink<E> implements Iterable<E >{
	private int size;
	private Node pre;
	private Node post;
	
	private class Node { 
		public E data;
		public Node next;
		private Node prev;
	}
	
	public DoubleLink() {
		pre = new Node();
		post = new Node();
		pre.next = post;
        	post.prev = pre;
	}
	
	/**
	 * Checks if DLL is empty.
	 */
	public boolean isEmpty() { 
		return size == 0; 
	}
	
	/**
	 * Returns the size of the DLL.
	 */
    public int size() { 
    	return size;      
    }
    
    /**
     * Adds an item to the end.
     */
    public void add(E item) {
        Node last = post.prev;
        Node x = new Node();
        x.data = item;
        x.next = post;
        x.prev = last;
        post.prev = x;
        last.next = x;
        size++;
    }
    
    /**
     * Reverses the order of the elements.
     */
    public void reverse() {
    	Node temp = pre;
    	pre = post;
    	post = temp;
    	Node p = pre;

    	while(p != null) { 
    	    temp = p.next; 
    	    p.next = p.prev; 
    	    p.prev = temp; 
    	    p = p.next;
    	}
    }
    
    public ListIterator<E> iterator()  { 
    	return new DoublyLinkedListIterator(); 
    }
    
    /**
     * Iterates through the Doubly Linked List.
     */
    private class DoublyLinkedListIterator implements ListIterator<E> {
        private Node current = pre.next;
        private Node before = null;
                                               
        private int index = 0;

        public boolean hasNext() { 
        	return index < size; 
        }
        
        public boolean hasPrevious() { 
        	return index > 0; 
        }
        
        public int previousIndex() { 
        	return index - 1; 
        }
        
        public int nextIndex() { 
        	return index;     
        }
        
        /**
         * Iterates to the next element.
         */
        public E next() {
            if (!hasNext()) throw new NoSuchElementException();
            before = current;
            E item = current.data;
            current = current.next; 
            index++;
            return item;
        }
        
        /**
         * Iterates to the previous element.
         */
        public E previous() {
            if (!hasPrevious()) throw new NoSuchElementException();
            current = current.prev;
            index--;
            before = current;
            return current.data;
        }
        
        /**
         * Sets an element at the position.
         */
        public void set(E item) {
            if (before == null) throw new IllegalStateException();
            before.data = item;
        }
        
        /**
         * Removes an element.
         */
        public void remove() { 
            if (before == null) throw new IllegalStateException();
            Node x = before.prev;
            Node y = before.next;
            x.next = y;
            y.prev = x;
            size--;
            if (current == before)
                current = y;
            else
                index--;
            before = null;
        }
        
        /**
         * Adds an item to the DLL.
         */
        public void add(E item) {
            Node x = current.prev;
            Node y = new Node();
            Node z = current;
            y.data = item;
            x.next = y;
            y.next = z;
            z.prev = y;
            y.prev = x;
            size++;
            index++;
            before = null;
        }
    }
    
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (E item : this)
            s.append(item + " ");
        return s.toString();
    }
}
