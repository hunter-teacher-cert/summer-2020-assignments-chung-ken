# Data Structure Design Challenge - Description
## Day 15 - Tuesday, July 21, 2020


## Folder contains
1. This text file
2. Person.java
3. PhoneBook.java


## Person.java
creates objects that are immutable. They store:

```java
  private String lastName;
  private String firstName;
  private String phoneNumber;
```

and implement the methods:

```java
  public String getLastName()
  public String getFirstName()
  public String getPhoneNumber()
```
along with an overrided `toString()` method.


## PhoneBook.java
Stores data of type `Person` in an array of LinkedLists.
```
  private LinkedList<Person>[] book
```
The LinkedLists are necessary to implement an open hashing, or chaining, in case our hash function produces collisions at an index.

*In this implementation, we are expecting many collisions, because we are only using the first letter of the last name to generate the hash key. It would be worthwhile to experiment with other algorithms to see how we could produce mostly unique hash keys.

PhoneBook has the following operations / methods:
```java
  public int hashFunction(String key)  //this should be private in real implementation
```
- generates a simple hash key based on `lastName` which is used to add or access items in the phone book
```java
  public void add(Person p)
```
- adds `Person p` to the array index of the hash key. If there is collision at that index, then `p` is added to the `LinkedList` at that index.
```java
  public Person find(String lastName)
```
- uses the hash key to quickly access the correct index of the array, but may take longer if there are multiple collisions at that index.
```java
  public void remove(String lastName)
```
- in this case, uses the same algorithm as find but then removes the object if found
```java
  public void printList()
```
- prints each `Person` object in the phone book using `Person.toString()`'s overridden method.


There is also a working `main()` method that demonstrates all of the method. The output is:
```
***** Testing add() and printList() *****

The phone book currently has 6 entries...

Last Name: Alfano
First Name: Michael
Phone Number: 555-0001

Last Name: Alonso
First Name: Izagma
Phone Number: 555-0002

Last Name: Baerga
First Name: Lisa
Phone Number: 555-0003

Last Name: Bry
First Name: Pat
Phone Number: 555-0004

Last Name: Chung
First Name: Ken
Phone Number: 555-0005

Last Name: Comeaux
First Name: Angelo
Phone Number: 555-0006


***** Testing find() and remove() *****

Last Name: Chung
First Name: Ken
Phone Number: 555-0005


Remove "Chung" from book:

The phone book currently has 5 entries...

Last Name: Alfano
First Name: Michael
Phone Number: 555-0001

Last Name: Alonso
First Name: Izagma
Phone Number: 555-0002

Last Name: Baerga
First Name: Lisa
Phone Number: 555-0003

Last Name: Bry
First Name: Pat
Phone Number: 555-0004

Last Name: Comeaux
First Name: Angelo
Phone Number: 555-0006


***** Check out hash function *****

Wang: 22
Weiss: 22
Zamansky: 25
...Not the best hashing algorithm (and the hash function should probably be private anyway)
```

Lastly, the constructor initializes the instance variables as:
```java
  capacity = 26;
  book = new LinkedList[capacity];
  size = 0;
```
The value for `capacity` was set to 26 to accommodate A-Z. The idea of adding the `capacity` and `size` is to potentially implement the ability to copy and grow the hash table (phone book) to avoid collisions if `size` approaches or passes `capacity`; This would first require a better hash function.

## Additional questions:

1. What is the runtime for add?
	Should be almost constant time O(1).
2. What is the runtime for find?
	Ranges from O(1) if there are no collisions. In the worst case, where there is only one index with all the collisions, the search may take O(n) time.
3. What is the runtime for remove?
	We aren't concerned about the runtime for remove, but in this implementation, its runtime should be the same as find.
4. What is the runtime for printList?
	This will be the slowest method because it has to traverse through the entire phonebook which will take O(n) time.




## Reverse lookup

In addition to looking up people by last name, you might want to do a reverse lookup - that is look up someone by number.

1. Describe an algorithm that you could use to implement a revese lookup by number on your data structure. What is the runtime?
	If I had to look up a `Person` by `phoneNumber` as the `PhoneBook` currently is, I'd have to do a brute force search, similar to the `printList()` method to loop through each object in my LinkedList array until I got a match.  This could potentially take O(n) time.

2. How might you augment the data structure to implement reverse
   lookup more quickly? What would the algorithm look like now? What is the new runtime?
	In my readings, I came across the idea of doubleHashing to make the hash key more unique.  This lead me to the idea of making our array a 2D array and using 2 hash keys to get a [row][column] index - one hash key for `lastName` and another hash key for `phoneNumber`. The cost is more memory, but in return we'd have an easier search.
	*In class, we mentioned the possibility of just creating a 2nd hash table to save memory.
	The runtime for both cases should be about the same as the orginal `find()` method, ranging from constant O(1) to O(n).

### EOF