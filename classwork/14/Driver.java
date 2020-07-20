import java.io.*;
import java.util.*;


public class Driver {
	public static void main(String[] args) {
		BSTree t = new BSTree();

/************** Test for insert() *************/
		t.insert(10);
		t.insert(20);
		t.insert(5);
		t.insert(7);
		t.insert(8);
		t.insert(3);
		t.insert(25);

		t.preorderTraverse();
		t.postorderTraverse();
		t.inorderTraverse();
		
		t.seed();
		t.inorderTraverse();

/************** TEST for search() *************
		t.seed();
		
		int value;
		value = t.search(10);
		System.out.println(value);
		value = t.search(15);
		System.out.println(value);
		try {
			value = t.search(17);
			System.out.println(value);
		} catch (NullPointerException e) {
			System.out.println("17 not in tree");
		}
**********************************************/
	}
}