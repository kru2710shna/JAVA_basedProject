# Grocery App

## Description
This Java program, named `GroceryThakkar`, implements a simple grocery list management system. It allows users to add, remove, and manipulate grocery items. The program consists of several classes that work together to provide the functionality for managing a grocery list.

### Classes and Interfaces

1. `Item`: This class represents a grocery item with attributes like food name, price, and expiration date. It implements the `Comparable` interface to enable comparisons between items.
2. `ListNode`: A class representing a node in a linked list. It contains an `Item` and a reference to the next node.
3. `List` (Interface): An interface specifying the methods required for a grocery list, such as adding, removing, and getting items.
4. `GroceryList`: A class that implements the `List` interface. It maintains a linked list of grocery items and provides methods for list manipulation.
5. `Driver`: The main driver class for testing the `GroceryList` class. It demonstrates the functionality of the grocery list management system by performing various operations.
6. `MyDriver`: Another driver class showcasing the `GroceryList` capabilities through different operations.

## How to Use

To use the Grocery App:

1. Compile the Java files using a Java compiler (e.g., `javac GroceryThakkar.java`).
2. Run the `Driver` or `MyDriver` class to see the program in action. These classes demonstrate adding items, finding the most expensive item, removing items, and more.
3. Explore the code comments to understand the logic and functionality of each class and method.

## Example

```java
public class Driver {
   public static void main(String[] args) {
      GroceryList list = new GroceryList();
      list.add("Bread", 5.99, "3/20/2022");
      list.add("Milk", 3.99, "2/1/2002");
      // ... other operations ...
      System.out.println("Here is the list of food items");
      System.out.println(list);
      System.out.println("Here is the most expensive item on the list");
      System.out.println(list.mostExpensive());
      // ... more operations ...
   }
}
