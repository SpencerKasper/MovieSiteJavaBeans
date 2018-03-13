package movies.dbAccess;

import movies.beans.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class PaymentMethodsDB {
	Connection conn = null;
	PreparedStatement ps = null;
	Statement stmt = null;
	
	// JDBC driver name and database URL
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	static final String DB_URL = "jdbc:mysql://cse.unl.edu:3306/jpfowler";

	//  Database credentials
	static final String USER = "jpfowler";
	static final String PASS = "SL_k:9";
	
	
	
	public ArrayList<PaymentMethodsBean> getPaymentMethodsByUser(UserBean user) {
		int userID = user.getUserID();
		String SQL = "SELECT * FROM PaymentMethods WHERE userId = '" + userID + "'";
		ArrayList<PaymentMethodsBean> ccs = new ArrayList<PaymentMethodsBean>();
		  
		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(SQL);
			
			PaymentMethodsBean cc = new PaymentMethodsBean();
			
			while(rs.next()) {
				cc.setPaymentID(rs.getInt(1));
				cc.setFirstName(rs.getString(2));
				cc.setLastName(rs.getString(3));
				cc.setCardNumber(rs.getString(4));
				cc.setSecurityCode(rs.getString(5));
				cc.setAddress(rs.getString(6));
				cc.setState(rs.getString(7));
				cc.setCity(rs.getString(8));
				cc.setZip(rs.getInt(9));
				cc.setUserID(rs.getInt(10));
				
				ccs.add(cc);
			}
			
			return ccs;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return ccs;
		}
	}
	
	
	
	public PaymentMethodsBean getPaymentMethods(int id) {
		String SQL = "SELECT * FROM PaymentMethods WHERE id = '" + id + "'";
		PaymentMethodsBean cc = new PaymentMethodsBean();
		  
		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(SQL);
						
			while(rs.next()) {
				cc.setPaymentID(rs.getInt(1));
				cc.setFirstName(rs.getString(2));
				cc.setLastName(rs.getString(3));
				cc.setCardNumber(rs.getString(4));
				cc.setSecurityCode(rs.getString(5));
				cc.setAddress(rs.getString(6));
				cc.setState(rs.getString(7));
				cc.setCity(rs.getString(8));
				cc.setZip(rs.getInt(9));
				cc.setUserID(rs.getInt(10));
				
			}
			
			return cc;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return cc;
		}
	}
	
	
	public int addPaymentMethod(PaymentMethodsBean cc) {
		
		
		String SQL = "INSERT INTO PaymentMethods(firstName, lastName, number, securityCode, address, state, city, zip, userId) VALUES ('"
				+ cc.getFirstName() + "', '"
				+ cc.getLastName() + "', '"
				+ cc.getCardNumber() + "', '"
				+ cc.getSecurityCode() + "', '"
				+ cc.getAddress() + "', '"
				+ cc.getState() + "', '"
				+ cc.getCity() + "', '"
				+ cc.getZip() + "', '"
				+ cc.getUserID() + "')";
				  
		try {
			stmt = conn.createStatement();
			stmt.executeUpdate(SQL);
			
			
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
		
		SQL = "SELECT * FROM PaymentMethods WHERE userId = '" + cc.getUserID() + "' AND number = '" + cc.getCardNumber() + "'";
		int id = 0;		  
		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(SQL);
						
			while(rs.next()) {
				id = rs.getInt(1);
								
			}
			
			return id;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
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
