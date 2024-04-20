/*Name- krushna thakkar
CSC-20
Date-June 5 ,2022. 
Lab-NumberMatchingGame
******************************************************************************
Proper naming
5 pts, Full Marks
All the names follows the naming convention

Indentation
program has proper indentation throughout
5 pts, Full Marks
the code is readable and indentation is provide for all the parts

Comments
comments throughout the code including the block comment with your name and a description of the program. comments for each method
5 pts, Full Marks
comments throughout the code is provided``

Program compiles
The program included all the required code and there are no compiler errors in the code
20 pts, Full Marks
program compiles properly and all the required code is included.

program runs and the output matches the provided output
30 pts, Full Marks
Program runs and generates the sample output

Reuirnmnets
Program follows are the provided requirements such as the required methods in each class, the signature for each methods. The classes are implemented properly
30 pts, Full Marks
Program follows all the requirements

Self grade
Self grade with clear explanation is provided
5 pts
Full Marks
Self grade with a complete explanation is provided

my grade for this assignment is 100 since all the requirements ae satisfied
*/
import java.util.*;

public class MatchingGameThakkar
{
   public static void main (String [] args)
   {  
      //Creating Scanner object
      Scanner kb = new Scanner(System.in) ;
            
   //Creating Condition Control String 
      String Stop = "" ;
      while (!Stop.equalsIgnoreCase("q"))
      {
      //Calling method description    
         description() ;
         
         System.out.print("") ;
         System.out.println("Please enter your name.") ;
         String name = kb.nextLine();
         System.out.println("Hello " + name + " let's start the game") ;
         System.out.println("");
         
          //Creating Random Class 
         Random rand  = new Random();
      
         play(rand) ; 
         
         System.out.print("Hit enter to let another person play or enter Q to quit the program\n");        
         
         Stop = kb.nextLine();
      } 
      System.out.println("Thank you for playing the game");
      System.out.println("See you again next time");      
          
   }    
   
   /*
   This method gets a Random object as its parameter and returns a random number.The number returned should be between 1 to 9 
   */
   public static int GetRandNum (Random rand )
   {   
   //Random numbers between 1 and 9 inclusive
     
      int num = rand.nextInt(9) + 1 ; 
      
      //Returning random number    
      return num ;
   }
   
   /*
   It is the important method that get the random number as a parameter and calculates money that 
   */
   public static void play(Random rand)
   {
   //Creating different Scanner class 
      Scanner Console = new Scanner(System.in) ;
      int total = 0 ; 
      int n1 = 0 ; int n2 = 0 ; int n3 = 0 ; 
      String Stop  = "" ;
      while (!Stop.equalsIgnoreCase("q"))
      {
         n1 = GetRandNum (rand) ;
         n2 = GetRandNum (rand) ;
         n3 = GetRandNum (rand) ; 
         
         //Displaying 3 random numbers  
         System.out.println( "You got: " +n1 + " " + n2 + " " + n3 ) ;
         int Match = Match(n1,n2,n3,Console) ;
         
         //Conditional loops for calculating individuals money won
         if (Match == 2 )
         {
            total = total + 100 ;
            System.out.println("You got 2 Matches: You won 100 Dollars");
            System.out.println("") ;
         }
         else if (Match == 3)
         {
            total = total + 300 ;
            System.out.println("You got all 3 Matches: You won 300 Dollars");
            System.out.println("") ;
         }
         else if (Match  == 0)
         {
            total = total + 0 ;
            System.out.println("Sorry no Match");
            System.out.println("") ;
         }
         else 
         {
            System.out.println("Again") ;
         }
         System.out.println("Hit enter to continue or press q/Q to quit");
         Stop = Console.nextLine();
         System.out.println("") ; 
      } 
      System.out.println("Total amount of money you have won is " + total) ;
      System.out.println("");
   }
      
   
   /*
    This method gets three numbers as its parameters. Compares the numbers. if the three numbers are the same returns 3, if the two numbers out of three are the same returns 2, otherwise should return 0. you need couple condition statements to cover all different combinations. 
   */
   public static int Match (int n1, int n2, int n3, Scanner Console )
   {
   //Conditional statements for matches of 3 random numbers
   
   //Case-1
      if(n1 == n2 && n2 == n3)
      {
         System.out.println("(Your three numbers are matched)") ;
         return 3 ;
      }
      //Case-2
      else if (n1 == n2 || n1 == n3 || n3 == n2)
      {
         System.out.println("(Your two numbers are matched) ") ;
         return 2  ;
      }
      //Case-3
      else 
      {
         System.out.println("(Not a Winner)");
         return 0 ;
      }     
   } 
   
    /*
    In this method the description of the game would be displayed
    */
   public static void description () 
   {
      System.out.println("***********************************************************************************"); 
      System.out.println("*************************");  
      System.out.println(" ") ;
      System.out.println(" Welcome to Number Matching Game. \n* I will generate three random numbers for you *\n* If two of the numbers match then you will win 100 dollars.* \n* If you get three matching numbers you will win 300 dollars.* ");
      System.out.println(" ") ;
      System.out.println("**********************************************************************************");
      System.out.println("*************************");
   }
}
