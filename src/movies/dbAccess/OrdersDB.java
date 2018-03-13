package movies.dbAccess;

import movies.beans.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class OrdersDB {
	Connection conn = null;
	PreparedStatement ps = null;
	Statement stmt = null;
	
	// JDBC driver name and database URL
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	static final String DB_URL = "jdbc:mysql://cse.unl.edu:3306/jpfowler";

	//  Database credentials
	static final String USER = "jpfowler";
	static final String PASS = "SL_k:9";
	
	
	
	public ArrayList<OrdersBean> getOrdersByUser(int userID) {
		String SQL = "SELECT * FROM Orders WHERE userId = '" + userID + "'";
		ArrayList<OrdersBean> orders = new ArrayList<OrdersBean>();
		  
		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(SQL);
			UsersDB usersdb = new UsersDB();
			PaymentMethodsDB paymentmethodsdb = new PaymentMethodsDB();
			
			while(rs.next()) {
				OrdersBean order = new OrdersBean();
				order.setOrderID(rs.getInt(1));
				
				usersdb.connectMeIn();
				order.setUser(usersdb.getUserByID(rs.getInt(2)));
				usersdb.closeConnection();
				
				paymentmethodsdb.connectMeIn();
				order.setPayment(paymentmethodsdb.getPaymentMethods(rs.getInt(3)));
				paymentmethodsdb.closeConnection();
				
				order.setTotalCost(rs.getDouble(4));
				order.setStatus(rs.getString(5));
				
				orders.add(order);
			}
			
			return orders;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return orders;
		}
	}
	
	
	
	public OrdersBean getOrder(int id) {
		String SQL = "SELECT * FROM Orders WHERE id = '" + id + "'";
		OrdersBean order = new OrdersBean();
		
		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(SQL);
			
			UsersDB usersdb = new UsersDB();
			PaymentMethodsDB paymentmethodsdb = new PaymentMethodsDB();
			TicketsDB ticketsdb = new TicketsDB();
					
			while(rs.next()) {
				order.setOrderID(rs.getInt(1));
				
				/* Get the associated user */
				usersdb.connectMeIn();
				order.setUser(usersdb.getUserByID(rs.getInt(2)));
				usersdb.closeConnection();
				
				/* Get the associated payment method */
				paymentmethodsdb.connectMeIn();
				order.setPayment(paymentmethodsdb.getPaymentMethods(rs.getInt(3)));
				paymentmethodsdb.closeConnection();
				
				/* Get the associated tickets */
				ticketsdb.connectMeIn();
				order.setTickets(ticketsdb.getTicketsByOrder(id));
				ticketsdb.closeConnection();
				
				/* Get the rest of the attributes */
				order.setTotalCost(rs.getDouble(4));
				order.setStatus(rs.getString(5));
			}
			
			return order;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return order;
		}
	}
	
	
	
	public int addOrder(OrdersBean order) {
		String SQL = "INSERT INTO Orders(userId, paymentId, total, status) VALUES ('"
				+ order.getUser().getUserID() + "', '"
				+ order.getPayment().getPaymentID() + "', '"
				+ order.getTotalCost() + "', '"
				+ order.getStatus() + "')";
				  
		try {
			stmt = conn.createStatement();
			stmt.executeUpdate(SQL);
			
			
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
		
		SQL = "SELECT * FROM Orders WHERE userId = '" + order.getUser().getUserID() + "' AND total = '" + order.getTotalCost() + "' AND status = 'PreProcessing'";
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
	
	
	public boolean deleteOrder(int id) {
		String SQL = "DELETE FROM Tickets WHERE orderId='" + id + "'";
		
		try {
			stmt = conn.createStatement();
			stmt.executeQuery(SQL);
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		
		SQL = "DELETE FROM Orders WHERE id = '" + id + "'";
		
		try {
			stmt = conn.createStatement();
			stmt.executeQuery(SQL);
			
			return true;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		

	}
	
	public boolean modifyTotal(int id, double cost) {
		OrdersBean order = getOrder(id);
		cost = order.getTotalCost() + cost;
		String SQL = "UPDATE Orders SET total='" + cost + "' WHERE id='" + id + "'";
		
		try {
			stmt = conn.createStatement();
			stmt.executeUpdate(SQL);
			
			return true;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		
	}
	
	public boolean modifyStatus(int id, String status) {
		String SQL = "UPDATE Orders SET status='" + status + "' WHERE id='" + id + "'";
		
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
