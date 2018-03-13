<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="Styles//Base.css" rel="stylesheet" type="text/css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Cancel Item #<c:out value="${ticket.ticketID}"></c:out></title>
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
	
	<table style="width:80%;margin-left:10%; margin-right:10%;">
		<tr class="headerRow">
			<th><h3>Movie Name</h3></th>
			<th><h3>Ticket Quantity</h3></th>
			<th><h3>Theater Multiplex</h3></th>
			<th><h3>Theater Number</h3></th>
			<th><h3>Showtime</h3></th>
			<th><h3>Date</h3></th>
		</tr>
	
		<tr>
			<td><c:out value="${ticket.showing.movie.title}"></c:out></td>
			<td><c:out value="${ticket.quantity}"></c:out></td>
			<td><c:out value="${ticket.showing.showroom.theatre.theatreName}"></c:out></td>
			<td><c:out value="${ticket.showing.showroom.showroomNumber}"></c:out></td>
			<td><c:out value="${ticket.showing.time}"></c:out></td>
			<td><c:out value="${ticket.showing.date}"></c:out></td>
		</tr>
	</table>
	
	<div>
		<p style="text-align:center;">
			Cancelled Item #<c:out value="${ticket.ticketID}"></c:out> from Order #<c:out value="${order.orderID}"></c:out>.
		</p>
	</div>
</body>
</html>