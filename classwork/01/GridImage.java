/*
 * Day 1 Assignment
 * Tuesday, June 30, 2020
 * Github Link:
https://github.com/hunter-teacher-cert/summer-2020/tree/master/day2day/01
 */

import java.io.*;
import java.util.*;

public class GridImage {

  public static void main(String[] args) {

    int rows = 250;
    int cols = 250;

    int[][] grid = new int[rows][cols];

    print2d(grid);
    System.out.println("\nSet column 2 to 2:");
	colPop(grid, 2, 2);
	print2d(grid);
	System.out.println("\nSet row 5 to 5:");
	rowPop(grid, 5, 5);
	print2d(grid);
	System.out.println("\nInvert values to 0 and 255:");
	invert(grid);
	print2d(grid);
	System.out.println("\nDiagonal test:");
	diagonal(grid, 9, 9, 0, 'a');
	diagonal(grid, 9, 10, 1, 'b');
	diagonal(grid, 10, 9, 2, 'c');
	diagonal(grid, 10, 10, 3, 'd');
	print2d(grid);
	ImageTest.drawImage(grid);
  }

  public static void print2d(int[][] d2) {

    for (int r=0; r < d2.length; r++) {
      for (int c=0; c < d2[r].length; c++) {

        System.out.printf("%03d ", d2[r][c]);
      }//end c for

      System.out.println();
    }//end r for
  }//end print2d

  public static void colPop(int[][] d2, int c, int value) {
	  for (int row = 0; row < d2.length; row++) {
		  d2[row][c] = value;
	  }//end for j
  }//end colPop

  public static void rowPop(int[][] d2, int r, int value) {
	  for (int col = 0; col < d2[r].length; col++) {
		  d2[r][col] = value;
	  }//end for i
  }//end rowPop

  public static void invert(int[][] d2) {
	  for (int i = 0; i < d2.length; i++) {
		  for (int j = 0; j < d2[i].length; j++) {
			  if (d2[i][j] == 0) {
				  d2[i][j] = 255;
			  } else {
				  d2[i][j] = 0;
			  }
		  }//end for j
	  }//end for i
  }//end invert

  /*
    direction = 
    0: up + left
    1: up + right
    2: down + left
    3: down + right
   */
  public static void diagonal(int[][] d2, int r, int c, int direction, int value) {
	  while (r >= 0 && r < d2.length && c >= 0 && c <= d2[0].length) {
		  d2[r][c] = value;
		  if (direction == 0) {
			  r--;
			  c--;
		  } else if (direction == 1) {
			  r--;
			  c++;
		  } else if (direction == 2) {
			  r++;
			  c--;
		  } else {
			  r++;
			  c++;
		  }
	  }//end while
  }//end diagonal

}