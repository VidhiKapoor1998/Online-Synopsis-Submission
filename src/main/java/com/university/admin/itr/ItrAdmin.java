package com.university.admin.itr;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ItrAdmin {
	
	public boolean login(int id, String password) throws SQLException;
	public ArrayList<Integer> availableFacultyList() throws SQLException;
	public ArrayList<Integer> unallottedSynosisList() throws SQLException;
	public void allotSynopsis(String faculty, int...synopsis) throws SQLException;
	

}
