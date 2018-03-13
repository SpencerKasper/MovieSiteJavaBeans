package movies.javaClasses;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.util.*;
import movies.beans.*;
import movies.dbAccess.*;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class CustomerHomepageHandler
 */
public class CustomerHomepageHandler extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public CustomerHomepageHandler() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession sess = request.getSession();
		if(sess.getAttribute("loggedIn") != "True") {
			response.sendRedirect("Login.jsp");
		}
		
		ArrayList<String> theatres = new ArrayList<String>();
		TheatresDB theatresdb = new TheatresDB();
		theatresdb.connectMeIn();
		theatres = theatresdb.getTheatreNames();
		theatresdb.closeConnection();
		request.setAttribute("theatres", theatres);
		request.getRequestDispatcher("/CustomerHomepage.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
