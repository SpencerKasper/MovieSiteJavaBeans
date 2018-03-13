package movies.javaClasses;

import java.io.IOException;
import java.util.ArrayList;

import movies.beans.*;
import movies.dbAccess.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class TheatreAndMovieSearchQueryHandler
 */
public class TheatreAndMovieSearchQueryHandler extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public TheatreAndMovieSearchQueryHandler() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			MovieShowingsDB movieshowingsdb = new MovieShowingsDB();
			movieshowingsdb.connectMeIn();

			HttpSession sess = request.getSession();
			ArrayList<MovieShowingsBean> movieShowings = new ArrayList<MovieShowingsBean>();
			
			/* If the search all button is hit, run that query and redirect */
			if(request.getParameter("getAll").equals("true")) {
				movieShowings = movieshowingsdb.getAllShowings();
				movieshowingsdb.closeConnection();
				int numberOfShowings = movieShowings.size();
				request.setAttribute("numberOfShowings", numberOfShowings);
				request.setAttribute("movieShowings", movieShowings);
				request.getRequestDispatcher("/MovieSearchResults.jsp").forward(request, response);
			}
			
			else {
				String date = request.getParameter("monthSelect") + "/" + request.getParameter("daySelect") + "/" + request.getParameter("yearSelect");
				String theatreName = request.getParameter("theater");
				String searchQuery = request.getParameter("searchTerm");
			
				if(date.equals("null/null/null") || date == "") {
					date = (String)sess.getAttribute("lastSearchDate");
				}
			
				else {
					sess.setAttribute("lastSearchDate", date);
				}
			
				if(theatreName == null || theatreName == "") {
					theatreName = (String)sess.getAttribute("lastSearchTheatreName");
				}
			
				else {
					sess.setAttribute("lastSearchTheatreName", theatreName);
				}
			
				if(searchQuery == null || searchQuery == "") {
					searchQuery = (String)sess.getAttribute("lastSearchQuery");
				}
			
				else {
					sess.setAttribute("lastSearchQuery", searchQuery);
				}
			
				movieShowings = movieshowingsdb.getShowingsBySearchQuery(date,theatreName,searchQuery);
				int numberOfShowings = movieShowings.size();
			
				movieshowingsdb.closeConnection();

				request.setAttribute("numberOfShowings", numberOfShowings);
				request.setAttribute("movieShowings", movieShowings);
				request.getRequestDispatcher("/MovieSearchResults.jsp").forward(request, response);
			}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
