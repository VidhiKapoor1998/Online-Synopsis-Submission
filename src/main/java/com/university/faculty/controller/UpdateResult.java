package com.university.faculty.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.university.faculty.itrImplementation.FacultyImplementation;

@WebServlet(value = "/updateResult") 
public class UpdateResult extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	public UpdateResult() {
        super();
    
	}

	FacultyImplementation fImpl=new FacultyImplementation();
	int taskId,facutlyId;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		System.out.println("Inside update result servlet");
		PrintWriter out=response.getWriter();
		
		HttpSession session=request.getSession(false);
		
		taskId=(Integer)session.getAttribute("task");
		facutlyId =(Integer) session.getAttribute("faculty");
		System.out.println("Value of faculty and task from session: "+ facutlyId+": "+taskId);
		
		boolean approvalStatus=Boolean.parseBoolean(request.getParameter("result"));
		String feedback=request.getParameter("feedback");
		
		try {
			if(fImpl.updateResult(taskId,facutlyId, approvalStatus, feedback)) {
				out.print("<h1>Task completed</h1>");
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	
	}

}
