package movies.dbAccess;

import movies.beans.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class TicketsDB {
	Connection conn = null;
	PreparedStatement ps = null;
	Statement stmt = null;
	
	// JDBC driver name and database URL
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	static final String DB_URL = "jdbc:mysql://cse.unl.edu:3306/jpfowler";

	//  Database credentials
	static final String USER = "jpfowler";
	static final String PASS = "SL_k:9";
	
	
	
	public ArrayList<TicketBean> getTicketsByOrder(int orderID) {
		String SQL = "SELECT * FROM Tickets WHERE orderId = '" + orderID + "'";
		ArrayList<TicketBean> tickets = new ArrayList<TicketBean>();
		  
		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(SQL);
			
			MovieShowingsDB movieshowingsdb = new MovieShowingsDB();
			movieshowingsdb.connectMeIn();
			
			while(rs.next()) {
				TicketBean ticket = new TicketBean();
				ticket.setTicketID(rs.getInt(1));
				ticket.setOrderID(rs.getInt(2));
				ticket.setShowing(movieshowingsdb.getShowing(rs.getInt(3)));
				ticket.setQuantity(rs.getInt(4));
				
				tickets.add(ticket);
			}

			movieshowingsdb.closeConnection();
			
			return tickets;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return tickets;
		}
	}
	
	
	
	public TicketBean getTicket(int id) {
		String SQL = "SELECT * FROM Tickets WHERE id = '" + id + "'";
		TicketBean ticket = new TicketBean();
		  
		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(SQL);
			
			MovieShowingsDB movieshowingsdb = new MovieShowingsDB();
			movieshowingsdb.connectMeIn();
						
			while(rs.next()) {
				ticket.setTicketID(rs.getInt(1));
				ticket.setOrderID(rs.getInt(2));
				ticket.setShowing(movieshowingsdb.getShowing(rs.getInt(3)));
				ticket.setQuantity(rs.getInt(4));
				ticket.setPrice(rs.getDouble(5));
			}

			movieshowingsdb.closeConnection();
			
			return ticket;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return ticket;
		}
	}
	
	
	
	public int addTicket(TicketBean ticket) {
		String SQL = "INSERT INTO Tickets(orderId, showingId, quantity, Price) VALUES ('"
				+ ticket.getOrderID() + "', '"
				+ ticket.getShowing().getMovieShowingID() + "', '"
				+ ticket.getQuantity() + "', '"
				+ ticket.getPrice() + "')";
				  
		try {
			stmt = conn.createStatement();
			stmt.executeUpdate(SQL);
			
			
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
		
		SQL = "SELECT * FROM Tickets WHERE orderId = '" + ticket.getOrderID() + "' AND showingId = '" + ticket.getShowing().getMovieShowingID() + "'";
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
	
	public boolean modifyOrder(int id, int orderId) {
		String SQL = "UPDATE Tickets SET orderId='" + orderId + "' WHERE id='" + id + "'";
		
		try {
			stmt = conn.createStatement();
			stmt.executeUpdate(SQL);
			
			return true;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		
	}
	
	public boolean deleteTicket(int id) {
		String SQL = "DELETE FROM Tickets WHERE id='" + id + "'";
		
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
