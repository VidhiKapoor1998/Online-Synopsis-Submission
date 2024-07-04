package com.university.student.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.university.student.itrImplementation.StudentImplementation;

public class StudentLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public StudentLogin() {
        super();
      System.out.println("Inside Servlet...");
    }

	
    
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("inside get method of studentlogin");
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		boolean status=false;
		
		HttpSession session=request.getSession(status);
		//String n=(String)session.getAttribute("student");
		
		System.out.println(session.getAttribute("student"));

		out.print("<h1>"+session.getAttribute("student")+"</h1>");
		request.getRequestDispatcher("Student.jsp").include(request, response);
	
		session.setAttribute("student", session.getAttribute("student"));
		
		
	}

	
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("Inside post method...");
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		
		
		System.out.println("Fetching values...");
		String rollNumber=request.getParameter("rollNum");
		String password=request.getParameter("password");
		
		System.out.println(rollNumber+" "+password);
		
		//Cookie studentCookie=new Cookie("student",rollNumber);
		HttpSession session=request.getSession(false);
		
		
		
		StudentImplementation sImpl=new StudentImplementation();
		try {
			if(sImpl.login(Integer.parseInt(rollNumber), password))
			{
				session.setAttribute("student", rollNumber);
				doGet(request, response);
				//response.sendRedirect("WelcomeStudent");
			}
			else
			{
				out.print("Incorrect credentials");
			}
		} catch (NumberFormatException | SQLException | ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		out.close();
	}

}
