/* Linked List - a bunch of Nodes
 * Created: Tuesday, July 7, 2020
 * Modified: Thursday, July 9, 2020
 * 	to include exception handling
 * 	Specifically:
 * 	1) IllegalArgumentException
 *		- used in AddFront(), set(), insert(), and search() when passing a null argument
 * 	2) IndexOutOfBoundsException
 *		- used in methods that reference a specific index
 *			i.e. - get(), set(), insert(), remove(), *split()
 * 	3) NoSuchElementException
 *		- used in search() and remove() - in the 0-length scenario
 * Modified: Friday, July 10, 2020
 *	to be used with MyStack.java
 *	Changes include:
 *		- length is now private
 *		- nodeCount() has been removed
 *		- length() just gets length
 */
import java.util.NoSuchElementException; //This exception not part of default package

public class LList {
	//instance variables
	private Node head;
	private int length = 0; //thinking of using this to replace nodeCount() method
	
	//constructor
	public LList(){
		this.head = null;
	}
	
	//methods
	public void addFront(String data) {
		if (data == null) {
			throw new IllegalArgumentException("You want me to add a Node with value null?");  //flag if no data
		}
		Node newNode = new Node(data);
		newNode.setNext(this.head);
		head = newNode;
		length++;
	}
	
	public boolean isEmpty() {
		return head == null;
	}
	
	public int length() { //
		return length;
	}
	
	public String get(int index) {
		if (index < length && index >= 0) {
			Node currentNode = head;
			for (int i = 0; i < index; i++) {
				currentNode = currentNode.getNext();
			}
			return currentNode.getData();
		} else {
			throw new IndexOutOfBoundsException();
		}
		//return null
	}
	
	public void set(int index, String value) {
		if (value == null) {
			throw new IllegalArgumentException("not setting values to null here");
		}
		if (index < length && index >= 0) {
			Node currentNode = head;
			for (int i = 0; i < index; i++) {
				currentNode = currentNode.getNext();
			}
			currentNode.setData(value);
		} else {
			System.out.printf("set(%d, \"%s\") index out of bounds\n", index, value);
			throw new IndexOutOfBoundsException("can't set... you got your indeces mixed up");
		}
	}
	
	public void insert(int index, String value) {
		if (value == null) {
			throw new IllegalArgumentException("not inserting a null value for you");
		}
		if (index == 0) {
			addFront(value);
		} else if (index > 0 && index <= length) {
			Node newNode = new Node(value);
			Node currentNode = head;
			for (int i = 0; i < index - 1; i++) {
				currentNode = currentNode.getNext();
			}
			newNode.setNext(currentNode.getNext());
			currentNode.setNext(newNode);
			length++;
		} else {
			System.out.printf("insert(%d, \"%s\") index out of bounds\n", index, value);
			throw new IndexOutOfBoundsException("you're trying to insert off the reservation");
		}
	}
	
	public int search(String key) {
		if (key == null) {
			throw new IllegalArgumentException("not going to search for null");
		}
		Node currentNode = head;
		for (int i = 0; i < length; i++) {
			if (key.equals(currentNode.getData())) {
				return i;
			}
			currentNode = currentNode.getNext();
		}
		throw new NoSuchElementException("i looked for it, but it's not here");
		// return -1;
	}
	
	/** the remove() method uses currentNode to traverse the list to 
	 * the Node preceding the Node we want to remove and copies the 
	 * next value of Node to be removed
	 */
	public void remove(int index) {
		if (length == 0) {
			throw new NoSuchElementException("can't remove anything if there's nothing here");
		}
		if (index == 0) {
			head = head.getNext();
			length--;
		} else if (index > 0 && index < length) {
			Node currentNode = head;
			for (int i = 0; i < index - 1; i++) {
				currentNode = currentNode.getNext();
			}
			//i.e. - remove(3) - get to index 2
			//currentMode is at index 2
			//curreNode.getNext().getNext() points to index 4
			currentNode.setNext(currentNode.getNext().getNext());
			length--;
		} else {
			System.out.printf("remove(%d) index does not exist\n", index);
			throw new IndexOutOfBoundsException("i can't remove things out of bounds");
		}
	}
	
	/*****EXTRA METHODS TO TRY*****
	 * 1) Copy
	 * 2) Merge
	 * 3) Split
	 */
	// copy() copies and replaces this list with same values and order from that list
	public void copy(LList that) {
		this.head = new Node();  //clear out old List
		Node thisCurrentNode = this.head;
		Node thatCurrentNode = that.head;
		thisCurrentNode.setData(thatCurrentNode.getData());
		for (int i = 1; i < that.length; i++) {
			thatCurrentNode = thatCurrentNode.getNext();
			Node newNode = new Node();
			thisCurrentNode.setNext(newNode);
			thisCurrentNode = newNode;
			thisCurrentNode.setData(thatCurrentNode.getData());
		}
	}
	
	// merge() appends that list to the end of this list
	public void merge(LList that) {
		Node currentNode = this.head;
		for (int i = 1; i < this.length; i++) {
			currentNode = currentNode.getNext();
		}
		currentNode.setNext(that.head);
		this.length += that.length;
	}
	
	// split(int index) truncates the current List up to index-1 and creates a 2nd List
	// from index to end
	public LList split(int index) {
		if (index < this.length) {
			LList list2 = new LList();
			Node currentNode = this.head;
			for (int i = 0; i < index-1; i++) {
				currentNode = currentNode.getNext();
			}
			list2.head = currentNode.getNext();
			currentNode.setNext(null);
			list2.length = this.length - index;
			this.length = index - 1;
			return list2;
		}
		throw new IndexOutOfBoundsException();
		// return null;
	}
	
	@Override
	public String toString() {
		String s = "< ";
		Node currentNode = this.head;
		
		while(currentNode != null) {
			s += currentNode.getData() + " ";
			//go to next Node
			currentNode = currentNode.getNext();
		}
		
		s += ">";
		return s;
	}
	
}//end of class