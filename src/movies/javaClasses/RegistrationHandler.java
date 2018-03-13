package movies.javaClasses;

import movies.beans.*;
import movies.dbAccess.*;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Registration
 */
public class RegistrationHandler extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public RegistrationHandler() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserBean user = new UserBean();
		UsersDB userdb = new UsersDB();
		boolean addSuccess = false;
		
		user.setUsername(request.getParameter("userName").trim());
		user.setPassword(request.getParameter("password"));
		user.setAddress(request.getParameter("address"));
		user.setCity(request.getParameter("city"));
		user.setState(request.getParameter("state"));
		user.setCountry(request.getParameter("country"));
		user.setZip("zip");
		user.setFirstName(request.getParameter("firstName"));
		user.setLastName(request.getParameter("lastName"));
		user.setPhoneNumber(request.getParameter("phoneNumber"));
		userdb.connectMeIn();
		addSuccess = userdb.addUser(user);
		userdb.closeConnection();
		
		if(addSuccess) {
			response.sendRedirect("Login.jsp");		
		}
		
		else {
			response.sendRedirect("Registration.jsp");		
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
}
