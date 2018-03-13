package movies.dbAccess;

import movies.beans.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class MoviesDB {
	Connection conn = null;
	PreparedStatement ps = null;
	Statement stmt = null;
	
	// JDBC driver name and database URL
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	static final String DB_URL = "jdbc:mysql://cse.unl.edu:3306/jpfowler";

	//  Database credentials
	static final String USER = "jpfowler";
	static final String PASS = "SL_k:9";
	
	
	
		
	public MovieBean getMovie(int movieid) {
		String SQL = "SELECT * from Movies where id = '" + movieid + "'";
		MovieBean movie = new MovieBean();
		  
		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(SQL);
			
			while(rs.next()) {
				movie.setMovieID(rs.getInt(1));
				movie.setTitle(rs.getString(2));
				movie.setDescription(rs.getString(3));
				movie.setRating(rs.getString(4));
				movie.setPoster(rs.getString(5));
			}
			
			return movie;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return movie;
		}
	}
	
	public ArrayList<MovieBean> searchMovies (String searchterm) {
		String SQL = "SELECT * FROM Movies WHERE "
				+ "title LIKE '%" + searchterm + "%' OR "
				+ "description LIKE '%" + searchterm + "%'";
		ArrayList<MovieBean> movies = new ArrayList<MovieBean>();
		  
		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(SQL);
			
			while(rs.next()) {
				MovieBean movie = new MovieBean();
				movie.setMovieID(rs.getInt(1));
				movie.setTitle(rs.getString(2));
				movie.setDescription(rs.getString(3));
				movie.setRating(rs.getString(4));
				movie.setPoster(rs.getString(5));
				
				movies.add(movie);
			}
			
			return movies;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return movies;
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
