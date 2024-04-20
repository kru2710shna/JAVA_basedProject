/*
Krushna Thakkar
Student ID: 302208101
csc-20 Assignment- Movie app
Proper naming
5 pts
Full Marks, 
All the names follows the naming convention

 Indentation program has proper indentation throughout
5 pts
Full Marks
the code is readable and indentation is provide for all the parts

Comments 
comments throughout the code including the block comment with your name and a description of the program. comments for each method
5 pts, 
Full Marks ,
comments throughout the code is provided

Program compiles, The program included all the required code and there are no compiler errors in the code
25 pts
Full Marks,
program compiles properly and all the required code is included

program runs and the output matches the provided output
30 pts
Full Marks,
Program runs and generates the sample output

Reuirnmnets,Program follows are the provided requirements such as the required methods in each class, the signature for each methods. The classes are implemented properly
25 pts
Full Marks,
Program follows all the requirements

Self grade
5 pts
Full Marks
Self grade with a complete explanation is provided
*/

public class MovieAppThakkar
{

}
class Movie implements Comparable 
{

//Instance Variables 
   private String name ;
   private String genre ;
   private int rating ;
   private int people;

// Constructor
   public Movie (String name , String genre , int rating , int people)
   {
      this.name = name ;
      this.genre = genre ;
      this.rating = rating ;
      this.people  = people ;
   }
   
//Getter Method
   public String getName()
   {
      return name ;
   }   
   public String getGenre()
   {
      return genre ;
   }   

   public int getRating()
   {
      return rating ;
   }  
   public int getPeople ()
   {
      return people ;
   }
   
//Setter Method
   public void setName(String names)
   {
      name = names ;
   }
   public void setGenre(String genres)
   {
      genre  = genres ;
   }
   public void setRating(int ratings)
   {
      rating  = ratings ;
   }
   public void setPeople(int peoples)
   {
      people = peoples ;
   }
   
//ComaperTo Method
   public int compareTo(Object o)
   {
      Movie m =(Movie)o;//TypeCasting
      return (this.name).compareTo(m.name); //Movie name is of type String , compareTo from the string class is being used
   }

//Tostring method  
   public String toString()
   {
      String rate = "";
    //create a rating string
      for(int i = 0; i < rating ; i++)
      {
         rate = rate + "*";
      }
      return ""+ name + " , " + genre + " , " + rate + " , " + people + "" + "\n";
   }
   
}
//ListNode Class

class ListNode
{
//Instance Variables 
   private Movie movie;
   private ListNode next ;

//Non-Argument Constructor
   public ListNode()
   {   
   }
   
//Constructor   
   public ListNode(Object o)
   {
      if ( o instanceof Movie)
      {
         Movie m = (Movie)o ;
         this.movie = m ;   
      }
   }
   
//Constructor   
   public ListNode(Object o , ListNode next )
   {
      if (o instanceof Movie)
      {
         this.movie = (Movie)o ;
         this.next = next ;
      }
   }
   
//Setter Method   
   public void setNext(ListNode n)
   {
      next = n ;
   }
   
//Getter Method
   public ListNode getNext()
   {
      return next ;
   }
   public Movie getMovie()
   {
      return movie ; 
   }
}
/*Creating an interface */
interface List
{
   public void add(String name, String genre, int star, int people);  

   public void add(int index, String name, String genre, int star, int people); 

   public int indexOf(String name); 
//returns the index of the movie in the list

   public void remove(String name); 
//removes the movie from the list

   public int size(); 
//returns the size of the list

   public String toString(); 
//returns a string representing of all the movies in the list

   public Movie get(int position);
 //return the movie at the given position
}

class MovieList implements List
{
//Instnace Variable
   private ListNode front ;
   public static int size = 0 ;
   
//Non Argument Constructor
   public MovieList()
   {
      front  = null ;
   }    
   
//Constructor   
   public MovieList (Movie m)
   {
      front  = new ListNode(m) ;
   }   

//ADD Method  
 
