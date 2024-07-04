package com.university.student.itr;

import java.io.InputStream;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;

public interface ItrStudent {
	public boolean login(int RollNumber, String password) throws SQLException;
	public int uploadSynopsis(InputStream synopsis, int rollNumber) throws SQLException;
	public ArrayList<String> showStatus(int rollNumber) throws SQLException;
}
