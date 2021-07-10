//ThinkJava Chapter 3 - Exercise 3

import java.io.*;
import java.util.*;

public class Converter {
	public static void main (String[] args) {
		
		Scanner bob = new Scanner(System.in);
		
		System.out.print("Enter the total number of seconds: ");
		int totalSeconds = bob.nextInt();
		bob.close();
		
		int seconds = totalSeconds % 60;
		int minutes = totalSeconds / 60 % 60;
		int hours = totalSeconds / 3600;
		System.out.printf("%d seconds = %d hour(s), %d minute(s), %d second(s)\n", totalSeconds, hours, minutes, seconds);
		
	}
}