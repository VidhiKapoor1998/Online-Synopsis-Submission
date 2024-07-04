package com.university;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



public class WelcomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	
	
	
    public WelcomeServlet() {
        super();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String param=request.getParameter("page");
	
		if(param.equals("StudentLogin")) {
			getServletContext().getRequestDispatcher("/StudentLogin.jsp").forward(request, response);
		}
		else if(param.equals("FacultyLogin"))
		{
			getServletContext().getRequestDispatcher("/FacultyLogin.jsp").forward(request, response);
		}
		else if(param.equals("AdminLogin"))
		{
			getServletContext().getRequestDispatcher("/AdminLogin.jsp").forward(request, response);
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		
		
	}

}
