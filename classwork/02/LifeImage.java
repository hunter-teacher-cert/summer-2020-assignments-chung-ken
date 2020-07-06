/* Game of Life
 * Wednesday, July 1, 2020
 **
The Rules of Life:
Survivals: Every cell with 2 or 3 living neighbours survives for the next generation.
Death:
  * Each cell with >3 neighbours dies from overpopulation.
  * Every cell with <2 neighbours dies from isolation.
Birth:
  * Each dead cell adjacent to exactly 3 living neighbours is a birth cell. It will come alive next generation.

NOTA BENE:  All births and deaths occur simultaneously. Together, they constitute a single generation
  */

import java.io.*;
import java.util.*;

public class LifeImage {
	public static int ROWS = 250;
	public static int COLS = 250;
	public static boolean WRAP = true;

	/**
	 * createNewBoard method
	 * creates a 2D array of ints to store states of cells
	 */
	public static int[][] createNewBoard() {
		int[][] board = new int[ROWS][COLS];
		for (int r = 0; r < ROWS; r++) {
			for (int c = 0; c < COLS; c++) {
				if (Math.random() < 0.8) {
					board[r][c] = 0;
				} else
					board[r][c] = 255;
			}
		}
		return board;
	}//end method createNewBoard

	//borrowed code from internet search
	public static void clearScreen() {
		System.out.print("\033[H\033[2J");
		// System.out.flush();
	}
	//ansi color picker
	public static String printColor(int[][] board, int r, int c) {
		int val = countNeighbours(board, r, c);
		String colorPrint = "";
		if ((val == 3 || val == 2) && board[r][c] == 'X')
			colorPrint += "\u001B[42m\u001B[30m"; //Green background
		else if (val == 3)
			colorPrint += "\u001B[43m"; //Yellow background
		// else if (val > 3)
		// 	colorPrint += "\u001B[41m"; //Red background
		else
			colorPrint += "\u001B[40m\u001B[31m"; //Black background
		return colorPrint + board[r][c] + "\u001B[0m";
	}
	//printBoard method
	public static void printBoard(int[][] board) {
		clearScreen();
		System.out.print("\033[0;0H");
		System.out.print((char)219);
		for (int c = 0; c < COLS; c++) {
			System.out.print((char)219);
		}
		System.out.println((char)219);
		for (int r = 0; r < ROWS; r++) {
			System.out.print((char)219);
			for (int c = 0; c < COLS; c++) {
				//System.out.print(board[r][c] + "," + countNeighbours(board, r, c) + " ");
				// System.out.printf(board[r][c] + " ");
				System.out.print(printColor(board, r, c));
			}
			System.out.println((char)219);
		}
		System.out.print((char)219);
		for (int c = 0; c < COLS; c++) {
			System.out.print((char)219);
		}
		System.out.println((char)219);
		// delay(1000);
	}//end method printBoard

	/**
	 * setCell method
	 * set the cell at (r,c) to val
	 * ' ' = no life, '#' = life and number of neighbors
	 */
	public static void setCell(int[][] board, int r, int c, int[][] boardCount) {
		if (r >= 0 && r < board.length && c >= 0 && c < board[r].length) {
			if (boardCount[r][c] == 0) {
				board[r][c] = ' ';
			} else {
				board[r][c] = (int)boardCount[r][c];
			}
		}
	}//end method setCell

	/**
	 * countNeighbours method
	 * determine the state (number of neighbors) of a cell
	 * returns an int
	 */
	 public static int countNeighbours(int[][] board, int r, int c) {
		int count = 0;
		//check 8 surrounding cells for life
		//account for special cases, i.e. - 4 corners and edge cells
		if (WRAP) {
			for (int dr = -1; dr <= 1; dr++) {
	 			for (int dc = -1; dc <= +1; dc++) {
	 				//System.out.println("   counting neighbors...");
					int col = (c + dc + COLS) % COLS;
					int row = (r + dr + ROWS) % ROWS;
	 				if (col != c || row != r) { //don't count itself
						if (board[row][col] != 0) {
	 						count++;
	 					}
	 				}
	 			}
	 		}
		} else {
			for (int row = r-1; row <= r+1; row++) {
				for (int col = c-1; col <= c+1; col++) {
					//System.out.println("   counting neighbors...");
					if (col >= 0 && col < COLS && row >= 0 && row < ROWS) { //within bounds
						if (col != c || row != r) { //don't count itself
							if (board[row][col] != 0) {
								count++;
							}
						}
					}
				}
			}
		}
		return count;
	 }//end method countNeighbours

