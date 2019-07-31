package com.akash.Database;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/loign")
public class login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		PrintWriter out = response.getWriter();

		response.setContentType("text/html");
		String user = request.getParameter("userid");
		String password = request.getParameter("password");

		System.out.println(user);
		System.out.println(password);

		try {
			int count = 0;
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/akash", "root", "akash@123");
			System.out.println("Connected");

			String query = "SELECT COUNT(*) FROM registeredusers where email ='" + user + "' AND password ='" + password + "'";
			System.out.println(query);
			Statement statement = con.createStatement();

			ResultSet rs = statement.executeQuery(query);
			while (rs.next())
				count = rs.getInt(1);
			System.out.println(count);

			if (count != 0) {
				RequestDispatcher dispatcher = request.getRequestDispatcher("/Home.html");
				dispatcher.forward(request, response);
				System.out.println("This is registered user so entered the application");
			} else {

				String message = "Hello World";
				request.setAttribute("message", message);
				RequestDispatcher dispatcher = request.getRequestDispatcher("/welcome.html");
				dispatcher.forward(request, response);
				System.out.println("failed");
			}
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
