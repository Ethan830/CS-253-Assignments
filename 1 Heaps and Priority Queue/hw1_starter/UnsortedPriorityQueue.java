import java.util.ArrayList;
import java.util.Comparator;

public class UnsortedPriorityQueue<Key,Value> extends AbstractPriorityQueue<Key,Value> {
	private ArrayList<Entry<Key,Value>> list = new ArrayList<>();

	// Constructors

	public UnsortedPriorityQueue(){ super(); }

	public UnsortedPriorityQueue(Comparator<Key> comp) { super(comp); }

	// Returns index of entry with smallest key
	private int findMin() {
		int curMin = 0;

		for(int i = 1; i < list.size(); i++){
			if (compare(list.get(i), list.get(curMin)) < 0)
				curMin = i;
		}

		return curMin;
	}

	// Insert a key-value pair and returns the entry created
	public Entry<Key,Value> insert(Key k, Value v) throws IllegalArgumentException {
		checkKey(k);
		Entry<Key,Value> newest = new PQEntry<>(k, v);
		list.add(newest);
		return newest;
	}

	// Returns (does not remove) an entry with minimal key
	public Entry<Key,Value> min() {
		if (list.isEmpty()) return null;
		return list.get(findMin());
	}

	// Removes and returns an entry with minimal key
	public Entry<Key,Value> removeMin() {
		if (list.isEmpty()) return null;
		return list.remove(findMin());
	}

	// Return the number of elements in the priority queue
	public int size() { return list.size(); }

	public String toString(){
		String s = "{";
		if (isEmpty()) return s + "}";

		s += list.get(0);
		for(int i = 1; i < list.size(); i++){
			s+= (", " + list.get(i));
		}
		s += "}";

		return s;
	}
}