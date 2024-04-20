/*
Krushna Thakkar  
CSC-20 Assignment #6- Grocery App
Student ID: 302763656


Proper naming
5 pts
All the names follows the naming convention

Indentation
program has proper indentation throughout
5 pts
the code is readable and indentation is provide for all the parts

comments
comments throughout the code including the block comment with your name and a description of the program. comments for each method
5 pts
comments throughout the code is provided

Program compiles
The program included all the required code and there are no compiler errors in the code
25 pts
program compiles properly and all the required code is included

program runs and the output matches the provided output
30 pts
Program runs and generates the sample output

Reuirnmnets
Program follows are the provided requirements such as the required methods in each class, the signature for each methods. The classes are implemented properly
25 pts
Program follows all the requirements

Self grade
Student has provided a self grade based on the rubric
5 pts
self grade is provided with a clear explanation
*/

public class GroceryThakkar
{

}

class Item implements Comparable<Object>
{
//instance variables are declared
   private String food;
   private double price;
   private String expDate;
  
   public Item(String food, double price, String expDate)
   //initializing the instance variables
   {
      this.food = food;
      this.price = price;
      this.expDate = expDate;
   }
  
   public String getFood()
   //this getter method retruns food.
   {
      return food;
   }
  
   public double getPrice()
   //this getter method returns price.
   {
      return price;
   }
  
   public String getExpDate()
   //this getter method returns the expDate.
   {
      return expDate;
   }
  
   public void setFood(String newFood)
   //this setter method sets food equals to newFood.
   {
      food = newFood;
   }
  
   public void setPrice(double newPrice)
   //this setter method sets Price equals to newPrice
   {
      price = newPrice;
   }
  
   public void setExpDate(String newExpDate)
   //this setter method sets expDate equals to newExpDate.
   {
      expDate = newExpDate;
   }
  
   public boolean equals(Item other)
   //this method checks if the items are same by going throught their price and the food
   {
      return this.food.equals(other.food) && this.price == other.price;
   }
  
   public int compareTo(Object o)
   //this method compares the two items depending on the instance variable food delared and initialized in class Item
   {
      Item a = (Item)o;
      return (this.food).compareTo(a.food);
   }
  
   public String toString()
   //this toString method helps to put the information in the form of a string.
   {
      String s = "\nFood: "+food;
      s = s + "\nPrice: "+price;
      s = s + "\nExpire Date: "+expDate;
      return s;
   }
}

class ListNode
//delaring variables of the class ListNode
{
   private Item i ;
   private ListNode next ;
  
   public ListNode(Item i)
   
   //this method initialized the instance variables
   {
      this.i = i;
   }
  
   public ListNode()
   {
   //this is an empty ListNode
   }
  
   public ListNode(Item i, ListNode next)
   //this method initializes the variables a and after
   {
      i = i;
      this.next = next;
   }
  
  //Getter Method
   public Item getItem()
   // this getter method returns the variable a
   {
      return i;
   }
  
   public ListNode getNext()
   //this getter method returns variable after.
   {
      return next;
   }
  
  //Setter Method
  
   public void setNext(ListNode b)
   //this setter method sets the variable after equals to b
   {
      this.next = b;
   }
}

interface List
//this interface list has all of the items in the list
{
   public void add(String food, double price, String expDate);
   public void add(int index, String food, double price, String expDate);
   public int indexOf(String food);
   public void remove(String food);
   public int size();
   public String toString();
   public Item get(int index);
   public Item mostExpensive();
}

