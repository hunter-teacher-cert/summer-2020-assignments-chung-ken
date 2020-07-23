import java.util.LinkedList;

public class PhoneBook {
	
	//array of LinkedLists for chaining (open hashing)
	private LinkedList<Person>[] book;
	private int size;
	private int capacity;
	
	public PhoneBook() {
		// this is a simple hash table with an array size of 26 - to store people by last name
		capacity = 26;
		book = new LinkedList[capacity];
		size = 0;
		//Note: Ideally, the capacity is equal to or slightly greater than the number of elements the table has to hold - but that is only necessary for closed hashing.  We have the LinkedList in order to hold multiple Persons at one index (open hashing).
	}
	
	//a very simple implementation of a hash function
	public int hashFunction(String key) {
		if (key == null) {
			return -1;
		}
		
		// takes the first letter of last name and converts to number [0,25]
		int hashKey = key.toUpperCase().charAt(0) - 'A';
		
		// consider a more sophisticated implementation where
		// 1) we might need to mod the keyValue to fit in our array
		// 2) we could mod by a prime # to create more unique indexing
		hashKey %= 26;
		
		return hashKey;
	}
	
	public void add(Person p) {
		if (p == null) {
			//could throw an exception here
			return;
		}
		
		//get hashKey by last name
		int hashKey = hashFunction(p.getLastName());
		
		if (book[hashKey] == null) {
			//not chaining yet. Initialize LinkedList
			book[hashKey] = new LinkedList<Person>();
		}
		book[hashKey].add(p); //Person added to "hash table"
		size++;
	}
	
	//find assumes last names are unique
	public Person find(String lastName) {
		//get hashKey by last name
		int hashKey = hashFunction(lastName);
		
		//this will be fast if there is only one Person at this index
		if (book[hashKey].peek().getLastName().equalsIgnoreCase(lastName)) {
			return book[hashKey].peek();
		}
		
		//if we're here, we have to look at a possibly longer LinkedList to find our lastName
		// the time will depend on how poorly we've hashed our table and how many collisions we may have at this index!
		int pageSize = book[hashKey].size();
		for (int i = 1; i < pageSize; i++) {
			if (book[hashKey].get(i).getLastName().equalsIgnoreCase(lastName)) {
				return book[hashKey].get(i);
			}
		}
		
		return null; //didn't find Person
	}
	
	//remove assumes last names are unique
	//otherwise, will only remove first instance
	public void remove(String lastName) {
		int hashKey = hashFunction(lastName);
		
		//this will be fast if there is only one Person at this index
		if (book[hashKey].peek().getLastName().equalsIgnoreCase(lastName)) {
			book[hashKey].pop();
			size--;
			return;
		}
		
		//if we're here, we have to look at a possibly longer LinkedList to find our lastName
		// the time will depend on how poorly we've hashed our table and how many collisions we may have at this index!
		int pageSize = book[hashKey].size();
		for (int i = 1; i < pageSize; i++) {
			if (book[hashKey].get(i).getLastName().equalsIgnoreCase(lastName)) {
				book[hashKey].remove(i);
				size--;
				return;
			}
		}
		
		//didn't find lastName to remove if we got here
	}
	
	public void printList() {
		System.out.println("\nThe phone book currently has " + size + " entries...\n");
		for (int i = 0; i < capacity; i++) {
			if (book[i] != null) {
				for (int j = 0; j < book[i].size(); j++) {
					System.out.println(book[i].get(j));
				}
			}
		}
	}
	
	/****************************************
	 *		Test methods using main			*
	 ****************************************/
	public static void main(String[] args) {
		PhoneBook pb = new PhoneBook();
		
		System.out.println("\n\n***** Testing add() and printList() *****");
		pb.add(new Person("Alfano", "Michael", "555-0001"));
		pb.add(new Person("Alonso", "Izagma", "555-0002"));
		pb.add(new Person("Baerga", "Lisa", "555-0003"));
		pb.add(new Person("Bry", "Pat", "555-0004"));
		pb.add(new Person("Chung", "Ken", "555-0005"));
		pb.add(new Person("Comeaux", "Angelo", "555-0006"));
		pb.printList();
		
		System.out.println("\n***** Testing find() and remove() *****\n");
		System.out.println(pb.find("Chung"));
		System.out.println("\nRemove \"Chung\" from book:");
		pb.remove("Chung");
		pb.printList();
		
		System.out.println("\n***** Check out hash function *****\n");
		System.out.println("Wang: " + pb.hashFunction("Wang"));
		System.out.println("Weiss: " + pb.hashFunction("Weiss"));
		System.out.println("Zamansky: " + pb.hashFunction("Zamansky"));
		//the 2 last names that start with W will produce the same hash key here
		System.out.println("...Not the best hashing algorithm (and the hash function should probably be private anyway)");
/* Names to add:
Jimmy Dillon
Jessica Du
Bob Garber
Joshua Hans
Sam Keener
Tsee Lee
Jack McCoy
Margie MonteLeon
Daniel Moscoe
Jack Padalino
Sangmin Pak
Jonathan Swotinsky
Alex Torres
Huan Wang
Rosane Weiss
Topher Mykolyk
Mike Zamansky
*/
	}

}