package movies.javaClasses;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import movies.beans.UserBean;
import movies.dbAccess.OrdersDB;
import movies.beans.OrdersBean;

/**
 * Servlet implementation class ViewOrders
 */
public class ViewOrders extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ViewOrders() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/* Pull the user off of the session to get the userID */
		HttpSession session = request.getSession();
		UserBean user = new UserBean();
		user = (UserBean)session.getAttribute("user");
		
		/* Connect to the Orders DB */
		OrdersDB ordersdb = new OrdersDB();
		ordersdb.connectMeIn();
		
		/* Get list of orders from DB and pass to view orders */
		ArrayList<OrdersBean> orders = ordersdb.getOrdersByUser(user.getUserID());
		ordersdb.closeConnection();
		request.setAttribute("orders", orders);
		request.setAttribute("ordersSize", orders.size());
		request.getRequestDispatcher("/ViewOrders.jsp").forward(request, response);	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
