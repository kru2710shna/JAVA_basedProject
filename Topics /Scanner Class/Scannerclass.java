import java .util.*;
public class Scannerclass 
{
   static Scanner kb = new Scanner(System.in) ; 

   public static void main (String [] args)
   {
      double weight  = 0 ; 
      System.out.print("Enter weight ");
      weight  = kb.nextDouble();
      System.out.print("weight is " + weight );
      
      double height  = 0 ; 
      System.out.print("Enter height ");
      height  = kb.nextDouble();
      System.out.print("\nheight is " + height );
      
      double bmi = 703*weight/(Math.pow(height , 2 ));
      System.out.print("\nbmi is " + bmi );
    
   }

}