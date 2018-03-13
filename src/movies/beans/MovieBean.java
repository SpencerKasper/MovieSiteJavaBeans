package movies.beans;

public class MovieBean {
	private int movieID;
	private String title;
	private String description;
	private String rating;
	private String poster;

	/* Constructors */
	public MovieBean() {
		
	}
	/* End Constructors */
	
	/* Getters and Setters */
	public String getPoster() {
		return poster;
	}
	public void setPoster(String poster) {
		this.poster = poster;
	}
	public int getMovieID() {
		return movieID;
	}
	public void setMovieID(int movieID) {
		this.movieID = movieID;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getRating() {
		return rating;
	}
	public void setRating(String rating) {
		this.rating = rating;
	}
	/* End Getters and Setters */
}
