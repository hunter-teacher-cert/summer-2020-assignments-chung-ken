//ThinkJava Chapter 8 - Exercises 1, 4 and 5

import java.io.*;
import java.util.*;

public class Arrays {
	
	//Exercise 1.1
	public static double[] powArray (double[] a, double x) {
		double[] b = new double[a.length];
		for (int i = 0; i < a.length; i++) {
			b[i] = Math.pow(a[i], x);
		}
		return b;
	}
	
	//Exercise 1.2
	public static int[] histogram (int[] scores, int counters) {
		int[] counts = new int[counters];
		for (int score : scores) {
			counts[score]++;
		}
		return counts;
	}
	public static int[] randomArray(int size, int counters) {
		Random random = new Random();
		int[] a = new int[size];
		for (int i = 0; i < a.length; i++) {
			a[i] = random.nextInt(counters);
		}
		return a;
	}
	
	//Exercise 4
	/* Q: Can you write this method using an enhanced for loop? Why or why not?
	 * A: The indexOfMax method is implemented with a regular for loop.  It would
	 * not be convenient to use an enhanced for loop (a for-each loop) to write
	 * this method because it does not use the index of the array.  You could
	 * do a work-around and use a counter to determine the index, but then why
	 * not use a standard for loop.
	 */
	public static int indexOfMax (int[] nums) {
		int max = nums[0];
		int index = 0;
		for (int i = 1; i < nums.length; i++) {
			if (nums[i] > max) {
				max = nums[i];
				index = i;
			}
		}
		return index;
	}
	
	//Exercise 5
	public static boolean[] sieve (int n) {
		boolean[] primes = new boolean[n];
		primes[0] = false;
		primes[1] = true;
		for (int i = 2; i < n; i++) {
			primes[i] = true;
			for (int j = 2; j < i; j++) {
				if (i % j == 0) {
					primes[i] = false;
				}
			}
		}
		return primes;
	}
	
	public static void main (String[] args) {
		
		//Test Exercise 1.1
		double[] a = {1, 2, 3, 4};
		double[] c;
		System.out.println(java.util.Arrays.toString(a));
		c = powArray(a,0.5);
		System.out.println(java.util.Arrays.toString(c));
		
		//Test Exercise 1.2
		int counters = 100;
		int[] scores = randomArray(500, counters);
		int[] counts = histogram(scores, counters);
		for(int i = 0; i < counts.length; i++) {
			System.out.printf("%02d: ", i);
			for(int j = 0; j < counts[i]; j++) {
				System.out.print("#");
			}
			System.out.println();
		}
		
		//Test Exercise 4
		int[] testArray = {0, 1, 2, 3, 9, 4, 5};
		System.out.println("The max is at index " + indexOfMax(testArray));
		
		//Test Exercise 5
		int n = 100;
		boolean[] primes = sieve(n);
		for (int i = 0; i < n; i++) {
			if (primes[i]) {
				System.out.print(i + " ");
			}
		}
		
	}
		
}