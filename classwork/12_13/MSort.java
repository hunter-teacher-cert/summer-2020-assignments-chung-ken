/** Started: Day 12 - Thursday, July 16, 2020
 * mergeSort method
**/

import java.io.*;
import java.util.*;

public class MSort {


  //return ArrayList of random ints on range [lo,lo+hi)
  //written by tofr
  public static ArrayList<Integer> prestoArrayListo(int numItems, int lo, int hi)
  {
    ArrayList<Integer> retArr = new ArrayList<Integer>();

    for(int i=0; i<numItems; i++) {
      retArr.add( lo + (int)(hi * Math.random()) ); // [lo,lo+hi)
    }
    return retArr;
  }//end prestoArrayListo()



	//###########################################
	//# 	3 methods for Selection Sort		#
	//#			- findSmallest()				#
	//#			- swap()						#
	//#			- selectionSort()				#
	//###########################################

	//** helper method used by selectionSort()
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
	
	//helper method to swap indeces in sort method
	public static void swap(ArrayList<Integer> al, int index1, int index2) {
		int tmp = (int)al.get(index1);
		al.set(index1, al.get(index2));
		al.set(index2, tmp);
	}

	// sorting method 1: Selection Sort (with n^2 runtime)
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
		
	}//end of selectionSort


	//###########################################
	//# 	3 methods for Merge Sort			#
	//#			- slice()						#
	//#			- merge()						#
	//#			- mergeSort()					#
	//###########################################
	
	//returns a sublist of al containing the elements at indices in the range [lo, hi)
	//written by DW
	public static ArrayList<Integer> slice(ArrayList<Integer> al, int lo, int hi) {

		ArrayList<Integer> sub = new ArrayList<Integer>();
		for (; lo < hi; lo++) {
			sub.add( al.get(lo) );
		}//copy
		return sub;

	}//slice

	//ideally, takes 2 sorted lists and merges them into 1 uber sorted list
	//(if 2 unsorted lists are passed in, the elements will be sorted one elements
	// at a time based on the next leading element)
	public static ArrayList<Integer> merge(ArrayList<Integer> list1, ArrayList<Integer> list2) {
		//setup variables
		ArrayList<Integer> merged = new ArrayList<Integer>(); //to be returned
		
		int length1 = list1.size();
		int length2 = list2.size();
		int i = 0; //index for list1
		int j = 0; //index for list2
		
		//while either indices are less than their respective lengths
		while ( i < length1 || j < length2 ) {
			//compare next smallest index elements of each input list,
			//add the smaller value to the new list,
			//then update index we took the value from
			if (i < length1 && j < length2) { //work on both lists
				if (list1.get(i) <= list2.get(j)) {
					merged.add(list1.get(i));
					i++;
				} else if (list2.get(j) < list1.get(i)) {
					merged.add(list2.get(j));
					j++;
				}
			} else if (i < length1) { //list2 is done, just work on list1
				merged.add(list1.get(i));
				i++;
			} else if (j < length2) { //list1 is done, just work on list2
				merged.add(list2.get(j));
				j++;
			}
		}//end while
    
		return merged;
	}//end merge

	//recursively slices itself until broken down to 1 element (which is "sorted")
	//then merge and sort the returned sorted lists
	//runtime n*log(n)
	public static ArrayList<Integer> mergeSort(ArrayList<Integer> al) {
		//use recursion to split the current list in 2
		//and push the sort down until you have 2 or 1 items
		//then pop back and to sort the 2 returned lists
		
		int size = al.size();
		//more elegant work flow inspired by DW
		if (size <= 1) {
			return al;
		}
		int mid = size/2;
		return merge(mergeSort(slice(al, 0, mid)), mergeSort(slice(al, mid, size)));

	}//end mergeSort

/*
	//Initial implementation of mergeSort
	//refined in the updated method above
	public static ArrayList<Integer> mergeSort(ArrayList<Integer> al) {
		ArrayList<Integer> sorted = new ArrayList<Integer>();
		ArrayList<Integer> list1 = new ArrayList<Integer>();
		ArrayList<Integer> list2 = new ArrayList<Integer>();
		
		int size = al.size();
		if (size > 1) {
			list1 = mergeSort(slice(al, 0, size/2));
			list2 = mergeSort(slice(al, size/2, size));
		} else {
			return al;
		}
				
		sorted = merge(list1, list2);
	
		return sorted;
	}//end mergeSort
*/


	//#######################################
	//#			Merge Sort Testing			#
	//#######################################
	public static void main( String[] args ) {
		// //DW's test
		// ArrayList<Integer> rando = prestoArrayListo(10, 0, 100);
		// ArrayList<Integer> calrissian = prestoArrayListo(10, 0, 100);

		// selectionSort(rando);
		// selectionSort(calrissian);

		// System.out.printf("rando: %s\n", rando);
		// System.out.printf("calrissian: %s\n", calrissian);

		// ArrayList<Integer> randoCalrissian = merge(rando, calrissian);
		// System.out.printf("randoCalrissian: %s\n", randoCalrissian);

		//tofr's test
		for( int n=1; n<=200000; n *= 2 ) {
				ArrayList tpe = prestoArrayListo(n,1,5);
				ArrayList ohr = prestoArrayListo(n,1,5);
				// System.out.println("\ntpe" + n + " = " + tpe);
				// System.out.println("ohr" + n + " = " + ohr);
				
				ArrayList teophr = merge(tpe, ohr);
				System.out.println("\nList size = " + teophr.size());
				// System.out.println("toephr" + (2*n) + " = " + teophr); //turned off for large lists
				long startM = System.nanoTime();
				ArrayList topherM = mergeSort(teophr);
				long endM = System.nanoTime();
				long timeM = endM - startM;
				String time = "";
				if (timeM < 1000)
					time = timeM + " ns";
				else if (timeM < 1_000_000)
					time = (timeM/1000) + " us";
				else if (timeM < 1_000_000_000)
					time = (timeM/1_000_000) + " ms";
				else if (timeM < 1_000_000_000_000L)
					time = (timeM/1_000_000_000) + " s";
				System.out.println(" Merge Sort completed in " + time);
				// System.out.println("topher" + (2*n) + " = " + topherM);
				
				//Note: selectionSort has to happen after mergeSort because it modifies the orginal ArrayList
				long startS = System.nanoTime();
				selectionSort(teophr);
				long endS = System.nanoTime();
				long timeS = endS - startS;
				time = ""; //recycling from timeM
				if (timeS < 1000)
					time = timeS + " ns";
				else if (timeS < 1_000_000)
					time = (timeS/1000) + " us";
				else if (timeS < 1_000_000_000)
					time = (timeS/1_000_000) + " ms";
				else if (timeS < 1_000_000_000_000L)
					time = (timeS/1_000_000_000) + " s";
				System.out.println(" Selection Sort completed in " + time);
				// System.out.println("topher" + (2*n) + " = " + teophr); //teophr is now the list after selection sorting
				
				// System.out.println("top" + n + " = " + slice(topher, 0, topher.size()/2));
				// System.out.println("her" + n + " = " + slice(topher, topher.size()/2, topher.size()));
		}
	
	}//end main

}//end class