package DAO;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import somepackage.Event;

public class RegistrationDAO {
	DatabaseConnection db = null;
	Connection connection = null;
	Statement statement = null;
	ResultSet result = null;
	
	public RegistrationDAO() {
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

	public boolean registerUser(Event event) {
		try {
			if (isUserExist(event.getUsername())) {
				String sql = "INSERT INTO `registration`(`username`,`password`,`role`)VALUES('"+ event.getUsername() + "','" + event.getPassword() + "','"+ event.getRole() + "')";

				statement.executeUpdate(sql);
				return true;

			} else 
				return false;
				

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
	}
	
	private boolean isUserExist(String username) {
		// TODO Auto-generated method stub
		try {
			result = (ResultSet) statement.executeQuery("select * from users where username ='" + username + "'");
			if (result.next())
				return false;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return true;
	}

}
