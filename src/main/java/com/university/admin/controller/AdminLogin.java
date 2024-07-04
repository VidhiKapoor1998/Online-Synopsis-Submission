package com.university.admin.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.university.admin.itrImplementation.AdminImplementation;


public class AdminLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	public AdminLogin() {
        super();
        
        System.out.println("Inside AdminLogin servlet...");
	}

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("inside get method of adminlogin");
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		boolean status=false;
		
		HttpSession session=request.getSession(status);
		//String n=(String)session.getAttribute("admin");
		
		System.out.println(session.getAttribute("admin"));

		out.print("<h1>"+session.getAttribute("admin")+"</h1>");
		request.getRequestDispatcher("Admin.jsp").include(request, response);
	

			
	}

	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		System.out.println("Inside post method...");
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		
		
		System.out.println("Fetching values...");
		String id=request.getParameter("Id");
		String password=request.getParameter("password");
		
		System.out.println(id+" "+password);
		
		AdminImplementation aImpl=new AdminImplementation();
		
		HttpSession session=request.getSession(false);
		
		
	try {
		if(aImpl.login(Integer.parseInt(id), password))
			{
				
				session.setAttribute("admin", id);
				doGet(request, response);
			}
			else
			{
				out.print("Incorrect credentials");
			}
	} catch (NumberFormatException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (ServletException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		
		out.close();


	}

}
