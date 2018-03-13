<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="Styles//Base.css" rel="stylesheet" type="text/css">
<title>Order #${orderID} Confirmed</title>
</head>

<body>
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
	
	<h1>Items in Shopping Cart</h1>
	<div style="text-align:center">
	<table style="width:80%;margin-left:10%; margin-right:10%;">
	<tr class="headerRow">
		<th><h3>Movie Name</h3></th>
		<th><h3>Ticket Quantity</h3></th>
		<th><h3>Theater Multiplex</h3></th>
		<th><h3>Theater Number</h3></th>
		<th><h3>Showtime</h3></th>
		<th><h3>Date</h3></th>
	</tr>
	
	<c:forEach var="shoppingCartItem" items="${lastShoppingCart}" begin="0" end="${shoppingCartSize}" step="1" varStatus="index">
		<c:choose>
			<c:when test="${index.index mod 2 == 0}">
				<tr>
					<td><c:out value="${shoppingCartItem.showing.movie.title}"></c:out></td>
					<td><c:out value="${shoppingCartItem.quantity}"></c:out></td>
					<td><c:out value="${shoppingCartItem.showing.showroom.theatre.theatreName}"></c:out></td>
					<td><c:out value="${shoppingCartItem.showing.showroom.showroomNumber}"></c:out></td>
					<td><c:out value="${shoppingCartItem.showing.time}"></c:out></td>
					<td><c:out value="${shoppingCartItem.showing.date}"></c:out></td>
				</tr>
			</c:when>
			
			<c:otherwise>
				<tr class="grayRow">
					<td><c:out value="${shoppingCartItem.showing.movie.title}"></c:out></td>
					<td><c:out value="${shoppingCartItem.quantity}"></c:out></td>
					<td><c:out value="${shoppingCartItem.showing.showroom.theatre.theatreName}"></c:out></td>
					<td><c:out value="${shoppingCartItem.showing.showroom.showroomNumber}"></c:out></td>
					<td><c:out value="${shoppingCartItem.showing.time}"></c:out></td>
					<td><c:out value="${shoppingCartItem.showing.date}"></c:out></td>
				</tr>
			</c:otherwise>
		</c:choose>
	</c:forEach>
	</table>
	</div>
	
	<div style="text-align:center">
		<p style="font-size:30px;">
			Total Cost: <c:out value="${shoppingCartPrice}"></c:out>
		</p>
	</div>
	
	<div>
		<p>
			Order #<c:out value="${orderID}"></c:out> Confirmed!
		</p>
	</div>
</body>
</html>