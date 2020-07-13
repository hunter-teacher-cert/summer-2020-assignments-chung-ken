/* Created: Friday, July 10, 2020
 * *** Stack Project ***
 * Traditional operations on a stack are:
 * Operation	|	Explanation								|	Pseudo code example
 * push			|	add an item to the top of the stack		|	push "A"
 * pop			|	remove and return the item on the top	|
				|	of the stack							|	pop
 * top			|	return but don't remove the item		|
				|	from the top of the stack				|	top
 * this code also implements an isEmpty() method and a toString() method
 */
 
public class MyStack{
	// add your internal data structure here
	private LList stack;

	public MyStack(){
		// add constructor code
		this.stack = new LList();	
	}

	public void push(String data){
		// add code to push data on to the stack
		this.stack.addFront(data);
	}

	public String pop(){
		// add code to remove and return the
		// item on the top of the stack
		if (this.stack.length() > 0) {
			String data = this.stack.get(0);
			this.stack.remove(0);
			return data;
		}
		return null;
		//consider throwing an error instead of returning null
		//throw new IndexOutOfBoundsException("Nothing to pop");
	}

	public String top(){
		// add code to return but not remove the
		// item on the top of the stack
		if (this.stack.length() > 0)
			return this.stack.get(0);
		return null;
		//throw new IndexOutOfBoundsException("Nothing to pop");
	}

	public boolean isEmpty(){
		// return true if the stack has no data, false otherwise
		return this.stack.isEmpty();
	}

	public int size(){
		// add code to return the number of items currently on the stack
		return this.stack.length();
	}

	public String toString(){//pops everything off the stack to print
		String output = "";
		//not implemented
		//not sure if I want to pop all of the items off of the stack with a toString call
		while(!this.stack.isEmpty()) {
			output += pop();
		}
		return output;
	}

}