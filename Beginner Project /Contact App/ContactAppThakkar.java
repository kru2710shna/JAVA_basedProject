/*
Krushna Thakkar
Student ID: 302208101
csc-20 A Contact app.
*/

import java . util. * ;
public class ContactAppThakkar
{
}

class Person implements Comparable
{

//Instnace Variable
   private String first ;
   private String last ;
   private String phone ;

//Constructor 

   public Person(String first , String last , String phone )
   {
      this.first = first ;
      this.last = last ;
      this.phone = phone ;
   }
//Getter Method

   public String getFirst()
   {
      return first ;
   }
   public String getLast()
   {
      return last ;
   }
   public String getPhone()
   {
      return phone ;
   }
//Setter Method

   public void setFirst(String newFirst)
   {
      first = newFirst ;
   }  
   public void setLast(String newLast)
   {
      last = newLast ;
   }
   public void setPhone(String newPhone)
   {
      phone  = newPhone ;
   }
//toString Method

   public String toString()
   {
      String s = " " + first ;
      s = s + " " + last ;
      s = s + " " + phone ;
      return s  ;
   }
//Equals Method   

   public boolean equlas (Person other)
   {
      return this.last.equalsIgnoreCase(other.last);
   }
//CompareTo method

   public int compareTo(Object o)
   {
      Person p = (Person)o;
      return this.last.compareTo(p.last) ; // last name is of type String , compareTo from the string class is being used
   }  
}

class Directory 
{
//Instnace Variable
   ArrayList<Person> contactList ;

//Constructor

   public Directory()
   {
      super();
      contactList = new ArrayList<Person> ();
   }
/* This method gets the information for a person, creates a person object and adds it to the proper location in the list to keep the state of the list sorted. */  
   
   public boolean addContact(String name  , String last , String phone)
   {
      Person p = new Person (name , last , phone) ;
      boolean added  = false ;
   /*If the list is empty just add it to the list amd return true*/      
      if (contactList.size() == 0  )
      {
         contactList.add(p) ;
         added = true ;
         return true;
      }
      for (int i = 0 ; i < contactList.size() ; i ++)
      {
         if (p.compareTo(contactList.get(i)) < 0)
         {
            contactList.add(i,p);
            added = true; 
            return true ;
         }
      }
   /*if is not the first element and cannot be added according to the alphabetical order then add it last*/      
      if (!added)
      {
         contactList.add(p);
      }
      return added ;
   }
   
/*Use sequential search to find the contact in the list and delete it. 
The last name of the contact should be passed to this method.  
This method should return true if the contact is found and deleted, returning false otherwise */   
   
   public boolean deleteContact(String last)
   {
      for(int i = 0; i < contactList.size(); i++)
      {
         if(contactList.get(i).getLast().equals(last))
         {
            contactList.remove(i); // delete the song from the list
            return true ;
         }
      }
      return false ;      // the song was not found in the list
   }
/*This method gets the contact’s last name and returns the phone number of the given person. 
 Search the list based on the last name. return type for this method is String. If the contact’s last name is not in the list return null. 
 This is a linear search*/   
   public String searchContact(String last)
  {
    //loop person in the contact list
    for(Person p : contactList)
    {
      //if last name was found,
      if(p.getLast().equals(last))
      {
        //get the person's phone numbers
        return p.getPhone();
      }
    }
    //otherwise, nothing 
    return null;
  }
//toString Method
   public String toString()
   {
      String s  = "" ;
      for (int  i =  0 ; i < contactList.size() ; i++)
      {
         s = s + "\n" + contactList.get(i).toString() ;
      }
      return s ;
   }   
}
class Driver
{
   public static void main (String [] args)
   {
      System.out.println("**Your Contact Information**");
      
   //Creating an object of type Directory:
      Directory myContact = new Directory();
      
   //Adding contacts       
      myContact.addContact("Ana" , "Baily" , " 568-345-9999" );
      myContact.addContact("Mary", "Busta", "111-222-3333");
      myContact.addContact("Smith", "Richard" , "123-123-4567");
      myContact.addContact("Alex", "Rodrigues" ,  "987-567-3333" );
      myContact.addContact("Jose", "Hernandez", "444-566-0000" );
      myContact.addContact("Sarah", "Schulz" ,"555-666-7777" );
   //Displaying contents of the contact       
      System.out.println("\nDisplaying the content of the Contact added");
      System.out.println(myContact);
   //Testing removing method     
      System.out.println("\nRemoving Baily from the contact list\n") ;
      myContact.deleteContact("Baily");
      System.out.println(myContact);
   //Testing searching method      
      System.out.println("\nLet's try for searching the person from the contact");
      System.out.println(myContact.searchContact("Richard"));
   //adding one more contact     
      System.out.println("\nThere is one more person to add as contact");
      myContact.addContact("Mary", "Brown","999-100-1244");
   //displaying contact      
      System.out.println(myContact); 
   //Searching for dobeck who is not in the contact
      System.out.println("\nSearching for dobeck who is not in the contact\n");
      myContact.searchContact("Dobeck");
   
          
   }
}