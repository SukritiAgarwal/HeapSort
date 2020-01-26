package Lab6;

import java.util.Random;
import java.util.Scanner;

public class HeapSort {
	public static void main(String[] args) {
		int min = -10000;
		int max = 10000;
		int numRuns = 100;
		
		Scanner in = new Scanner(System.in);
		System.out.print("Please enter a positive integer: ");
		int n = in.nextInt();
		int a[] = null;
		
		a = generateRandomarray(n, min, max);
		
		System.out.print("\nUnsorted Array: [");
		for (int i=0; i<n-1; i++) // To print the right commas
			System.out.print(a[i] + ", ");
		System.out.print(a[n-1] + "]\n");
		
		heapSort(a);
		//selectionSort(a);
		
		System.out.print("\n Sorted  Array: [");
		for (int i=0; i<n-1; i++) // To print the right commas
			System.out.print(a[i] + ", ");
		System.out.print(a[n-1] + "]\n");
		
		double heapSortTime = 0, selectionSortTime = 0;
		for(int i=0; i<numRuns; i++){
			int[] b = generateRandomarray(1000, min, max);	
			int[] c = b.clone();
		
			final double startHeapSortTime = System.nanoTime();
			heapSort(b);
			heapSortTime += (System.nanoTime() - startHeapSortTime);

			final double startSelectionSortTime = System.nanoTime();
			selectionSort(c);
			selectionSortTime += (System.nanoTime() - startSelectionSortTime);
		}
		System.out.println("\nAverage runtime ");
		System.out.println("----------------");
		System.out.println("Heap Sort for n = 1000: " + heapSortTime/numRuns + "ns");
		System.out.println("Selection Sort for n = 1000: " + selectionSortTime/numRuns + "ns");
	}
	
	public static int[] generateRandomarray(int n, int min, int max){
        int list[] = new int[n];
        Random random = new Random();    
        for (int i = 0; i < n; i++) {            
            list[i] = random.nextInt((max - min) + 1) + min;
        }
       return list;
    } 
	public static void printArray(int[] a, int n) {
		System.out.print("\n[");
		for (int i=0; i<n-1; i++) // To print the right commas
			System.out.print(a[i] + ", ");
		System.out.print(a[n-1] + "]\n");
	}

	public static void heapSort(int[] a) { //O(nlogn)
		buildMaxHeap(a); //O(n)
		int len = a.length - 1; //Subtract one because maxHeapify in its last iteration will leave the minimum value as root, since everything else is already sorted.
		
		for(int i=len; i >= 0; i--) { //Swap root with item at the end of array
			int temp = a[i];
			a[i] = a[0];
			a[0] = temp;
			maxHeapify(a, i, 0); //O(log(n)) runs n-1 times
		}
	}
	
	public static void buildMaxHeap(int[] a) { //O(n)
		for(int i = (a.length/2); i > -1; i--) { //later half of the heap, i.e., the leaves are already sorted
			maxHeapify(a, a.length, i);
		}
	}
	
	//O(log(n))
	public static void maxHeapify(int[] a, int len, int n) { //Node n is always the root
		int largest = n;
		int left = (2 * n);
		int right = (2 * n) + 1;
		
		if((left < len) && (a[left] > a[largest]))
			largest = left;
		if((right < len) && (a[right] > a[largest]))
			largest = right;
		if(largest != n) {
			int temp = a[n]; //If largest is not at root, place it at the root
			a[n] = a[largest];
			a[largest] = temp;
			maxHeapify(a, len, largest); //subtree of a[largest] should also be maxHeapified
		}
	}
	
	public static int[] selectionSort(int[] a) {
		for (int i = 0; i < a.length - 1; i++) {
			int index = i;
			
			for(int j = i+1; j < a.length; j++) { //find min of array [j..end] which becomes next inserted element in final array
				if(a[j] < a[index])
					index = j;
				
				int min = a[index];
				a[index] = a[i];
				a[i] = min;
			}
		}
		return a;
	}
}
