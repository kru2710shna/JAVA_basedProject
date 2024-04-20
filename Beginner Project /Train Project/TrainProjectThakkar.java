/*
Name: krushna thakkar
Subject - Train App

*/

import java.util.* ;
public class TrainProjectThakkar
{
//Instance Varibale 
   private String firstname ;
   private String lastname ;
   private String phone ;
   private String email ;

//Constructor
   public TrainProjectThakkar (String firstname , String lastname , String phone , String email)
   {
      this.firstname = firstname ;
      this.lastname = lastname ;
      this.phone  = phone ;
      this.email = email ;
   }
//Noargument Constructor 
   public TrainProjectThakkar ()
   {
   
   }   
//Getter method   
   public String getFirstname()
   {
      return firstname ;
   }
   public String getLastname()
   {
      return lastname ;
   }
   public String getPhone()
   {
      return phone ;
   }
   public String getEmail()
   {
      return email ;
   }
//Setter Method
   public void setFirstname(String n)
   {
      firstname = n ;
   }   
   public void setLastname(String l)
   {
      lastname  = l ;
   }
   public void setPhone(String p)
   {
      phone  = p ;
   }
   public void setEmail(String e)
   {
      email = e ;
   }
//toString method  
   public String toString ()
   {
      String s = "" ;
      s = s + "\nFirst Name: " + firstname ;
      s = s + "\nLast Name: " + lastname ;
      s = s + "\nEmail ID: " + email ;
      s = s + "\nPhone: " + phone ;
      return s ;
   }
//Equals Method   
   public boolean equals(Object o)
   {
      if (o instanceof TrainProjectThakkar)
      {
         TrainProjectThakkar p = (TrainProjectThakkar) o  ;//Type casting the object
         return  this.lastname.equals(p.lastname) &&  this.firstname.equals(p.firstname); 
      }
      else 
         return false ;
   }   
}
class Passenger extends TrainProjectThakkar
{
//Instance Variable 
   private int seatNumber ;
   private String classType;
   
///Constructor
   public Passenger(String firstname , String lastname , String phone , String email, int seatNumber, String classType)
   {
      super(firstname , lastname ,  phone , email) ;
      this.seatNumber = seatNumber ;
      this.classType = classType ;
   }
//Getter Method
   public int getSeatNumber()
   {
      return seatNumber ;
   }
   public String getclassType ()
   {
      return classType ;
   }
//Setter method 

   public void setSeatNumber(int sn)
   {
      seatNumber = sn ;
   }
   public void setClassType(String c)
   {
      classType = c ;
   }
//toString method
   public String toString ()
   {
      String s = super.toString() ;
      s = s + "\nSeat Number: " + seatNumber ;
      s = s + "\nClass:  " + classType ;
      return s ;
   }   
}

interface list
{
   public boolean add(Object o);
   public Object search(Object o);
   public boolean delete(Object o);
   public void printLast();
} 

class Train implements list
{
   public static int count = 0 ;//Class Constants
   private Passenger [] passenger ;
   private int trainNumber ; 
   
//Constructor
   public Train (int trainNumber)
   {
      count  = 0 ;
      this.trainNumber  = trainNumber;
      passenger = new Passenger[12] ;
   }
//Non-argument Constructor

   public Train ()
   {
      super();
   }
//Getter Method
   public int gettrainNumber()
   {
      return trainNumber ;
   }
   public static int  getCount()
   {
      return count  ;
   }
   public Passenger[] getPassenger()
   {
      return passenger ;
   }
/*This method gets the Object o as its parameter. This method must check the class type of the Object o by using the keyword instanceof. if the type is of type Passenger, type cast it to the Passenger class then add it to the array. */  
   public boolean add(Object o)
   {
      if (o instanceof Passenger)
      {     
         Passenger p = (Passenger) o ;
         passenger[count] = p ;
         count ++ ;
         
         return true ;
      }
      return false  ;
   }
   
/*This method searches the list of the passenger to find the passenger with the particular last name. */  
   public Object search(Object o)
   {
      boolean b = o instanceof String;  
      if(!b)  
      {
         return null;
      } 
      String lastname = (String) o;  
      for(int i = 0; i < passenger.length ; i++)
      { 
         if(passenger[i]!= null && passenger[i].getLastname().equalsIgnoreCase(lastname))  
         { 
            //passenger[i];   //returning the found object }  }return null;//returning null if the object is not found}
            System.out.println("\n**Found the matching last name** ");
            return passenger[i] ;
         }
         else
         {
            System.out.println("\n**Not Found**");  
            return null ;
         }      
      } 
      return null ; 
   }   
   //private void shift(int i) 
   {
    for (int index = i; index < count - 1; index++) {
        passenger[index] = passenger[index + 1];
    }
    passenger[count - 1] = null; // Remove this line
}

/*This method helps to delete the name of the person by finding the last name of the person and deleteing it */  
   public boolean delete(Object o )
   {
      if (o instanceof String )  
      {
         String lastname = (String)o;  
         for(int i = 0; i < count ; i++)
         { 
            if(passenger[i]!= null && passenger[i].getLastname().equalsIgnoreCase(lastname))  
            { 
               passenger[i] = null ;  //returning the found object }  }return null;//returning null if the object is not found}
               shift(i) ;
               count -- ;
               return true ;
            }
         }
         count -- ;             
      } 
      return false ;
   }   
   
