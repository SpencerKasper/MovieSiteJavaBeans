<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="movies.beans.*"%>
<%@ page import="java.util.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<link href="Styles//Base.css" rel="stylesheet" type="text/css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Details</title>
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
	
	<h1>Movie Details</h1>
	
	<ul>
		<li style="padding:15px;"><img src="${showing.movie.poster}"></img></li>
		<li><strong>Name:</strong> <c:out value="${showing.movie.title}"></c:out></li>
		<li><strong>Description:</strong> <c:out value="${showing.movie.description }"></c:out></li>
		<li><strong>Rating:</strong> <c:out value="${showing.movie.rating}"></c:out></li>
		<li><strong>Movie Theatre:</strong> <c:out value="${showing.showroom.theatre.theatreName}"></c:out></li>
		<li><strong>Theatre Number:</strong> <c:out value="${showing.showroom.showroomNumber}"></c:out></li>
		<li><strong>Showtime:</strong> <c:out value="${showing.time}"></c:out></li>
		<li><strong>Price:</strong> <c:out value="${showing.price}"></c:out></li>
		<li><strong>Available Seats:</strong> <c:out value="${showing.availableSeats}"></c:out></li>
	</ul>
	
	<div style="text-align:center;padding:15px;">
		
		
		<form style="padding:10px;display:inline-block;" name="AddToCart" action=UpdateShoppingCartHandler method="post">
			<div style="text-align:center;">
				<label class="label">How many tickets would you like?</label>
				<select class="textInput" name="ticketQuantity">
					<c:forEach begin="0" end="${showing.availableSeats}" varStatus="loop">
						<option value="${loop.index}">${loop.index}</option>
					</c:forEach>
				</select>
			</div>
			<br>
			<input style="display:inline-block" class="button" type="submit" value="Add to cart"></input>
			<input type="hidden" value="${showing.movieShowingID}" name="movieShowingID">
			<input type="hidden" value="add" name="type">
		</form>
		<br>
		<form style="display:inline-block;padding:10px" name="CustomerReviewForm" action=CustomerReview method="post">
			<input style="display:inline-block" class="button" type="submit" value="Review this movie"></input>
			<input type="hidden" value="${showing.movie.movieID}" name="movieID">
		</form>
		
		<form style="display:inline-block;padding:10px;" name="Back" action=TheatreAndMovieSearchQueryHandler method="post">
			<input style="display:inline-block" class="button" type="submit" value="Back"></input>
		</form>
		
		<form style="display:inline-block;padding:10px" name="Home" action=CustomerHomepageHandler method="post">
			<input style="display:inline-block" class="button" type="submit" value="Home"></input>
		</form>
		
		<a href="\MovieDetailsAndSelection.jsp?Id=${showing.movieShowingID}\"></a>
	</div>
	
	<h2>Viewer Reviews</h2>
	
	<c:forEach var="review" items="${reviews}" begin="0" end="${numberOfReviews}" step="1" varStatus="index">
		<c:choose>
			<c:when test="${index.index mod 2 == 1}" >
				<ul style="text-align:left;background-color:white;color:Black;">
					<li><strong>Name:</strong> <c:out value="${review.user.firstName}"></c:out> <c:out value="${review.user.lastName}"></c:out></li>
					<li><strong>Rating:</strong> <c:out value="${review.rating}"></c:out></li>
					<li><strong>Review Date:</strong> <c:out value="${review.date}"></c:out></li>
					<li><strong>Review:</strong> <c:out value="${review.review}"></c:out></li>
				</ul>
			</c:when>
			
			<c:otherwise>
				<ul style="text-align:left;background-color:Black;color:white;">
					<li><strong>Name:</strong> <c:out value="${review.user.firstName}"></c:out> <c:out value="${review.user.lastName}"></c:out></li>
					<li><strong>Rating:</strong> <c:out value="${review.rating}"></c:out></li>
					<li><strong>Review Date:</strong> <c:out value="${review.date}"></c:out></li>
					<li><strong>Review:</strong> <c:out value="${review.review}"></c:out></li>
				</ul>
			</c:otherwise>
		</c:choose>
	</c:forEach>
</body>
</html>