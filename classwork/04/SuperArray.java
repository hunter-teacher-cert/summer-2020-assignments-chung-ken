// Monday, July 6, 2020

import java.io.*;
import java.util.*;

public class SuperArray {
	
	//instance variables (attributes)
	private int[] data;
	private int numberElements = 0;
	
	//constructors
	// public SuperArray() {
		// data = new int[10];
		// //numberElements = 0;
	// }
	
	public SuperArray(int size) {
		data = new int[size];
		//numberElements = 0;
	}
	
	public SuperArray() {
		this(10);
	}//practice using this() as a constructor call
	
	//methods
	public void add(int value) {
		if (numberElements == data.length) {
			grow();
		}
		data[numberElements++] = value;
	}
	
	public void add(int index, int value) {
		if (numberElements == data.length) {
			grow();
		}
		if (index >= numberElements) {//add value to end
			add(value);
		} else {//or shift everything over and add in middle
			for (int i = numberElements; i > index; i--) {
				data[i] = data[i-1];
			}
			data[index] = value;
			numberElements++;
		}
	}
	
	private void grow() {//only used in-house
		int[] data = new int[numberElements + 1];
		for (int i = 0; i < numberElements; i++) {
			data[i] = this.data[i];
		}
		this.data = data;
		System.out.println("I grew!");
	}
	
	public void remove(int index) {
		for (int i = index; i < numberElements - 1; i++) {
			data[i] = data[i+1];
		}
		numberElements--;
		//note that the duplicate value of the last element is not deleted
		System.out.println("I shrunk...");
	}
	
	public int get(int index) {
		if (index < numberElements) {
			return data[index];
		}
		return -1;
	}
	
	public int set(int index, int value) {//added feature by topher
		
		return oldVal;
	}
	
	public boolean isEmpty() {
		// if (numberElements == 0) {
			// return true;
		// }
		// return false;
		return numberElements == 0;
	}
	
	public String toString() {
		String output = "[";
		for (int i = 0; i < numberElements; i++) {
			output += data[i];
			if (i == numberElements - 1) {
				output += "]";
			} else {
				output += ",";
			}
		}
		return output;
	}
	
	public static void main(String[] args) {
		//test methods
		SuperArray arr1 = new SuperArray();
		if (arr1.isEmpty()) {
			System.out.println("I have no elements =(");
		}
		
		for (int i = 0; i < 11; i++) {
			arr1.add(i);
			System.out.println(arr1.toString());
		}
		System.out.println("Element at index 1 = " + arr1.get(1));
		System.out.println("Element at index 12 = " + arr1.get(12));
		System.out.println(arr1.isEmpty());
		arr1.add(2,99);
		System.out.println(arr1.toString());
		arr1.remove(5);
		System.out.println(arr1.toString());
		
	}//end of main
	
}//end of class