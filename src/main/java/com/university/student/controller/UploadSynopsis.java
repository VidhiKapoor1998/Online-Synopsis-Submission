package com.university.student.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.university.student.itrImplementation.StudentImplementation;

@MultipartConfig(
		  fileSizeThreshold = 1024 * 1024 * 1/2, // 1 MB
		  maxFileSize = 1024 * 1024 * 10,      // 10 MB
		  maxRequestSize = 1024 * 1024 * 100   // 100 MB
		)

public class UploadSynopsis extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	
	public UploadSynopsis() {
        super();
       System.out.println("------------------------------------------------------------------------------");
	}

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	
	}

	
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		System.out.println("Inside servlet(post method)");
		response.setContentType("text/HTML");
		PrintWriter out=response.getWriter();

		HttpSession session=request.getSession(false);
		
		StudentImplementation sImpl=new StudentImplementation();
		
		
		Part synopsis=request.getPart("synopsis");
	System.out.println("file recieved");
		
		if(synopsis!=null)
		{
			System.out.println("file is not null");
			System.out.println(synopsis.getName());
			System.out.println(synopsis.getSize());
			System.out.println(synopsis.getContentType());
			
			InputStream is =synopsis.getInputStream();
			
			int result;
			try {
				result = sImpl.uploadSynopsis(is, Integer.parseInt((String) session.getAttribute("student")));
				if(result>=1)
				{
					out.print("<h4>You have sucessfully uploaded your synopsis</h4>");
					
				}
			} catch (NumberFormatException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
		
		
	}

}
