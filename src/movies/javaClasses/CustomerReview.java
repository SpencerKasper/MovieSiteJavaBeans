package movies.javaClasses;

import java.io.IOException;
import movies.beans.*;
import movies.dbAccess.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CustomerReview
 */
public class CustomerReview extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public CustomerReview() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			int movieID = Integer.parseInt(request.getParameter("movieID"));
			
			MovieBean movie = new MovieBean();
			MoviesDB moviesdb = new MoviesDB();
			moviesdb.connectMeIn();
			
			movie = moviesdb.getMovie(movieID);
			
			moviesdb.closeConnection();
			
			request.setAttribute("movie", movie);
			request.getRequestDispatcher("/CustomerReview.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
