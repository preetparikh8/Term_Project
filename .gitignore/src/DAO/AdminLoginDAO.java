package DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class AdminLoginDAO {
	public static Boolean validateUser(String username, String password) {
		// TODO Auto-generated method stub
		try {
			DatabaseConnection db = new DatabaseConnection();
			Connection con = db.createConnection();
			Statement statement = con.createStatement();
			ResultSet rest = statement
					.executeQuery("select * from admin where (username ='" + username + "'&& password='" + password + "')");
			if (rest.next())
				return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return false;
	}

}
