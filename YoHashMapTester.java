import java.util.ArrayList;

/**
 * YoHashMap stores the key and its value as a pair using the Data inner class.
 * I have an array of ArrayList<Data> to function serve as the buckets. I determine which bucket the data
 * (key-value pair) goes into based on the length of the key string. If the ArrayList<Data> the data has to 
 * go into already contains data with the same key as the incoming data, I remove the old data and return its value. 
 * The remove and get methods work by hashing the key and then searching the ArrayList<Data> at the position 
 * defined by length of the key string for data whose key matches the target. Either returns data.value or removes data.
 * 
 * Testing: All test cases passed. YoHashMap accurately handles collisions and cases where an object 
 * is put with the same key as an existing element in the YoHashMap. All methods tested.
 */

/**
 * Tester class for HashMap
 * @author abhi
 */
public class YoHashMapTester {
	/**
	 * Tests the HashMap by putting and removing elements.
	 */
	public static void main(String[] args) {
		YoHashMap h = new YoHashMap();
		
		System.out.println("Putting zero, 0 ...");
		h.put("zero", new Integer(0));
		System.out.println(h.get("zero"));
		System.out.println();
		
		System.out.println("Putting four, 4 ... (testing collision)");
		h.put("four", new Integer(4));
		System.out.println(h.get("four"));
		System.out.println();
		
		System.out.println("Putting twenty-seven, 27 ...");
		h.put("twenty-seven", new Integer(27));
		System.out.println(h.get("twenty-seven"));
		System.out.println("Size: " + h.size());
		System.out.println();
		
		System.out.println("Removing four, 4 ...");
		h.remove("four");
		System.out.println("HashMap contains four? " + h.containsKey("four"));
		System.out.println("Size: " + h.size());
		System.out.println();
		
		System.out.println("Putting twenty-seven, 270 ... (testing whether old value with same key is removed)");
		h.put("twenty-seven", new Integer(270));
		System.out.println(h.get("twenty-seven"));
	}
}

/**
 * Represents a basic HashMap data structure.
 * @author abhi
 */
class YoHashMap {
	private ArrayList<Data>[] buckets;
	private int size;
	
	/**
	 * Constructs an empty HashMap with the default initial capacity (16).
	 */
	public YoHashMap() {
		buckets = (ArrayList<Data>[])new ArrayList[16];
		size = 0;
	}
	
	/**
	 * Represents a Key-Value pair.
	 * @author abhi
	 */
	class Data {
		private String key;
		private Object value;
		
		public Data(String str, Object val) {
			key = str;
			value = val;
		}
	}
	
	/**
	 * Associates the specified value with the specified key in this map.
	 * If the map previously contained a mapping for this key, the old value is replaced.
	 * 
	 * @return previous value associated with specified key, or null if there was no mapping for key.
	 */
	public Object put(String key, Object value) {
		Data insert = new Data(key, value);
		int hash = key.length();
		hash = hash % buckets.length;
		
		if (buckets[hash] == null) {
			buckets[hash] = new ArrayList<Data>();
		}
		for (int i = 0; i < buckets[hash].size(); i++) {
			if (buckets[hash].get(i).key.equals(insert.key)) {
				Data removed = buckets[hash].set(i, insert);
				return removed.value;
			}
		}
		buckets[hash].add(insert);
		size++;
		return null;
	}
	
	/**
	 * Removes the mapping for this key from this map if present.
	 * 
	 * @return previous value associated with specified key, or null if there was no mapping for key.
	 */
	public Object remove(String key) {
		int hash = key.length();
		hash = hash % buckets.length;
		
		for (int i = 0; i < buckets[hash].size(); i++) {
			if (buckets[hash].get(i).key.equals(key)) {
				Data removed = buckets[hash].remove(i);
				size--;
				return removed.value;
			}
		}
		return null;
	}
	
	/**
	 * Returns the value to which the specified key is mapped in this identity hash map.
	 */
	public Object get(String key) {
		int hash = key.length();
		hash = hash % buckets.length;
		
		for (Data d : buckets[hash]) {
			if (d.key.equals(key)) {
				return d.value;
			}
		}
		return null;
	}
	
	/**
	 * Returns true if this map contains a mapping for the specified key.
	 */
	public boolean containsKey(String key) {
		return get(key) != null;
	}
	
	/**
	 * Returns the number of key-value mappings in this map.
	 */
	public int size() {
		return size;
	}
}