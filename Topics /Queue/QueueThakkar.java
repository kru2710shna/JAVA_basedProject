import java.util.*;
import java.util.Stack;
public class QueueThakkar
{
   ArrayList<Integer> list;
   
   public QueueThakkar()
   {
      list = new ArrayList<Integer>();
   }
   
   //Enqueue Method
   public void enqueue(Integer num)
   {
      list.add(num);
   }
   
   // Dequeue Method
   public Integer dequeue()
   {
      return list.remove(0);
   }
   
   //Tostring Method  
   public String toString()
   {
      String s = "";
      QueueThakkar copy = new QueueThakkar();
      boolean b = false;
      while(!b)
      {
         try
         {
            int num = this.dequeue();
            copy.enqueue(num);
            s = s + " " + num;
         }
         catch(Exception e)
         {
            b = true;
         }
      }
     //System.out.println(copy.list+ "&&&&");
      restore(copy);
      return s;
   }
   
   public int getMax()
   {
      QueueThakkar copy = new QueueThakkar();
      boolean b = false;
      int max = 0;
      while(!b)
      {
         try
         {
            int num = this.dequeue();
            if(num > max)
               max = num;
            copy.enqueue(num);   
          
         }
         catch(Exception e)
         {
            b = true;
         }
      }
      restore(copy);
      return max;
   }
   
   //Restoring Queue after using any method
   public void restore(QueueThakkar q)
   {
      boolean b = false;
      while(!b)
      {
         try
         {
            this.enqueue(q.dequeue());
         }
         catch(Exception e)
         {
            b = true;
         }
      }
   }
   
   //Method to get min value 
   public Integer getMin()
   {
      QueueThakkar copy = new QueueThakkar();
      boolean b = false;
      int min = getMax() ;
      while(!b)
      {
         try
         {
            int num = this.dequeue();
            if(num < min)
               min = num;
            copy.enqueue(num);   
          
         }
         catch(Exception e)
         {
            b = true;
         }
      }
      restore(copy);
      return min ;
               
   }
   public double getAverage()
   {
      int sum = 0 ;
      double average = 0.0 ; 
      int  count = 0 ;
      QueueThakkar copy = new QueueThakkar();
   
      boolean b  = false ;
      while (!b)
      {
         try
         {
            int d  = this.dequeue();
            copy.enqueue(d);
            sum = sum + d;
            count ++;
         }
         catch (Exception e)
         {
            b = true ;
         }
      }
      restore(copy);
      average  = (double)sum/count ;
      
      return average;
   }
     
  public void reverse()
  {   
    Stack<Integer> s = new Stack<>();
    boolean  b = false ;  
    while (!b)
    {
      try
      {
        s.push(this.dequeue());
      }
      catch (Exception e)
      {
        b = true ;
      }
    }
    b = false;
    while (!b)
    {
      try
      {
        this.enqueue(s.pop());
      }
      catch (Exception e)
      {
          b = true ;
      }     
    }
  }
   
                 
   public boolean isSorted()
   {
    
      QueueThakkar q = new QueueThakkar();
      boolean b = false ;
      boolean sorted  = true ;
      while (!b)
      {
         try
         {
            int n1 = this.dequeue();
            int n2  = this.dequeue();
         
            q.enqueue(n1);
            q.enqueue(n2);
         
            if (n1>n2)
               sorted  = false ;
         }
         catch (Exception e )
         {
            b = true ;
         
         }
      }
      restore(q);
      return sorted ;
    
   }
}
class Driver
{
   public static void main(String[] args)
   {
      QueueThakkar m = new QueueThakkar();
      m.enqueue(10);
      m.enqueue(12);
      m.enqueue(15);
      m.enqueue(7);
      m.enqueue(100);
      m.enqueue(22);
      //System.out.println(m);
      System.out.println("The queue is : " + m);
      m.reverse();
      System.out.println("The queue in the reverse order is: "+m);
      m.reverse();
      System.out.println("Queue is back to it original state: "+m);
    
      System.out.printf("Average = %.2f\n",m.getAverage());
      System.out.println("Max = " + m.getMax());
      System.out.println("Min = " + m.getMin());
      System.out.println("The list is sorted: "+ m.isSorted()) ;
      
           
   }
}