//ListDriver class for LList and Node
//Tuesday, July 7, 2020


public class ListDriver {

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
		// System.out.println(g.length + " =? " + g.nodeCount());

		System.out.println("\nTest addFront 3x & isEmpty():");
		g.addFront("good");
		g.addFront("news");
		g.addFront("everyone!");
		// System.out.println(g.length + " =? " + g.nodeCount());
		System.out.println(g);
		System.out.println(g.isEmpty());

		System.out.println("\nTest get 0, 1, 5:");
		System.out.println(g.get(0));
		System.out.println(g.get(1));
		System.out.println(g.get(5));

		System.out.println("\nTest set 0, 1, 5:");
		g.set(0, "me!");
		g.set(1, "job");
		g.set(5, "wow");
		// System.out.println(g.length + " =? " + g.nodeCount());
		System.out.println(g);

		System.out.println("\nTest insert 0, 2, 5, 10:");
		g.insert(0, "woo!");
		g.insert(2, "go");
		g.insert(5, "cool");
		g.insert(10, "too far");
		// System.out.println(g.length + " =? " + g.nodeCount());
		System.out.println(g);

		System.out.println("\nTest search \"woo!\", \"cool\", \"too far\"");
		System.out.println(g.search("woo!"));
		System.out.println(g.search("cool"));
		System.out.println(g.search("too far"));

		System.out.println("\nTest remove 0, 2:");
		g.remove(0);
		// System.out.println(g.length + " =? " + g.nodeCount());
		System.out.println(g);
		g.remove(2);
		// System.out.println(g.length + " =? " + g.nodeCount());
		System.out.println(g);
		g.remove(5);
		// System.out.println(g.length + " =? " + g.nodeCount());
		
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