package com.university.faculty.itrImplementation;

import java.io.OutputStream;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.catalina.connector.Response;

import com.mysql.cj.jdbc.Blob;
import com.university.dao.DatabaseConnector;
import com.university.faculty.itr.ItrFaculty;




public class FacultyImplementation implements ItrFaculty{

	
	
	boolean status;
	
	public FacultyImplementation() {

		System.out.println("Inside faculty implementation class");
	}
	
	String query;
	
	
		DatabaseConnector db=new DatabaseConnector();
		
		public Blob synopsis;
		@Override
		public boolean login(int id, String password) throws SQLException {
			System.out.println(id+" "+password);
			query=" select id from facultydetails where id=? and password=?";
					db.preparedStatement(query,	String.valueOf(id), password);
			return db.status;
					
			}

	
	//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

	@Override
	public ArrayList<Integer> viewList(int Id) throws SQLException {
		System.out.println("Inside viewList method(impl class)");
		System.out.println("Id");
		
		
		query="Select submissionId from synopsis where facultyId=?  and status=\"Under faculty\"";
		
	
		return db.retrieveSynopsisDetails(query,String.valueOf(Id));
	}


	
	
	//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	
	@Override
	public boolean viewSynopsis(int taskId, int facultyId) throws SQLException {
		
		System.out.println("Inside ");
		query="select synopsis from synopsis where facultyId=? and submissionId=? ";
		db.downloadFile(query, facultyId, taskId);
		
		synopsis=db.blob;
		return db.status;
		
		
	}


	//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

	@Override
	public boolean updateResult(int submissionId,int facultyid, boolean approvalStatus, String feedback) throws SQLException {
		query="insert into result(submissionId,approval_status,feedback) values(?,?,?)";
		int row=db.insertResultData(query,submissionId, approvalStatus, feedback);
		
			if(row==1) {
				System.out.println("Value inserted in result table");
				
				query="update synopsis set status=\"completed\" where submissionId=? ";
				status=db.updateTables(query,submissionId);
				
				if(status) {
					System.out.println("value from db. status 1:"+status);
					
					System.out.println("status changed in synopsis table");
					query="update facultytracker set completedTasks=completedTasks+1 , pendingTasks=pendingTasks-1 where facultyId=? ";
					status=db.updateTables(query, facultyid);
					System.out.println("value of status 2:"+status);
				}
			
			}
		return status;
	}




	

}
