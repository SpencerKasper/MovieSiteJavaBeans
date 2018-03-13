package movies.javaClasses;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import movies.beans.*;
import movies.dbAccess.*;

/**
 * Servlet implementation class CancelOrder
 */
public class CancelOrder extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public CancelOrder() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/* Get the order and ticket IDs */
		int orderId = Integer.parseInt(request.getParameter("OId"),10);
		int ticketId = Integer.parseInt(request.getParameter("TId"),10);
		
		/* Get the order using the orderID */
		OrdersDB ordersdb = new OrdersDB();
		ordersdb.connectMeIn();
		OrdersBean order = ordersdb.getOrder(orderId);
		ordersdb.closeConnection();
		
		/* Get the ticket using the ticketID */
		TicketsDB ticketsdb = new TicketsDB();
		ticketsdb.connectMeIn();
		TicketBean ticket = ticketsdb.getTicket(ticketId);
		ticketsdb.closeConnection();
		
		/* Set the attributes on the request and redirect */
		request.setAttribute("ticket", ticket);
		request.setAttribute("order", order);
		request.getRequestDispatcher("/CancelOrder.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
