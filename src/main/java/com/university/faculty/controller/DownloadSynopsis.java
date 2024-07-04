package com.university.faculty.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.university.faculty.itrImplementation.FacultyImplementation;



public class DownloadSynopsis extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	FacultyImplementation fImpl=new FacultyImplementation();
	
	
	public DownloadSynopsis() {
        super();
    
	}
	
	
	boolean downloadStatus=false;
	  int facultyId,taskId;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("Inside get method of downloadSynopsis...");
		
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		HttpSession session=request.getSession(false);
		
		facultyId=Integer.parseInt((String) session.getAttribute("faculty"));
		taskId=Integer.parseInt(request.getParameter("allottedTask"));
		
		session.setAttribute("faculty", facultyId);
		session.setAttribute("task", taskId);
		response.sendRedirect("viewSynopsis.jsp");
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, IllegalStateException {
		
		System.out.println("*******************************inside post method of download synopsis...");
		
		String page="";
		HttpSession session=request.getSession(false);
		//UpdateResult res=new UpdateResult();
		
			
		
        try {
			if(fImpl.viewSynopsis(taskId, facultyId))
			{
				
				System.out.println("back to servlet");
				response.setContentType("application/octet-stream");
			    response.setHeader("Content-Disposition", "attachment;");
			    
				try(OutputStream output=response.getOutputStream()) {
					output.write(fImpl.synopsis.getBytes(1,(int)fImpl.synopsis.length()));
					//System.out.println("File downloaded...");
					page="result.jsp";
					
					//enableButton(true);
//					 System.out.println("checking weather response is committed or not");
//					 String htmlContent = "<html><head><meta http-equiv=\"refresh\" content=\"0;url=" + page + "\"></head><body></body></html>";
			      //  response.getWriter().write("<script>document.getElementById('updateStatus').disabled=false</script>");
//				    
					System.out.println("File downloaded");
					downloadStatus=true;
					
//					session.setAttribute("faculty", facultyId);
//					session.setAttribute("task", taskId);
//					
				} catch (IOException | SQLException e) {
					page="error.jsp";
					enableButton(false);
					e.printStackTrace();
				}
				finally {
//					if(response.isCommitted()) {
//						downloadStatus=false;
//						request.setAttribute("download", downloadStatus);
//					}
//					request.getRequestDispatcher("insex.jsp");
					System.out.println("Inside finally block");
					System.out.println("Value of downloadStatus: "+downloadStatus);
					session.setAttribute("status",downloadStatus);
					session.setAttribute("taskId",taskId);
				}
				
			}
			else {
				page="error.jsp";
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			System.out.println("value of page:"+page);
		
		
			
			
//			if (response.isCommitted()) {
//				 System.out.println("checking weather response is committed or not");
//				 String htmlContent = "<html><head><meta http-equiv=\"refresh\" content=\"0;url=" + page + "\"></head><body></body></html>";
//			        response.getWriter().write(htmlContent);
//			    }
//		}
//		
		
		
	
		
		
	}
	
	
	
	void enableButton( boolean status) {
		HttpServletRequest request;
		HttpServletResponse response = null;
		
		
		try {
			response.setContentType("text/html");
			PrintWriter out=response.getWriter();
			
			if(!status) {
				out.print("<h1 style=\"color:red\">Download failed</h1>");
			}
			else {
				out.print("<script>document.getElementById(\"updateStatus\").disable=false</script");
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

