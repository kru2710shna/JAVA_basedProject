/*
Name: krushna thakkar
csc=20 RecursiveHomeworkThakkar

Proper naming
5 pts, Full Marks
All the names follows the naming convention

Indentation
program has proper indentation throughout
5 pts, Full Marks
the code is readable and indentation is provide for all the parts

comments
comments throughout the code including the block comment with your name and a description of the program. comments for each method
5 pts, Full Marks
comments throughout the code is provided

Compiles
The program included all the required code and there are no compiler errors in the code
25 pts, Full Marks
program compiles properly and all the required code is included

program runs and the output matches the provided output
30 pts, Full Marks
Program runs and generates the sample output

Reuirnmnets
Program follows are the provided requirements such as the required methods in each class, the signature for each methods. The classes are implemented properly
30 pts, Full Marks
Program follows all the requirements

Self grade
5 pts, Full Marks
Self grade with a complete explanation is provided
*/

import java.util.*;
public class RecursiveHomeworkThakkar
{
   public static void main(String[] args)
   {
      System.out.println("Testing the Palindrom Method");
      
      int[] a = {5,6,4,5,8,5,4,6,5,12};      
      System.out.print(Arrays.toString(a) + " is Palindrome?  ");
      System.out.println(palindrome(a,0));
      
      int[] b = {1,2,3,4,3,2,1};
      System.out.print(Arrays.toString(b) + " is Palindrome?  ");
      System.out.println(palindrome(b,0));
   
      
      System.out.println("\n Testing sum of the digits");      
      int num = 12345; 
      System.out.println("The sum of the digits in " + num + " is "+ sum(12345));
   
      
      System.out.println("\n Testing longest string in an array of string");     
      String[] s = {"Hello","Bye","Said","song","Building"};
      System.out.println("The longest string is the array " + Arrays.toString(s) + " is " + longest(s,1,s[0]));
   
      
      System.out.println("\nTesing the equals method on the strings");
      String s1 = "hello";
      String s2 = "helloo";
      System.out.println("are the strings " + s1 + " and " + s2 +" equal? " +equals(s1,s2,0));     
      s1 = "tomorrow";
      s2 = "tomorrow";
      System.out.println("are the strings " + s1 + " and " + s2 +" equal? " + equals(s1,s2,0));
   
      
      System.out.println("\n Testing the sum of the integers in a link list");
      LinkedList<Integer> list = new LinkedList<Integer>();
      list.add(5);
      list.add(7);
      list.add(8);
      list.add(12);
      System.out.println("The sum of the numbers in the linklist " + list + " is " + listSum(list,0));
   }
// write a method that accepts an array of String and returns 
//true if the array is palindrom and returns false otherwise
   public static boolean palindrome(int[] a, int first )
   /*{
     if (first == a.length/2)
     {
     return true ;
     }
     return (a[first] == (a.length -1 - first)) && palindrome(a , first + 1); 
   }
   */
   {
      if (first >= a.length - (first + 1))
      {
         return true ;
      }
      if (a[first] == a[a.length - (first+1) ] )
      {
         return palindrome(a , first + 1); 
      }
      else 
         return false ;
   }
   
   
/*write a recursive method that accepts an integer as its parameter and returns the
sum of the digits in the given number*/

//for example sumDigit(1234) should return 1 + 2 + 3 + 4 = 10
   public static int sum(int num)
   {
      //Output = 1 + 2 + 3 + 4 = 10
      
      if (num % 10 == 0)
         return 0 ;
      return (num % 10 + sum(num -1));
   }
/*write a method that accepts an array of String and returns the string with the 
longest length*/
   public static String longest(String[] s, int index, String longest)
   {
   
      if (index == s.length)
         return longest;
      
      if (s[index].length () > longest.length ())
      {
         return longest (s, index + 1, s[index]);
      } 
      else  
         return longest (s, index + 1, longest);
      
   }
/*write a recursive method that accepts two string paramters, returns tru if the 
strings are the same, false otherwise*/
   public static boolean equals(String s1, String s2, int index)
   {   
      if(s1.length() != s2.length())
      {
         return false;
      }
         
      if(index == s1.length())
      {
         return true; 
      }
           
      if(s1.charAt(index) != s2.charAt(index))
      {
         return false;
      }
      else
         return true && equals(s1,s2,index+1);          
   }
/*write a method that accepts a linklist of integers and finds the sum of all the 
numbers in the list*/
   public static int listSum(LinkedList<Integer> list, int  index)
   {
      if(list.size()-1 == index) 
      {
         return list.get(index);
      }
      return list.get(index) + listSum(list, index+1);
   }
}
