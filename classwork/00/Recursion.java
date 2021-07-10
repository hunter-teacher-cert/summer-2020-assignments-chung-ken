/* Day 0: June 29, 2020 Assignment
 * Focus: Recursive algorithms
 * Assignment page:
https://github.com/hunter-teacher-cert/summer-2020/blob/master/daily_assignments.md
 */

import java.io.*;
import java.util.*;

public class Recursion {

	// A factorial method using recursion (Part 2)
	// returns n! where n is a postive integer
	public static int factorial(int n) {
		if(n <= 1) {	//considers 0! but will return 1 for negative inputs
			return 1;
		}
		return n * factorial(n-1);
	}

	// A fibonacci method using recursion (Part 3)
	// returns the nth number of the series where n is positive
	public static int fib(int n) {
		if(n <= 2) {
			return 1;
		}
		return fib(n-1) + fib(n-2);
	}

	// Ackermann function method using recursion (Part 5)
	// from pre05, ThinkJava Chapter 6, Exercise 8
	public static int ack(int m, int n) {
		if (m == 0) {
			return n + 1;
		} else if (m > 0 && n == 0) {
			return ack(m - 1, 1);
		} else if (m > 0 && n >  0) {
			return ack(m - 1, ack(m, n - 1));
		}
		return -1; //Error check. Code should not reach here.
	}

	// recrusiveSum method to add the elements in an array
	// returns sum
	public static int recursiveSum(int[] ia) {
		if(ia.length < 1) {
			return 0;
		}
		int[] ib = new int[ia.length-1];
		for(int i = 0; i < ia.length-1; i++) {
			ib[i] = ia[i];
		}
		return ia[ia.length-1] + recursiveSum(ib);
	}

	public static void main(String[] args) {

		// Part 1 - Get the file working
		System.out.println("Hello, world!");

		// Part 2 - Write a recursive factorial method
		System.out.println("\nCheck for Exercise 2:");
		for(int i = 5; i > 0; i--)
			System.out.printf("factorial(%d) = %d\n", i, factorial(i));

		// Part 3 - Write a recursive fibonacci method
		System.out.println("\nCheck for Exerise 3:");
		for(int i = 1; i < 9; i++)
			System.out.printf("fib(%d) = %d\n", i, fib(i));

		// Part 4 - Trace Diagrams
		/* factorial recursion:
		 *	factorial(4) = 4 * factorial(3)
		 *		factorial(3) = 3 * factorial(2)
		 *			factorial(2) = 2 * factorial(1)
		 *				factorial(1) = 1
		 */
		/* fibonacci recursion:
		 *	fib(4) = fib(3) 			+  	fib(2)
		 *				/ 1+1=2					\
		 *		fib(3) = fib(2) + fib(1)		fib(2) = 1
		 *					/		\
		 *			fib(2) = 1		fib(1) = 1
		 */

		// Part 5 - Ackermann method from pre05, exercise 8
		System.out.println("\nCheck for Exercise 5:");
		for(int m = 0; m < 4; m++) {
			for(int n = 0; n < 5; n++) {
				System.out.printf("ack(%d,%d)=%d\t", m, n, ack(m,n));
			}
			System.out.println();
		}

		// Part 6 - Challenge: sum of the elements of an array
		System.out.println("\nCheck for Exercise 6:");
		int[] ia = {1, 2, 3, 4, 5, 6};
		System.out.println("Sum of array" + Arrays.toString(ia) + " = " + recursiveSum(ia));

	}

}
