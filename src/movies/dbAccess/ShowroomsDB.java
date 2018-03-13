package movies.dbAccess;

import movies.beans.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class ShowroomsDB {
	Connection conn = null;
	PreparedStatement ps = null;
	Statement stmt = null;
	
	// JDBC driver name and database URL
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	static final String DB_URL = "jdbc:mysql://cse.unl.edu:3306/jpfowler";

	//  Database credentials
	static final String USER = "jpfowler";
	static final String PASS = "SL_k:9";
	
	
	
		
	public ShowroomsBean getShowroom(int id) {
		String SQL = "SELECT * from Showrooms where id = '" + id + "'";
		ShowroomsBean showroom = new ShowroomsBean();
		TheatresDB theatresdb = new TheatresDB();
		theatresdb.connectMeIn();
		  
		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(SQL);
			
			while(rs.next()) {
				TheatreBean theatre = new TheatreBean();
				theatre = theatresdb.getTheatre(rs.getInt(2));
				showroom.setShowroomID(rs.getInt(1));
				showroom.setTheatre(theatre);
				showroom.setShowroomNumber(rs.getInt(3));
			}
			
			theatresdb.closeConnection();
			
			return showroom;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return showroom;
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
