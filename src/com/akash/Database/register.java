package com.akash.Database;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.akash.Database.Database;

public class register extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try {

			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/akash", "root", "akash@123");
			System.out.println("Connected");

			// String query = "SELECT COUNT(*) FROM registeredusers where email
			// ='" + user + "' AND password ='" + password
			// + "'";

			String user = request.getParameter("email");
			String password = request.getParameter("password");
			String repeatpassword = request.getParameter("repeatpassword");
			String tablename = "registeredusers";
			System.out.println(user);
			System.out.println(password);
			System.out.println(repeatpassword);

			String query = "INSERT into " + tablename + " (email,password) VALUES ('" + user + "','" + password + "')";
			System.out.println(query);

			if (password.equals(repeatpassword)) {

				Database database = new Database();

				ResultSet rs = con.createStatement().executeQuery("SELECT COUNT(*) FROM registeredusers where email ='" + user + "'");

				if (rs.next()) {
					System.out.println(rs.getInt(1));
					if (rs.getString(1).equals("1")) {
						RequestDispatcher dispatcher = request.getRequestDispatcher("/useralreadyexists.html");
						dispatcher.forward(request, response);
						System.out.println("The user already registered");
					} else {
						database.updateTable(query, tablename);
						RequestDispatcher dispatcher = request.getRequestDispatcher("/registeredSucessfully.html");
						dispatcher.forward(request, response);
						System.out.println("executed");
					}

				}

				con.close();
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
