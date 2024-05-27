/*
THIS CODE WAS MY OWN WORK, IT WAS WRITTEN WITHOUT CONSULTING ANY
SOURCES OUTSIDE OF THOSE APPROVED BY THE INSTRUCTOR. ETHAN YANG
*/

import java.text.DecimalFormat;
import java.util.Arrays;

public class Sorting {
	
	public static void countingSort(long[] a) {

		long[] b = new long[a.length];
		
		// Find the maximun number (k) in the array (a)
		long k = a[0];
		for (int i = 0; i < a.length; i++){
			if (a[i] > k){
				k = a[i];
			}
		}

		long[] c = new long[(int)k+1];

		// Tally the number of times a number appears in a in the array c
		for (int i = 0; i < a.length; i++){
			c[(int)a[i]]++;
		}
		
		// Aggregate the elements in c
		for (int i = 1; i < c.length; i++){
			c[i] += c[i-1];
		}

		// Build the sorted array 
		for (int i = a.length-1; i >= 0; i--){
			b[(int)c[(int)a[i]]-1] = a[i];
			c[(int)a[i]]--;
		}

		// Copy the sorted array b back to a
		for (int i = 0; i < a.length; i++){
			a[i] = b[i];
		}
	}

	// Version of counting sort specifically for radix sort
	public static void countingSortLSD(long[] a, int exp) {
		long[] b = new long[a.length];
		
		// Find the maximun number (k) in the array (a)
		long k = a[0];
		for (int i = 0; i < a.length; i++){
			if (a[i] > k){
				k = a[i];
			}
		}

		long[] c = new long[(int)k+1];

		// Tally the number of times a digit appears in a in the array c
		for (int i = 0; i < a.length; i++){
			c[((int)a[i]/exp)%10]++;
		}
		
		// Aggregate the elements in c
		for (int i = 1; i < c.length; i++){
			c[i] += c[i-1];
		}

		// Build the sorted array, reading one digit at a time
		for (int i = a.length-1; i >= 0; i--){
			b[(int)c[((int)a[i]/exp)%10]-1] = a[i];
			c[((int)a[i]/exp)%10]--;
		}

		// Copy the sorted array b back to a
		for (int i = 0; i < a.length; i++){
			a[i] = b[i];
		}
	}

	public static void radixSort(long[] a){
		// Find the maximun number (k) in the array (a)
		long k = a[0];
		for (int i = 0; i < a.length; i++){
			if (a[i] > k){
				k = a[i];
			}
		}

		// Sort with counting sort starting from the LSD to the MSD
		for (int i = 1; k/i > 0; i *= 10){
			countingSortLSD(a, i);
		}	
	}

	public static void shellSort(long[] a) {
		// Start with h = n/2, and reduce h by half everytime
		for (int h = a.length/2; h > 0; h /= 2){
			// Perform insertion sort with the gap h
			for (int i = h; i < a.length; i++){
				// Save a[i] to a temporary variable and leave position i empty
				long temp = a[i];
				int j;
				// Shift the elements to the correct location
				for (j = i; j >= h && a[j-h] > temp; j -= h){
					a[j] = a[j-h];
				}
				// Put the temporary variable to the correct location
				a[j] = temp;
			}
		}	
	}

	//-------------------------------------------------------------
	//------- Below is an implementation of Selection Sort --------
	//-------------------------------------------------------------		
	public static void SelectionSort(long[] a) {
		int N = a.length;
	    for (int i = 0; i < N; i++) {
	    	int min = i;
	        for (int j = i+1; j < N; j++) {
	        	if (a[j] < a[min]) min = j;
	        }
	        exch(a, i, min);
	    }
	}
	
	
	//-----------------------------------------------------------------------
	//---------- Below is an implementation of Insertion Sort ----------
	//-----------------------------------------------------------------------
	public static void InsertionSort(long[] a) {
        int N = a.length;
        for (int i = 0; i < N; i++) {
            for (int j = i; j > 0 && a[j] < a[j-1]; j--) {
                exch(a, j, j-1);
            }
        }
	}


	//-----------------------------------------------------------------------
	//---------- Below is an implementation of recursive MergeSort ----------
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
	}
	
	
	// This creates an array with n randomly generated elements between (0, n*10]
	private static long[] randArray(int n) {
		long[] rand = new long[n];
		for(int i=0; i<n; i++)
			rand[i] = (int) (Math.random() * n * 10);
		return rand;
	}
	
	private static void startTimer() { 
		timestamp = System.nanoTime();
	}
	
	private static double endTimer() {
		return (System.nanoTime() - timestamp)/1000000.0;
	}
	        
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
		final int SELECT = 0, INSERT = 1, MERGE = 2, COUNT = 3, RADIX = 4, SHELL = 5;
		int[] algorithms = {SELECT, INSERT, MERGE, COUNT, RADIX, SHELL};
		
		// max defines the maximum size of the array to be tested, which is 2^max
		// runs defines the number of rounds to be performed per test, in order to get an average running time.
		int max = 15, runs = 5;
		double[][] stats = new double[algorithms.length][max];
		for(int i=0; i<algorithms.length; i++) {             //loop through each sorting algorithm
			switch(i) {
				case SELECT: System.out.print("Running Selection Sort ..."); break;
				case INSERT: System.out.print("Running Insertion Sort ..."); break;
				case MERGE: System.out.print("Running MergeSort ..."); break;
				case COUNT: System.out.print("Running Counting Sort ..."); break;
				case RADIX: System.out.print("Running Radix Sort ..."); break;
				case SHELL: System.out.print("Running Shellsort ..."); break;
			}
			for(int j=0; j<max; j++) {        //loop through each array size 
				double avg = 0;
				for(int k=0; k<runs; k++) {    // loop through each run
					long[] a = randArray((int) Math.pow(2, j+1));
					startTimer();
					switch(i) {
						case SELECT: SelectionSort(a); break;
						case INSERT: InsertionSort(a); break;
						case MERGE: MergeSort(a); break;
						case COUNT: countingSort(a); break;
						case RADIX: radixSort(a); break;
						case SHELL: shellSort(a); break;
					}
					avg += endTimer();
					if (testSort(a) == false)
						System.out.println("The sorting is INCORRECT!" + "(N=" + a.length + ", round=" + k + ").");
				}
				avg /= runs;
				stats[i][j] = avg;
			}
			System.out.println("done.");
		}
		
		DecimalFormat format = new DecimalFormat("0.0000");
		System.out.println();
		System.out.println("Average running time:");
		System.out.println("N\t Selection Sort\t Insertion Sort\t MergeSort\t Counting Sort\t Radix Sort\t Shellsort");
		for(int i=0; i<stats[0].length; i++) {
			System.out.print((int) Math.pow(2, i+1) + "\t  ");
			for(int j=0; j<stats.length; j++) {
				System.out.print(format.format(stats[j][i]) + "\t  ");
			}
			System.out.println();
		}
	}
	
}
