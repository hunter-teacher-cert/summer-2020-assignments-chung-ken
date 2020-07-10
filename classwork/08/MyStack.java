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
	}

	public String top(){
		// add code to return but not remove the
		// item on the top of the stack
		if (this.stack.length() > 0)
			return this.stack.get(0);
		return null;
	}

	public boolean isEmpty(){
		// return true if the stack has no data, false otherwise
		return this.stack.isEmpty();
	}

	public int size(){
		// add code to return the number of items currently on the stack
		return this.stack.length();
	}

}