   public void add(String name, String genre, int rating, int people) // add the movie at end of the list
   {
   
   //Create a Movie Object
      Movie m = new Movie (name, genre, rating , people); 
        
      //Make a copy of the front node 
      ListNode curr = front ;
      
      //Check to see the list is empty
      if(curr == null)
      {
         front  = new ListNode(m) ;
         size ++ ;
         return ;
      }
      
      //Craete a node so that it can be added at end of the list 
      ListNode  n = new ListNode(m);
      while(curr.getNext() != null)
      {
         curr   = curr.getNext() ;
      }
      curr.setNext(n);
      size++ ;
   }
 //Add method for particular index
   public void add(int index, String name, String genre, int rating , int people)
   {
    //Create a Movie Object
      Movie m = new Movie(name,genre , rating , people) ;
      ListNode  n = new ListNode(m) ;
    
    //Check whather its at start or not which means at index 0
      if (index == 0)
      {
         n.setNext(front) ;
         front  = n ;
         size++ ;
      }
    
    //Make a copy of front node
      ListNode curr = front ;
      int i = 0 ; 
      while (curr.getNext() != null && i < index )
      {
         curr = curr.getNext();
         i++ ;
      }    
      n.setNext(curr.getNext());
      curr.setNext(n);
      size++ ;
   }
   /*This method helps us to get the index of the movie and at the end displayes the movie */
   public int indexOf(String name) //returns the index of the movie in the list
   {
      
      if (front  == null)
      {
         return -1 ;
      }
   //Traverse through whoel List and find movie      
      if (name.equalsIgnoreCase(front.getMovie().getName()))
      {
         return 0 ;
      }
         
         //Create a copy of front node 
      ListNode curr = front ;
      int index = 0 ;
      while (curr  != null && index <= size)
      {
         if(curr.getMovie().getName().equalsIgnoreCase(name))
         { 
            return index ;
         }
         curr = curr.getNext();
         index ++ ;
      } 
      return -1 ;
   }
  //Remove Method 
   public void remove(String name) //removes the movie from the list
   {
   
      if (front ==null)
      {
         return ;
      }
   //remove the first node
      if (front.getMovie().getName().equals(name))
      {
         front = front.getNext() ;
      
      }
      ListNode pre = front ;
      ListNode curr = front ;
      while (curr != null && !(curr.getMovie().getName().equals(name)))
      {
         pre = curr ;
         curr = curr.getNext();
      }
      
      if (curr != null && curr.getNext() == null && (curr.getMovie().getName().equals(name)))
      {
         pre.setNext(null);      
         size--;
         System.out.println("The last node is removed");
      }
      else if (curr == null)
      {
         System.out.println(" ");
      }
      else 
      {
         pre.setNext(curr.getNext());
         size--;
         System.out.println("Node in the middle is removed");
      }
   }
   /*This method returns size of the list of movies added.*/ 
   
   public int size() //returns the size of the list
   {
      return  size+1 ;
   }
   //ToString Method
   public String toString () //returns a string representing of all the movies in the list
   {     
      if(front == null)  //checking to see if the list is empty
         return "";
        
      ListNode curr = front; // making a copy of the front node
      String s = "   {\t";
   
     //traverse through the list all the way to the end
      while(curr != null)
      {
         s = s + curr.getMovie().toString() + " ";
         curr = curr.getNext();
      }   
      return s + "\t}";
   }
   /*This method accepts the position to which the move is to be find and returns movie at that particular location */
   public Movie get(int position) //return the movie at the given position
   {
   
      if(front == null)
         return null;
         
     //if the position is greater than the size
      if (position > size)
         return null;    
     
    //make a copy of the front of the list
    
      ListNode curr = front;
      int index = 0;
      while(curr!= null && position != index)
      {
         index++; 
         curr = curr.getNext();    
      } 
     
     //the song was not found
      if(curr == null)   
         return null;
         
    // the song was found
      return curr.getMovie(); 
   }
   