	/**
	 * neighborCount method
	 * Count the number of living neighbors each cell has
	 * and return an int
	 *
	public static int[][] neighborCensus(int[][] board) {
		int[][] boardCount = new int[ROWS][COLS];
		for (int r = 0; r < ROWS; r++) {
			for (int c = 0; c < COLS; c++) {
				boardCount[r][c] = getCell(board, r, c);
				//Note: getCell was replaces with countNeighbours method
			}
		}
		return boardCount;
	}
	 * Realized we don't need an int array
	 */

	/**
	 * getNextGenCell method
	 * given a board and a cell, determine if the cell is
	 * alive or dead in the next generation based on rules
	 * for Conway's Game of Life
	 */
	public static int getNextGenCell(int[][] board, int r, int c) {
		int state = board[r][c];
		// int neighbors = countNeighbours(board, r, c);
		int neighbors = countNeighbours(board, r, c);
		if (state != 0) {
			if (neighbors < 2 || neighbors > 3) {
				state = 0; //death rules
			}
		} else if (neighbors == 3) {
			state = 255; //birth rule
		}
		//System.out.println("    Ending getNextGenCell");
		return state;
	}//end method getNextGenCell

	//generateNextBoard method
	public static int[][] generateNextBoard(int[][] board) {
		int[][] newBoard = new int[ROWS][COLS];
		for (int r = 0; r < ROWS; r++) {
			for (int c = 0; c < COLS; c++) {
				//System.out.println("  In generateNextBoard for loop...");
				newBoard[r][c] = getNextGenCell(board, r, c);
			}
		}
		return newBoard;
	}//end method generateNextBoard

	/**
	 * signsOfLife method
	 * check for any life still on the board
	 * if no life, game overpopulation
	 */
	public static boolean signsOfLife(int[][] board) {
		int count = 0;
		for (int r = 0; r < ROWS; r++) {
			for (int c = 0; c < COLS; c++) {
				if (board[r][c] != ' ') {
					count++;
				}
			}
		}
		if (count > 0) {
			return true;
		}
		return false;
	}//end method signsOfLife

	//helper method to slow down animation 
	public static void delay(int n) {
		try {
			Thread.sleep(n);
		} 
		catch(InterruptedException e) {}
	}
	
	public static void main(String[] args) {
		int[][] board;
		board = createNewBoard();
		ImageTest img = new ImageTest(board);
		img.drawImage();
		for(int i = 0; i < 1000; i++) {
			board = generateNextBoard(board);
			delay(10);
			img.updateImage(board);
		}
		img = null;
		/*
		Scanner bob = new Scanner(System.in);
		if (args.length >= 2) {
			ROWS = Integer.parseInt(args[0]);
			COLS = Integer.parseInt(args[1]);
		}
		if (args.length == 3) {
			WRAP = Boolean.parseBoolean(args[2]);
		}
		board = createNewBoard();
		printBoard(board);
		System.out.println("I finished printing the first board.");

		System.out.print("Press and hold Enter to cycle (type anything to quit):  ");
		String next = bob.nextLine();
		// while (signsOfLife(board)) {
		while (next.equals("")) {
			//System.out.println("In main while loop...");
			board = generateNextBoard(board);
			printBoard(board);
			System.out.print("Press and hold Enter to cycle (type anything to quit): ");
			next = bob.nextLine();
		}
		bob.close();
		*/
	}//end of main

}//end of class
