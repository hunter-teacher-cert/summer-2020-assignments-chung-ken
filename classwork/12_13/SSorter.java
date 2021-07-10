/** Day 11 / 12 - Started: Wednesday, July 15, 2020
 * Moved selectionSort method from Boogle.java (day 11)
 * to here, SSorter.java
**/

import java.io.*;
import java.util.*;

public class SSorter
{


  //return ArrayList of random ints on range [lo,lo+hi)
  //written by tofr
  public static ArrayList<Integer> prestoArrayListo(int numItems, int lo, int hi)
  {
    ArrayList<Integer> retArr = new ArrayList<Integer>();

    for(int i=0; i<numItems; i++) {
      //System.out.println(i);  //diagnostic under-the-hood view
      //retArr.add( Math.random() ); // [0,1)
      //retArr.add( (int)Math.random() ); // 0
      //retArr.add( (int)(hi * Math.random()) ); // [0,hi)
      retArr.add( lo + (int)(hi * Math.random()) ); // [lo,lo+hi)
    }
    return retArr;
  }//end prestoArrayListo()


  //#################################################
  //#		Selection Sort methods					#
  //#################################################

	// find and return the index of the smallest value in the
    // ArrayList al from index lo to index hi inclusive 
    public static int findSmallest(ArrayList<Integer> al, int lo, int hi){
	
		//correct simple lo-hi issues
		if (lo < 0) lo = 0;
		if (hi > al.size()-1) hi = al.size()-1;
		
		//check for remaining errors in arguments passed in
		if ( lo>hi ) throw new IndexOutOfBoundsException("Invalid search range");
		
		//the code
		int minValue = (int)al.get(lo);
		int minIndex = lo;
		for (int i = lo+1; i <= hi; i++) {
			if ((int)al.get(i) < minValue) {
				minValue = (int)al.get(i);
				minIndex = i;
			}
		}
		return minIndex;
    }
	
	//helper method to swap indeces in selection sort method
	public static void swap(ArrayList<Integer> al, int index1, int index2) {
		int tmp = (int)al.get(index1);
		al.set(index1, al.get(index2));
		al.set(index2, tmp);
	}

	// sorting method 1 - Selection Sort
	// look at the whole list to find the smallest value, then swap it to the front
	// then repeat the previous step for the remaining unsorted list
	public static void selectionSort(ArrayList<Integer> al) {
		
		if (al.size() < 2) { //take from Tsee / Margie
			return; //no need to sort if the array has size 1 or less
		}
		
		for (int i = 0; i < al.size()-1; i++) {
			int smallestIndex = findSmallest(al, i, al.size()-1);
			// System.out.println("i = " + i + ", smallest index = " + smallestIndex);
			if ( smallestIndex != i ) {
				swap(al, i, smallestIndex);
			}
			// System.out.println(al);
		}
		
	}


  //#################################################
  //#		Test Selection Sort						#
  //#################################################

  public static void main( String[] args )
  {
    System.out.println("Selection Sort check:");
	ArrayList<Integer> arr = prestoArrayListo(20, 0, 50);
	System.out.println(arr);
	selectionSort(arr);
	System.out.println(arr);
	
	ArrayList<Integer> numElements = new ArrayList<Integer>();
	ArrayList<Integer> processingTime = new ArrayList<Integer>();
	for (int n = 10000; n < 100000; n += 10000) {
		ArrayList<Integer> randos = prestoArrayListo(n, 0, 100);
		// System.out.println(randos);

		long start = System.currentTimeMillis();

		selectionSort(randos);

		long elapsed = System.currentTimeMillis() - start;
		
		// System.out.println(randos);
		System.out.println("\nSelection Sort on list with " + n + " elements\nElapsed time: " + elapsed + " ms");
		numElements.add(n);
		processingTime.add((int)elapsed);
    }
	
	//notice from the time analysis that althought the list is growing linearly
	//the time to sort the list using selection sort is growing quadratically
	System.out.println("\n numElements	|  processingTime	| deltaTime	| delta(deltaTime)");
	System.out.println("----------------------------------------------------------------");
	
	for (int i = 0; i < numElements.size(); i++) {
		System.out.print("  " + numElements.get(i) + "\t\t|\t" + processingTime.get(i) + " ms");
		if (i == 0) System.out.println("\t\t|");
		if (i > 0) //first derivative
			System.out.print("\t\t| " + (processingTime.get(i) - processingTime.get(i-1)));
		if (i == 1) System.out.println("\t\t|");
		if (i > 1) {//second derivative
			System.out.println("\t\t| " + ((processingTime.get(i) - processingTime.get(i-1)) - (processingTime.get(i-1) - processingTime.get(i-2))));
		}
	}
	
	System.out.println("\t\t\t\t\t\t\t   ^");
	System.out.println("\t\t\t\t\t\t\t   |");
	System.out.println("Notice that while the the processing time is growing, and the change\nin the processing time is growing, the change in the change in the\nprocessing time is not!\nWe have a second order growth here.");
	
  }//end main

}//end class