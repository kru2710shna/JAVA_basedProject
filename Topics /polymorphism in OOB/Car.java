public class Car
{
   public void m1() 
   {
      System.out.println("car 1");
   }
   public void m2() {
      System.out.println("car 2");
   }

   public String toString()
   {
      return "vroom";
   }
}
class Truck extends Car 
{
   public void m1() 
   {
      System.out.println("truck 1");
   }
    
   public void m2()
   {
      super.m1();
   }
    
   public String toString() 
   {
      return super.toString() + super.toString();
   }
}
class Monstertruck extends Truck 
{
   public void m1()
   {
      System.out.println("Monster 1 "); 
   }
   public String toString()
   {
   String s = " ";
   s = s + "monster" + super.toString(); 
   return s ;
   }
}
class Driver
{
   public static void main (String []args)
   {
   Monstertruck m = new Monstertruck();
   Truck n = new Monstertruck();

   
   n.m1();
   n.m2();
   System.out.println(m);   }
}