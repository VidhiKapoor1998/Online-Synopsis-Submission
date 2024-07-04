package com.university.admin.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;



import com.university.admin.itrImplementation.AdminImplementation;



public class AllotSynopsis extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	ArrayList<Integer> faculty=new ArrayList<>();
	ArrayList<Integer> synopsis=new ArrayList<>();
	
       
	AdminImplementation aImpl=new AdminImplementation();

	
	public AllotSynopsis() {
        super();
        System.out.println("------------------------Inside AllotSynopsis servlet------------------------");
	
	}

	
	
	//To retrieve data of available faculty
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		
		HttpSession session=request.getSession(false);
		
		System.out.println("----------------------------First step:inside doGet method of AllotSynopsis servlet----------------------------");
		
		
		out.print("<h1>Admin ID:"+session.getAttribute("admin")+"</h1>");
		out.print("<script> function logout() { alert(\"Click ok if you want to log out\");}</script>");
	  out.print("<a href=\"<%=request.getContextPath() %>/Logout\" onclick=\"logout()\">Logout</a><br/>");
	  try {
		
		ArrayList<Integer> faculty=aImpl.availableFacultyList();
	
		System.out.println("available faculty list in servlet:"+ faculty);
		
		
		
		System.out.println("back to servlet");
		out.print("<form action=\"AllotSynopsis\" method=\"post\">");
		out.print("<h3>Faculty:</h3><br/>");
		
		

		if(faculty.isEmpty())
		{
			out.print("<h4 style=\"color:blue\">Faculties are occupied</h4>");
		}
		
		
		else {
			for(Integer f:faculty)
			{
				
				System.out.println("faculty"+f);
				
				
					out.print("<input type=\"radio\" id=\"faculty\" name=\"facultyList\" value="+ f+">");
					out.print("<label for="+f+">"+f+"</label><br>");
					
					System.out.println("-----------------------\nvalue of f: "+f+"\n-----------------------");
				}
				
		}	
		
		
		
		
		//To display synopsis
		ArrayList<Integer> synopsis;
		
			synopsis = aImpl.unallottedSynosisList();
		
			
		out.print("<h3>Synopsis:</h3><br/>");
		
		System.out.println("list of unallotted synopsis: "+synopsis);;
		if(synopsis.isEmpty())
		{
			out.print("<h4 style=\"color:blue\">All the synopsis are assigned</h4>");
		}
		
		else 
		{
			for(Integer s:synopsis)
			{
				
				
				
					System.out.println(s);
					out.print("<input type=\"checkbox\" id=\"syn\" name=\"synopsis\" value="+ s+">");
					out.print("<label for="+s+">"+s+"</label><br>");
					
					System.out.println("-----------------------\nvalue of s: "+s+"\n-----------------------");
			}
					
			
		
		}
		session.setAttribute("admin", session.getAttribute("admin"));
		
		
		if((!synopsis.isEmpty())&& (!faculty.isEmpty())) {
			out.print("<input type=\"submit\">");}
		out.print("</form>");
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	
	
	
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		
		
		System.out.println("--------------------Second step:Inside AllotSynopsis doPost method--------------------------");
		String faculty=request.getParameter("facultyList");
		System.out.println(faculty);
		
		String[] synopsis=request.getParameterValues("synopsis");
		System.out.println("vlaues retrieved from checkbox and radio button");
		
		int i[]=new int[synopsis.length];
		for(int j=0;j<i.length;j++)
		{
			i[j]=Integer.parseInt(synopsis[j]);
		}
		
		try {
			aImpl.allotSynopsis(faculty, i);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		response.sendRedirect("AllotSynopsis");
		
		
	}

}
