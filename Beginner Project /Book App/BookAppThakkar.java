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
import java.util.*;
public class BookAppThakkar
{

}
class Book implements Comparable
{
//Instance Variables 

   private String title;
   private String author;
   private double price;
   private String ISBN;
      
   //Constructor
   public Book(String title, String author, double price, String ISBN)
   {
      this.title = title;
      this.author = author;
      this.ISBN = ISBN;
      this.price = price;
   }
   //Getter Method
   
   public String getTitle()
   {
      return title;
   }
   public String getAuthor()
   {
      return author;
   }
   public String getISBN()
   {
      return ISBN;
   }
   public double getPrice()
   {
      return price;
   }
   //Setter Method
   
   public void setTitle(String t)
   {
      title = t;
   }
   public void setPrice(double p)
   {
      price = p;
   }
   public void setIsbn(String sb)
   {
      ISBN = sb;
   }
   //ToString Method
   
   public String toString()
   {
      return title+ ", " + author+", "+price+", "+ISBN ;
   }
   //Equals Method
   
   public boolean equals(Book other)
   {
      return this.ISBN.equals(other.ISBN);
   }   
   //CompareTO Methods
   
   /* compares the book objects based on the title of the book
   This compareTo method is called in the selection sort*/
   // #1 
   public int compareTo(Object o)
   {
    
      if(o instanceof Book) //type casting to Book
      {
      
         Book b1 = (Book)o;
      
         return (this.title).compareTo(b1.title);
      }
    
      return -1; 
   }     
   /*compares objects of type book based on their author
   This comapreTo method is called in the insertion sort
   the paramter for this method is of type Book and the author of the book b 
   needed to be compared with the author of the Book object called this*/
   // #2   
   public int Compare(Book b) // Type: Insertion Sort   
   {      
      return this.author.compareTo(b.author) ;
   } 
   
   /*compares the Book object based on the author, if the author is the same then 
compares the 

   Book  objects based on the price
   this compareTo method is used in the bubbleSort method
   conditional statemnet needs to be used in this method
   if the authors of the book b is the same as the author of the Book object this
        return this.price - b.price
   else
      return   return author.compareTo(b.author)
    */
   // #3
   public double compare(Book b)
   {  
      if (this.author.equalsIgnoreCase(b.author) )
      {
         return (this.price) - b.price ;
      }
      else
      {
         return author.compareTo(b.author);
      }      
   }
}
//Class BookStore
class BookStore
{
   private ArrayList<Book> books;
   private ArrayList<Book> ss = new ArrayList<Book>();
   public BookStore()
   {
      books = new ArrayList<Book>();
   }
   
   public void add(String title, String author, double price, String isbn)
   {
      books.add(new Book(title, author, price, isbn));
   }
   
   public String toString()
   {
      String s = "";
      for(int i = 0; i < books.size(); i++)
      {
         s= s+ books.get(i).toString()+"\n" ;
      }
      return s;
   }
   
   public boolean delete(String isbn)
   {
      for(int i = 0; i < books.size(); i++)
      {
         if (books.get(i).getISBN().equals(isbn))
         {
            books.remove(i);
            return true;
         }
      }
      return false;
   }
   /*sorts the books based on the title of the book
   call the compareTo #1 in this method
   remember the name of the ArrayList is books and it is not list
   refer to the given selectionSort code in the modules*/
   public void selectionSort()   
   {  
      
      for(int i = 0; i <books.size()-1; i++)
      {
         int min = i;
         
         for(int j = i+1; j < books.size(); j++)
            if(books.get(j).compareTo(books.get(min)) < 0)
               min = j;
                         
            
         
      
         ss.add(books.get(i));
         books.set(i, books.get(min));
         books.set(min , ss.get(0));
           
         ss.clear();
          
      }
   }         
   
   /*sorts the book objects based on the author of the book
   remember the name of the ArrayList is books and it is not list
   call compare method  #2 in this method
   refer to the provided insertionSort code in the modules*/
   public void insertionSort()
   {
        
      for(int i = 0; i < books.size()-1; i++)
      {
         
         int j = i+1;
         String n = books.get(j).getAuthor();
         ss.add(books.get(j));
      
      
         while(j >0 && n.compareTo(books.get(j-1).getAuthor()) <0) 
         {
            
            books.set(j, books.get(j-1)); 
            j--;
         }
        
         books.set(j, ss.get(0)); 
          
         ss.clear();
      
      }
      
   }
   /*sorts the book objcts based on the author, 
   if the author is the same then sorts it based on the price
   call comapre #3 method in this method
   refer to the provided bubbleSort code for in the modules
   */
   public void bubbleSort()
   {
   
      for(int i = 0; i <books.size() ; i++)
      {
         for(int j = 0 ; j <books.size() -1 - i; j++)
         {
            if(books.get(j+1).getAuthor().equalsIgnoreCase(books.get(j).getAuthor()))
            {
               if((books.get(j+1).getPrice())  < (books.get(j).getPrice()))               
               {
               
                  ss.add(books.get(j));
               
                  books.set(j,  books.get(j+1));
               
               
                  books.set(j+1, ss.get(0));
               
                  ss.clear();
               }
            }
            else
            {               
               
               ss.add(books.get(j));
               
               books.set(j,  books.get(j+1));
               
               
               books.set(j+1, ss.get(0));
               
               ss.clear();
            }
               
         }
      }
   
   }
      /*the following search method searches the book based on the title of the book
   therfore the first line of code should be a call to the selection sort to sort 
the books
   based on the title of the books
   refer to the binarySearch code provided in the modules*/
   public Book binarySearch(String title)
   {
   
      selectionSort();
      
      int first = 0;
      int last = books.size() - 1;
      int mid = (first + last)/2;
      
      while (first <= last)
      {
         if((title).equalsIgnoreCase(books.get(mid).getTitle())) //found the element
            return books.get(mid);
        
         else if(title.compareTo(books.get(mid).getTitle()) > 0)  //the element is on the right side
            first = mid + 1;
         
         else         //the element is on the left side
            last = mid -1;
       
         mid =(first + last)/2;            
      }
      
      return null;
   }
   
}
   
class YourDriver {
    public static void main(String[] args) {
        Scanner kb = new Scanner(System.in);
        BookStore myStore = new BookStore();
        // ... (rest of the code)

        boolean b = true;
        while (b) {
            System.out.println("Enter 1 to sort based on the title");
            System.out.println("Enter 2 to sort based on the author");
            System.out.println("Enter 3 to sort based on the author and the price");
            System.out.println("Enter any other number to exit");
            System.out.print("Enter your choice: ");
            int option = kb.nextInt();
            System.out.println("\n*************");

            if (option == 1) {
                System.out.println("Sorted based on the title\n");
                myStore.selectionSort();
            } else if (option == 2) {
                System.out.println("Sorted based on the author\n");
                myStore.insertionSort();
            } else if (option == 3) {
                System.out.println("Sorted based on the author and price\n");
                myStore.bubbleSort();
            } else {
                System.out.println("Exiting the program. Thank you!");
                break; // Exit the loop
            }

            System.out.println(myStore);
            System.out.println("\n     **************     ");
            System.out.println("Enter the title of the book to search for it: ");
            kb.nextLine();
            String t = kb.nextLine();

            Book book = myStore.binarySearch(t);
            if (book != null)
                System.out.println(book);
            else
                System.out.println("Book not found");

            System.out.println("\n");
        }

        kb.close(); // Close the Scanner object
    }
}
