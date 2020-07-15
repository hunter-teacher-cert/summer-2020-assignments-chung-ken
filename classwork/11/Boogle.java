/**
   classwork -- Day 11 | 0b1010 | 0x0b
   class: Boogle
   A tester class for code analysis and search exploration.
   USAGE:
   Read through at least once.
   Compile, run.
   Comment/uncomment lines, recompile, run to explore functionality.
   "Lather, rinse, repeat" until comprehension achieved.
   Rename methods to indicate purpose.
   Insert comment preceding each method to summarize functionality.
   T. Mykolyk, F. Dragon
   June 2020
**/

/* Created: Tuesday, July 14, 2020
 * Comparing search methods: Linear, Binary Iterative, and Binary Recursive
 *
 * Updated: Wednesday, July 15, 2020
 * added findSmallest method
 * main() focus is...
 */

import java.io.*;
import java.util.*;

public class Boogle
{

  //return index of first occurence of target, or -1 if not found
  public static int linSearch( ArrayList al, int target )
  {
    long startTime = System.nanoTime();
	// System.out.print("Linr Iteration...");
	int count = 0;
	for( int pos=0; pos<al.size(); pos++) {
		// System.out.print(pos + "...");
		count++;
		if (al.get(pos).equals(target)) {
			System.out.print("\nlinSearch completed in " + (System.nanoTime() - startTime) + " ns");
			System.out.println(" and " + count + " iterations\ntarget found at index " + pos);
			return pos;
		}
    }
	System.out.print("\nlinSearch completed in " + (System.nanoTime() - startTime) + " ns");
	System.out.println(" and " + count + " iterations\ntarget not found");
    return -1;
  }//end linSearch()



  //return index of target, or -1 if not found
  // Iterative version of binary search
  public static int binSearch( ArrayList al, int target )
  {
    long startTime = System.nanoTime(); //timer
	//consider the empty ArrayList case
	if ( al.size() == 0 ) {
		return -1;
	}
	
	// target = 8
	// lowerBound = 0, upperBound = 4
	// searchIndex = 2, middle(value) = 15
	// 15 > 8.... lowerBound = 0, upperBound = 1
	// searchIndex = (0+1)/2 = 0
	// middle(index 0) = 4
	// 4 < 8... lowerBound = 1, upperBound = 1;
	// searchIndex = 1, middle(value) = 8
	// end loop
	// return searchIndex = 1
	
	//start algorithm
	int lowerBound = 0;
	int upperBound = al.size() - 1;
	int searchIndex = (lowerBound + upperBound) / 2;
	int middle = (int)al.get(searchIndex);
	// System.out.print("BinI Iteration...");
	int count = 1;
	while ( middle != target ) {
		// System.out.print(count++ + "...");
		count++;
		if ( middle > target ) { //search lower half
			upperBound = searchIndex - 1;
		} else if ( middle < target ) { //search upper half
			lowerBound = searchIndex + 1;
		}
		//check if bounds are still valid, if not return -1 (target not present)
		if ( lowerBound > upperBound ) {
			System.out.print("\nbinSearch completed in " + (System.nanoTime() - startTime) + " ns"); //timer
			System.out.println(" and " + count + " iterations\ntarget not found");
			return -1; //didn't find target
		}
		//update searchIndex and middle
		searchIndex = (lowerBound + upperBound) / 2;
		middle = (int)al.get(searchIndex);
	}
	//if we got here, we found the right target at searchIndex
	System.out.print("\nbinSearch Completed in " + (System.nanoTime() - startTime) + " ns"); //timer
	System.out.println(" and " + count + " iterations\ntarget found at index " + searchIndex);
    return searchIndex;
  }//end binSearch()


	// Recursive version of binary search
	public static int binSearchRecur(ArrayList al, int target, int lowerBound, int upperBound) {
		
		//start algorithm
		// System.out.print("Bin Recursion...");

		//check if bounds are still valid, if not return -1 (target not present)
		if ( lowerBound > upperBound ) {
			return -1;
		} else {
			int searchIndex = (lowerBound + upperBound) / 2;
			int middle = (int)al.get(searchIndex);
			if ( middle == target ) {
				return searchIndex;
			}
			else if ( middle > target ) { //search lower half
				return binSearchRecur(al, target, lowerBound, searchIndex - 1);
			} else if ( middle < target ) { //search upper half
				return binSearchRecur(al, target, searchIndex + 1, upperBound);
			}
		}
		return -1; //make compiler happy
	}//end binSearchRecur()


  // subgoal: recognize target found (and take what action?) -> return index
  // subgoal: recognize search space exhausted (and take what action?) -> return -1
  // subgoal: recognize target in lower portion of range (and do what?) -> repeat search on lower
  // subgoal: recognize target in upper portion of range (and do what?) -> repeat search on upper

  //nota bene: A helper method could be very useful.
  // Q: Why?
  // Q: What would the parameters be for such a method?



