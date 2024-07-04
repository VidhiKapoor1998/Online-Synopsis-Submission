package com.university.faculty.itr;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ItrFaculty {
	public boolean login(int id, String password) throws SQLException;
	public ArrayList<Integer> viewList(int Id) throws SQLException;
	public boolean viewSynopsis(int taskId, int facultyId) throws SQLException;
	public boolean updateResult(int subissionId, int facultyid,boolean approvalStatus,String feedback)throws SQLException;
	
	
	
	
}
