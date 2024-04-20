# Contact App

This Java application implements a simple contact management system. It allows users to create, delete, and search for contacts by last name, and displays the contact list.

## Features

- Adding a Contact: The program enables users to add new contacts with a first name, last name, and phone number. Contacts are inserted into the list while maintaining alphabetical order by last name.

- Deleting a Contact: Contacts can be removed from the list based on their last name. If a contact is found and deleted, the list is updated.

- Searching a Contact: Users can search for a contact's phone number by providing the last name. A linear search is performed to find the contact in the list.

- Displaying Contacts: The program can display the entire list of contacts, showing their first name, last name, and phone number.

## Code Structure

- `Person`: This class represents a contact person and contains attributes for first name, last name, and phone number. It implements the `Comparable` interface to enable sorting based on last names.

- `Directory`: This class manages the contact list. It provides methods to add, delete, search for contacts, and display the list. The list is maintained in sorted order.

- `Driver`: The main class contains a sample scenario where contacts are added, deleted, searched, and displayed. It demonstrates the functionality of the `ContactAppThakkar`.

## Usage

1. Compile the Java files using a Java compiler.
2. Run the `Driver` class to see the sample scenario of contact management.

## Example

```
java
// Adding contacts
myContact.addContact("Ana", "Baily", " 568-345-9999");
myContact.addContact("Mary", "Busta", "111-222-3333");
// ... (other contacts)

// Displaying contents of the contact
System.out.println("Displaying the content of the Contact added");
System.out.println(myContact);

// Removing a contact
myContact.deleteContact("Baily");
System.out.println("Removed Baily from the contact list");
System.out.println(myContact);

// Searching for a contact
System.out.println("Searching for contact with last name 'Richard'");
System.out.println(myContact.searchContact("Richard"));

// Adding another contact
myContact.addContact("Mary", "Brown", "999-100-1244");
System.out.println("Added one more person as a contact");
System.out.println(myContact);

// Searching for a contact not in the list
System.out.println("Searching for 'Dobeck' who is not in the contact");
myContact.searchContact("Dobeck");

```

