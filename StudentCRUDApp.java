package myApplication;
import java.sql.*;  

import java.util.Scanner; 

public class StudentCRUDApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in); 

        try {
            
            Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/studentdb", "root", "admin");
           

            int choice;
            do {
                
                System.out.println("\n--- Student Management ---");
                System.out.println("1. Insert Student");   
                System.out.println("2. Display Students");  
                System.out.println("3. Update Student");    
                System.out.println("4. Delete Student");   
                System.out.println("5. Exit");             
                System.out.print("Enter your choice: ");
                choice = sc.nextInt(); 

                switch (choice) {
                    case 1:
                        // Insert Operation
                        System.out.print("Enter ID: ");
                        int id = sc.nextInt();
                        sc.nextLine();

                        System.out.print("Enter Name: ");
                        String name = sc.nextLine(); 

                        System.out.print("Enter Marks: ");
                        int marks = sc.nextInt();

                        String insertQuery = "INSERT INTO student VALUES (?, ?, ?)";
                       

                        PreparedStatement insertStmt = con.prepareStatement(insertQuery);
                        insertStmt.setInt(1, id);
                        insertStmt.setString(2, name);
                        insertStmt.setInt(3, marks);
                        insertStmt.executeUpdate();
                        System.out.println("✅ Student Inserted");
                        break;

                    case 2:
                        // Read Operation
                        Statement readStmt = con.createStatement();
                        ResultSet rs = readStmt.executeQuery("SELECT * FROM student");
                    
                        System.out.println("\nID\tName\tMarks");
                        System.out.println("-----------------------------");
                        while (rs.next()) {
                            System.out.println(rs.getInt("id") + "\t" +
                                               rs.getString("name") + "\t" +
                                               rs.getInt("marks"));
                           
                          
                        }
                        break;

                    case 3:
                        // Update Operation
                        System.out.print("Enter ID to Update: ");
                        int uid = sc.nextInt(); 

                        System.out.print("Enter New Marks: ");
                        int newMarks = sc.nextInt(); 
                                                    

                        String updateQuery = "UPDATE student SET marks = ? WHERE id = ?";
                        PreparedStatement updateStmt = con.prepareStatement(updateQuery);
                        updateStmt.setInt(1, newMarks);
                        updateStmt.setInt(2, uid);

                        int rowsUpdated = updateStmt.executeUpdate(); 
                                                                       

                        if (rowsUpdated > 0)
                            System.out.println("✅ Marks Updated"); 
                        else
                            System.out.println("❌ Student not found");
                        break;

                    case 4:
                        // Delete Operation
                        System.out.print("Enter ID to Delete: ");
                        int did = sc.nextInt(); 
                                               

                        String deleteQuery = "DELETE FROM student WHERE id = ?";
                        PreparedStatement deleteStmt = con.prepareStatement(deleteQuery);
                        deleteStmt.setInt(1, did);

                        int rowsDeleted = deleteStmt.executeUpdate();
                                                                       
                        if (rowsDeleted > 0)
                            System.out.println("✅ Student Deleted"); 
                        else
                            System.out.println("❌ Student not found"); 
                        break;

                    case 5:
                        System.out.println("Exiting..."); 
                        break;

                    default:
                        System.out.println("❗ Invalid Choice");
                }

            } while (choice != 5);

            con.close(); 

        } catch (Exception e) {
            e.printStackTrace(); 
    }
}
}
