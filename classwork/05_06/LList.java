//Linked List - a bunch of Nodes
//Tuesday, July 7, 2020

public class LList {
	//instance variables
	private Node head;
	public int length = 0; //thinking of using this to replace nodeCount() method
	
	//constructor
	public LList(){
		this.head = null;
	}
	
	//methods
	public void addFront(String data) {
		Node newNode = new Node(data);
		newNode.setNext(this.head);
		head = newNode;
		length++;
	}
	
	public boolean isEmpty() {
		return head == null;
	}
	
	//helper method to count how many Nodes are in the List
	//Update: This method is obsolete now that we have a length instance variable
	public int nodeCount() {
		int count = 0;
		Node currentNode = head;
		while (currentNode != null) {
			count++;
			currentNode = currentNode.getNext();
		}
		return count;
	}
	
	public String get(int index) {
		if (index < length) {
			Node currentNode = head;
			for (int i = 0; i < index; i++) {
				currentNode = currentNode.getNext();
				//if (currentNode == null) break;
			}
			//if (currentNode != null) {
			return currentNode.getData();
			//}
		}
		return null;
	}//commented out code in this method was from implementation without nodeCount() - now length
	
	public void set(int index, String value) {
		if (index < length) {
			Node currentNode = head;
			for (int i = 0; i < index; i++) {
				currentNode = currentNode.getNext();
			}
			currentNode.setData(value);
		} else {
			System.out.printf("set(%d, \"%s\") index out of bounds\n", index, value);
		}
	}
	
	public void insert(int index, String value) {
		if (index == 0) {
			addFront(value);
		} else if (index <= length) {
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
		}
	}
	
	public int search(String key) {
		Node currentNode = head;
		// int max = nodeCount();  //replaced with length instance variable
		for (int i = 0; i < length; i++) {
			if (key.equals(currentNode.getData())) {
				return i;
			}
			currentNode = currentNode.getNext();
		}
		return -1;
	}
	
	/** the remove() method uses currentNode traverses the list to 
	 * the Node preceding the Node we want to remove and copies the 
	 * next value of Node to be removed
	 */
	public void remove(int index) {
		if (index == 0) {
			head = head.getNext();
			length--;
		} else if (index < length) {
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
		return null;
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