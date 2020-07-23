public class Person {
	private String lastName;
	private String firstName;
	private String phoneNumber;
	
	public Person(String lastName, String firstName, String phoneNumber) {
		this.lastName = lastName;
		this.firstName = firstName;
		this.phoneNumber = phoneNumber;
	}
	
	//immutable!
	public String getLastName() {
		return this.lastName;
	}
	
	public String getFirstName() {
		return this.firstName;
	}
	
	public String getPhoneNumber() {
		return this.phoneNumber;
	}
	
	@Override
	public String toString() {
		String output = "";
		output += "Last Name: " + lastName + "\n";
		output += "First Name: " + firstName + "\n";
		output += "Phone Number: " + phoneNumber + "\n";
		return output;
	}
}//end of class