package movies.javaClasses;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import movies.beans.*;
import movies.dbAccess.*;


/**
 * Servlet implementation class ManageOrder
 */
public class ManageOrder extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ManageOrder() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/* Get the order ID from the request */
		int orderId = Integer.parseInt(request.getParameter("Id"),10);
		
		/* Get the order from the DB using ID */
		OrdersDB ordersdb = new OrdersDB();
		ordersdb.connectMeIn();
		OrdersBean order = ordersdb.getOrder(orderId);
		ordersdb.closeConnection();
		
		/* Store the order info on the request and forward */
		request.setAttribute("order", order);
		request.setAttribute("ticketsInOrder", order.getTickets().size());
		request.getRequestDispatcher("/ManageOrder.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