  //return ArrayList of random ints on range [lo,lo+hi)
  public static ArrayList prestoArrayListo(int numItems, int lo, int hi)
  {
    ArrayList retArr = new ArrayList();

    for(int i=0; i<numItems; i++) {
      //System.out.println(i);  //diagnostic under-the-hood view
      //retArr.add( Math.random() ); // [0,1)
      //retArr.add( (int)Math.random() ); // 0
      //retArr.add( (int)(hi * Math.random()) ); // [0,hi)
      retArr.add( lo + (int)(hi * Math.random()) ); // [lo,lo+hi)
    }
    return retArr;
  }//end prestoArrayListo()


  //return ArrayList of random ints, sorted in ascending order
  public static ArrayList prestoSortedArrayListo(int numItems, int lo, int hi)
  {
    ArrayList retArr = new ArrayList();

    //if requested, return empty ArrayList
    if ( numItems<1 )
      return retArr;

    //populate index 0
    retArr.add( (int)(lo * Math.random()) );

    //populate rest of indices, each one greater than last
    for(int i=1; i<numItems; i++) {
      //System.out.println(i);  //diagnostic under-the-hood view
      //retArr.add( Math.random() ); // [0,1)
      //retArr.add( (int)Math.random() ); // 0
      //retArr.add( (int)(hi * Math.random()) ); // [0,hi)
      //retArr.add( lo + (int)(hi * Math.random()) ); // [lo,lo+hi)
      //System.out.println(retArr.get(i-1));
      retArr.add( (int)retArr.get(i-1)
                  + lo + (int)(hi * Math.random()) ); // ["left neighbour",hi)
    }

    return retArr;
  }//end prestoSortedArrayListo()


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
	
	
  public static void main( String[] args )
  {
	  
	//###################################################################  
	  
    /**
       ~~~ The RSPB Special Trace-athon ~~~
       YOUR MISSION:
       Form a Dynamic Duo of Destiny or a Trio of Triump. Conjure team name portending greatness (TNPG).
       Copypasta this into main method of your java sourcecode file containing methods linSearch and binSearch
         (Boogle.java for almost all of you)
       Verify it works. 
          (If any test calls do not yield expected outputs, there is some tuning to be done... 
           Probably best to just grab a working version for now.)
       On KtS, with at least 1 tracing buddy, trace out each binSearch() call.
       NOTA BENE: The second batch of test calls are identical to the first, 
                  except that the search space has grown by 1 element...
     **/
    
    ArrayList salamander = new ArrayList();

    //explicitly fill arraylist with intent to search for
    // present and nonpresent values...
    // generate an arraylist with an odd num of elems...
    salamander.add(8);
    salamander.add(15);
    salamander.add(4);
    salamander.add(23);
    salamander.add(16);
    System.out.println(salamander);
	selectionSort(salamander);
	System.out.println(salamander);
	
    //search for target in the list
    System.out.println(binSearch(salamander, 8));  // 1
	// [4, 8, 15, 16, 23] <-- check 15... too big
	// [4, 8] <-- check 4... too small
	// [8] <-- check 8... yay! found it at index 1
	
	System.out.println(binSearch(salamander, 15)); // 2
    System.out.println(binSearch(salamander, 16)); // 3

    //search for target not in the list
    System.out.println(binSearch(salamander, 3)); // -1
	// check 15 --> [4, 8]
	// check 4 --> done, not found
	
    System.out.println(binSearch(salamander, 9)); // -1
	// check 15 --> [4, 8]
	// check 4 --> [8]
	// check 8 --> done

    //add another element, for an even num of elems
    salamander.add(19);
    System.out.println(salamander);
	selectionSort(salamander);
	System.out.println(salamander);
    //search for target in the list
    System.out.println(binSearch(salamander, 8));  // 1
	
	
    System.out.println(binSearch(salamander, 15)); // 2
    
	System.out.println(binSearch(salamander, 16)); // 3
	// [4, 8, 15, 16, 23, 42]
	//check 15 --> [16, 23, 42]
	//check 23 --> [16]
	//check 16 --> get it! at index 3

    //search for target not in the list
    System.out.println(binSearch(salamander, 3)); // -1
    System.out.println(binSearch(salamander, 9)); // -1
	
	
	    /**
       The EPIC SHOWDOWN: Linear Search vs Binary Search
       A simple foray into how execution time grows as search spaces grows...
    **/
	/*
    //generate increasingly large search spaces
    for( int n=1; n<=100000; n*=10 ) {
      System.out.println( "\n~~~~~~~~~~~~~~~~~~~" );
      System.out.println("dataset size: " + n);
      ArrayList sal = prestoSortedArrayListo(n,1,5);

      // pick random index aka position on range [0,n)
      int randPos = (int)(n * Math.random());

      // pick target known to be in collection
      int target = (int)sal.get(randPos);

      //System.out.println( sal );  //diag

      System.out.println( "target: " + target );

      System.out.println( linSearch(sal,target) );
      System.out.println( binSearch(sal,target) );
    }
	*/
  }//end main

}//end class