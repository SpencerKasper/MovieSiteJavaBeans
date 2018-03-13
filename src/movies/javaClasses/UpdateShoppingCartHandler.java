package movies.javaClasses;

import movies.beans.*;
import movies.dbAccess.*;
import java.util.*;
import java.io.IOException;
import java.text.DecimalFormat;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class UpdateShoppingCartHandler
 */
public class UpdateShoppingCartHandler extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public UpdateShoppingCartHandler() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		ArrayList<TicketBean> shoppingCart = new ArrayList<TicketBean>();
		TicketBean item = new TicketBean();
		String type = request.getParameter("type");
		double shoppingCartPrice = 0;
		String added = (String)session.getAttribute("added");
		String deleted = (String)session.getAttribute("deleted");
		
		/* If the type is null, we just want to display the cart */
		if(type == null) {
			
			shoppingCart = (ArrayList<TicketBean>)session.getAttribute("shoppingCart");
			
			if(shoppingCart == null) {
				session.setAttribute("shoppingCartSize", "0");
				session.setAttribute("shoppingCartPrice", "0.00");
				session.setAttribute("cartMessage", "It seems your cart is empty.  Please click the home button to search for showings to add to your cart!");
			}
			
			else {
				session.setAttribute("shoppingCartSize", shoppingCart.size());
			}
			
			/* Reset ability to add showings */
			session.setAttribute("added", null);

			request.getRequestDispatcher("ViewAndCheckoutShoppingCart.jsp").forward(request, response);
		}
		
		/* If it is an add to shopping cart, add the item to the end of the list and load beans to pass */
		else if(type.equals("add")) {
			if(session.getAttribute("shoppingCart") != null) {
				shoppingCart = (ArrayList<TicketBean>)session.getAttribute("shoppingCart");
				shoppingCartPrice = Double.parseDouble((String)session.getAttribute("shoppingCartPrice"));
			}			
			
			/* Set movieShowingID in shopping cart item */
			MovieShowingsDB movieshowingsdb = new MovieShowingsDB();
			movieshowingsdb.connectMeIn();
			item.setShowing(movieshowingsdb.getShowing(Integer.parseInt(request.getParameter("movieShowingID"))));
			movieshowingsdb.closeConnection();
			
			/* Set quantity of movie showing to purchase */
			int quantity = Integer.parseInt(request.getParameter("ticketQuantity"));
			item.setQuantity(quantity);
			
			/* Set price of movie showing and add item to cart */
			double itemPrice = quantity*(item.getShowing().getPrice());
			item.setPrice(itemPrice);
			
			if(added != null && added.equals("true")) {
				shoppingCart = (ArrayList<TicketBean>)session.getAttribute("shoppingCart");
				
				if(shoppingCart == null) {
					session.setAttribute("shoppingCartSize", "0");
					session.setAttribute("shoppingCartPrice", "0.00");
					request.setAttribute("cartMessage", "It seems your cart is empty.  Please click the home button to search for showings to add to your cart!");
				}
				
				else {
					session.setAttribute("shoppingCartSize", shoppingCart.size());
				}

				request.getRequestDispatcher("ViewAndCheckoutShoppingCart.jsp").forward(request, response);
			}
			
			else {
				shoppingCart.add(item);
				
				/* Set added to true to prevent refresh */
				session.setAttribute("added", "true");
				
				shoppingCartPrice = shoppingCartPrice + itemPrice;
				DecimalFormat df=new DecimalFormat("#.00"); 
				String totalPrice = df.format(shoppingCartPrice);
				
				session.setAttribute("shoppingCartSize", shoppingCart.size());
				session.setAttribute("shoppingCart", shoppingCart);
				session.setAttribute("shoppingCartPrice", totalPrice);
				request.getRequestDispatcher("ViewAndCheckoutShoppingCart.jsp").forward(request, response);
			}
		}
		
		else if(type.equals("delete")) {
			if(session.getAttribute("shoppingCart") != null) {
				shoppingCart = (ArrayList<TicketBean>)session.getAttribute("shoppingCart");
				int cartID = Integer.parseInt(request.getParameter("cartID"));
				
				/* Update the price of the cart */
				double removedPrice = shoppingCart.get(cartID).getPrice();
				shoppingCartPrice = Double.parseDouble((String)session.getAttribute("shoppingCartPrice"));
				shoppingCartPrice = shoppingCartPrice - removedPrice;
				DecimalFormat df=new DecimalFormat("#.00"); 
				String totalPrice = df.format(shoppingCartPrice);
				session.setAttribute("shoppingCartPrice", totalPrice);
				
				/* Remove the item from the shopping cart and redirect */
				shoppingCart.remove(cartID);
				session.setAttribute("shoppingCartSize", shoppingCart.size());
				session.setAttribute("shoppingCart", shoppingCart);
			}
			
			else {
				session.setAttribute("shoppingCartSize", "0");
				session.setAttribute("shoppingCartPrice", "0.00");
				request.getRequestDispatcher("ViewAndCheckoutShoppingCart.jsp").forward(request, response);
			}
			
			response.sendRedirect("ViewAndCheckoutShoppingCart.jsp");
		}
		
		else if(type.equals("checkout")) {
			shoppingCart = (ArrayList<TicketBean>)session.getAttribute("shoppingCart");
			
			/* Set request and session objects */
			session.setAttribute("shoppingCart", shoppingCart);
			session.setAttribute("added", null);
			request.getRequestDispatcher("CustomerTransaction.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
