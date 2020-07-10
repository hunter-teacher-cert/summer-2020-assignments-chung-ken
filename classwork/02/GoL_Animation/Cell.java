//Cell.java to be used with LifeImage
//This is the class for the individual cell in the Game of LifeImage

import java.io.*;
import java.util.*;

public class Cell {
	//instance variables
	private int life;
	private int neighbors;
	
	//constructors
	public Cell(int chance) {
		if (Math.random() * 100 < chance) {
			this.life = 255;
		} else {
			this.life = 0;
		}
	}
	
	public Cell() {
		this(50);
	}
	
	//getter method
	public int getCell() {
		return life;
	}
	
	//setter method
	public void setCell(int life) { //give cell the value life
		this.life = life;
	}
	public void setCell() { //have cell change its own value for life based on # of neighbors
		if (this.life != 0) {
			if (neighbors < 2 || neighbors > 3) {
				this.life = 0; //death rules
			}
		} else if (neighbors == 3) {
			this.life = 255; //birth rule
		}
	}
	
	public void setNeighbors(int neighbors) {
		this.neighbors = neighbors;
	}
	
	public int getNeighbors() {
		return this.neighbors;
	}
	
}//end of class