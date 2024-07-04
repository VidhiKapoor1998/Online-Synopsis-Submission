package com.university.student.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.university.student.itrImplementation.StudentImplementation;


public class viewStatus extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	int rollNumber;
	StudentImplementation sImpl=new StudentImplementation();
	ArrayList<String> result=new ArrayList<String>();
	String status,date;

	public viewStatus() {
        super();
    
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		
		HttpSession session=request.getSession(false);
		rollNumber=Integer.parseInt((String) session.getAttribute("student"));
		
		try {
			result=sImpl.showStatus(rollNumber);
			status=result.get(0);
			date=result.get(1);
			
			session.setAttribute("status", status);
			session.setAttribute("date", date);
			
			response.sendRedirect("viewStatus.jsp");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}

}
