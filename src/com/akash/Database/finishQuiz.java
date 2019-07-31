package com.akash.Database;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sound.midi.Soundbank;

@WebServlet("/finishQuiz")
public class finishQuiz {
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		PrintWriter out = response.getWriter();
		
		response.setContentType("text/html");
		String score = request.getParameter("score");
		
		System.out.println(score);
		
		


	}

}
