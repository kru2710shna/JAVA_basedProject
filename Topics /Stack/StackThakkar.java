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
30 pts
Full Marks,
Program follows all the requirements

Self grade
5 pts
Full Marks
Self grade with a complete explanation is provided
*/

import java.util.*;

public class StackThakkar
{
}
interface myStack
{
   public void push(String s);
   public String peek();
   public boolean isEmpty();
   public String pop();
}
class Stack implements myStack 
{
   
   ArrayList<String> s ;
    
   public Stack() 
   {
   
      s = new ArrayList<String>() ;
   
   }
   
   public void push(String token)
   {
   
      s.add(token) ;
           
   }
   
   public String peek()
   {
   
      if(s.size()==0)
         return null;
      
      return s.get(s.size()-1);
      
   }
   
   public boolean isEmpty()
   {
   
      if(s.size() == 0)
         return true;
       
      else
         return false;
   
   }
   
   public String pop()
   {
   
      if(s.size()==0)
         return null;
      
      return s.remove(s.size()-1);
         
   }
   
   public String tostring()
   {
      ArrayList<Object> list = new ArrayList<Object>();
      
      String s= " ";
      
      while(!isEmpty())
      {
         Object o = pop();
         s = s+" "+o;
         list.add(o);
      }
      
      int i=list.size()-1;
      
      while(i>=0)
      {
         Object o = list.remove(i);
         String p = (String)o;
         
         System.out.println(o);
         push(p);
         i--;
      }
      return s;
   }

}

class Expression
{
   private String exp;  // instance variable
  
   public Expression(String s)
   {
      exp = s;
   }
   
   public String getPostfix()
   {
      String postfix = " ";
      
      //Creating Stack Object
      Stack stack = new Stack();
      
      //using the stringTokenizer class to tokenize the exp instance variable declared in the Expression class: 
      StringTokenizer st = new StringTokenizer(exp," ");
      
      while(st.hasMoreTokens())
      {
         String token = st.nextToken();
      //If the token is any of the operations +, -, /, *
         if (token.equals("*") || token.equals("+")||token.equals("-")||token.equals("/"))
         {
           //Get the precedence of the token by calling the method precedence
            int answer = precedence(token) ;
           
           //If the precedence is 3 
            if (answer == 3 )
            {
               //The stack is not empty and the precedence of the top of the stack is 3//the token is * or /
               while (! (stack.isEmpty()) && precedence(stack.peek())==3)
               {
                        //Pop the element at the top of the stack
                  String k = stack.pop() ;
                  
                  postfix = postfix + " "+k;    
                        //Concatenate it to the postfix expression
                 
               }
                //end while
            }
            //the precedence of the token is 2 Meaning the token is + or -
            else if(answer == 2 )
            {
                  //the stack is not empty and the precedence of the top of the stack is 2 or 3
               while ( !stack.isEmpty() && (precedence(stack.peek())==2 || precedence(stack.peek())==3))
               {
                  //Pop the element at the top of the stack
                  String k = stack.pop() ;
                        //Concatenate it to the postfix expression
                  postfix = postfix +" "+ k;                 
                 //end while   
               }
                 
            }
            
            stack.push(token);
                    // Push the token to the stack
         }
         
         else          
         {    
          // token is a number and must be concatenated to the postfix
         //concatenate the token to the postfix expression
            postfix = postfix + " " + token ;
         }
         
      }// end of while
     
      //the stack is not empty
      while (! (stack.isEmpty()))
      {
      //Pop the stack 
         String k =  stack.pop();
      //Concatenate it to the postfix
         postfix = postfix + " " + k;
      
      }
   //end while
   
      return postfix ;
   //end of the method               
   }
   
   private static int precedence(String opr)
   {
      if (opr.equals("*") || opr.equals("/"))
      {
         return 3;
      }
      if (opr.equals("+") || opr.equals("-"))
      {
         return 2 ;
      }
     
      return 0 ;
   }
   
   public int evalPostfix()  
   {
      String post = this.getPostfix()    ;//cretes the postfix expression
   
   //Declare a Stack of the stack class that you created
      Stack stack = new Stack();
   
      int result = 0;
   
      StringTokenizer st = new StringTokenizer(post," ");
   
      while (st.hasMoreTokens()) 
      { 
         String token = st.nextToken();
         //If the token is not * / + -
         if (!token.equals("+") && !token.equals("-") && !token.equals("/") && !token.equals("*")) 
         {
            stack.push(token);
         }   //Push the token to the stack
         
           
         else
         {
            String n1 = stack.pop() ;
            String n2 = stack.pop() ;
                 
            int  num1 = Integer.parseInt(n1); //convert string numbers to int. “12”  12
            int  num2 = Integer.parseInt(n2);
                 
                 //Call the method calculate with num1, num2, and token
            result = calculate(num1, num2, token) ;
                 
                 /*Push the result from the calculate method to the stack. (Since the calculate method returns an 
         int and the stack is of type string you must concatenate the result with “” before pushing it to the stack. 
         )*/
         
            String r = Integer.toString(result);
         
            stack.push(r) ;
            
         } 
         
      }//end while
      
      //Pop the stack and convert it to an integer and return the result
   
      String n = stack.pop(); 
      
      int value = Integer.parseInt(n);
      
      return value;
      
        //Pop the stack and convert it to an integer and return the result
   } 
   
   private int calculate(int num1, int num2, String opr)
   {
      if(opr.equals("*"))
         return num1 * num2 ;
      
      if (opr.equals("/"))
         return num2/num1 ;
      
      if (opr.equals("+"))
         return num1+num2 ;
      
      if (opr.equals("-"))
         return num2-num1 ;
      
      return 0 ;
   }
}
/*
class ExpDrive
{
   public static void main(String[] args)
   {
     
    // String s = "5 - 2";
      ArrayList <String> exp = new ArrayList<String>();
      exp.add("2 + 3 + 7 * 4 - 2 / 3");
      exp.add("3 - 4 / 2 + 6 * 3");
      exp.add("5 * 6 - 8 + 2 * 10");
      exp.add("4 + 8 * 3 - 2 / 34");
      exp.add("6 - 3 + 6 / 2 * 4 - 8");
      
      for(int i = 0; i < exp.size(); i++)
      {
      
         Expression e1 = new Expression(exp.get(i)) ;
         
         String post = e1.getPostfix();
         
         int result = e1.evalPostfix();
         
         System.out.println("Infix: "+ exp.get(i) + ",  postfix: " + post + " = " + result) ;
      }
   }
}
/*Write your own driver to test your code
this driver should be similar to the one 
I provided but must be your own expressions
*/
class MyExpDrive
{
   public static void main(String[] args)
   {
      String s = "5 - 2";
      ArrayList <String> exp = new ArrayList<String>();
      exp.add("10 + 20 - 1 * 2 / 10 + 20");
      exp.add("8 + 9 + 7 * 3 - 2 + 6 / 3");
      exp.add("1 + 12 + 2 / 2 - 10 * 4");
      exp.add(" 4 + 8 * 8 - 9 / 2");
      exp.add("6 - 3 + 6 / 2 * 4 - 8");
      
      for(int i = 0; i < exp.size(); i++)
      {
      
         Expression e1 = new Expression(exp.get(i));
         
         String post = e1.getPostfix();
         int result = e1.evalPostfix();
         
         System.out.println("Infix: "+ exp.get(i) + ",  postfix: " + post + " = " + result);
      }
   
   }
}
