import java.util.*;

public class LinkedHeapPriorityQueue<K, V> extends AbstractPriorityQueue<K, V>{

	//protected ArrayList<Entry<K,V>> heap = new ArrayList<>();

	public HeapPriorityQueue() { super(); }

	public HeapPriorityQueue(Comparator<K> comp) { super(comp); }

	protected int parent(int j) {return (j-1) / 2;}
	protected int left(int j) { return 2*j + 1; }
	protected int right(int j) { return 2*j + 2; }
	protected boolean hasLeft(int j) { return left(j) < heap.size();}
	protected boolean hasRight(int j) { return right(j) < heap.size();}
	
	protected void swap(int i, int j) {
		
	}

	protected void upheap(int j) {
		
	}

	protected void downheap(int j) {
		
	}

	public int size() {
		
	}

	public Entry<K,V> min() {
		
	}

	public Entry<K,V> insert(K k, V v) throws IllegalArgumentException {
		
	}

	public Entry<K,V> removeMin() {
		
	}

	public String toString(){
		
	}

	public HeapPriorityQueue(K[] keys, V[] values) {
		
	}

	protected void heapify() {
		
	}

}