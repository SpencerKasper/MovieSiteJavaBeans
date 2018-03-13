package movies.javaClasses;

import movies.beans.*;
import movies.dbAccess.*;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class CustomerReviewHandler
 */
public class CustomerReviewHandler extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    public CustomerReviewHandler() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int movieID = Integer.parseInt(request.getParameter("movieID"));
		String date = new SimpleDateFormat("MM/dd/yyyy").format(new Date());

		ReviewBean review = new ReviewBean();
		ReviewsDB reviewsdb = new ReviewsDB();
		
		UserBean user = new UserBean();
		user = (UserBean)request.getSession().getAttribute("user");
		
		MovieBean movie = new MovieBean();
		MoviesDB moviedb = new MoviesDB();
		moviedb.connectMeIn();
		movie = moviedb.getMovie(movieID);
		
		review.setReview(request.getParameter("review"));
		review.setRating(Integer.parseInt(request.getParameter("stars")));
		review.setUser(user);
		review.setMovie(movie);
		review.setDate(date);
		
		reviewsdb.connectMeIn();
		reviewsdb.addReview(review);
		
		reviewsdb.closeConnection();
		moviedb.closeConnection();
		response.sendRedirect("CustomerReviewConfirmation.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
