package movies.beans;

public class TicketBean {
	private int ticketID;
	private int orderID;
	private MovieShowingsBean showing;
	private int quantity;
	private double price;

	/* Constructors */
	public TicketBean() {
		
	}
	/* End Constructors */
	
	/* Getters and Setters */
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getTicketID() {
		return ticketID;
	}
	public void setTicketID(int ticketID) {
		this.ticketID = ticketID;
	}
	public int getOrderID() {
		return orderID;
	}
	public void setOrderID(int orderID) {
		this.orderID = orderID;
	}
	public MovieShowingsBean getShowing() {
		return showing;
	}
	public void setShowing(MovieShowingsBean showing) {
		this.showing = showing;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	/* End Getters and Setters */
}
