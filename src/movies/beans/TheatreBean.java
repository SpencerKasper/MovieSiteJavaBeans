package movies.beans;

public class TheatreBean {
	private int theatreID;
	private String theatreName;
	private String address;
	
	/* Constructors */
	public TheatreBean() {
		
	}
	/* End Constructors */
	
	/* Getters and Setters */
	public int getTheatreID() {
		return theatreID;
	}
	public void setTheatreID(int theatreID) {
		this.theatreID = theatreID;
	}
	public String getTheatreName() {
		return theatreName;
	}
	public void setTheatreName(String theatreName) {
		this.theatreName = theatreName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	/* End Getters and Setters */
}
