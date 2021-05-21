import java.util.*;
import java.io.*;

//-------------------------------abstract class-----------------------------------------

abstract class Book
{
   abstract void getLibraryInfo();
}


//-------------------------------interface----------------------------------------------

interface Books
{
   void create();
}


//-------------------------------class containing library information--------------------

class LibraryInfo extends Book
{
  String Book_ID;
  String Book_Name;
  String Author_Name;
  String Price;

  public void getLibraryInfo()
  {
    Scanner sc=new Scanner(System.in);
    System.out.print("Book ID: ");
    Book_ID=sc.nextLine();
    System.out.print("Book Name: ");
    Book_Name=sc.nextLine();
    System.out.print("Author Name: ");
    Author_Name=sc.nextLine();
    System.out.print("Price: ");
    Price=sc.nextLine();
  }
}


//------------------------------class for adding books in file---------------------------
//exception handling used

class AddBook implements Books
{
   public void create()
   {
      LibraryInfo lib=new LibraryInfo();
      lib.getLibraryInfo();

      try
      {
         File f1=new File("libraryfile"+lib.Book_ID+".txt");
         if(f1.createNewFile())
         {
            FileWriter myWritter= new FileWriter("libraryfile"+lib.Book_ID+".txt");
            myWritter.write("Book Id:     " + lib.Book_ID + "\n" + 
                            "Book Name:   " + lib.Book_Name + "\n" +
                            "Author Name: " + lib.Author_Name + "\n" +
                            "Price:       " + lib.Price);
            myWritter.close();
            System.out.println("");
            System.out.println("Added Sucessfully!!! ");
            System.out.println("");
            System.out.println("");
         }
         else
         {
            System.out.println("Book already exits ");
            System.out.println("");
            System.out.println("");
         }
      }
      catch(Exception e)
      {
         System.out.println(e);
      }
   }
}


//------------------------------class for deleting record --------------------------------

class RemoveBook 
{
   public void remove(String ID)
   {
      File file = new File("libraryfile"+ID+".txt");
      if(file.exists())
      {
         if(file.delete())
         {
            System.out.println("Book removed successfully ");
            System.out.println("");
            System.out.println("");
         }
      }
      else
      {
         System.out.println("Book does not exits ");
         System.out.println("");
         System.out.println("");
      }
   }
}


//------------------------------class for updating book record----------------------------

class UpdateBook 
{
   public void update(String s,String o,String n) throws IOException
   {
      File f2=new File("libraryfile"+s+".txt");
      Scanner sc=new Scanner(f2);
      String fileContent="";
      while(sc.hasNextLine())
      {
         fileContent = fileContent + "\n" + sc.nextLine();
      }
      FileWriter myWritter=new FileWriter("libraryfile"+s+".txt");
      fileContent=fileContent.replaceAll(o,n);
      myWritter.write(fileContent);
      myWritter.close();
      System.out.println("Record updated successfully");
      System.out.println("");
      System.out.println("");
   }
}


//------------------------------class to search book details-------------------------------

class SearchBook 
{
   public void search(String s) throws Exception
   { 
      File f2=new File("libraryfile"+s+".txt");
      Scanner sc=new Scanner(f2);
      while(sc.hasNextLine())
      {
         System.out.println(sc.nextLine());
      }
      System.out.println("");
      System.out.println("");
   }
}


//------------------------------class for selecting function------------------------------

class Choice
{
   public static void main(String arv[])
   {
      Scanner sc= new Scanner(System.in);

      System.out.println("");
      System.out.println("");
      System.out.println("----------WELCOME TO LIBRARY MANAGEMENT SYSTEM----------"); 
      System.out.println("");
      System.out.println("");

      int i=0;
      while(i<=5)
      {
         System.out.println("1. Add Books ");
         System.out.println("2. Search Books ");
         System.out.println("3. Update Books ");
         System.out.println("4. Remove Books ");
         System.out.println("5. Exit");

         System.out.println("");
         System.out.println("Enter choice");
         i=Integer.parseInt(sc.nextLine());

         switch(i)
         {
            case 1:
            {
               AddBook ab=new AddBook();
               ab.create();
               break;
            }
            case 2:
            {
               SearchBook sb=new SearchBook();
               System.out.println("Enter Book ID: ");
               String s=sc.nextLine();
               try
               {
                  sb.search(s);
               }
               catch(Exception e)
               {
                  System.out.println(e);
               }
               break;
            }
            case 3:
            {
                SearchBook sb=new SearchBook();
                System.out.println("Enter Book ID: ");
                String s=sc.nextLine();
                try
                {
                   sb.search(s);
                }
                catch(Exception e)
                {
                   System.out.println(e);
                }
                
                UpdateBook ub = new UpdateBook();
                System.out.println("Enter the previous detail to be changed: ");
                String s1=sc.nextLine();
                System.out.println("Enter new detail: ");
                String s2=sc.nextLine();
                try
                {
                   ub.update(s,s1,s2);
                }
                catch(IOException e)
                {
                   System.out.println(e);
                }
                break;
            }
            case 4:
            {
                RemoveBook rb=new RemoveBook();
                System.out.println("Enter Book ID: ");
                String s=sc.nextLine();
                rb.remove(s);
                break;
            }
            case 5:
            {
                System.out.println("Thank You");
                System.exit(0); 
            }
            default:
            {
               System.out.println("Wrong choice, Try again!!! ");
               break;
            }
         }
      }
   }
}
