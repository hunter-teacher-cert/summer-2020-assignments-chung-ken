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

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;

// import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class LifeImage extends Canvas {
	public int ROWS = 250;
	public int COLS = 250;
	public boolean WRAP = true;
	private Cell[][] world;
	private int pixelWidth = 2;
	private int pixelHeight = 2;

	/**
	 * createNewBoard method
	 * creates a 2D array of ints to store states of cells
	 */
	 
	public LifeImage(){
		setBackground(new Color(0x000000));
		setSize(COLS * pixelWidth, ROWS * pixelHeight);
		//create 2-D array of cells
		createNewWorld();
	}
	
	public void createNewWorld() {
		world = new Cell[ROWS][COLS];
		for (int r = 0; r < ROWS; r++) {
			for (int c = 0; c < COLS; c++) {
				world[r][c] = new Cell();
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
				countNeighbours(r, c);
			}
		}
		for (int r = 0; r < ROWS; r++) {
			for (int c = 0; c < COLS; c++) {
				System.out.println("  In updateWorld for loop..." + (r * c));
				world[r][c].setCell();
			}
		}
	}//end method updateWorld

	//drawCell method
	public void drawCell(Graphics g, int r, int c) {

		int x = c * pixelWidth;
		int y = r * pixelHeight;
		int color = world[r][c].getCell();
		//Graphics2D g2d = (Graphics2D) g;
		g.setColor(new Color(color, color, color));
        g.fillRect(x, y, pixelWidth, pixelHeight);
		//Rectangle2D pixel = new Rectangle2D(x, y, pixelWidth, pixelHeight);
		//g2d.draw(pixel);
	}
	
	public void paint(Graphics g) {
		// BufferedImage img = new BufferedImage(COLS * pixelWidth, ROWS * pixelHeight, BufferedImage.TYPE_INT_RGB);
		// Graphics2D g = (Graphics2D)img.getGraphics();
		for(int i = 0; i < COLS; i++) {
			for(int j = 0; j < ROWS; j++) {
				drawCell(g, i, j);
			}
		}
		// updateWorld();
	}

	//helper method to slow down animation 
	public static void delay(int n) {
		try {
			Thread.sleep(n);
		} 
		catch(InterruptedException e) {}
	}
	
	public static void main(String[] args) {
        // make the frame
        JFrame frame = new JFrame("Game of Life");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // create and add the Life World
        LifeImage canvas = new LifeImage();
        frame.getContentPane().add(canvas);

        // show the frame
        frame.pack();
        frame.setVisible(true);
		for (int i = 0; i < 1000; i++) {
			canvas.updateWorld();
			delay(1000);
			frame.getContentPane().add(canvas);
		}
    }//end of main

}//end of class