class GroceryList implements List
//this class declares the variables nd helps to implement them to the interface list
{
   private ListNode head;
   public static int size =0; 
  
   public GroceryList()
   //this constructor sets top equals to null
   {
      head = null;
   }
  
   public GroceryList(Item i)
   //this constructor sets top equals to newListNode
   {
      head = new ListNode(i);
   }
  
   public void add(String food, double price, String expDate)
   //this add method adds the food item in the list
   {
      Item i = new Item(food, price, expDate);
      ListNode thing = head;
      if(head == null)
      //this if statement checks if top equals to null then length is increased with ++ and top equal to new list node(a)
      {
         head = new ListNode(i);
         size++;
         return ;
      }
      ListNode next = new ListNode(i);
      while(thing.getNext() != null)
      {
         thing = thing.getNext();
      }
      
      thing.setNext(next);
      size++;
   }
  
   public void add(int index, String food, double price, String expDate)
   //this method adds the item to the index list.
   {
      Item i = new Item(food, price, expDate);
      if(index > size)
      {
         return;
      }
      if(index == 0)
      {
         ListNode b = new ListNode(i);
         b.setNext(head);
         head = b;
         size++;
         return;
      }
      ListNode thing = head;
      int j = 0;
      while(thing.getNext() != null && j < index-1)
      {
         thing = thing.getNext();
         j++;
      }
      ListNode next = new ListNode(i);
      next.setNext(thing.getNext());
      thing.setNext(next);
      size++;
   }
   public int indexOf(String food)
   {
      if(head == null)
         return -1;
     
      if(food.equals(head.getItem().getFood()))
      {
         return 0;
      }
      ListNode thing = head;
      int index = 0;
      while(thing!= null && index <= size)
      {
         if(thing.getItem().getFood().equals(food))
         {
            return index;
         }
         thing = thing.getNext();
         index++;
      }
      return -1;
   } 
  
   public void remove(String food) 
   {
      if(head == null)
         return;
      
      if(head.getItem().getFood().equals(food))
         head = head.getNext();
      ListNode before = head;
      ListNode thing = head ;      
   	
      while(thing != null && !(thing.getItem().getFood().equals(food)))
      {
         before = thing;
         thing = thing.getNext();
      } 
      if (thing!= null && thing.getNext() == null &&(thing.getItem().getFood().equals(food)))       
      {
         before.setNext(null);
         size--;
         System.out.println("The last node has been removed");
      } 
      else if (thing == null)
         System.out.println("food is not found in the list");
      else
      {
         before.setNext(thing.getNext());
         size--;
        // System.out.println("A node in the middle has been removed");
      } 
   }
   
   public int size()
   //this mehtod will return the size of the list
   {
      return size + 1;
   }
   
   public String toString()
   {
      if(head == null)
         return "";
      ListNode thing = head;
      String s = "";
      while(thing != null)
      {
         s = s + thing.getItem().toString() + "\n";
         thing = thing.getNext();
      }
      return s;
   }
   
   public Item get(int pos) 
   //this method return the item at a given index.
   {
      if(head == null)
         return null;
      if (pos > size)
         return null;    
     
      ListNode thing = head;
      int index = 0;
      while(thing!= null && pos != index)
      {
         index++; 
         thing = thing.getNext();    
      } 
      if(thing == null)   
         return null;
      return thing.getItem(); 
   }

   public Item mostExpensive() 
    //this method goes trought eh list and return the most expensive item in the list.
   {
      if(head == null)
         return null;
      ListNode thing = head;
      Item maxprice = thing.getItem();
      while(thing != null)
      {
         if(thing.getItem().getPrice() > maxprice.getPrice())
            maxprice = thing.getItem();
         thing = thing.getNext();
      }
      return maxprice;
   }
}

class Driver
{
   public static void main(String []args)//main method 
   {
     
      GroceryList list = new GroceryList();
      list.add("Bread", 5.99, "3/20/2022");
      list.add("Milk", 3.99, "2/1/2002");
      list.add("Chips", 2.99, "12/30/2025");
      list.add("Rice", 35.50, "8/15/2030");
      System.out.println("Here is the list of food items");
      System.out.println(list);
      System.out.println("Here is the most expensive item on the list");
      System.out.println(list.mostExpensive());
      System.out.println("Removing Milk from the list and adding a new expensive item on the list in the 2nd node");
      list.remove("Milk");
      list.add(1, "Truffle", 800, "4/20/2050");
      System.out.println(list);
      System.out.println("Testing the mostExpensive method to see what is the most expensive item now");
      System.out.println(list.mostExpensive());
      System.out.println("Testing the get method to get the item at the 3rd node");
      System.out.println(list.get(2));
   }

}


class MyDriver
{
   public static void main(String[] args)
   {
     //cretaes an object of grocery list and add the listed items to the list
      GroceryList list = new GroceryList();
      list.add("Juice", 4.99, "6/13/2022");
      list.add("Oreo", 6.99, "5/19/2023");
      list.add("Donuts", 3.99, "8/26/2022");
      list.add("Chocolate", 8.99, "7/15/2025");
   
      System.out.println("Here is the list of food items");//display list of food items
      System.out.println(list);//The list has been called here.
      System.out.println();
      System.out.println("Here is the most expensive item on the list");
      System.out.println(list.mostExpensive());//mostExpensive is called
      System.out.println();
      System.out.println("Removing Milk from the list and adding a new expensive item on the list in the 2nd node");
      list.remove("Oreo");//using the remove method to remove Donumts from list
      list.add(1, "Coffee", 15.99, "5/16/2022");//uses the add method to add coffee
      System.out.println(list);//printing list of food items
      System.out.println("Testing the mostExpensive method to see what is the most expensive item now");
      System.out.println(list.mostExpensive());//mostExpensive is called here
      System.out.println();
      System.out.println("Testing the get method to get the item at the 3rd node");
      System.out.println(list.get(2));//get is called from the list
   }
}
