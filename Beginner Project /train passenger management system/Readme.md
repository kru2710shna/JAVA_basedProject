# Train App

## Description
The `PersonThakkar` Java program is designed to manage a list of passengers on a train. It includes classes to represent individuals (`PersonThakkar` and `Passenger`) and a train (`Train`) that can store and manage passenger information. The program demonstrates inheritance, object-oriented principles, and basic data structures.

### Classes
1. `PersonThakkar`: Represents an individual with attributes such as first name, last name, phone, and email. It provides methods for getting and setting these attributes and includes a `toString` method for displaying the person's information.

2. `Passenger`: Inherits from `PersonThakkar` and represents a passenger on a train. It includes additional attributes like seat number and class type. The class provides methods to get and set these attributes, as well as a `toString` method for displaying passenger information.

3. `Train`: Implements the `list` interface and represents a train that can store and manage a list of passengers. It includes methods for adding passengers, searching for passengers by last name, deleting passengers, and printing the last names of all passengers. The class also includes a static count of passengers.

### Features
- Creating classes to represent individuals (`PersonThakkar`) and passengers (`Passenger`) with relevant attributes.
- Implementing inheritance to extend the `PersonThakkar` class to create the `Passenger` class.
- Creating a `Train` class that implements the `list` interface to manage a list of passengers.
- Adding passengers to the train with seat numbers and class types.
- Searching for passengers by their last name.
- Deleting passengers from the train based on their last name.
- Displaying the last names of all passengers.
- Utilizing static counters to keep track of the number of passengers in the train.

## Example Usage
```java
// Creating a train and adding passengers
Train sierraTrain = new Train(12);

Passenger dad = new Passenger("Ram", "Singh", "123-123-123", "ramsingh@gmail.com", 12, "First Class");
Passenger mom = new Passenger("Zill", "Panchal", "123-334-344", "zillpanchal@gmail.com", 15, "Second class");
// ... adding more passengers ...

sierraTrain.add(dad);
sierraTrain.add(mom);
// ... adding more passengers ...

// Displaying passengers and performing operations
System.out.println("Here is the list of the passengers in this train:\n" + sierraTrain);
sierraTrain.printLast();

System.out.println("This train has " + sierraTrain.getCount() + " passengers");

String lastNameToSearch = "Panchal";
Passenger foundPassenger = (Passenger) sierraTrain.search(lastNameToSearch);
if (foundPassenger != null) {
    System.out.println("Found passenger:\n" + foundPassenger);
} else {
    System.out.println("Passenger not found");
}

String lastNameToDelete = "Singh";
boolean passengerDeleted = sierraTrain.delete(lastNameToDelete);
if (passengerDeleted) {
    System.out.println("Passenger " + lastNameToDelete + " has been removed from the list");
} else {
    System.out.println("Passenger not found");
}
System.out.println("Updated passenger list:\n" + sierraTrain);
System.out.println("This train has " + Train.getCount() + " passengers");
