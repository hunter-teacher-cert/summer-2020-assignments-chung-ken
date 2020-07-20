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