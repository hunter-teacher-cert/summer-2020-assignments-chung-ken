/*###################################################
 *#		BSTree Class - no BS! It's Binary Search.	#
 *#		- Instance Variable:						#
 *#			TreeNode root							#
 *#		- Public Methods:							#
 *#			int search(int key)						#
 *#			void insert(int key)					#
 *#			void inorderTraverse()					#
 *#			void preorderTraverse()					#
 *#			void postorderTraverse()				#
 *#			void delete(int key)					#
 *#			void seed() - create simple tree		#
 *#			void createTree(int h) - complete tree	#
 *#			int countLayers()						#
 *#			String toString()						#
 *###################################################
 *
 * Created: Tuesday, July 20, 2020
 * Hunter Summer Institute - Day 14
 *
 */

import java.io.*;
import java.util.*;

public class BSTree {

	private TreeNode root;
	
	public BSTree() {
		this.root = null;
	}
	
	public int search(int key) {
		TreeNode current = root;
		
		while ( current != null ) {
			int currentValue = current.getData();
			if (currentValue == key) {
				return currentValue;
			} else if (currentValue < key) {
				current = current.getRight();
			} else {
				current = current.getLeft();
			}
		}
		
		throw new NullPointerException();
	}//end search


	/* insert a value, key, at the end of a tree */	
	public void insert(int key) {
		
		TreeNode newNode = new TreeNode(key);
		
		//if the tree is empty, just start a new tree
		if (root == null) {
			root = newNode;
			return;
		}
		
		TreeNode front = root;
		TreeNode trailer = front;
		
		//find which open leaf to attach our value to
		while ( front != null ) {
			int frontValue = front.getData();
			if (frontValue == key) {
				//key value already exists in tree
				return;
			} else if (frontValue < key) {
				trailer = front;
				front = front.getRight();
			} else {
				trailer = front;
				front = front.getLeft();
			}
		}
		
		//set the correct node to key value
		if (key < trailer.getData()) {
			trailer.setLeft(newNode);
		} else {
			trailer.setRight(newNode);
		}
		
	}//end search


	/* traverse the list in order - for a sorted list*/
	public void inorderTraverse() {
		inorderTraverse(root);
		System.out.println();
	}
	private void inorderTraverse(TreeNode current) {
		
		if (current == null) { //stop if at the end of tree
			return;
		}
		
		//recursively print out the left subtree (all the less than values)
		inorderTraverse(current.getLeft());
		
		//process the current node
		System.out.print(current.getData() + ", ");
		
		//recursively print out the right subtree
		inorderTraverse(current.getRight());
	}
	
	
	/* process each node then traverse */
	public void preorderTraverse() {
		preorderTraverse(root);
		System.out.println();
	}
	private void preorderTraverse(TreeNode current) {
		
		if (current == null) { //stop if at the end of tree
			return;
		}
		
		//process the current node
		System.out.print(current.getData() + ", ");
		
		//recursively print out the left subtree (all the less than values)
		preorderTraverse(current.getLeft());
				
		//recursively print out the right subtree
		preorderTraverse(current.getRight());
	}


