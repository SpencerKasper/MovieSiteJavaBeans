package movies.dbAccess;

import movies.beans.*;
import java.util.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TheatresDB {
	Connection conn = null;
	PreparedStatement ps = null;
	Statement stmt = null;
	
	// JDBC driver name and database URL
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	static final String DB_URL = "jdbc:mysql://cse.unl.edu:3306/jpfowler";

	//  Database credentials
	static final String USER = "jpfowler";
	static final String PASS = "SL_k:9";
	
	public TheatreBean getTheatre(String name) {
		String SQL = "SELECT * from Theatres where name = '" + name + "'";
		TheatreBean theatre = new TheatreBean();
		  
		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(SQL);
			
			while(rs.next()) {
				theatre.setTheatreID(rs.getInt(1));
				theatre.setTheatreName(rs.getString(2));
				theatre.setAddress(
						rs.getString(3) + " " +		//street address
						rs.getString(4) + ", " +	//city
						rs.getString(5) + " " +		//state
						rs.getString(6) + " " +		//zip
						rs.getString(7)				//country								
						);
			}
			
			return theatre;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return theatre;
		}
	}
	
	public TheatreBean getTheatre(int id) {
		String SQL = "SELECT * from Theatres where id = '" + id + "'";
		TheatreBean theatre = new TheatreBean();
		  
		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(SQL);
			
			while(rs.next()) {
				theatre.setTheatreID(rs.getInt(1));
				theatre.setTheatreName(rs.getString(2));
				theatre.setAddress(
						rs.getString(3) + " " +		//street address
						rs.getString(4) + ", " +	//city
						rs.getString(5) + " " +		//state
						rs.getString(6) + " " +		//zip
						rs.getString(7)				//country								
						);
			}
			
			return theatre;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return theatre;
		}
	}
	
	public ArrayList<String> getTheatreNames() {
		String SQL = "SELECT name from Theatres";
		ArrayList<String> theatres = new ArrayList<String>();
		  
		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(SQL);
			
			while(rs.next()) {
				theatres.add(rs.getString(1));
			}
			
			return theatres;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return theatres;
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
