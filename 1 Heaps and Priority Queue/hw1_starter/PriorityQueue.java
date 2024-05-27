public interface PriorityQueue<Key,Value> {
	int size();
	boolean isEmpty();
	Entry<Key,Value> insert(Key k, Value v);
	Entry<Key,Value> min();
	Entry<Key,Value> removeMin();
}