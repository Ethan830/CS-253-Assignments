/*
THIS CODE WAS MY OWN WORK, IT WAS WRITTEN WITHOUT CONSULTING ANY
SOURCES OUTSIDE OF THOSE APPROVED BY THE INSTRUCTOR. ETHAN YANG
*/

import java.util.*;

public class TreePQ<K,V> extends AbstractPriorityQueue<K,V>{

	protected  HeapTree<Entry<K,V>> heap = new HeapTree<Entry<K,V>>();

	// constructors
	public TreePQ(){ super();}

	public TreePQ(Comparator<K> comp) {super(comp); }

	// return the size of the heap tree
	public int size(){
		return heap.size();
	}

	// returns the min element, which is the root
	public Entry<K,V> min() {
		if (heap.isEmpty()) return null;
		return heap.root().getElement();
	}

	// compare a node to its parent to put the heap in order
	protected void upheap(Position<Entry<K,V>> p){
		while(p != heap.root()){
			Position<Entry<K,V>> parent = heap.getParent(p);
			if (compare(p.getElement(), parent.getElement()) >= 0){		// compare the child with the parent (if child > parent)
				break;
			}
			heap.swap(p, parent);										// swap the child and the parent if the child is smaller
			p = parent;													// set the parent to the child for the next level
		}
	}

	// compare a node to its children to put the heap in order
	protected void downheap(Position<Entry<K,V>> p){
		while (heap.hasLeft(p)){										// check if the parent have a left child
			Position<Entry<K,V>> left = heap.getLeft(p);
			Position<Entry<K,V>> smallerChild = left;					// assume that the left child is smaller than the right child
			if (heap.hasRight(p)){										// check if the parent have a right child
				Position<Entry<K,V>> right = heap.getRight(p);
				if (compare(left.getElement(), right.getElement())>0){	// compare the 2 childs
					smallerChild = right;
				}
			}
			if (compare(smallerChild.getElement(), p.getElement())>=0){	// compare the parent with the smaller child
				break;
			}
			heap.swap(p, smallerChild);									// swap the parent and the smaller child
			p = smallerChild;
		}
	}

	// insert a new node to the heap tree
	public Entry<K,V> insert(K k, V v) throws IllegalArgumentException{
		checkKey(k);													// check if the key is valid
		Entry<K, V> newest = new PQEntry<>(k,v);		
		heap.addLast(newest);											// insert the new child to the leftmost position
		upheap(heap.getLast());											// upheap it to put it in order
		return newest;
	}

	// remove the minimum value from the heap tree
	public Entry<K,V> removeMin(){
		if (heap.isEmpty()) return null;								// if the heap is empty, there's nothing to do
        Entry<K, V> min = heap.root().getElement();
        if (heap.size() == 1)											// if the heap only has the root, remove the root
            heap.remove(heap.root());
        else {
            heap.swap(heap.root(), heap.getLast());						// swap the root with the leftmost key
            heap.remove(heap.getLast());								// remove the root that have been moved to the last position
            downheap(heap.root());										// downheap the key swapped with the root to its correct order
        }
        return min;
	}

	// print the heap out with brackets
	public String toString(){
        return heap.toString();
    }

}