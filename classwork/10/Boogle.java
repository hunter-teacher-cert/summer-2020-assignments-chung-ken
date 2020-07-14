/**
   classwork -- Day 10 | 0b1010 | 0x10
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
 * The program measures time in nanoseconds to complete each of the 3 methods
 *
 * Conclusion: Linear searches are slower than Binary searches except for special cases
 *			Binary Iterative searches are usually faster than Binary Recursive searches
 *					but there doesn't seem to be a significant difference as the list size grows.
 *					Occasionally they are the same speed or the recursive method may run faster.
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
	for( int pos=0; pos<al.size(); pos++) {
		// System.out.print(pos + "...");
		if (al.get(pos).equals(target)) {
			System.out.println("\nLinear Completed in " + (System.nanoTime() - startTime) + " ns");
			return pos;
		}
    }
	System.out.println("\nLinear Completed in " + (System.nanoTime() - startTime) + " ns");
    return -1;
  }//end linSearch()



  //return index of target, or -1 if not found
  // Iterative version of binary search
  public static int binSearch( ArrayList al, int target )
  {
    long startTime = System.nanoTime();
	//consider the empty ArrayList case
	if ( al.size() == 0 ) {
		return -1;
	}
	
	//start algorithm
	int lowerBound = 0;
	int upperBound = al.size() - 1;
	int searchIndex = (lowerBound + upperBound) / 2;
	int middle = (int)al.get(searchIndex);
	// System.out.print("BinI Iteration...");
	int count = 0;
	while ( middle != target ) {
		// System.out.print(count++ + "...");
		if ( middle > target ) { //search lower half
			upperBound = searchIndex - 1;
		} else if ( middle < target ) { //search upper half
			lowerBound = searchIndex + 1;
		}
		//check if bounds are still valid, if not return -1 (target not present)
		if ( lowerBound > upperBound ) {
			System.out.println("\nIterative Completed in " + (System.nanoTime() - startTime) + " ns");
			return -1;
		}
		//update searchIndex and middle
		searchIndex = (lowerBound + upperBound) / 2;
		middle = (int)al.get(searchIndex);
	}
	//if we got here, we found the right target at searchIndex
	System.out.println("\nIterative Completed in " + (System.nanoTime() - startTime) + " ns");
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


  public static void main( String[] args )
  {
    /*~~~~v~~~~~~move~me~down~~~1~block~at~a~time~~~~~~~~~~v~~~~
    System.out.println("\n"+"al000"+":");
    ArrayList al000 = prestoArrayListo(0,0,0);
    System.out.println(al000);
    System.out.println(linSearch(al000,3));
    System.out.println("\n"+"al01"+":");
    ArrayList al01 = prestoArrayListo(5,0,100);
    System.out.println(al01);
    System.out.println(linSearch(al01,3));
    System.out.println("\n"+"al02"+":");
    ArrayList al02 = prestoArrayListo(5,3,10);
    System.out.println(al02);
    System.out.println(linSearch(al02,3));
    System.out.println("\n"+"al05"+":");
    ArrayList al05 = prestoArrayListo(20,1,5);
    System.out.println(al05);
    System.out.println(linSearch(al05,3));
      ~~~~^~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~^~~~~*/


    // test battery using sorted ArrayLists as search space
    // /*~~~~v~~~~~~move~me~down~~~1~block~at~a~time~~~~~~~~~~v~~~~
    System.out.println("\n"+"sal000"+":");
    ArrayList sal000 = prestoSortedArrayListo(0,0,0);
    System.out.println(sal000);
    System.out.println(linSearch(sal000,3));
    System.out.println(binSearch(sal000,3));
    System.out.println("\n"+"sal00"+":");
    ArrayList sal00 = prestoSortedArrayListo(5,0,100);
    System.out.println(sal00);
    System.out.println(linSearch(sal00,3));
    System.out.println(binSearch(sal00,3));
    System.out.println("\n"+"sal01"+":");
    ArrayList sal01 = prestoSortedArrayListo(5,0,100);
    System.out.println(sal01);
    System.out.println(linSearch(sal01,3));
    System.out.println(binSearch(sal01,3));
    System.out.println("\n"+"sal02"+":");
    ArrayList sal02 = prestoSortedArrayListo(5,3,100);
    System.out.println(sal02);
    System.out.println(linSearch(sal02,3));
    System.out.println(binSearch(sal02,3));
    System.out.println("\n"+"sal03"+":");
    ArrayList sal03 = prestoSortedArrayListo(5,0,100);
    System.out.println(sal03);
    System.out.println(linSearch(sal03,3));
    System.out.println(binSearch(sal03,3));
    System.out.println("\n"+"sal04"+":");
    ArrayList sal04 = prestoSortedArrayListo(20,0,3);
    System.out.println(sal04);
    System.out.println(linSearch(sal04,3));
    System.out.println(binSearch(sal04,3));
    System.out.println("\n"+"sal05"+":");
    ArrayList sal05 = prestoSortedArrayListo(20,1,5);
    System.out.println(sal05);
    System.out.println(linSearch(sal05,3));
    System.out.println(binSearch(sal05,3));
      // ~~~~^~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~^~~~~*/
	System.out.println("\n"+"sal06"+":");
	ArrayList sal06 = prestoSortedArrayListo(20,0,3);
    System.out.println(sal06);
    System.out.println(linSearch(sal06,10));
    System.out.println(binSearch(sal06,10));
    System.out.println("\n"+"sal07"+":");
    ArrayList sal07 = prestoSortedArrayListo(20,1,5);
    System.out.println(sal07);
    System.out.println(linSearch(sal07,15));
    System.out.println(binSearch(sal07,15));
	System.out.println("\n"+"sal08"+":");
	ArrayList sal08 = prestoSortedArrayListo(40,0,3);
    System.out.println(sal08);
    System.out.println(linSearch(sal08,10));
    System.out.println(binSearch(sal08,10));
	
	long startTime = System.nanoTime();
	int index08 = binSearchRecur(sal08,10,0,sal08.size()-1);
	System.out.println("\nRecursive Completed in " + (System.nanoTime() - startTime) + " ns");
	System.out.println(index08);
    
	System.out.println("\n"+"sal09"+":");
    ArrayList sal09 = prestoSortedArrayListo(512,1,5);
    System.out.println(sal09);
    System.out.println(linSearch(sal09,40));
    System.out.println(binSearch(sal09,40));
	startTime = System.nanoTime();
	int index09 = binSearchRecur(sal09,40,0,sal09.size()-1);
	System.out.println("\nRecursive Completed in " + (System.nanoTime() - startTime) + " ns");
	System.out.println(index09);
    //batch of commands for easier test case generation:
    /*
    System.out.println("\n"+"al"+":");
    ArrayList al = prestoArrayListo(,,);
    System.out.println(al);
    System.out.println(linSearch(al, ));
    System.out.println(binSearch(al, ));
    */
  }//end main

}//end class