package movies.javaClasses;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import movies.beans.MovieBean;
import movies.beans.MovieShowingsBean;
import movies.beans.OrdersBean;
import movies.beans.ShowroomsBean;
import movies.beans.TheatreBean;
import movies.beans.TicketBean;
import movies.dbAccess.MovieShowingsDB;
import movies.dbAccess.MoviesDB;
import movies.dbAccess.OrdersDB;
import movies.dbAccess.ShowroomsDB;
import movies.dbAccess.TheatresDB;
import movies.dbAccess.TicketsDB;

/**
 * Servlet implementation class CancellationConfirmation
 */
public class CancellationConfirmation extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CancellationConfirmation() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/* Get the orderID and the ticketID from the request */
		int orderId = Integer.parseInt(request.getParameter("OId"),10);
		int ticketId = Integer.parseInt(request.getParameter("TId"),10);
		
		/* Get the order from the DB using the ID */
		OrdersDB ordersdb = new OrdersDB();
		ordersdb.connectMeIn();
		OrdersBean order = ordersdb.getOrder(orderId);
		ordersdb.closeConnection();
		
		/* Get the ticket from the DB using the ID */
		TicketsDB ticketsdb = new TicketsDB();
		ticketsdb.connectMeIn();
		TicketBean ticket = ticketsdb.getTicket(ticketId);
		ticketsdb.closeConnection();
		
		/* Modify the total of the order */
		ordersdb.connectMeIn();
		ordersdb.modifyTotal(orderId, ticket.getPrice() * -1);
		order = ordersdb.getOrder(orderId);
		
		/* If the new total is 0 or less, then the entire order is cancelled */
		if (order.getTotalCost() <= 0.0) {
			ordersdb.modifyStatus(orderId, "Cancelled");
			order = ordersdb.getOrder(orderId);
		}
		ordersdb.closeConnection();
		
		/* Release the seats back to the showing */
		MovieShowingsDB movieShowingsDB = new MovieShowingsDB();
		movieShowingsDB.connectMeIn();
		int currentTickets = movieShowingsDB.getShowing(ticket.getShowing().getMovieShowingID()).getAvailableSeats();
		int newTotalTickets = currentTickets + ticket.getQuantity();
		movieShowingsDB.updateShowingAvailability(ticket.getShowing().getMovieShowingID(), newTotalTickets);
		movieShowingsDB.closeConnection();
		
		/* Remove the ticket from Ticket DB */
		ticketsdb.connectMeIn();
		ticketsdb.deleteTicket(ticketId);
		ticketsdb.closeConnection();
		
		/* Pass the order and ticket to the request and forward */
		request.setAttribute("ticket", ticket);
		request.setAttribute("order", order);
		request.getRequestDispatcher("/CancellationConfirmation.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
