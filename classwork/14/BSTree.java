import java.io.*;
import java.util.*;

public class BSTree {
	public TreeNode root;
	
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
	
	public void inorderTraverse(TreeNode current) {
		
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
	
	public void preorderTraverse(TreeNode current) {
		
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
	
	public void postorderTraverse(TreeNode current) {
		
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
	
}//end of class