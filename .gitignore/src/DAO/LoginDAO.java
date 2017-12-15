package DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class LoginDAO {
	public static Boolean validateUser(String username, String password, String role) {
		// TODO Auto-generated method stub
		try {
			DatabaseConnection db = new DatabaseConnection();
			Connection con = db.createConnection();
			Statement statement = con.createStatement();
			ResultSet rest = statement
					.executeQuery("select * from registration where (username ='" + username + "'&& password='" + password + "'&& role='" + role + "')");
			if (rest.next())
				return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return false;
	}

}
