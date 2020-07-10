/* Game of Life v3
 * Tuesday, July 7, 2020
 * Game of LifeBoard class
 * Attempt to animate GoL board in a single panel/frame
*********************************************************
The Rules of Life:
Survivals: Every cell with 2 or 3 living neighbours survives for the next generation.
Death:
* Each cell with >3 neighbours dies from overpopulation.
* Every cell with <2 neighbours dies from isolation.
Birth:
* Each dead cell adjacent to exactly 3 living neighbours is a birth cell. It will come alive next generation.

NOTA BENE:  All births and deaths occur simultaneously. Together, they constitute a single generation
*********************************************************/

import java.io.*;
import java.util.*;

public class LifeBoard {
	public int ROWS = 250;
	public int COLS = 250;
	public boolean WRAP = true;
	public Cell[][] world;
	public int pixelWidth = 2;
	public int pixelHeight = 2;

	/**
	 * createNewBoard method
	 * creates a 2D array of ints to store states of cells
	 */
	 
	public LifeBoard(){
		createNewWorld();
	}
	
	public LifeBoard(int rows, int cols){
		this.ROWS = rows;
		this.COLS = cols;
		createNewWorld();
	}
	
	public LifeBoard(int rows, int cols, boolean wrap){
		this.ROWS = rows;
		this.COLS = cols;
		this.WRAP = wrap;
		createNewWorld();
	}
	
	public LifeBoard(int rows, int cols, boolean wrap, int pixelSize){
		this.ROWS = rows;
		this.COLS = cols;
		this.WRAP = wrap;
		this.pixelWidth = pixelSize;
		this.pixelHeight = pixelSize;
		createNewWorld();
	}
	
	public void createNewWorld() {
		world = new Cell[ROWS][COLS];
		for (int r = 0; r < ROWS; r++) {
			for (int c = 0; c < COLS; c++) {
				world[r][c] = new Cell();
			}
		}
		for (int r = 0; r < ROWS; r++) {
			for (int c = 0; c < COLS; c++) {
				countNeighbours(r, c);
			}
		}
	}//end method createNewBoard

	/**
	 * countNeighbours method
	 * determine the state (number of neighbors) of a cell
	 * returns an int
	 */
	public void countNeighbours(int r, int c) {
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
						if (world[row][col].getCell() != 0) {
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
							if (world[row][col].getCell() != 0) {
								count++;
							}
						}
					}
				}
			}
		}
		world[r][c].setNeighbors(count);
	}//end method countNeighbours
	
	//updateWorld method
	public void updateWorld() {
		for (int r = 0; r < ROWS; r++) {
			for (int c = 0; c < COLS; c++) {
				// System.out.println("  In updateWorld for loop..." + (r * c));
				world[r][c].setCell();
			}
		}
		for (int r = 0; r < ROWS; r++) {
			for (int c = 0; c < COLS; c++) {
				countNeighbours(r, c);
			}
		}
	}//end method updateWorld

	//helper method to slow down animation 
	public static void delay(int n) {
		try {
			Thread.sleep(n);
		} 
		catch(InterruptedException e) {}
	}

}//end of class
