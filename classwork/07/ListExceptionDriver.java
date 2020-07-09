//ListDriver class for LList and Node
//Thursday, July 9, 2020
//Modified from Tuesday's Driver to try-catch exceptions

import java.util.NoSuchElementException;

public class ListExceptionDriver {

	public static void main(String[] args) {
		
		LList l = new LList();
		System.out.println(l.length + " =? " + l.nodeCount());
		l.addFront("C1");
		System.out.println(l.length + " =? " + l.nodeCount());
		System.out.println(l);
		l.addFront("C2");
		System.out.println(l.length + " =? " + l.nodeCount());
		System.out.println(l);
		l.addFront("C3");
		System.out.println(l.length + " =? " + l.nodeCount());
		System.out.println(l);
		l.addFront("C4");
		l.addFront("C5");
		l.addFront("C6");
		l.addFront("C7");
		l.addFront("C8");
		System.out.println(l.length + " =? " + l.nodeCount());
		System.out.println(l);
		System.out.println("----------End my Test----------\n");
		
		LList g = new LList();

		System.out.println("Test Empty list print & isEmpty:");
		System.out.println(g);
		System.out.println(g.isEmpty());
		
		try {
			g.addFront(null);
		}
		catch (IllegalArgumentException e) {
			e.printStackTrace();
			System.out.println("Element not added");
		}
		
		System.out.println("\nTest addFront 3x & isEmpty():");
		g.addFront("good");
		g.addFront("news");
		g.addFront("everyone!");
		
		System.out.println(g);
		System.out.println(g.isEmpty());

		System.out.println("\nTest get 0, 1, 5:");
		System.out.println(g.get(0));
		System.out.println(g.get(1));
		try {
			System.out.println(g.get(5));
		}
		catch (IndexOutOfBoundsException e) {
			e.printStackTrace();
			System.out.println("could not get element at index 5");
		}

		System.out.println("\nTest set 0, 1, 5:");
		g.set(0, "me!");
		g.set(1, "job");
		
		try {
			g.set(5, "wow");
		}
		catch (IndexOutOfBoundsException e) {
			e.printStackTrace();
			System.out.println("could not set index 5 to wow");
		}
		
		System.out.println(g);

		System.out.println("\nTest insert 0, 2, 5, 10:");
		g.insert(0, "woo!");
		g.insert(2, "go");
		g.insert(5, "cool");
		try {
			g.insert(10, "too far");
		}
		catch (IndexOutOfBoundsException e) {
			System.out.println("could not insert c'too far' into index 10");
		}
		
		System.out.println(g);

		System.out.println("\nTest search \"woo!\", \"cool\", \"too far\"");
		System.out.println(g.search("woo!"));
		System.out.println(g.search("cool"));
		try {
			System.out.println(g.search("too far"));
		}
		catch ( NoSuchElementException e) {
			e.printStackTrace();
			System.out.println("coudln't find it");
		}

		System.out.println("\nTest remove 0, 2:");
		LList t = new LList();
		try {
			t.remove(0);
		}
		catch ( IndexOutOfBoundsException e1 ) {
			e1.printStackTrace();
			System.out.println("Index error for remove(0)");
		}
		catch ( NoSuchElementException e2) {
			e2.printStackTrace();
			System.out.println("No such Element error for remove(0)");
		}
		g.remove(0);
		System.out.println(g);
		g.remove(2);
		System.out.println(g);
		try {
			g.remove(5);
		}
		catch ( NoSuchElementException e2) {
			e2.printStackTrace();
			System.out.println("No such Element error for remove(5)");
		}
		catch ( IndexOutOfBoundsException e1 ) {
			e1.printStackTrace();
			System.out.println("Index error for remove(5)");
		}
		
		System.out.println("\nTest copy(), merge() and split()");
		LList copyList = new LList();
		copyList.copy(l);
		System.out.println(l + " =copy? " + copyList);
		// g.merge(l);
		System.out.println(l + " merged with " + g);
		l.merge(g);
		System.out.println("= " + l);
		LList splitList = l.split(4);
		System.out.println(l + " got separated at index 4 " + splitList);

	}//end of main

}//end of class