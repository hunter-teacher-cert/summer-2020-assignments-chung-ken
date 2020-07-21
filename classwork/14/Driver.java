import java.io.*;
import java.util.*;


public class Driver {
	public static void main(String[] args) {
		BSTree t = new BSTree();

/************** Test for insert() *************/
				
		t.seed();
		// t.inorderTraverse();

		t.insert(10);
		t.insert(20);
		t.insert(5);
		t.insert(7);
		t.insert(8);
		t.insert(3);
		t.insert(25);
		t.inorderTraverse();
		System.out.println(t);
		// t.delete(15);
		// System.out.println("Delete 15:");
		// t.inorderTraverse();
		// System.out.println(t);
		t.delete(22);
		System.out.println("Delete 22:");
		t.inorderTraverse();
		System.out.println(t);
		t.delete(10);
		System.out.println("Delete 10:");
		t.inorderTraverse();
		System.out.println(t);
		t.delete(25);
		System.out.println("Delete 25:");
		t.inorderTraverse();
		System.out.println(t);
		t.delete(5);
		System.out.println("Delete 5:");
		t.inorderTraverse();
		System.out.println(t);
		t.delete(3);
		System.out.println("Delete 3:");
		t.inorderTraverse();
		System.out.println(t);
		t.delete(8);
		System.out.println("Delete 8:");
		t.inorderTraverse();
		System.out.println(t);
		t.delete(7);
		System.out.println("Delete 7:");
		t.inorderTraverse();
		System.out.println(t);
		t.insert(4);
		System.out.println("Insert 4:");
		t.inorderTraverse();
		System.out.println(t);
		t.delete(20);
		System.out.println("Delete 20:");
		t.inorderTraverse();
		System.out.println(t);
/************** Test for Traverse *************
		t.preorderTraverse();
		t.postorderTraverse();
		t.inorderTraverse();
*/

/************** Test for Remove ***************
		t.delete(20);
		t.inorderTraverse();
*/
		t.createTree(2);
		t.inorderTraverse();
		// t.preorderTraverse();
		// System.out.println(t.countLayers());
		System.out.println(t);
		t.delete(8);
		System.out.println("Delete 8:");
		t.inorderTraverse();
		System.out.println(t);
		t.delete(16);
		System.out.println("Delete 16:");
		t.inorderTraverse();
		System.out.println(t);
		t.delete(7);
		System.out.println("Delete 7:");
		t.inorderTraverse();
		System.out.println(t);

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