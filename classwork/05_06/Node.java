/** Linked Lists - just the Node
 * Tuesday, July 7, 2020
 *
 * Note: This the Node for a Linked List that only go in one direction.
 * 	We could go in both directions if we used Node prev too.
 */


import java.io.*;
import java.util.*;

public class Node {
	//instance variables
	private String data;
	private Node next;
	
	//constructors
	public Node(String data, Node next) {
		this.next = next;
		this.data = data;
	}
	public Node(String data) {
		this.next = null;
		this.data = data;
	}
	public Node() {
		this.next = null;
		this.data = "";
	}
	
	//getters
	public String getData() {
		return this.data;
	}
	public Node getNext() {
		return this.next;
	}
	
	//setters
	public void setData(String data) {
		this.data = data;
	}
	public void setNext(Node next) {
		this.next = next;
	}
	
	@Override
	public String toString() {
		return this.data;
	}
	
	//main - to test
	public static void main(String[] args) {
		Node n1 = new Node("Al");
		Node n2 = new Node("Bob");
		n1.setNext(n2);
		System.out.println(n1);
		System.out.println(n2);
		Node n3 = new Node("Carl",n2);
		n1.setNext(n3);
		Node currentNode = n1;
		System.out.println(currentNode);
		while(currentNode.getNext() != null) {
			currentNode = currentNode.getNext();
			System.out.println(currentNode.getClass().getName()+"@"+Integer.toHexString(System.identityHashCode(currentNode))+currentNode);
		}
		String s = "";
		System.out.println(s.getClass().getName()+"@"+Integer.toHexString(System.identityHashCode(s)));
		s = "a";
		System.out.println(System.identityHashCode(s));
		System.out.println(s.getClass().getName()+"@"+Integer.toHexString(System.identityHashCode(s)));
		System.out.println(s.hashCode());
		s = null;
		System.out.println(Integer.toHexString(System.identityHashCode(s)));
	}//end of main
	
}//end of class