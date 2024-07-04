package com.university.student.itrImplementation;

import java.io.InputStream;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;

import com.university.dao.DatabaseConnector;
import com.university.student.itr.ItrStudent;

public class StudentImplementation implements ItrStudent {
	
	
	

	public StudentImplementation() {

		System.out.println("Inside student implementation class");
	}
	
	
	
	
	String query;
	DatabaseConnector db=new DatabaseConnector();
	
	
	@Override
	public boolean login(int RollNumber, String password) throws SQLException {
		System.out.println(RollNumber+" "+password);
		query=" select rollNumber from studentdetails where rollNumber=? and password=?";
		
		db.preparedStatement(query,	String.valueOf(RollNumber), password);
		
		return db.status;
		// TODO Auto-generated method stub
		
	}
	
	
	//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

	@Override
	public int uploadSynopsis(InputStream synopsis, int rollNumber) throws SQLException {
		
		System.out.println("inside uploadSynopsis");
		System.out.println( rollNumber);
		query="insert into synopsis(synopsis,rollNumber, Date_Time, status) values(?, ?, curdate(),'Submitted')";
		db.uploadSynopsis(query, synopsis, String.valueOf(rollNumber));
		
		return db.row;
	}

	//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

	
	@Override
	public ArrayList<String> showStatus(int rollNumber) throws SQLException {
		
		query="select status, Date_Time from synopsis where rollNumber=?";
		db.preparedStatement(query,String.valueOf(rollNumber));
		
		return db.data;
		
	}
	
	
	
	
	
	

}
