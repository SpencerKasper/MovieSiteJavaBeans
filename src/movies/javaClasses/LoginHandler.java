package movies.javaClasses;

import movies.beans.*;
import java.io.IOException;
import movies.dbAccess.*;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class LoginHandler
 */
public class LoginHandler extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginHandler() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		boolean isValidUsername = false;
		boolean isValidPassword = false;
		
		response.setContentType("text/html");
		UsersDB userdb = new UsersDB();
		
		userdb.connectMeIn();
		
		UserBean user = new UserBean();
		user.setUsername(request.getParameter("username"));
		user.setPassword(request.getParameter("password"));
		
		isValidUsername = userdb.validateUsername(user.getUsername());
		isValidPassword = userdb.validatePassword(user.getPassword());
		
		if(isValidUsername) {
					
			if(isValidPassword) {
				user = userdb.getUser(user.getUsername());
				user.setPassword("");
				userdb.closeConnection();
				
				HttpSession session = request.getSession();
				session.setAttribute("user", user);
				session.setAttribute("loggedIn", "True");
				ArrayList<String> theatres = new ArrayList<String>();
				TheatresDB theatresdb = new TheatresDB();
				theatresdb.connectMeIn();
				theatres = theatresdb.getTheatreNames();
				theatresdb.closeConnection();
				int days[] = new int[31];
				int months[] = new int[12];
				int years[] = new int[10];
				
				for(int i = 0; i < 31; i++) {
					days[i] = i+1;
				}
				
				for(int i = 0; i <  12; i++) {
					months[i] = i+1;
				}
				
				for(int i = 0; i < 10; i++) {
					years[i] = i + 2017;
				}
				
				session.setAttribute("months", months);
				session.setAttribute("years", years);
				session.setAttribute("days", days);
				session.setAttribute("theatreNames", theatres);
				response.sendRedirect("CustomerHomepageHandler");
			}
			
			else {
				response.sendRedirect("Login.jsp");
			}
			
		}
		
		else {
			response.sendRedirect("Registration.jsp");
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
