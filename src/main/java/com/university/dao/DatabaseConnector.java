package com.university.dao;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.cj.jdbc.Blob;

public class DatabaseConnector {

	Connection connection;
	PreparedStatement ps;
	ResultSet rs;
	ResultSetMetaData rsm;
	public int row=0;
	public boolean status=false;
	public ArrayList<Integer> result=new ArrayList<>();
	public Blob blob;
	public ArrayList<String> data=new ArrayList<String>();
	
	//Constructor
	public DatabaseConnector(){
		System.out.println("Inside database connector class...");
		try {
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("Driver manager loaded...");
			
			connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/university","root","Vidhi@1998");
			System.out.println("Connection created");
			
		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
	
	//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	
	//verify data
	public boolean preparedStatement(String query, String...strings) throws SQLException
	{
		System.out.println("Inside preparedStatement method...");
		int i=1;
		Throwable e;
		try {
			ps=connection.prepareStatement(query);
			System.out.println("Query alloted...");
		
			if(strings!=null)
			{
				for(String value:strings)
				{
	
					System.out.println(i+" "+value);
					ps.setString(i, value);
					
					i++;
				}
			}
			
			rs=ps.executeQuery();
			System.out.println("Query executed...");
			
			rsm=rs.getMetaData();
			int columnCount=rsm.getColumnCount();
			System.out.println("columnCount: "+columnCount);
			
			
			
			while(rs.next())
			{
				System.out.println("Inside while loop");
				for (int j=1;j<=columnCount;j++)
				{
					
					System.out.println("Inside for loop");
					
					String value=rs.getString(j);
					System.out.println(j+". "+value);
					data.add(String.valueOf(value));
//					if(j==1) {
//					result.add(Integer.parseInt(value));}
//					
					
					System.out.println(rsm.getColumnName(j)+": "+value);
					status=true;
			}
				
			}
				
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		
		
		System.out.println("end of prepared statement method...");
		return status;
		
	}
	
	
	
	//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	
	
	//upload synopsis
	public int uploadSynopsis(String query,InputStream file, String...strings) throws SQLException
	{
		System.out.println("inside dao method...");
		int i=2;
		
		try {
			ps=connection.prepareStatement(query);
			System.out.println("query alloted");
			ps.setBlob(1, file);
			System.out.println("File alloted");
			for(String s:strings)
			{
				System.out.println("Strings:"+ s);
				ps.setString(i, s);
				i++;
			}
			
			
			
			System.out.println("data alloted");
			row=ps.executeUpdate();
			System.out.println("query executed");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			
		}
		
		
		System.out.println(row);
		return row;
	}
	
	
	
	
	//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	
	
	
	public ArrayList<Integer> retrieveSynopsisDetails( String query, String id) throws SQLException
	{
		
		
		ArrayList<Integer> result=new ArrayList<>();
		System.out.println("Inside retrieveSynopsisDetails() method...");
		
		
		try {
			System.out.println("Inside try method...");
			ps=connection.prepareStatement(query);
			System.out.println("Query alloted...");
			
			
			
			
				System.out.println(id);
				ps.setString(1, id);
			
			
			rs=ps.executeQuery();
			System.out.println("Query executed...");
			
			rsm=rs.getMetaData();
			
			
			
			
			
			while(rs.next())
			{
				
					System.out.println();
					result.add(rs.getInt(1));
					System.out.println(result);
					//j++;
				
			}
				
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
					}
		
		return result;
		
		
	}
	
	
	
	//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	
	//changing status after allotting synopsis to faulty
	public boolean updateSynopsisData(String query,String faculty, String status ,ArrayList<Integer> where) {
		try {
			
			System.out.println("Inside upadteSynopsisData()--------------------------");
			int j=3;
			ps=connection.prepareStatement(query);
			System.out.println("query alloted");
			
			
			ps.setString(1, faculty);
			ps.setString(2, status);
			for(Integer i:where) {
				ps.setInt(j, i);
				System.out.println("list element"+i);
				j++;
			}
			

			System.out.println("query is about to execute...");
			this.status=ps.execute();
			System.out.println("query executed...");
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return this.status;
	}

	
	//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	
	public boolean updatefacultyData(String query,String faculty, int count) throws SQLException {
		try {
			
			
			
			ps=connection.prepareStatement(query);
			
			ps.setInt(1, count);
			ps.setString(2, faculty);
			
			
			this.status=ps.execute();
			
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return this.status;
	}

	//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	
	public boolean downloadFile(String query, int facultyId, int taskId) throws SQLException
	{
		try {
			
			status=false;
			ps=connection.prepareStatement(query);
			System.out.println("query:"+query);
			System.out.println("facultyId: "+facultyId+" taskId: "+taskId);
			ps.setInt(1, facultyId);
			ps.setInt(2, taskId);
			
			rs=ps.executeQuery();
			
			if(rs.next())
			{
				blob=(Blob) rs.getBlob("synopsis");
				status=true;
			}
			
			else {
				System.out.println("File not found");
			}
			
		} catch (SQLException e) {
	
			
			e.printStackTrace();
			
		}finally {
			
		}
		
		return status;
		
	}

	//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	
	
	public int insertResultData(String query,int submissionId, boolean approvalStatus, String feedback) {
		
		System.out.println("Inside updateData method");
		
		try {
			ps=connection.prepareStatement(query);
			ps.setInt(1, submissionId);
			ps.setBoolean(2, approvalStatus);
			ps.setString(3, feedback);
			
			row=ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Value of row:"+row);
		
		return row;
	}
	
	
	public boolean updateTables(String query,int id) {
			status=false;
			
		try {
			
			System.out.println("inside updateTables...");
			ps=connection.prepareStatement(query);
			System.out.println("query allotted");
			ps.setInt(1, id);
		
			
			ps.execute();
			status=true;
			System.out.println("query executed");
			System.out.println("value of db status"+ status);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return status;
	}
}



