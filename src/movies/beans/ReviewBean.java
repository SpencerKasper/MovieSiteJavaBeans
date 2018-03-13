package movies.beans;

public class ReviewBean {
	private int reviewID;
	private String review;
	private int rating;
	private MovieBean movie;
	private UserBean user;
	private String date;

	/* Constructors */
	public ReviewBean() {
		
	}
	/* End Constructors */
	
	/* Getters and Setters */
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public UserBean getUser() {
		return user;
	}
	public void setUser(UserBean user) {
		this.user = user;
	}
	public int getReviewID() {
		return reviewID;
	}
	public void setReviewID(int reviewID) {
		this.reviewID = reviewID;
	}
	public String getReview() {
		return review;
	}
	public void setReview(String review) {
		this.review = review;
	}
	public int getRating() {
		return rating;
	}
	public void setRating(int rating) {
		this.rating = rating;
	}
	public MovieBean getMovie() {
		return movie;
	}
	public void setMovie(MovieBean movie) {
		this.movie = movie;
	}
	/* End Getters and Setters */	
}