   private void shift (int i)
   {
      for(int index = i ; index < count-1 ; index ++)
      {
         passenger[i] = passenger[i+1];
      }
      passenger[count] = null ;
   }
/* This method uses a for loop to display the last name of the passengers in the train.*/   
   public void printLast()
   {    
      for(int i = 0; i < count ; i++)
      { 
         System.out.println( "- " + passenger[i].getLastname() + " ")  ;  //returning the found object }  }return null;//returning null if the object is not found}
         System.out.println();
      }
   }
//Equals Method   
   public boolean equals(Object o)
   {
      if (o instanceof Train )
      {
         Train t = (Train) o  ;//Type casting the object
         return this.trainNumber == t.trainNumber ;
      }
      else 
         return false ;
   } 
//toString Method        
   public String toString()
   {
      String s  = super.toString();
      for (int i = 0 ; i < count  ; i++)
      {
         System.out.println(passenger[i]) ;              
      }
      return s  ;
   }
}
//Driver class
class PersonThakkarDriver
{
   public static void main (String [] args)
   {
   /*
   //Craeting Scanner Object
      Scanner kb = new Scanner(System.in);
   //Creating objects
   //   public Passenger(String firstname , String lastname , String phone , String email, int seatNumber, String Class)
      Train myTrain = new Train(12);
      
      Passenger p1 = new Passenger("Alex", "Mano", "123-456-7893", "Mano@gmail.com", 12, "First Class");      
      Passenger p2 = new Passenger("Mary", "Trump","123-456-4894","mary@sierracollege.edu", 23, "Coach class");      
      Passenger p3 = new Passenger("Al", "Busta","123-456-7890", "AlB@csus.edu", 34,"Business class");      
      Passenger p4 = new Passenger("Jose", "Rodrigues", "123-222-7890", "Jose*gmail.com",22, "First class");     
      Passenger p5 = new Passenger("Joe", "Rodrigues", "123-222-7890", "joe@yahoo.com", 25, "First class");
   
      myTrain.add(p1);      
      myTrain.add(p2);      
      myTrain.add(p3);      
      myTrain.add(p4);      
      myTrain.add(p5);  
    
      System.out.println("\nHere is the list of the passengers in this train");      
      System.out.println(myTrain);
      System.out.println();
      
      System.out.println("Testing the printLast method to display the last names");      
      myTrain.printLast();   
        
      System.out.println("\nTesting the static method getCount");      
      System.out.println("This train has " + myTrain.getCount() + " Passengers\n");      
      
      System.out.println("Testing the search method\n");      
      System.out.print("Enter the last name of the passenger: ");      
      String last = kb.nextLine();
      Passenger p = (Passenger)myTrain.search(last);      
      if(p == null)         
         System.out.println("Passenger not found");      
      else        
         System.out.println("Here is the info for the passenger: " +p); 
                 
      System.out.println("\nTesting the delete method");      
      System.out.print("Enter the last name of the passenger: ");      
      last = kb.nextLine();      
      boolean delete = myTrain.delete(last);     
      if(delete)         
         System.out.println("Passenger  "+ last + " has been removed from the list");      
      else       
         System.out.println("Passenger not found");       
      System.out.println("\nHere is the updated list");      
      System.out.println(myTrain);       
      System.out.println("This tarin has " + Train.getCount() + " passengers");
   }
   */
   //Craeting Scanner Object
      Scanner kb = new Scanner(System.in);
// ... rest of your code ...

// Closing the Scanner object to prevent resource leak
kb.close();

   //Creating objects
   //public Passenger(String firstname , String lastname , String phone , String email, int seatNumber, String Class)
      Train sierraTrain = new Train(12);
      
      Passenger dad = new Passenger("Ram" , "Singh" , "123-123-123" , "ramsingh@gmail.com" , 12 , "First Class");
      Passenger mom = new Passenger("Zill" , "panchal" , "123-334-344" , "zillpanchal@gmail.com" , 15 , "Second class");
      Passenger brother = new Passenger("john" , "March" , "123-876-009" , "johnmarcg@gmail.com" , 17 , "First Class");
      Passenger sister = new Passenger("liz" , "lional" , "123-456-123" , "lizlional@gmail.com" , 19 , "Seconf class");
      Passenger uncle = new Passenger("zylic" , "lional", "123-123-222", "zyliclional@gmail.com" , 17, "First class");
      
   
      sierraTrain.add(dad);      
      sierraTrain.add(mom);      
      sierraTrain.add(brother);      
      sierraTrain.add(sister);      
      sierraTrain.add(uncle);  
    
      System.out.println("\nHere is the list of the passengers in this train");      //displaying the list of passengers
      System.out.println(sierraTrain);
      System.out.println();
      
      System.out.println("***Testing the printLast method to display the last names***\n");      //testing printlast method
      sierraTrain.printLast();                                                                  //calling the method
        
      System.out.println("\n***Testing the static method getCount***\n");                         //testing method to check number of passengers in train      
System.out.println("This train has " + Train.getCount() + " Passengers\n");
                                                               
      System.out.println("***Testing the search method***\n");                                     //testing searching method to search passengers by their last name   
      System.out.print("Enter the last name of the passenger: ");                                  // interacting with user to prompt to enter last name  
      String last = kb.nextLine();
      Passenger p = (Passenger)sierraTrain.search(last);      
      if(p == null)         
         System.out.println("Passenger not found");      
      else        
         System.out.println("Here is the info for the passenger: " +p); 
                 
                 
      System.out.println("\n***Testing the delete method***\n");                             //testing delete method   
      System.out.print("Enter the last name of the passenger: ");       
      last = kb.nextLine();      
      boolean delete = sierraTrain.delete(last);     
      if(delete)         
         System.out.println("\nPassenger  "+ last + " has been removed from the list\n");      
      else       
         System.out.println("\nPassenger not found\n");       
         
         
      System.out.println("\nHere is the updated list\n");                                 //displaying the updated passenger list after deletion
      System.out.println(sierraTrain);          
      System.out.println("\nThis train has " + Train.getCount() + " passengers");         //counting passengers by the end  
   }   
}





