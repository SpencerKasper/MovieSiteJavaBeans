package movies.javaClasses;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import movies.beans.*;
import movies.dbAccess.*;

/**
 * Servlet implementation class CustomerTransactionConfirmation
 */
public class CustomerTransactionConfirmation extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public CustomerTransactionConfirmation() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		
		HttpSession sess = request.getSession();
		ArrayList <TicketBean> shoppingCart = (ArrayList <TicketBean>) sess.getAttribute("shoppingCart");
				
		if (shoppingCart == null) {
			shoppingCart = new ArrayList <TicketBean>();
		}
		
		UserBean user = (UserBean)sess.getAttribute("user");
		
		/* Set the payment method bean values */
		PaymentMethodsBean cc = new PaymentMethodsBean();
		cc.setAddress(request.getParameter("address"));
		cc.setCardNumber(request.getParameter("cardNumber"));
		cc.setCity(request.getParameter("city"));
		cc.setFirstName(request.getParameter("firstName"));
		cc.setLastName(request.getParameter("lastName"));
		cc.setSecurityCode(request.getParameter("securityCode"));
		cc.setState(request.getParameter("state"));
		cc.setUserID(user.getUserID());
		cc.setZip(Integer.valueOf(request.getParameter("zip")));
		cc.setMonth(request.getParameter("monthSelect"));
		cc.setYear(request.getParameter("yearSelect"));
		
		/* Add the payment method to the database */
		PaymentMethodsDB paymentdb = new PaymentMethodsDB();
		paymentdb.connectMeIn();
		int paymentID = paymentdb.addPaymentMethod(cc);
		cc.setPaymentID(paymentID);
		paymentdb.closeConnection();
		
		/* Get total cost of shopping cart from the session */
		double totalCost = Double.parseDouble((String)sess.getAttribute("shoppingCartPrice"));
		
		/* Set the order bean with values */
		OrdersBean order = new OrdersBean();
		order.setPayment(cc);
		order.setStatus("PreProcessing");
		order.setTotalCost(totalCost);
		order.setUser(user);
		
		/* Connect to Orders DB and add order.  Status is processing because we have no ID yet. */
		OrdersDB ordersdb = new OrdersDB();
		ordersdb.connectMeIn();
		int orderID = ordersdb.addOrder(order);
		ordersdb.modifyStatus(orderID, "Processing");
		ordersdb.closeConnection();
		
		/* 
		 * Now that we know the orderID, set that for each item in the shoppingCart, and add item to Tickets DB.
		 * Also, update avaliable seats. 
		 */
		MovieShowingsDB movieshowingsdb = new MovieShowingsDB();
		TicketsDB ticketsdb = new TicketsDB();
		
		for(int i = 0; i < shoppingCart.size(); i++) {
			/* Set orderID and add item to database */
			shoppingCart.get(i).setOrderID(orderID);
			ticketsdb.connectMeIn();
			ticketsdb.addTicket(shoppingCart.get(i));
			ticketsdb.closeConnection();
			
			/* Update avaliable tickets in DB */
			movieshowingsdb.connectMeIn();
			int newTicketAmount = shoppingCart.get(i).getShowing().getAvailableSeats() - shoppingCart.get(i).getQuantity();
			movieshowingsdb.updateShowingAvailability(shoppingCart.get(i).getShowing().getMovieShowingID(), newTicketAmount);
			movieshowingsdb.closeConnection();
		}	
		
		/* Save the previous shopping cart on the session and put a blank one in the current shopping cart */
		ArrayList<TicketBean> newShoppingCart = new ArrayList<TicketBean>();
		sess.setAttribute("lastShoppingCart", shoppingCart);
		sess.setAttribute("shoppingCart", newShoppingCart);
		
		/* Set the orderID in the request and redirect */
		request.setAttribute("orderID", orderID);    
		request.getRequestDispatcher("/CustomerTransactionConfirmation.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
