/*
 * Gridz.java is a sample program on 2D Arrays
 * Tuesday, June 30, 2020
 * JonAlf Dyrland-Weaver Code-along
 */
 
import java.io.*;
import java.util.*;

public class Gridz {

	public static void main(String[] args) {
		
		//int[row][column]
		int[][] arr = new int[10][5];
		
		System.out.println(arr);
		printArray(arr);
		
	}//end main
	
	public static void printArray(int[][] a) {
		
		int value = 0;
		
		for (int i = 0; i < a.length; i++) {//loops thru rows
			for (int j = 0; j < a[i].length; j++) {//loops thru columns
				a[i][j] = value++;
				System.out.printf("%06d ", a[i][j]);
			}//end for j
			System.out.println(" = " + rowSum(a, i));
		}//end for i
		for (int c = 0; c < a[0].length; c++) {//loops thru columns
				System.out.printf(" = %03d ", colSum(a,c));
			}//end for j
		System.out.println();
		
	}//end printArray
	
	public static int rowSum(int[][] a, int r) {
		
		int sum = 0;
		
		for (int j = 0; j < a[r].length; j++) {
			sum += a[r][j];
		}//end for j
		
		return sum;
	}//end rowSum
	
	public static int colSum(int[][] a, int c) {
		
		int sum = 0;
		
		for (int i = 0; i < a.length; i++) {
			sum += a[i][c];
		}//end for i
		
		return sum;
	}//end colSum
	
}//end class