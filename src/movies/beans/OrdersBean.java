package movies.beans;
import java.util.*;

public class OrdersBean {
	private int orderID;
	private UserBean user;
	private PaymentMethodsBean payment;
	private double totalCost;
	private String status;
	private ArrayList<TicketBean> tickets;
	
	/* Constructors */
	public OrdersBean() {
		
	}
	/* End Constructors */
	
	/* Getters and Setters */
	public ArrayList<TicketBean> getTickets() {
		return tickets;
	}
	public void setTickets(ArrayList<TicketBean> tickets) {
		this.tickets = tickets;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getOrderID() {
		return orderID;
	}
	public void setOrderID(int orderID) {
		this.orderID = orderID;
	}
	public UserBean getUser() {
		return user;
	}
	public void setUser(UserBean user) {
		this.user = user;
	}
	public PaymentMethodsBean getPayment() {
		return payment;
	}
	public void setPayment(PaymentMethodsBean payment) {
		this.payment = payment;
	}
	public double getTotalCost() {
		return totalCost;
	}
	public void setTotalCost(double totalCost) {
		this.totalCost = totalCost;
	}
	/* End Getters and Setters */
}
