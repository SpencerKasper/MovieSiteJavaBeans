package movies.dbAccess;

import movies.beans.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class MovieShowingsDB {
	Connection conn = null;
	PreparedStatement ps = null;
	Statement stmt = null;
	
	// JDBC driver name and database URL
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	static final String DB_URL = "jdbc:mysql://cse.unl.edu:3306/jpfowler";

	//  Database credentials
	static final String USER = "jpfowler";
	static final String PASS = "SL_k:9";
	
	public ArrayList<MovieShowingsBean> getShowingByShowingID(int showingID) {
		String SQL = "SELECT * from MovieShowings where id = '" + showingID + "'";
		MovieShowingsBean movieshowing = new MovieShowingsBean();
		ArrayList<MovieShowingsBean> movieshowings = new ArrayList<MovieShowingsBean>();
		MoviesDB moviesdb = new MoviesDB();
		moviesdb.connectMeIn();
		
		ShowroomsDB showroomsdb = new ShowroomsDB();
		showroomsdb.connectMeIn();
	
		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(SQL);
			
			while(rs.next()) {
				MovieBean movie = new MovieBean();
				movie = moviesdb.getMovie(Integer.parseInt(rs.getString(2)));
				
				ShowroomsBean showroom = new ShowroomsBean();
				showroom = showroomsdb.getShowroom(Integer.parseInt(rs.getString(3)));
				
				movieshowing.setMovieShowingID(rs.getInt(1));
				movieshowing.setMovie(movie);
				movieshowing.setShowroom(showroom);
				movieshowing.setAvailableSeats(Integer.parseInt(rs.getString(4)));
				movieshowing.setPrice(Double.parseDouble(rs.getString(5)));
				movieshowing.setDate(rs.getString(6));
				movieshowing.setTime(rs.getString(7));
				
				movieshowings.add(movieshowing);
			}
			
			moviesdb.closeConnection();
			showroomsdb.closeConnection();
			
			return movieshowings;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return movieshowings;
		}
	}
	
	public ArrayList<MovieShowingsBean> getShowings(String date){
		String SQL = "SELECT * FROM MovieShowings WHERE date = '" + date + "'";
		ArrayList<MovieShowingsBean> showings = new ArrayList<MovieShowingsBean>();
		
		MoviesDB moviesdb = new MoviesDB();
		moviesdb.connectMeIn();
		
		ShowroomsDB showroomsdb = new ShowroomsDB();
		showroomsdb.connectMeIn();
		  
		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(SQL);
			
			while(rs.next()) {
				MovieBean movie = new MovieBean();
				ShowroomsBean showroom = new ShowroomsBean();
				
				movie = moviesdb.getMovie(rs.getInt(2));
				showroom = showroomsdb.getShowroom(rs.getInt(3));
				MovieShowingsBean showing = new MovieShowingsBean();
				showing.setMovieShowingID(rs.getInt(1));
				showing.setMovie(movie);
				showing.setShowroom(showroom);
				showing.setAvailableSeats(rs.getInt(4));
				showing.setPrice(rs.getDouble(5));
				showing.setDate(rs.getString(6));
				showing.setTime(rs.getString(7));
				
				showings.add(showing);
			}
			
			moviesdb.closeConnection();
			showroomsdb.closeConnection();
			
			return showings;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return showings;
		}
	}
	
	public ArrayList<MovieShowingsBean> getAllShowings(){
		String SQL = "SELECT * FROM MovieShowings";
		ArrayList<MovieShowingsBean> showings = new ArrayList<MovieShowingsBean>();
		
		MoviesDB moviesdb = new MoviesDB();
		moviesdb.connectMeIn();
		
		ShowroomsDB showroomsdb = new ShowroomsDB();
		showroomsdb.connectMeIn();
		  
		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(SQL);
			
			while(rs.next()) {
				MovieBean movie = new MovieBean();
				ShowroomsBean showroom = new ShowroomsBean();
				
				movie = moviesdb.getMovie(rs.getInt(2));
				showroom = showroomsdb.getShowroom(rs.getInt(3));
				
				MovieShowingsBean showing = new MovieShowingsBean();
				showing.setMovieShowingID(rs.getInt(1));
				showing.setMovie(movie);
				showing.setShowroom(showroom);
				showing.setAvailableSeats(rs.getInt(4));
				showing.setPrice(rs.getDouble(5));
				showing.setDate(rs.getString(6));
				showing.setTime(rs.getString(7));
				
				showings.add(showing);
			}
			
			moviesdb.closeConnection();
			showroomsdb.closeConnection();
			
			return showings;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return showings;
		}
	}
	
	public ArrayList<MovieShowingsBean> getShowings(MovieBean movie, String date, TheatreBean theatre) {
		int movieID = movie.getMovieID();
		int theatreID = theatre.getTheatreID();
		
		MoviesDB moviesdb = new MoviesDB();		
		ShowroomsDB showroomsdb = new ShowroomsDB();
		
		//String SQL = "SELECT * FROM MovieShowings WHERE movieId = '" + movieID + "' AND date = '" + date + "' AND showroomID";
		ArrayList<MovieShowingsBean> showings = new ArrayList<MovieShowingsBean>();
		  String SQL = "";
		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(SQL);
			
			MovieShowingsBean showing = new MovieShowingsBean();
			
			while(rs.next()) {
				MovieBean moviebean = new MovieBean();
				ShowroomsBean showroom = new ShowroomsBean();
				
				moviesdb.connectMeIn();
				moviebean = moviesdb.getMovie(rs.getInt(2));
				moviesdb.closeConnection();
				
				showroomsdb.connectMeIn();
				showroom = showroomsdb.getShowroom(rs.getInt(3));
				showroomsdb.closeConnection();
				
				showing.setMovieShowingID(rs.getInt(1));
				showing.setMovie(moviebean);
				showing.setShowroom(showroom);
				showing.setAvailableSeats(rs.getInt(4));
				showing.setPrice(rs.getDouble(5));
				showing.setDate(rs.getString(6));
				showing.setTime(rs.getString(7));
				
				showings.add(showing);
			}
			
			return showings;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return showings;
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
	
	public ArrayList<MovieShowingsBean> getShowingsBySearchQuery(String date, String theatre, String searchTerm){
		
		if(searchTerm == null || searchTerm == "") {
			ArrayList<MovieShowingsBean> queryResult = new ArrayList<MovieShowingsBean>();
			
			ArrayList<MovieShowingsBean> showings = new ArrayList<MovieShowingsBean>();
			this.connectMeIn();
			showings = this.getShowings(date);
			this.closeConnection();
			
			TheatreBean theatrebean = new TheatreBean();
			TheatresDB theatresdb = new TheatresDB();
			theatresdb.connectMeIn();
			theatrebean = theatresdb.getTheatre(theatre);
			theatresdb.closeConnection();
			
			ShowroomsDB showroomdb = new ShowroomsDB();
			
			for(int i = 0; i < showings.size(); i++) {
				ShowroomsBean showroom = new ShowroomsBean();
				
				showroomdb.connectMeIn();
				showroom = showroomdb.getShowroom(showings.get(i).getShowroom().getShowroomID());
				showroomdb.closeConnection();
				
				if(showroom.getTheatre().getTheatreID() == theatrebean.getTheatreID()) {
					queryResult.add(showings.get(i));
				}
			}
			
			return queryResult;
		}
		
		else {
			ArrayList<MovieShowingsBean> queryResult = new ArrayList<MovieShowingsBean>();
			ArrayList<MovieShowingsBean> temp = new ArrayList<MovieShowingsBean>();
			
			MoviesDB moviesDB = new MoviesDB();		
			moviesDB.connectMeIn();
			ArrayList<MovieBean> movies = new ArrayList<MovieBean>();
			movies = moviesDB.searchMovies(searchTerm);
			moviesDB.closeConnection();
			
			/* Get all showings under specific date */
			ArrayList<MovieShowingsBean> showings = new ArrayList<MovieShowingsBean>();
			this.connectMeIn();
			showings = this.getShowings(date);
			this.closeConnection();
			
			/* Get theatre by chosen theatre nane */
			TheatreBean theatrebean = new TheatreBean();
			TheatresDB theatresdb = new TheatresDB();
			theatresdb.connectMeIn();
			theatrebean = theatresdb.getTheatre(theatre);
			theatresdb.closeConnection();
			
			ShowroomsDB showroomdb = new ShowroomsDB();
			
			/* Filter list of showings(found by date) by theatre */
			for(int i = 0; i < showings.size(); i++) {
				ShowroomsBean showroom = new ShowroomsBean();
				
				showroomdb.connectMeIn();
				showroom = showroomdb.getShowroom(showings.get(i).getShowroom().getShowroomID());
				showroomdb.closeConnection();
				
				if(showroom.getTheatre().getTheatreID() == theatrebean.getTheatreID()) {
					temp.add(showings.get(i));
				}
			}
			
			/* Filter list of showings(found by date and theatre) by movie */
			for(int i = 0; i < temp.size(); i++) {
				for(int j = 0; j < movies.size(); j++) {
					if(showings.get(i).getMovie().getMovieID() == movies.get(j).getMovieID()) {
						queryResult.add(showings.get(i));
					}
				}
			}
			
			return queryResult;
		}
	}
	
	public boolean updateShowingAvailability(int showingID, int numberOfTickets) {			
			String SQL = "UPDATE MovieShowings SET availableSeats='" + numberOfTickets + "' WHERE id='" + showingID + "'";
			
			try {
				stmt = conn.createStatement();
				stmt.executeUpdate(SQL);
				return true;
				
			} catch (SQLException e) {
				e.printStackTrace();
				return false;
			}
	}
	
	public MovieShowingsBean getShowing(int id){
		String SQL = "SELECT * FROM MovieShowings WHERE id = '" + id + "'";
		MovieShowingsBean showing = new MovieShowingsBean();
		  
		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(SQL);
			
			MoviesDB moviesdb = new MoviesDB();
			ShowroomsDB showroomsdb = new ShowroomsDB();

			while(rs.next()) {
				MovieBean moviebean = new MovieBean();
				ShowroomsBean showroom = new ShowroomsBean();
				
				moviesdb.connectMeIn();
				moviebean = moviesdb.getMovie(rs.getInt(2));
				moviesdb.closeConnection();
				
				showroomsdb.connectMeIn();
				showroom = showroomsdb.getShowroom(rs.getInt(3));
				showroomsdb.closeConnection();
				
				showing.setMovieShowingID(rs.getInt(1));
				showing.setMovie(moviebean);
				showing.setShowroom(showroom);
				showing.setAvailableSeats(rs.getInt(4));
				showing.setPrice(rs.getDouble(5));
				showing.setDate(rs.getString(6));
				showing.setTime(rs.getString(7));
			}
			
			return showing;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return showing;
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
