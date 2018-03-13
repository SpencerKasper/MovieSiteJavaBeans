package movies.dbAccess;

import movies.beans.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UsersDB {
	Connection conn = null;
	PreparedStatement ps = null;
	Statement stmt = null;
	
	// JDBC driver name and database URL
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	static final String DB_URL = "jdbc:mysql://cse.unl.edu:3306/jpfowler";

	//  Database credentials
	static final String USER = "jpfowler";
	static final String PASS = "SL_k:9";
	
	public boolean validateUsername(String login) {
		String SQL = "SELECT * from Users";
	  
		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(SQL);
			
			boolean valid = false;
			
			while(rs.next()) {
				if(login.equals(rs.getString(7))) {
					valid = true;
					return valid;
				}
			}
			
			return valid;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean validatePassword(String password) {
		String SQL = "SELECT * from Users";
		  
		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(SQL);
			
			boolean valid = false;
			
			while(rs.next()) {
				if(password.equals(rs.getString(8))) {
					valid = true;
					return valid;
				}
			}
			
			return valid;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public UserBean getUser(String username) {
		String SQL = "SELECT * from Users where username = '" + username + "'";
		UserBean user = new UserBean();
		  
		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(SQL);
			
			while(rs.next()) {
				user.setAddress(rs.getString(5));
				user.setCity(rs.getString(6));
				user.setCountry(rs.getString(11));
				user.setFirstName(rs.getString(2));
				user.setLastName(rs.getString(3));
				user.setPhoneNumber(rs.getString(4));
				user.setState(rs.getString(7));
				user.setUsername(rs.getString(8));
				user.setUserID(rs.getInt(1));
				user.setZip(rs.getString(12));
			}
			
			return user;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return user;
		}
	}
	
	
	public UserBean getUserByID(int id) {
		String SQL = "SELECT * from Users where id = '" + id + "'";
		UserBean user = new UserBean();
		  
		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(SQL);
			
			while(rs.next()) {
				user.setAddress(rs.getString(5));
				user.setCity(rs.getString(6));
				user.setCountry(rs.getString(11));
				user.setFirstName(rs.getString(2));
				user.setLastName(rs.getString(3));
				user.setPhoneNumber(rs.getString(4));
				user.setState(rs.getString(7));
				user.setUsername(rs.getString(8));
				user.setUserID(rs.getInt(1));
				user.setZip(rs.getString(12));
			}
			
			return user;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return user;
		}
	}
	
	public boolean addUser(UserBean user) {
		String SQL = "INSERT INTO Users(username, password, firstName, lastName, address, city, state, zip, country, phone) VALUES ('"
				+ user.getUsername() + "', '" + user.getPassword() + "', '" + user.getFirstName() + "', '"
				+ user.getLastName() + "', '" + user.getAddress() + "', '"
				+ user.getCity() + "', '" + user.getState() + "', '"
				+ user.getZip() + "', '" + user.getCountry() + "', '"
				+ user.getPhoneNumber() + "')";
				  
		try {
			stmt = conn.createStatement();
			stmt.executeUpdate(SQL);
			
			return true;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public void connectMeIn() {
		try{
			//Register the JDBC driver
			Class.forName("com.mysql.jdbc.Driver");			
		}catch(ClassNotFoundException e){
			System.err.println(e);
			System.exit (-1);
		}
		try {
			 //Open a connection
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
		} catch (SQLException e){
			e.printStackTrace();
		}
	}
	
	
	public void closeConnection(){
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
