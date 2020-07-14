//Stack Applications
//Created: Monday, July 13, 2020

public class StackAppDriver extends MyStack{
	
	//Use a stack to push on a string and pop off in reverse
	public static String reverse(String s) {
		MyStack stack = new MyStack();
		if (s.contains(" ")) { //if there's a space, reverse by word
			int i = s.indexOf(" ");
			while (i != -1) {
				stack.push(s.substring(0, i+1)); //include space in push for toString spacing
				s = s.substring(i+1, s.length());
				i = s.indexOf(" ");
			}
			stack.push(s + " ");
		} else { //else, reverse by letter
			for (int i = 0; i < s.length(); i++) {
				stack.push(""+s.charAt(i));
			}
		}
		return stack.toString();
	}//reverse
	
	//Check if a string is a palindrome:
	// case1) by characters ignoring spaces and capitalization, OR
	// case2) by word ignoring capitalization
	public static boolean isPalindrome(String s) {
		String sJustLetters = "";
		if ( s.contains(" ") ) {
			for (int i = 0; i < s.length(); i++) {
				char c = s.charAt(i);
				if (c != ' ' && c != '.' && c != ',' ) {
					sJustLetters += c;
				}
			}
		}
		boolean case1 = sJustLetters.equalsIgnoreCase(reverse(sJustLetters));
		
		//Check case2
		boolean case2 = false;
		
		//let n = # of words (separated by single spaces)
		//if n is even, push half and then start comparing the 2nd half for string equality
		//if n is odd, we can ignore the middle word and check (n-1)/2 cases for string equality
		if (s.contains(" ")) {
			case2 = true;
			MyStack stack = new MyStack();
			int n = 1;
			String sRemaining = s.substring(0, s.length()); //potential error if same pointer(?)
			while (sRemaining.contains(" ")) {
				n++;
				sRemaining = sRemaining.substring(sRemaining.indexOf(" ") + 1, sRemaining.length());
			}
			if (n % 2 == 0) { //even # of words, push n/2 words onto stack and then start checking
				for (int i = 0; i < n/2; i++) {
					stack.push(s.substring(0, s.indexOf(" ")));
					s = s.substring(s.indexOf(" ") + 1, s.length());
				}
				for (int i = 0; i < n/2; i++) {
					if (s.contains(" ")) {
						if (!stack.pop().equalsIgnoreCase(s.substring(0, s.indexOf(" ")))) {
							case2 = false;
						}
						s = s.substring(s.indexOf(" ") + 1, s.length());
					} else { //no more spaces means it's time to check the last word
						if(!stack.pop().equalsIgnoreCase(s)) {
							case2 = false;
						}
					}
				}
			} else { //odd # of words, push on (n-1)/2 words, skip the middle, then start checking
				for (int i = 0; i < (n-1)/2; i++) {
					stack.push(s.substring(0, s.indexOf(" ")));
					s = s.substring(s.indexOf(" ") + 1, s.length());
				}
				s = s.substring(s.indexOf(" ") + 1, s.length()); //ignore middle word
				for (int i = 0; i < (n-1)/2; i++) {
					if (s.contains(" ")) {
						if (!stack.pop().equalsIgnoreCase(s.substring(0, s.indexOf(" ")))) {
							case2 = false;
						}
						s = s.substring(s.indexOf(" ") + 1, s.length());
					} else { //no more spaces means it's time to check the last word
						if(!stack.pop().equalsIgnoreCase(s)) {
							case2 = false;
						}
					}
				}
			}				
		}
		return case1 || case2;
			
	}//isPalindrome
	
	public static boolean parenCheck(String s) {
		MyStack roundStack = new MyStack();
		MyStack squareStack = new MyStack();
		MyStack curlyStack = new MyStack();
		
		//instead of just counting all brackets from the get-go...
		//sort brackets into 3 different stacks
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if ( c == '(' || c == ')' ) roundStack.push(""+s.charAt(i));
			if ( c == '[' || c == ']' ) squareStack.push(""+s.charAt(i));
			if ( c == '{' || c == '}' ) curlyStack.push(""+s.charAt(i));
		}
		if (roundStack.size() % 2 == 1) { //check if uneven number of ( and )
			return false;
		}
		if (squareStack.size() % 2 == 1) { //check if uneven number of [ and ]
			return false;
		}
		if (curlyStack.size() % 2 == 1) { //check if uneven number of { and }
			return false;
		}
		
