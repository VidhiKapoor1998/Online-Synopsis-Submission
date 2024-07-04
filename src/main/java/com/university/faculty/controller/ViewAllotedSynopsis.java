package com.university.faculty.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mysql.cj.x.protobuf.MysqlxDatatypes.Array;
import com.university.faculty.itrImplementation.FacultyImplementation;





public class ViewAllotedSynopsis extends HttpServlet {
	
	
	
	
	private static final long serialVersionUID = 1L;
       
	
	
    public ViewAllotedSynopsis() {
       	super();
    	System.out.println("--------------------Inside synospis servlet--------------------------");
    }
    
    
    FacultyImplementation fImpl=new FacultyImplementation();

    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		response.setContentType("text/Html");
		PrintWriter out=response.getWriter();
		
		
		System.out.println("Inside post method of viewAllotedSynopsis");
		
		
		HttpSession session=request.getSession(false);
		int id=Integer.parseInt((String) session.getAttribute("faculty"));
		
		
		out.print("<h1>Faculty ID: "+id+"</h1>");
		System.out.println("Data from session: "+id);
		
		ArrayList<Integer> task;
		try {
			task = fImpl.viewList(id);
		
		
		out.print("<form action=\"DownloadSynopsis?allottedTask=?\" method=\"get\">");
		out.print("<h3>Task:</h3><br/>");
		
		

		if(task.isEmpty())
		{
			out.print("<h4 style=\"color:blue\">Task list is empty</h4>");
		}
		
		else {

			for(int i:task)
			{	
				System.out.println(i);
				if(i>0)
				{
	
					out.print("<input type=\"radio\" id=\"tsk\" name=\"allottedTask\" value="+ i+">");
					out.print("<label for="+i+">"+i+"</label><br>");
				}
			}
		}
		
		if((!task.isEmpty())) {
			out.print("<input type=\"submit\" value=\"Open\">");}
		out.print("</form>");
	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
}	



	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	

}
}