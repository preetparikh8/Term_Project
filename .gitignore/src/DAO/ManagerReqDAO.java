package DAO;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import somepackage.Event;

public class ManagerReqDAO {
	DatabaseConnection db = null;
	Connection connection = null;
	Statement statement = null;
	ResultSet result = null;
	
	public ManagerReqDAO() {
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

	public boolean managerReq(Event event) {
		try {
			 
				String sql = "INSERT INTO `manager`(`username`,`role`,`status`)VALUES('"+ event.getUsername() + "','"+ event.getRole() + "','D')";
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
