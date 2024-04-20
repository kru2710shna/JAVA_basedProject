# Train Booking System - Java Project

This repository contains a Java-based Train Booking System that allows users to manage passenger information, search for passengers by last name, delete passengers, and view the passenger list for a train.

## Project Overview

The project is structured as follows:

- `TrainProjectThakkar.java`: This file defines the main class `TrainProjectThakkar`, representing a passenger with attributes like first name, last name, phone number, and email. It also includes methods for setting and getting attributes, along with overrides for `toString()` and `equals()`.

- `Passenger.java`: This class extends `TrainProjectThakkar` and introduces additional attributes like seat number and class type. It provides methods to interact with these attributes and inherits the `toString()` method from the superclass.

- `List.java`: An interface that defines methods for adding, searching, deleting, and printing passengers.

- `Train.java`: This class implements the `List` interface and represents a train. It contains methods to manage passengers, such as adding, searching, deleting, and printing the last names of passengers.

- `PersonThakkarDriver.java`: The main driver class where the application logic is implemented. It creates instances of `Train`, adds passengers, demonstrates the system's functionalities, and interacts with users to perform operations.

## Usage

To run the Train Booking System, compile and execute the `PersonThakkarDriver.java` class. The program will prompt you to enter passenger details, search for passengers, delete passengers, and display information.

## How to Run

1. Ensure you have Java installed on your system.
2. Clone this repository using `git clone`.
3. Compile the Java files using `javac PersonThakkarDriver.java`.
4. Run the compiled class using `java PersonThakkarDriver`.

## Author

Krushna Thakkar - [GitHub Profile](https://github.com/krushnathakkar)

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

Feel free to contribute, raise issues, or suggest improvements. Your contributions are greatly appreciated!
