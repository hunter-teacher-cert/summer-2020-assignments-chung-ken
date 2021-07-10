/* MyStackDriver is the Driver class to
 * test the implementation of MyStack.java
 *
 * Created: Friday, July 10, 2020
 */
 
 public class MyStackDriver {
	 
	 public static void main(String[] args) {
		 MyStack stack1 = new MyStack(); //to test the empty case
		 
		 MyStack stack2 = new MyStack();
		 //Ultimately going to push the sentence:
		 //"are you as clever as I am"?
		 
		 // 1) Test isEmpty
		 System.out.println("\nTest isEmpty():");
		 stack2.push("are");
		 System.out.println("Stack 1 is empty: " + stack1.isEmpty());
		 System.out.println("   top of Stack 1 = " + stack1.top());
		 System.out.println("Stack 2 is empty: " + stack2.isEmpty());
		 System.out.println("   top of Stack 2 = " + stack2.top());
		 
		 // 2) Test push with size and top
		 System.out.println("\nTest push() and size():");
		 System.out.println("Stack 1 has size = " + stack1.size());
		 System.out.println("Stack 2 has size = " + stack2.size());
		 stack2.push("you");
		 System.out.println("   ...just pushed " + stack2.top());
		 System.out.println("Stack 2 has size = " + stack2.size());
		 stack2.push("as");
		 System.out.println("   ...just pushed " + stack2.top());
		 System.out.println("Stack 2 has size = " + stack2.size());
		 stack2.push("clever");
		 System.out.println("   ...just pushed " + stack2.top());
		 System.out.println("Stack 2 has size = " + stack2.size());
		 stack2.push("as");
		 System.out.println("   ...just pushed " + stack2.top());
		 System.out.println("Stack 2 has size = " + stack2.size());
		 stack2.push("I");
		 System.out.println("   ...just pushed " + stack2.top());
		 System.out.println("Stack 2 has size = " + stack2.size());
		 stack2.push("am");
		 System.out.println("   ...just pushed " + stack2.top());
		 System.out.println("Stack 2 has size = " + stack2.size());
		 
		 // 3) Test top, pop, and size
		 System.out.println("\nTest top(), pop(), and size():");
		 System.out.println(stack2.top() + "... just to check top(). Here we go...");
		 while ( !stack2.isEmpty() ) {
			 System.out.println(stack2.size() + "... " + stack2.pop());
		 }
		 System.out.println("Try to pop an empty stack: " + stack2.pop());
		 
	 }//end of main
	 
 }//end of class