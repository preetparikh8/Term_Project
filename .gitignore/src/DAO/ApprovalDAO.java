package DAO;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import somepackage.Event;

public class ApprovalDAO {
	DatabaseConnection db = null;
	Connection connection = null;
	Statement statement = null;
	ResultSet result = null;
	
	public ApprovalDAO() {
		// TODO Auto-generated constructor stub
		try {
			db = new DatabaseConnection();
			connection = db.createConnection();
			statement = connection.createStatement();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	public boolean appr(Event event) {
		try {
			 
				String sql = "UPDATE `manager` set status = 'A' where username = '"+event.getUsername()+"' ";
				statement.executeUpdate(sql);
				return true;
		} 
		
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
	}
	
}