		//if we have an even number of each type of bracket... now we pop() to count
		//again, i am intentionally trying to do this using Stack methods.
		//Check if () do not match
		int unpaired = 0;
		while (roundStack.size() != 0) {
			//check that a closed bracket comes off the stack first
			if ( roundStack.pop().equals(")") ) unpaired++;
			else unpaired--;
			
			if ( unpaired < 0 ) return false;
		}
		//if there are any closed brackets still unpaired...
		if (unpaired != 0) {
			return false;
		}
		
		//Check if [] do not match
		unpaired = 0;
		// **This part has extra comments from debugging... was using a for loop instead of a while
		// System.out.println("number of square brackets = " + squareStack.size() );		
		while (squareStack.size() != 0) {
			//check that a closed bracket comes off the stack first
			// System.out.println(squareStack.top());
			if ( squareStack.pop().equals("]") ) {
				unpaired++;
				// System.out.println("adding 1 to unpaired... = " + unpaired);
			}
			else {
				unpaired--;
				// System.out.println("subtracting 1 to unpaired... " + unpaired);
			}
			
			if ( unpaired < 0 ) {
				return false;
			}
		}
		//if there are any closed brackets still unpaired...
		if (unpaired != 0) {
			// System.out.println("GOT TO HERE");
			// System.out.println("unpaired = " + unpaired);
			return false;
		}
		
		//Check if {} do not match
		unpaired = 0;
		while (curlyStack.size() != 0) {
			//check that a closed bracket comes off the stack first
		if ( curlyStack.pop().equals("}") ) unpaired++;
			else unpaired--;
			
			if ( unpaired < 0 ) return false;
		}
		//if there are any closed brackets still unpaired...
		if (unpaired != 0) {
			return false;
		}
		
		//if we got to here, all brackets match
		return true;
	}//parenCheck
	
	public static void main(String[] args) {
		String s;
		System.out.println("****** Test reverse ******");
		s = "palindrome";
		System.out.println("Input: " + s);
		System.out.println("Reverse: " + reverse(s));
		s = "not a palindrome";
		System.out.println("Input: " + s);
		System.out.println("Reverse: " + reverse(s));
		
		System.out.println("\n****** Test isPalindrome ******");
		System.out.println("Input: " + s);
		System.out.println("isPalindrome: " + isPalindrome(s));
		s = "racecar";
		System.out.println("Input: " + s);
		System.out.println("isPalindrome: " + isPalindrome(s));
		s = "race car";
		System.out.println("Input: " + s);
		System.out.println("isPalindrome: " + isPalindrome(s));
		s = "A man a plan a canal Panama";
		System.out.println("Input: " + s);
		System.out.println("isPalindrome: " + isPalindrome(s));
		s = "Was it a car or a cat I saw";
		System.out.println("Input: " + s);
		System.out.println("isPalindrome: " + isPalindrome(s));
		s = "Never even or odd";
		System.out.println("Input: " + s);
		System.out.println("isPalindrome: " + isPalindrome(s));
		s = "Never odd or even";
		System.out.println("Input: " + s);
		System.out.println("isPalindrome: " + isPalindrome(s));
		s = "I am what am I";
		System.out.println("Input: " + s);
		System.out.println("isPalindrome: " + isPalindrome(s));
		s = "I am what I am";
		System.out.println("Input: " + s);
		System.out.println("isPalindrome: " + isPalindrome(s));
				
		System.out.println("\n****** Test parenCheck ******");
		s = "{(4+5)/[(2-3)+2]}";
		System.out.println("Input: " + s);
		System.out.println("parenCheck: " + parenCheck(s));
		s = "{(4+5)/[(2-3+2]}";
		System.out.println("Input: " + s);
		System.out.println("parenCheck: " + parenCheck(s));
		s = "{(4+5)/[(2-3)+2}";
		System.out.println("Input: " + s);
		System.out.println("parenCheck: " + parenCheck(s));
		s = "(4+5)/[(2-3)+2]}";
		System.out.println("Input: " + s);
		System.out.println("parenCheck: " + parenCheck(s));
		
		
	}//end main
	
}//end class