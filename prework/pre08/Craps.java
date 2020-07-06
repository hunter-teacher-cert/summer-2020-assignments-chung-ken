//Final Project - a Simplified game of Craps

import java.io.*;
import java.util.*;

public class Craps {
	
	//roll accepts an integer parameter and returns a random number between 1 and that number, inclusive
	public static int roll (int n) {
		Random random = new Random();
		return random.nextInt(n) + 1;
	}
	
	//shoot accepts two parameters - a number of dice and the maximum value for those dice and returns the result of rolling those dice
	public static int shoot (int dice, int max) {
		int total = 0;
		System.out.print("Roll: ");
		for (int i = 0; i < dice; i++) {
			int pips = roll(max);
			System.out.print(pips + " ");
			total += pips;
		}
		System.out.println();
		return total;
	}
	
	//round accepts no parameters and returns a boolean to indicate if the shooter of the round wins or loses.
	public static boolean round () {
		int sum = shoot(2, 6);
		if (sum == 2 || sum == 3 || sum == 12) {
			return false;
		} else if (sum == 7 || sum == 11) {
			return true;
		} else {
			System.out.println("Point number = " + sum);
			int newSum = -1;
			while (newSum != sum || newSum != 7) {
				newSum = shoot(2, 6);
				if (newSum == sum) {
					return true;
				} else if (newSum == 7) {
					return false;
				}
			}
			return false; //hate that I had to put this here. Logically, we will never get here. We'd be stuck in the while loop.
		}
	}
	
	/* The main program takes a parameter from the command line, plays that many rounds
	 * and as indicates each round whether the shooter wins or loses.
	 * The program also prints out the overall statistics of wins at the end.
	 */
	public static void main (String[] args) {
		Scanner bob = new Scanner(System.in);
		System.out.print("Let's play Craps...\nHow many rounds do you want to play? ");
		int rounds = bob.nextInt();
		bob.close();
		System.out.println();
		
		int winCount = 0;
		for (int i = 0; i < rounds; i++) {
			System.out.println("Round " + (i+1) + ":");
			if (round()) {
				System.out.println("You win!\n");
				winCount++;
			} else {
				System.out.println("You lose =(\n");
			}
		}
		
		//Check statistics of rounds
		double winPercent = winCount * 100.0 / rounds;
		System.out.printf("You won %d out of %d rounds (%.1f%%)... ", winCount, rounds, winPercent);
		if (winPercent > 50) {
			System.out.println("not bad. The pseudorandom number generator is in your favor!");
		} else {
			System.out.println("better quit while you can.");
		}
	}
}