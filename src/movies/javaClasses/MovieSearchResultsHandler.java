package movies.javaClasses;

import movies.beans.*;
import movies.dbAccess.*;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.util.*;

/**
 * Servlet implementation class MovieSearchResultsHandler
 */
public class MovieSearchResultsHandler extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public MovieSearchResultsHandler() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/* Pull the showingId from the request */
		int showingID = Integer.parseInt(request.getParameter("showingId"));
		
		/* Get the showing */
		ArrayList<MovieShowingsBean> showings = new ArrayList<MovieShowingsBean>();
		MovieShowingsDB movieshowingsdb = new MovieShowingsDB();
		movieshowingsdb.connectMeIn();
		showings = movieshowingsdb.getShowingByShowingID(showingID);
		
		/* Get the reviews */
		ReviewsDB reviewsdb = new ReviewsDB();
		reviewsdb.connectMeIn();
		ArrayList<ReviewBean> reviews = new ArrayList<ReviewBean>();
		reviews = reviewsdb.getReviews(showings.get(0).getMovie().getMovieID());
		
		/* Close the database connections */
		movieshowingsdb.closeConnection();
		reviewsdb.closeConnection();
		
		/* Store our objects in the request */
		request.setAttribute("showing", showings.get(0));
		request.setAttribute("reviews", reviews);
		request.setAttribute("numberOfReviews", reviews.size());
		
		/* Reset ability to add to cart and delete from cart */
		HttpSession session = request.getSession();
		session.setAttribute("added", null);
		
		/* Forward to MovieDetailsAndSelection.jsp with the loaded request */
		request.getRequestDispatcher("/MovieDetailsAndSelection.jsp").forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
