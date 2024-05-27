import java.util.*;

public class TreeTest{

	public static void main(String[] args){
		System.out.println("Testing HeapTree:");
        HeapTree<Integer> heapTree = new HeapTree<>();
        heapTree.addRoot(10);
        System.out.println(heapTree.size());
        Position<Integer> leftChild = heapTree.addLeft(heapTree.root(), 5);
        System.out.println(heapTree.size());
        Position<Integer> rightChild = heapTree.addRight(heapTree.root(), 8);
        System.out.println(heapTree.size());
        Position<Integer> test = heapTree.getParent(heapTree.getLast());

        heapTree.addLeft(leftChild, 3);
        System.out.println(heapTree.size());
        heapTree.addRight(leftChild, 7);
        System.out.println(heapTree.size());
        heapTree.addLeft(rightChild, 2);
        System.out.println(heapTree.size());
        heapTree.addLast(9);
        System.out.println(heapTree.size());
        //heapTree.addLast(11);
        //heapTree.addLast(12);

        System.out.println("HeapTree Before Swap:");
        System.out.println(heapTree);
        System.out.println(heapTree.getLast().getElement());

        // Testing swapping nodes
        System.out.println("Swapping leftChild and rightChild:");
        heapTree.swap(leftChild, rightChild);
        System.out.println(heapTree);
        


		TreePQ<Integer, Integer> pq = new TreePQ<>();
		for (int i = 10; i > 0; i--){
			pq.insert(i,i);
			System.out.printf("%s\n", pq);
		}

		System.out.printf("%s\n", pq);

		System.out.println();

		TreePQ<Integer, Integer> qr = new TreePQ<>();
		for (int i = 1; i <= 10; i++){
			qr.insert(i,i);
			System.out.printf("%s\n", qr);
		}

		System.out.printf("%s\n", qr);

		ArrayList<Integer> path = new ArrayList<Integer>();
		int newNodeLocation = 0+1;
		for (int i = newNodeLocation; i > 1; i /= 2){
			path.add(i%2);
		}
		for (int i = 0; i<path.size(); i++){
			System.out.println(path.get(i));
		}

		System.out.println();

		for (int i = path.size()-1; i>=0; i--){
			System.out.println(path.get(i));
		}

		/* System.out.println("\nTesting TreePQ:");
        TreePQ<Integer, String> treePQ = new TreePQ<>();

        treePQ.insert(10, "A");
        treePQ.insert(5, "B");
        treePQ.insert(8, "C");
        treePQ.insert(3, "D");
        treePQ.insert(7, "E");
        treePQ.insert(2, "F");

        System.out.println("Priority Queue (Min Heap) before removal:");
        while (!treePQ.isEmpty()) {
            Entry<Integer, String> entry = treePQ.removeMin();
            System.out.println(entry);
        }*/
    
    }
   
}