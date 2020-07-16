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


	// sorting method 1
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

	//helper method to swap indeces in sort method
	public static void swap(ArrayList<Integer> al, int index1, int index2) {
		int tmp = (int)al.get(index1);
		al.set(index1, al.get(index2));
		al.set(index2, tmp);
	}

  //returns a sublist of al containing the elements at indices in the range [lo, hi)
  //written by DW
  public static ArrayList<Integer> slice(ArrayList<Integer> al, int lo, int hi) {

    ArrayList<Integer> sub = new ArrayList<Integer>();
    for (; lo < hi; lo++) {
      sub.add( al.get(lo) );
    }//copy
    return sub;
	
  }//slice


	//Input: 2 SORTED ArrayLists
	//returns 1 new SORTED ArrayList containing all the elements from lists a and b
	public static ArrayList<Integer> merge(ArrayList<Integer> list1, ArrayList<Integer> list2) {
		//setup variables
		//   make a new ArrayList
		//   indices for each input
		ArrayList<Integer> sorted = new ArrayList<Integer>(); //to be returned
		
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
					sorted.add(list1.get(i));
					i++;
				} else if (list2.get(j) < list1.get(i)) {
					sorted.add(list2.get(j));
					j++;
				}
			} else if (i < length1) { //list2 is done, just work on list1
				sorted.add(list1.get(i));
				i++;
			} else if (j < length2) { //list1 is done, just work on list2
				sorted.add(list2.get(j));
				j++;
			}//end outer if
		}//end while
    
    //return the new list
    return sorted;
  }//merge


  public static void main( String[] args )
  {
    //DW's test
	ArrayList<Integer> rando = prestoArrayListo(10, 0, 100);
    ArrayList<Integer> calrissian = prestoArrayListo(10, 0, 100);

    selectionSort(rando);
    selectionSort(calrissian);

    System.out.printf("rando: %s\n", rando);
    System.out.printf("calrissian: %s\n", calrissian);

    ArrayList<Integer> randoCalrissian = merge(rando, calrissian);
    System.out.printf("randoCalrissian: %s\n", randoCalrissian);

	//tofr's test
	for( int n=2; n<=10; n += 2 ) {
			ArrayList tpe = prestoArrayListo(n,1,5);
			ArrayList ohr = prestoArrayListo(n,1,5);
			ArrayList topher = merge(tpe, ohr);
			System.out.println("\ntpe" + n + " = " + tpe);
			System.out.println("ohr" + n + " = " + ohr);
			System.out.println("topher" + (2*n) + " = " + topher);
			System.out.println("top" + n + " = " + slice(topher, 0, topher.size()/2));
			System.out.println("her" + n + " = " + slice(topher, topher.size()/2, topher.size()));
	}
	
  }//end main

}//end class