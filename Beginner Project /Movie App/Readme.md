# Movie App

## Description
The `MovieAppThakkar` Java program is designed to manage a list of movies. It includes a custom implementation of a linked list (`MovieList`) to store movie information and provides various methods for adding, removing, and retrieving movies. The program demonstrates the use of object-oriented principles and data structures to manage and manipulate movie data.

### Classes
1. `Movie`: Represents a movie with attributes such as name, genre, rating, and viewer count. It implements the `Comparable` interface for comparing movies based on their names.

2. `ListNode`: Defines a node for the linked list. It holds a reference to a `Movie` object and the next node in the list.

3. `List` (Interface): Specifies the methods to be implemented by classes that manage movie lists. Methods include adding movies, finding movie indices, removing movies, getting movie information by position, and more.

4. `MovieList`: Implements the `List` interface and provides functionality to manage a list of movies using linked list operations. It supports adding movies to the end or a specific position, finding the index of a movie, removing movies, getting the most-watched movie, and retrieving movies by rating.

5. `Driver`: Contains the main method to demonstrate the functionality of the `MovieList` class. It adds movies to the list, performs various operations, and displays the results.

### Features
- Creating a `Movie` class to represent movie objects with attributes.
- Implementing a linked list using the `MovieList` class to manage movies.
- Adding movies to the list at the end or a specific position.
- Finding the index of a movie by its name.
- Removing movies from the list.
- Displaying the most-watched movie.
- Retrieving movie information by position.
- Demonstrating the usage of the implemented classes in the `Driver` class.

## Example Usage
```java
MovieList mylist = new MovieList();
mylist.add("Donal", "Cartoon", 3, 1000);
mylist.add("Terminator", "Action", 5, 10000);
mylist.add("Fairy", "Children", 3, 5000);
mylist.add("Black Hole", "Science", 4, 80000);

System.out.println("Here is the list of the movies:\n" + mylist);
System.out.println("The most watched movie:\n" + mylist.mostWatched());
System.out.println("Movie with the highest rating:\n" + mylist.getMovie(5));
System.out.println("First movie in the list:\n" + mylist.get(1));
mylist.remove("Donal");
System.out.println("Updated list of Movies:\n" + mylist);
System.out.println("Movie at index 1:\n" + mylist.get(1));
mylist.add(2, "Goo", "Carton", 3, 4500);
System.out.println("Updated list after adding a movie:\n" + mylist);
