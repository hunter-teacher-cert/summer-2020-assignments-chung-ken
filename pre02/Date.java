//ThinkJava Chapter 2 - Exercise 2

import java.io.*;
import java.util.*;

public class Date {
	public static void main (String[] args) {
		
		String day = "Wednesday";
		int date = 17;
		String month = "June";
		int year = 2020;
		
		System.out.println("American format:");
		System.out.println(day + ", " + month + " " + date + ", " + year);
		
		System.out.println("European format:");
		System.out.println(day + " " + date + " " + month + " " + year);

	}
}