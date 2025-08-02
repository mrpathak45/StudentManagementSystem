/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package myApplication;

/**
 *
 * @author hp
 */

import java.sql.*;
public class DatabaseConnection {

	    public static void main(String[] args) {
	        try {
	            // Step 1: Load the MySQL JDBC Driver
	            Class.forName("com.mysql.cj.jdbc.Driver");

	            // Step 2: Establish connection
	            Connection con = DriverManager.getConnection(
	                "jdbc:mysql://localhost:3306/", "root", "admin");

	            System.out.println("Connection Successful");

	            con.close();
	        } catch (Exception e) {
	            System.out.println(e);
	        }
	    }


}
