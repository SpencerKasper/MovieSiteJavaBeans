<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="movies.beans.*"%>
<%@ page import="movies.dbAccess.*" %>
<%@ page import="java.util.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Movie Search Results</title>
<link href="Styles//Base.css" rel="stylesheet" type="text/css">
</head>
<body>
	<%
		HttpSession sess = request.getSession();
	
		if(sess.getAttribute("loggedIn") != "True"){
			response.sendRedirect("Login.jsp");
		}
	%>

	<div class="banner">
		<ul>
			<li><a class="home" href="CustomerHomepageHandler">Home</a></li>
			<li><a class="shoppingCart" href="UpdateShoppingCartHandler">Shopping Cart</a></li>
			<li><a class="viewOrder" href="ViewOrders">View Orders</a></li>
			<li><a class="logout" href="LogoutHandler">Log Out</a></li>
		</ul>
		
		<div class="displayName">
			<c:out value="${user.firstName }"></c:out>
			<c:out value="${user.lastName}"></c:out>
		</div>
	</div>
	
<h1>Search Results</h1>
<div>
<table>
	<tr>
		<th></th>
		<th><h3>Theatre Multiplex</h3></th>
		<th><h3>Theatre Number</h3></th>
		<th><h3>Showtime</h3></th>
		<th><h3>Available Seats</h3></th>
		<th><h3>Price</h3></th>
		<th></th>
	</tr>
	
	<c:forEach var="movieShowing" items="${movieShowings}" begin="0" end="${numberOfShowings}" step="1" varStatus="index">
		<c:choose>
			<c:when test="${index.index mod 2 == 0}">
				<tr>
					<td style="height:auto; width:auto:"><img src="${movieShowing.movie.poster}"></img></td>
					<td><c:out value="${movieShowing.showroom.theatre.theatreName}"></c:out></td>
					<td><c:out value="${movieShowing.showroom.showroomNumber}"></c:out></td>
					<td><c:out value="${movieShowing.time}"></c:out></td>
					<td><c:out value="${movieShowing.availableSeats}"></c:out></td>
					<td><c:out value="${movieShowing.price}"></c:out></td>
					<td>
						<c:url var="detailsURL" value="MovieSearchResultsHandler">
							<c:param name="showingId" value="${movieShowing.movieShowingID}"></c:param>
						</c:url>
						<a href="${detailsURL}">View Details</a>
					</td>
				</tr>
			</c:when>
			
			<c:otherwise>
				<tr class="grayRow">
					<td style="height:auto; width:auto:"><img src="${movieShowing.movie.poster}"></img></td>
					<td><c:out value="${movieShowing.showroom.theatre.theatreName}"></c:out></td>
					<td><c:out value="${movieShowing.showroom.showroomNumber}"></c:out></td>
					<td><c:out value="${movieShowing.time}"></c:out></td>
					<td><c:out value="${movieShowing.availableSeats}"></c:out></td>
					<td><c:out value="${movieShowing.price}"></c:out></td>
					<td>
						<c:url var="detailsURL" value="MovieSearchResultsHandler">
							<c:param name="showingId" value="${movieShowing.movieShowingID}"></c:param>
						</c:url>
						<a href="${detailsURL}">View Details</a>
					</td>
				</tr>
			</c:otherwise>
		</c:choose>
	</c:forEach>
</table>
</div>
</body>
</html>