   /*This method returns the movie with most of the rating */   
   public Movie mostWatched() 
   {
    //mostWatched method to get the most viewed movie
      int mostViews = 0; //integer to log the most views
      ListNode curr = front;
      Movie mostWatched = null; //empty movie to log the most viewed movie
           
      while (curr != null)
      { //while there are still nodes in the list run this loop
      
         if(curr.getMovie().getPeople() > mostViews) 
         { 
               //if the people  of the current position is greater than most views
            mostViews = curr.getMovie().getPeople(); //sets the mostViews to the views of the curr movie
            mostWatched = curr.getMovie(); //changes the mostWatched movie to the movie at the curr position
         }
         curr = curr.getNext(); //recieves the next node in the list
      }
      return mostWatched; 
   }
 /*This method gets the movie's star as rating and as per the rating it returns the name of movie at that rating */  
   public String getMovie(int star)
   {
      if(front == null)
         return null;
         
     //if the position is greater than the size
      if (star > size)
         return null;    
     
    //make a copy of the front of the list
    
      ListNode curr = front;
      int index = 0;
      while(curr!= null && star != index)
      {
         index++; 
         curr = curr.getNext();    
      } 
     
     //the song was not found
      if(curr == null)   
         return null;
         
    // the song was found
      return curr.getMovie().toString() ; 
   
   }
}
/*   
class Driver
{
   public static void main (String []args)
   {
      MovieList list = new MovieList();
    
      list.add("Sunny Day", "Action",5, 20000);
      list.add("Airplane", "Comedy", 3, 1200);
      list.add("Doctor Zhivago","comedy", 4,23000);
      list.add("The Deer Hunter", "Family", 3, 2345);
      
      System.out.println("\nHere is the list of the movies\n");
      System.out.println(list);
      
      System.out.println("\nhere is the the movie that was most watched");
      System.out.println(list.mostWatched());
      System.out.println("\nHere is the list of 5 stars ratings");
      //System.out.println(list.getMovie(5));
      System.out.println("\nRemoving Sunny Day");
      list.remove("Sunny Day");
      System.out.println(list);
      System.out.println("\nDisplaying the second movie in the list");
      System.out.println(list.get(1));
      System.out.println("\nAdding a movie at position 2");
      list.add(2, "Up", "Carton",3,4500);
      System.out.println(list);
      int i = list.indexOf("Up"); 
      System.out.println("\nThe movie up is in the position " + i); 
   }
}
*/
class Driver
{
   public static void main (String []args)
   {
      MovieList mylist = new MovieList();
      //Adding movies 
      
      mylist.add("Donal" , "Cartoon" , 3 , 1000);
      mylist.add("Terminator" , "Action" , 5 , 10000);
      mylist.add("Fairy" , "Children" , 3 , 5000);
      mylist.add("Black Hole" , "Science" , 4 , 80000);
      mylist.add("Vampire Dairies" , "Dark" , 5 , 90000);
   
   //Displaying all the movies 
      System.out.println("\nHere is the list of the movies\n");
      System.out.println(mylist);
   
   //display the most watched movie
      System.out.println("\nHere is the the movie that was most watched");
      System.out.println(mylist.mostWatched());
   
   //display the movie with the highest rating
      System.out.println("\nDisplaying the movie with the Highest Rating");
      System.out.println(mylist.getMovie(5));
   
   //display the index of one of the movies
      System.out.println("\nDisplaying the First Movie in the list");
      System.out.println(mylist.get(1));
   
   //remove one of the movies from the list
      System.out.println("\nRemoving Movie Name Donal from the list");
      mylist.remove("Donal");
   
   //display the list
      System.out.println("Displaying the updated list of Movies\n");
      System.out.println(mylist);
   
   //display the movie at the index 1
      System.out.println("\nDisplay the movie at the index 1");
      System.out.println(mylist.get(1));
   //Adding movie at particular Position 
      System.out.println("\nAdding a movie at position 2");
      mylist.add(2, "Goo", "Carton",3,4500);
      
      System.out.println("\nDisplaying the updated list\n");
      System.out.println(mylist);
      int i = mylist.indexOf("Goo"); 
      
      System.out.println("\nDisplaying the index of the new movie.");
      System.out.println("The movie up is in the position: " + i); 
   
   }
}
 




 
 

