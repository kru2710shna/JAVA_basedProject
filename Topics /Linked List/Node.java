class Node
{
   public String name;
   public Node next;
   public Node(String name)
   {
      this.name = name;
   }
   public Node()
   {
      name = null;
   }
}
//********************************************

//A list of Nodes
class Students
{
   Node front;
   public Students()
   {
      front = new Node();
   }

// add nodes to the list
   public void add(String s )
   {
      Node cur = front;
      while(cur.next!= null)
      {
         cur = cur.next;
      }
      Node n = new Node(s);
      cur.next = n;
      n.next= new Node(s);
   } 
   public String tostring()
   {
      if(front == null)
         return null;
     Node curr = front;
      String s = "";
      while(curr!= null)
      {
         s = s + curr.getData() + " ";
         curr = curr.getNext();
     }
      return s;    
      
   }

   
}
class Driver
{
   public static void main(String[] args)
   {
      Students list = new Students();
      list.add("a");
      list.add("b");
      list.add("c");
      list.add("d");
      list.add("e");
      list.add("f");
      System.out.print(list) ;
     
   }
}