package com.collabera.jdbc;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;


public class MyResultSetExample {
	
	static final String URL = "jdbc:mysql://localhost:3306/sakila";
	static final String USERNAME = "root";
	static final String PASSWORD = "root";
	
	public static Connection getConnection() {
		
		Connection conn = null;
		
		try {
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			System.out.println("Connection was made");
			
		} catch (SQLException e) {
			e.printStackTrace();	
		}
		return conn;
	}

/****************** Main ***************************/
	
	public static void main(String[] args) {
		
		Connection conn = MyResultSetExample.getConnection();
		
		try {

			Statement statement = conn.createStatement();
			String query = "SELECT * FROM actor";
			ResultSet rs = statement.executeQuery(query);
			rs.absolute(195);
			String firstName = rs.getString("first_name");
			String actorID = rs.getString(1);
			System.out.println("ID: " + actorID + "   Name: " + firstName);
			while(rs.next()) {
				firstName = rs.getString("first_name");
				actorID = rs.getString(1);
				System.out.println("ID: " + actorID + "   Name: " + firstName);
			}

		}catch(SQLException e) {
			e.printStackTrace();
		}

	
/****************** Close Connection ********************/
		
		try {
			conn.close();
			System.out.println("Connection was closed");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}