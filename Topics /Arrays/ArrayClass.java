import java .util.*;
public class ArrayClass 
{ 
   public static void main (String [] args)
   {
       
      int m [] = {1,2,34, 1234, 999} ;
      int Max  =  getMax(m) ;
      System .out.println("Max value is " + Max);
   
   
   }
   public static int getMax(int [] a)
   {
   
      int max = 0  ; 
   
      for (int  i = 0  ; i < a.length ; i ++)
      {
      
         if ( a[i] > max )
         {
          max = a[i] ;
           i++ ;
         }
      
      }
   return max ;
   
   }
}