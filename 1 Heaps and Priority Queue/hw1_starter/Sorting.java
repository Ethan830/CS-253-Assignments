
import java.text.DecimalFormat;
import java.util.Arrays;

public class Sorting {
	

	
	//-------------------------------------------------------------
	//---------- Below is an implementation of Selection Sort -----
	//-------------------------------------------------------------		
	public static void SelectionSort(long[] a) {
		PriorityQueue<Long,Long> queue = new UnsortedPriorityQueue<>();
		for (int i = 0; i < a.length; i++) {
			queue.insert(a[i], a[i]);
		}

		int i = 0;
		while(!queue.isEmpty()){
			a[i++] = queue.removeMin().getKey();
		}
	}//SelectionSort()
	
	
	//-----------------------------------------------------------------------
	//---------- Below is an implementation of Insertion Sort ----------
	//-----------------------------------------------------------------------
	public static void InsertionSort(long[] a) {
		PriorityQueue<Long,Long> queue = new SortedPriorityQueue<>();
		for (int i = 0; i < a.length; i++) {
			queue.insert(a[i], a[i]);
		}

		int i = 0;
		while(!queue.isEmpty()){
			a[i++] = queue.removeMin().getKey();
		}
	}//InsertionSort()

	//-----------------------------------------------------------------------
	//---------- Below is an implementation of HeapSort ----------
	//-----------------------------------------------------------------------
	public static void HeapSort(Long[] a) {
		PriorityQueue<Long,Long> queue = new HeapPriorityQueue<>(a, a);
		// for (int i = 0; i < a.length; i++) {
		// 	queue.insert(a[i], a[i]);
		// }

		int i = 0;
		while(!queue.isEmpty()){
			a[i++] = queue.removeMin().getKey();
		}
	}//HeapSort()


	//-----------------------------------------------------------------------
	//---------- Below is an implementation of MergeSort ----------
	//-----------------------------------------------------------------------
	
    private static void merge(long[] a, long[] aux, int lo, int mid, int hi) {

        // copy to aux[]
        for (int k = lo; k <= hi; k++) {
            aux[k] = a[k]; 
        }

        // merge back to a[]
        int i = lo, j = mid+1;
        for (int k = lo; k <= hi; k++) {
            if      (i > mid)           a[k] = aux[j++];
            else if (j > hi)            a[k] = aux[i++];
            else if (aux[j] < aux[i]) 	a[k] = aux[j++];
            else                        a[k] = aux[i++];
        }
    }

    
    // mergesort a[lo..hi] using auxiliary array aux[lo..hi]
    private static void sort(long[] a, long[] aux, int lo, int hi) {
        if (hi <= lo) return;
        int mid = lo + (hi - lo) / 2;
        sort(a, aux, lo, mid);
        sort(a, aux, mid + 1, hi);
        merge(a, aux, lo, mid, hi);
    }

    public static void MergeSort(long[] a) {
        long[] aux = new long[a.length];
        sort(a, aux, 0, a.length-1);
    }
    
	//------------------------------------------------------
	//---------- below are several helper methods ----------
	//------------------------------------------------------
	
	// This tests whether your sorted result is correct by comparing it to reference result
	public static boolean testSort(long[] a) {
		long[] a2 = new long[a.length];
		System.arraycopy(a, 0, a2, 0, a.length);
		Arrays.sort(a);
		for(int i = 0; i < a.length; i++)
			if(a2[i] != a[i]) 
				return false;
		return true;
	}//testSort()
	
	
	// This creates an array with n randomly generated elements between (0, n*10]
	private static long[] randArray(int n) {
		long[] rand = new long[n];
		for(int i=0; i<n; i++)
			rand[i] = (int) (Math.random() * n * 10);
		return rand;
	}//randArray()
	
	private static void startTimer() { 
		timestamp = System.nanoTime();
	}//startTimer()
	
	private static double endTimer() {
		return (System.nanoTime() - timestamp)/1000000.0;
	}//endTimer()
	        
    // exchange a[i] and a[j]
    private static void exch(long[] a, int i, int j) {
        long swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }
	
	private static long timestamp;
	
	//---------------------------------------------
	//---------- This is the main method ----------
	//---------------------------------------------		
	public static void main(String[] args) {
	
		// run experiments
		final int SELECT = 0, INSERT = 1, HEAP = 2,  MERGE = 3;
		int[] algorithms = {SELECT, INSERT, HEAP, MERGE};
		
		// max defines the maximum size of the array to be tested, which is 2^max
		// runs defines the number of rounds to be performed per test, in order to get an average running time.
		int max = 14, runs = 5;
		double[][] stats = new double[algorithms.length][max];
		for(int i=0; i<algorithms.length; i++) {             //loop through each sorting algorithm
			switch(i) {
				case SELECT: System.out.print("Running Selection Sort ..."); break;
				case INSERT: System.out.print("Running Insertion Sort ..."); break;
				case HEAP: System.out.print("Running HeapSort ..."); break;
				case MERGE: System.out.print("Running MergeSort ..."); break;
			}//switch
			for(int j=0; j<max; j++) {        //loop through each array size 
				double avg = 0;
				for(int k=0; k<runs; k++) {    // loop through each run
					long[] a = randArray((int) Math.pow(2, j+1));
          Long[] b = new Long[a.length];
          for (int l = 0; l < a.length; l++)
            b[l] = a[l];
					startTimer();
					switch(i) {
						case SELECT: SelectionSort(a); break;
						case INSERT: InsertionSort(a); break;
						case HEAP: HeapSort(b); break;
						case MERGE: MergeSort(a); break;
					}//switch
					avg += endTimer();
          if (i == HEAP)
            for (int l = 0; l < a.length; l++)
              a[l] = b[l];
            
					if (testSort(a) == false)
						System.out.println("The sorting is INCORRECT!" + "(N=" + a.length + ", round=" + k + ").");
				}//for
				avg /= runs;
				stats[i][j] = avg;
			}//for
			System.out.println("done.");
		}//for
		
		DecimalFormat format = new DecimalFormat("0.0000");
		System.out.println();
		System.out.println("Average running time:");
		System.out.println("N\t Selection Sort\t Insertion Sort\t HeapSort\t MergeSort");
		for(int i=0; i<stats[0].length; i++) {
			System.out.print((int) Math.pow(2, i+1) + "\t  ");
			for(int j=0; j<stats.length; j++) {
				System.out.print(format.format(stats[j][i]) + "\t  ");
			}//for
			System.out.println();
		}//for
	}//main()
	
}//end of class
