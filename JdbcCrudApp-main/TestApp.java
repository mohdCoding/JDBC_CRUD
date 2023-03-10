import java.io.InputStreamReader;
import java.sql.*;
import java.util.*;

class TestApp {

    public static void main(String[] args) {
        
       

       Connection connection = null;
       PreparedStatement pstmt = null;
       ResultSet resultSet = null;
       Integer noOfRows = null;
       boolean flag = true;

    try {
    	
    	       String url = "jdbc:mysql://localhost:3306/crudproject";
	       String userName = "root";
	       String password = "root123";

	       connection = DriverManager.getConnection(url, userName, password);
    	
               
               
    	do
    	{
    		
    		 System.out.println("1. Create a new Student Record\n" + 
                     "2. Read The student Records\n" +
                     "3. Update the existing student Record\n" +
                     "4. Delete an existing student Record\n");

                  Scanner in = new Scanner(System.in);


                  System.out.println("Enter Your Choice");
                  int choice = in.nextInt();
                   in.nextLine();
    	       
    	    if (connection != null) {
    	        String gQuery;
    	       if(choice == 1) {
    	    	       gQuery = "insert into student (name, age, marks) values(?, ?, ?)";
    	    	       pstmt = connection.prepareStatement(gQuery);
    	    	       
    	               System.out.println("Enter the name of student :: ");
    	               String name = in.nextLine();
    	               
    	               System.out.println("Enter the age of student");
    	               int age = in.nextInt();
    	               
    	               System.out.println("Enter the marks of student");
    	               int marks = in.nextInt();
    	                
    	               pstmt.setString(1, name);
    	               pstmt.setInt(2, age);
    	               pstmt.setInt(3, marks);;
    	              

    	               noOfRows = pstmt.executeUpdate();
    	               System.out.println(noOfRows + " Student Record added successfully");


    	             
    	       } else if(choice == 2) {
    	             gQuery = "select id, name, age, marks from student;";
    	             pstmt =  connection.prepareStatement(gQuery);
    	             resultSet = pstmt.executeQuery();

    	             if(resultSet != null) {
    	                 System.out.println("id\tname\tage\tmarks");
    	                while(resultSet.next()) {
    	                    System.out.println(
    	                       resultSet.getInt(1)     +  "\t" + 
    	                       resultSet.getString(2) + "\t"  +
    	                       resultSet.getInt(3)    +  "\t" +
    	                       resultSet.getInt(4));
    	                }
    	             }
    	       } else if(choice == 3) {
    	           
    	          System.out.println("1. To update a student Name");
    	          System.out.println("2. To update a student age");
    	          System.out.println("3. To update a student mark");

    	          System.out.println("Select Your Choice");
    	          int option = in.nextInt();
    	          in.nextLine();
    	          
    	          String sname;
    	    
    	          if(option == 1) {
    	              System.out.println("Enter the name to update :: ");
    	              String name = in.nextLine();

    	              System.out.println("Enter the existing name to replace with this current name :: ");
    	              sname = in.nextLine();

    	              gQuery = "update student set name = ? where name = ?";
    	              
    	              pstmt = connection.prepareStatement(gQuery);
    	              pstmt.setString(1, name);
    	              pstmt.setString(2, sname);

    	              noOfRows = pstmt.executeUpdate();

    	              if(noOfRows == 0) 
    	                System.out.println("Invalid name -> no matching data found");
    	              else
    	               System.out.println(noOfRows + " name has updated successfully");

    	          } else if (option == 2) {

    	              System.out.println("Enter the age to update");
    	              int age = in.nextInt();
    	              in.nextLine();

    	              System.out.println("Enter the existing name to replace with this current age :: ");
    	              sname = in.nextLine();

    	             gQuery = "update student set age = ?  where name = ?";
    	                     

    	              pstmt = connection.prepareStatement(gQuery);
    	              pstmt.setInt(1, age);
    	              pstmt.setString(2, sname);
    	          
    	              noOfRows = pstmt.executeUpdate();
    	              if(noOfRows == 0) 
    	                System.out.println("Invalid name -> no matching data found");
    	              else
    	               System.out.println(noOfRows + " age has updated success fully");

    	          } else if (option == 3) {

    	             System.out.println("Enter the mark to update");
    	             int mark = in.nextInt();
    	             in.nextLine();

    	             System.out.println("Enter the existing name to replace with this current mark :: ");
    	             sname = in.nextLine();

    	             gQuery = "update student set marks = ? where name = ?";
    	 
    	               pstmt = connection.prepareStatement(gQuery);
    	               pstmt.setInt(1, mark);
    	               pstmt.setString(2, sname);
    	               
    	              noOfRows = pstmt.executeUpdate();

    	              if(noOfRows == 0) 
    	                System.out.println("Invalid name -> no matching data found");
    	              else
    	               System.out.println(noOfRows + " mark has updated success fully");

    	          } else {

    	             System.out.println("Invalid Option select from 1, 2, 3");
    	          }



    	       } else if(choice == 4) {
    	            System.out.println("Enter the name of a student to delete the record :: ");
    	            String name = in.nextLine();
    	            
    	            gQuery = "delete from student where name = ?";
    	            pstmt = connection.prepareStatement(gQuery);
    	            pstmt.setString(1, name);
    	            noOfRows = pstmt.executeUpdate();
    	            if(noOfRows == 0)
    	              System.out.println("Invalid student name -> no matchig records found");
    	            else  
    	              System.out.println(noOfRows + " student data has deleted succesfully");
    	       } else {
    	           System.out.println("Invalid option");
    	       }
    	       
    	       
    	    }
    	    
    	    
    	    System.out.println("\n Do you want to continue :: 1. yes, 2.no");
 	       int ask = in.nextInt();
 	       
 	       if(ask == 2) 
 	    	   flag = false;
 	           System.out.println("Thanks for using our application");
    		
    	} while(flag);

      
       
    
    
    


    } catch (SQLException se) {
        se.printStackTrace();
    } catch (Exception e) {
        e.printStackTrace();
    } finally {

        try {
           
           if (resultSet != null) 
               resultSet.close();
            
           if (pstmt != null)
              pstmt.close();

           if (connection != null) 
              connection.close();

        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
      
    }

}
