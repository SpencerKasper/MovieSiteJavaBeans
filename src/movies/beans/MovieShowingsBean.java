package movies.beans;

public class MovieShowingsBean {
	private int movieShowingID;
	private MovieBean movie;
	private ShowroomsBean showroom;
	private int availableSeats;
	private double price;
	private String date;
	private String time;
	
	/* Constructors */
	public MovieShowingsBean() {
		
	}
	/* End Constructors */
	
	/* Getters and Setters */
	public int getMovieShowingID() {
		return movieShowingID;
	}
	public void setMovieShowingID(int movieShowingID) {
		this.movieShowingID = movieShowingID;
	}
	public MovieBean getMovie() {
		return movie;
	}
	public void setMovie(MovieBean movie) {
		this.movie = movie;
	}
	public ShowroomsBean getShowroom() {
		return showroom;
	}

	public void setShowroom(ShowroomsBean showroom) {
		this.showroom = showroom;
	}

	public int getAvailableSeats() {
		return availableSeats;
	}
	public void setAvailableSeats(int availableSeats) {
		this.availableSeats = availableSeats;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	/* End Getters and Setters */
}
