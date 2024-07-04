package com.university.admin.itrImplementation;

import com.university.dao.DatabaseConnector;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.university.admin.itr.ItrAdmin;


public class AdminImplementation implements ItrAdmin  {
	public AdminImplementation() {

		System.out.println("Inside admin implementation class");
	}
	String query;
	public ArrayList<Integer> faculty=new ArrayList<>();
	public ArrayList<Integer> synopsis=new ArrayList<>();
	
	
		
	DatabaseConnector db=new DatabaseConnector();
		
	@Override
	public boolean login(int id, String password) throws SQLException {
		System.out.println(id+" "+password);
		query=" select id from admin details where id=? and password=?";
		
		db.preparedStatement(query,	String.valueOf(id), password);
		
		return db.status;
		// TODO Auto-generated method stub
		
	}


	@Override
	public ArrayList<Integer> availableFacultyList() throws SQLException{
		
		
		db.result.clear();
		String facultyIdQuery="select facultyId from facultyTracker where pendingTasks<=2";
		
		db.preparedStatement(facultyIdQuery, null);
		//faculty=db.result;
		System.out.println("Faculty available: "+db.result);
		
			
			
		
		//System.out.println("Faculty available: "+faculty);
		return db.result;
	
	}
	
	

	@Override
	public ArrayList<Integer> unallottedSynosisList() throws SQLException {
		
		db.result.clear();
		String synopsisIdQuery="select submissionId from synopsis where facultyId is null";
		
		db.preparedStatement(synopsisIdQuery, null);
		//synopsis=db.result;
		System.out.println("Unallotted synosis: "+db.result);
		return db.result;
		
	}
	



	@Override
	public void allotSynopsis(String faculty, int...i )  throws SQLException{
		
		System.out.println("inside allot synopsis method-------------------------");
		StringBuffer value=new StringBuffer();
		ArrayList<Integer> where=new ArrayList<Integer>();
		int count=0;
		System.out.println("lenghth of i"+where.size());
		
		
		for(int j=0;j<i.length;j++) {
			where.add(i[j]);
			count++;
			System.out.println("count: "+count);
			
			if(j==i.length-1) {
				value.append("?");
			}
			else {
				value.append("?,");
			}
			
			
			
		}
		System.out.println("? count:"+value);
		System.out.println("******************final value of count:"+count);
		
		
		String status="Under faculty";

		
		query = "update synopsis set facultyId=? ,status=? where submissionId in("+value+");";
		
		System.out.println(query);
		
		db.updateSynopsisData(query, faculty, status, where);
		
		
		
		//------------------Update faculty data-------------------------------
		if(db.status)
		{
			System.out.println("sysnopsis table updated");
			db.status=false;
		}
		
		query="update facultytracker set pendingtasks=pendingtasks+"+count+" where facultyid=?";

				
		System.out.println("Value of count again"+count);
		db.updateTables(query ,Integer.parseInt(faculty));
		if(db.status)
		{
			System.out.println("facultyTracker table updated");
		}
	}
}



