/*
THIS CODE WAS MY OWN WORK, IT WAS WRITTEN WITHOUT CONSULTING ANY
SOURCES OUTSIDE OF THOSE APPROVED BY THE INSTRUCTOR. ETHAN YANG
*/

import java.util.*;

public class HeapTree<E> extends LinkedBinaryTree<E>{

	public HeapTree() {super(); }

	// accessor methods
	protected Position<E> getParent(Position<E> p) {return parent(p);}
	protected Position<E> getLeft(Position<E> p) {return left(p); }
	protected Position<E> getRight(Position<E> p) {return right(p); }

	protected Position<E> getLast(){
		if(isEmpty()){ return null; }							// do nothing if the heap is empty
		Position<E> current = root();
		ArrayList<Integer> path = new ArrayList<Integer>();		// to record the path
		int lastLocation = size();								// location of the bottom leftmost key equals to the size of the heap
		for (int i = lastLocation; i > 1; i /= 2){				// record to go left or right to go to the bottom leftmost key
			path.add(i%2);
		}
		for (int i = path.size()-1; i >= 0; i--){				// traverse the array from the back
			if (path.get(i) == 1){
				current = getRight(current);					// if 1, go right
			}
			else if (path.get(i) == 0){							// if 0, go left
				current = getLeft(current);
			}
		}
		return current;
	}

	// return true or false telling if a parent have a right or left child
	protected boolean hasLeft(Position<E> p) {return left(p) != null;}
	protected boolean hasRight(Position<E> p) {return right(p) != null;}

	protected void swap(Position<E> p, Position<E> pp){
		E temp = p.getElement();								// swap 2 elements at the given positions
		set(p, pp.getElement());
		set(pp, temp);
	}

	// add an element at the bottom leftmost position
	protected void addLast(E newest){
		Position<E> current = root();
		ArrayList<Integer> path = new ArrayList<Integer>();
		int newNodeLocation = size()+1;							// the position of where the newly added node will be
		if (newNodeLocation == 1){
			addRoot(newest);									// if the newest location is 1, the tree is empty, so we add the root
		}
		for (int i = newNodeLocation; i > 1; i /= 2){			// record the path like getLast() 
			path.add(i%2);
		}
		for (int i = path.size()-1; i >= 0; i--){				// traverse the path like getLast()
			if (path.get(i) == 1){
				if (getRight(current) != null){					// if a node exist, keep going down the tree
					current = getRight(current);
				}
				else if (getRight(current) == null){			// if no nodes exist, add a new node
					addRight(current, newest);
				}
			}
			else if (path.get(i) == 0){
				if (getLeft(current) != null){					// the same as above but for going left
					current = getLeft(current);
				}
				else if (getLeft(current) == null){
					addLeft(current, newest);
				}
			}
		}
	}
}

