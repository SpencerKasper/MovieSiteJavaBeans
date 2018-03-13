package movies.beans;

public class ShowroomsBean {
	private int showroomID;
	private TheatreBean theatre;
	private int showroomNumber;
	
	/* Constructors */
	public ShowroomsBean() {
		
	}
	/* End Constructors */
	
	/* Getters and Setters */
	public int getShowroomID() {
		return showroomID;
	}
	public void setShowroomID(int showroomID) {
		this.showroomID = showroomID;
	}
	public int getShowroomNumber() {
		return showroomNumber;
	}
	public void setShowroomNumber(int showroomNumber) {
		this.showroomNumber = showroomNumber;
	}
	public TheatreBean getTheatre() {
		return theatre;
	}

	public void setTheatre(TheatreBean theatre) {
		this.theatre = theatre;
	}
	/* End Getters and Setters */
}