	/* traverse the list and process each node at the end */
	public void postorderTraverse() {
		postorderTraverse(root);
		System.out.println();
	}
	private void postorderTraverse(TreeNode current) {
		
		if (current == null) { //stop if at the end of tree
			return;
		}
		
		//recursively print out the left subtree (all the less than values)
		postorderTraverse(current.getLeft());
		
		//recursively print out the right subtree
		postorderTraverse(current.getRight());
		
		//process the current node
		System.out.print(current.getData() + ", ");
	}

	
	/* delete a node from a tree... reconnecting other nodes can get complicated */
	public void delete(int key) {
		
		//if tree is empty... stop here
		if (root == null) {
			return;
		}
		
		//otherwise, look for key in the tree
		TreeNode front = root;
		TreeNode trailer = front;
		
		while (front != null && front.getData() != key) {
			int frontValue = front.getData();
			if (frontValue == key) { //found the key
				break;
			} else if (frontValue < key) { //not big enough, move right
				trailer = front;
				front = front.getRight();
			} else { //too big, move left
				trailer = front;
				front = front.getLeft();
			}
		}
		
		if (front == null) { //we didn't find it
			return;
		}
		
		//if we get to here, we found key in the tree. now we have to "delete" it
		//case 1 - if the node is a leaf, we can just delete
		//  this means that left and right point both point to null
		//case 2 - if the node has one branch, we can just delete by
		//  attaching this.child to this.parent
		//case 3 - if the node has 2 branches, we have to replace with either:
		//  a) the largest node on the left branch OR
		//  b) the smallest node on the right branch
		if (front.getLeft() == null && front.getRight() == null) { //case 1
			System.out.println("Deletion case 1:");
			if (trailer.getLeft() != null && trailer.getLeft().getData() == key) {
				trailer.setLeft(null);
			} else {
				trailer.setRight(null);
			}
		} else if (front.getLeft() == null) { //case 2, has right value
			System.out.println("Deletion case 2R:");
			if (front == root) { //special case where trailer is not used
				root = root.getRight();
			} else {
				if (trailer.getLeft().getData() == key) {
					trailer.setLeft(front.getRight());
				} else {
					trailer.setRight(front.getRight());
				}
			}
		} else if (front.getRight() == null) { //case 2, has left value
			System.out.println("Deletion case 2L:");
			if (front == root) { //special case where trialer is not used
				root = root.getLeft();
			} else {
				if (trailer.getLeft().getData() == key) {
					trailer.setLeft(front.getLeft());
				} else {
					trailer.setRight(front.getLeft());
				}
			}
		} else { //case 3 - left and right not null
		System.out.println("Deletion case 3:");
			//save current location
			TreeNode deletionNode = front;
			// TreeNode deletionTrailer = trailer; //was not needed
			
			//find replacement value - using (a) - largest node on the left
			front = front.getLeft();
			while (front.getRight() != null) {
				trailer = front;
				front = front.getRight();
			}
			//if replacement node is the first left branch node
			if (front == deletionNode.getLeft()) {
				deletionNode.setLeft(null);
			} else {
				//if replacement node has a left branch, connect it to trailer
				if (front.getLeft() != null) {
					trailer.setRight(front.getLeft());
				} else { //otherwise, just disconnect
					trailer.setRight(null);
				}
			}
			//replace data at deletionNode with replacement data
			//instead of replacing whole node and copying left-right branches
			deletionNode.setData(front.getData());
			
			
		}
	}

	
	//create a basic tree
	public void seed() {
		TreeNode t;
		 
		t = new TreeNode(10);
		root = t;
		t = new TreeNode(5);
		root.setLeft(t);
		t = new TreeNode(20);
		root.setRight(t);
		 
		root.getLeft().setRight( new TreeNode(8));

		t = new TreeNode(15);
		root.getRight().setLeft(t);

		t = new TreeNode(22);
		root.getRight().setRight(t);
	}
	
	
	//Uses recursion to build a symmetrical tree with the numbers 
	// 1 through (2^(h+1)-1) spread across h rows.
	public void createTree(int h) { //h is the height of the tree
		int value = (int)Math.pow(2,h);
		root = new TreeNode(value);
		createTree(root, value, h-1);
	}
	private void createTree(TreeNode current, int pValue, int h) {
		if (h == 0) {
			current.setLeft(new TreeNode(pValue-1));
			current.setRight(new TreeNode(pValue+1));
			return;
		}
		int leftValue = pValue-(int)Math.pow(2,h);
		int rightValue = pValue+(int)Math.pow(2,h);
		current.setLeft(new TreeNode(leftValue));
		current.setRight(new TreeNode(rightValue));
		
		createTree(current.getLeft(), leftValue, h-1);
		createTree(current.getRight(), rightValue, h-1);
	}
	
	
	//recursive method to count how many layers in our tree
	public int countLayers() {
		TreeNode current = root;
		int layer = 0;
		return countLayers(root, layer);
	}
	private int countLayers(TreeNode current, int layer) {
		if (current.getLeft() == null && current.getRight() == null) {
			return layer;
		}
		int rightLayers = layer;
		int leftLayers = layer;
		layer++; //go to the next layer
		if (current.getRight() != null) { //count the right branch
			rightLayers = countLayers(current.getRight(), layer);
		}
		
		if (current.getLeft() != null) { //count the left branch
			leftLayers = countLayers(current.getLeft(), layer);
		}
		
		if (layer >= rightLayers && layer >= leftLayers) {
			return layer;
		}
		
		if (rightLayers >= leftLayers && rightLayers > layer) {
			return rightLayers;
		}
		
		if (leftLayers > rightLayers && leftLayers > layer) {
			return leftLayers;
		}
		
		return -1;
	}
	
	
	//helper method for toString - puts all node data into an ArrayList by layer
	//left nodes are printed in cyan, right nodes are printed in red
	//note: the root node is printed in green, but that is handled in toString()
	private void getChildren(TreeNode current, ArrayList<ArrayList<String>> layerElements, int layer, int maxLayer) {
		if (current != null) { //error checking
			System.out.println("Entering layer " + layer);
			System.out.println("... from " + current.getData());
		} else {
			System.out.println("Current is null... what do i do???");
		}
		
		String spaces = " ";
		for (int i = 0; i <= (maxLayer-layer)*(maxLayer-layer+1); i++) {
			spaces += "  ";
		}
		
		//populate information from left branch
		if (current != null && current.getLeft() != null) { //uses ansi color for Cyan (\u001b[36m)
			System.out.println("...looking left..." + current.getLeft().getData());
			if (current.getLeft().getData() < 10) { //pad single digit numbers
				layerElements.get(layer).add(spaces + " <\u001b[36m" + current.getLeft().getData() + "\u001b[0m" + spaces);
			} else {
				layerElements.get(layer).add(spaces + "<\u001b[36m" + current.getLeft().getData() + "\u001b[0m" + spaces);
			}
		} else {
			System.out.println("...looking left..." + "null");
			layerElements.get(layer).add(spaces + "<\u001b[36m__" + "\u001b[0m" + spaces + " ");
		}
		
		//populate information from right branch
		if (current != null && current.getRight() != null) { //uses ansi color for Red (\u001b[31m)
			System.out.println("...looking right..." + current.getRight().getData());
			if (current.getRight().getData() < 10) { //pad single digit numbers
				layerElements.get(layer).add(spaces + " \u001b[31m" + current.getRight().getData() + "\u001b[0m>" + spaces);
			} else {
				layerElements.get(layer).add(spaces + "\u001b[31m" + current.getRight().getData() + "\u001b[0m>" + spaces);
			}
		} else {
			System.out.println("...looking right..." + "null");
			layerElements.get(layer).add(spaces + "\u001b[31m__\u001b[0m>" + spaces);
		}
		
		if (layer == maxLayer) { //end if at last layer
			return;
		}
		
		//else, work on next layer
		layer++;
		getChildren(current.getLeft(), layerElements, layer, maxLayer);
		getChildren(current.getRight(), layerElements, layer, maxLayer);
	}
	//creates an ArrayList of ArrayLists to store values in the binary tree layer by layer
	//so the entire BSTree can be printed by layer
	//calls getChildren() method which then calls itself - 
	@Override
	public String toString() {
		String output = "";
		if (root == null) return output; //check if tree is empty
		
		int totalLayers = this.countLayers();
		for (int i = 0; i < (int)Math.pow(2,totalLayers); i++) {
			output += "=========";
		}
		output += "\n\n";
		
		//the ArrayList of ArrayLists to store the tree values by layer
		ArrayList<ArrayList<String>> allData = new ArrayList<ArrayList<String>>();
		for (int i = 0; i <= totalLayers; i++) {
			allData.add(new ArrayList<String>());
		}
		String spaces = "";
		for (int i = 0; i < (totalLayers+2)*(totalLayers+2); i++) {
			spaces += " ";
		}
		allData.get(0).add(spaces + "\u001b[32m" + root.getData() + "\u001b[0m" + spaces); //layer 0
		System.out.println("Root = " + root.getData());
		TreeNode current = root;
		int layer = 1;
		if (root.getLeft() != null || root.getRight() != null) { //populate layer 1 if not empty
			getChildren(root, allData, layer, totalLayers);
		}
		
		for (int i = 0; i <= totalLayers; i++) {
			for (int j = (totalLayers-i)*(totalLayers-i+1); j > 0; j--) { //pad front with spaces
				output += " ";
			}
			// output += allData.get(i).toString() + "\n\n";
			for (int k = 0; k < allData.get(i).size(); k++) {
				output += allData.get(i).get(k);
			}
			output += "\n\n";
		}
		for (int i = 0; i < (int)Math.pow(2,totalLayers); i++) {
			output += "=========";
		}
		output += "\n\n";
		return output;
	}
		
}//end of class