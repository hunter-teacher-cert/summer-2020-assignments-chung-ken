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
	public static int[] histogram (int[] scores, int scoreUpperLimit) {
		int[] counts = new int[scoreUpperLimit];
		for (int score : scores) {
			counts[score]++;
		}
		return counts;
	}
	public static int[] randomArray(int scoreUpperLimit, int counters) {
		Random random = new Random();
		int[] a = new int[counters];
		for (int i = 0; i < a.length; i++) {
			a[i] = random.nextInt(scoreUpperLimit);
		}
		return a;
	}
	
	//Exercise 4
	/* Q: Can you write this method using an enhanced for loop? Why or why not?
	 * A: The indexOfMax method is implemented with a regular for loop.  It would
	 * not be convenient to use an enhanced for loop (a for-each loop) to write
	 * this method because a for each loop does not refer to the index of the array.  You could
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
	//Check if prime using division
	public static boolean[] prime (int n) {
		boolean[] primes = new boolean[n];
		primes[0] = false;
		primes[1] = false;
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
	
	//Check if prime using multiples (Sieve of Eratosthenes)
	public static boolean[] sieve (int n) {
		boolean[] primes = new boolean[n];
		//first assume all values are prime
		for (int i = 0; i < primes.length; i++) {
			primes[i] = true;
		}
		//set 0 and 1 as not prime
		primes[0] = false;
		primes[1] = false;
		//start the sieve knowing that 2 is prime
		for (int i = 2; i < n; i++) {
			for (int j = i; i*j < n; j++) {
				if (primes[i]) {
					primes[i*j] = false;	//mark multiples as composite
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
		double power = 0.5;
		c = powArray(a,power);
		System.out.println("Raise each element of the array to the power " + power);
		System.out.println(java.util.Arrays.toString(c));
		System.out.println();
		
		//Test Exercise 1.2
		int counters = 500;
		int scoreUpperLimit = 100;
		System.out.printf("Histogram of %d random scores from 0 to (but not including) %d:\n", counters, scoreUpperLimit);
		int[] scores = randomArray(scoreUpperLimit, counters);
		int[] counts = histogram(scores, scoreUpperLimit);
		for(int i = 0; i < counts.length; i++) {
			System.out.printf("%02d: ", i);
			for(int j = 0; j < counts[i]; j++) {
				System.out.print("#");
			}
			System.out.println();
		}
		
		//Test Exercise 4
		int[] testArray = {0, 1, 2, 3, 9, 4, 5};
		System.out.println("In the array: " + java.util.Arrays.toString(testArray));
		System.out.println("The max is at index " + indexOfMax(testArray));
		
		//Test Exercise 5
		int n = 100;
		boolean[] sifted = sieve(n);
		for (int i = 0; i < n; i++) {
			if (sifted[i]) {
				System.out.print(i + " ");
			}
		}
		System.out.println();
		boolean[] primes = prime(n);
		for (int i = 0; i < n; i++) {
			if (primes[i]) {
				System.out.print(i + " ");
			}
		}
		
	}
		
}