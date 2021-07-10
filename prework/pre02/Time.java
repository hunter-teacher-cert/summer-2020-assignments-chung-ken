//ThinkJava Chapter 2 - Exercise 3

import java.io.*;
import java.util.*;

public class Time {
	public static void main (String[] args) {
		
		int hour, minute, second;
		hour = 15;
		minute = 30;
		second = 45;
		
		//number of seconds since midnight
		int secondsPassed = (hour * 60 * 60) + (minute * 60) + second;
		System.out.println("It has been " + secondsPassed + " seconds since midnight.");
		
		//number of seconds remaining in the day
		int secondsLeft = (24 * 60 * 60) - secondsPassed;
		System.out.println("There are " + secondsLeft + " seconds remaining in the day.");
		
		//percent of day that has passed
		double percentPassed = secondsPassed * 100 / (24 * 60 * 60);
		System.out.println(percentPassed + "% of the day has passed.");
		
		//elapsed time since start of exercise
		int startHour = 14;
		int startMinute = 58;
		int startSecond = 59;
		int secondsElapsed = (second - startSecond);
		int minutesElapsed = (minute - startMinute);
		int hoursElapsed = (hour - startHour);
		if (secondsElapsed < 0) {
			secondsElapsed += 60;
			minutesElapsed--;
		}
		if (minutesElapsed < 0) {
			minutesElapsed += 60;
			hoursElapsed--;
		}
		System.out.println(hoursElapsed + " hours, " + minutesElapsed + " minutes, " + secondsElapsed + " seconds have since I started working on this exercise.");
		
	}
}