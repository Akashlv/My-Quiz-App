package com.akash.Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {

	// This method will connect to database and return the connection
	// PLEASE CLOSE CONNECTION if you use this method
	public Connection connectToDb() throws ClassNotFoundException, SQLException {

		Class.forName("com.mysql.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/akash", "root", "akash@123");
		System.out.println("Connected");
		return con;
	}

	public void updateTable(String query, String tablename) {

		try {
			Connection con = connectToDb();
			System.out.println(query);
			Statement statement = con.createStatement();
			int rs = statement.executeUpdate(query);
			System.out.println(rs);
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
