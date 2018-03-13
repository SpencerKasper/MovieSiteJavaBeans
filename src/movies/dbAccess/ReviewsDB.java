package movies.dbAccess;

import movies.beans.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import movies.dbAccess.*;

public class ReviewsDB {
	Connection conn = null;
	PreparedStatement ps = null;
	Statement stmt = null;
	
	// JDBC driver name and database URL
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	static final String DB_URL = "jdbc:mysql://cse.unl.edu:3306/jpfowler";

	//  Database credentials
	static final String USER = "jpfowler";
	static final String PASS = "SL_k:9";
	
	
	
	public ArrayList<ReviewBean> getReviews(int movieID) {
		String SQL = "SELECT * from Reviews where movieId = '" + movieID + "'";
		ArrayList<ReviewBean> reviews = new ArrayList<ReviewBean>();
		UsersDB usersdb = new UsersDB();
		usersdb.connectMeIn();
		
		MoviesDB moviesdb = new MoviesDB();
		moviesdb.connectMeIn();
		  
		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(SQL);
			
			while(rs.next()) {
				UserBean user = new UserBean();
				ReviewBean review = new ReviewBean();
				
				user = usersdb.getUserByID(rs.getInt(2));
				review.setReviewID(rs.getInt(1));
				review.setUser(user);
				review.setMovie(moviesdb.getMovie(rs.getInt(3)));
				review.setReview(rs.getString(4));
				review.setRating(rs.getInt(5));
				review.setDate(rs.getString(6));
				
				reviews.add(review);				
			}
			
			moviesdb.closeConnection();
			usersdb.closeConnection();
			
			return reviews;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return reviews;
		}
	}
	
	public boolean addReview(ReviewBean review) {
		String SQL = "INSERT INTO Reviews(userId, movieId, text, rating, date) VALUES ('"
				+ review.getUser().getUserID() + "', '"
				+ review.getMovie().getMovieID() + "', '"
				+ review.getReview() + "', '"
				+ review.getRating() + "', '"
				+ review.getDate() + "')";
				  
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
