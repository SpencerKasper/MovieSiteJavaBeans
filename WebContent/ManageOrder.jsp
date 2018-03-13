<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="Styles//Base.css" rel="stylesheet" type="text/css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Order Number: <c:out value="${order.orderID}"></c:out></title>
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
	
	<div>
		<p>
			Order Number: <c:out value="${order.orderID}"></c:out>
		</p>
	</div>

	<table style="width:80%;margin-left:10%; margin-right:10%;">
		<tr class="headerRow">
			<th><h3>Movie Name</h3></th>
			<th><h3>Ticket Quantity</h3></th>
			<th><h3>Theater Multiplex</h3></th>
			<th><h3>Theater Number</h3></th>
			<th><h3>Showtime</h3></th>
			<th><h3>Date</h3></th>
			<th></th>
		</tr>
	
		<c:forEach var="ticketItem" items="${order.tickets}" begin="0" end="${ticketsInOrder}" step="1" varStatus="index">				
			<c:choose>
				<c:when test="${index.index mod 2 == 0}">
					<tr>
						<td><c:out value="${ticketItem.showing.movie.title}"></c:out></td>
						<td><c:out value="${ticketItem.quantity}"></c:out></td>
						<td><c:out value="${ticketItem.showing.showroom.theatre.theatreName}"></c:out></td>
						<td><c:out value="${ticketItem.showing.showroom.showroomNumber}"></c:out></td>
						<td><c:out value="${ticketItem.showing.time}"></c:out></td>
						<td><c:out value="${ticketItem.showing.date}"></c:out></td>
						<td>
							<c:url var="cancelOrderURL" value="CancelOrder">
								<c:param name="OId" value="${order.orderID}"></c:param>
								<c:param name="TId" value="${ticketItem.ticketID}"></c:param>
							</c:url>
								
							<a href="${cancelOrderURL}">Cancel Order Item</a>
						</td>
					</tr>
				</c:when>
				
				<c:otherwise>
					<tr class="grayRow">
						<td><c:out value="${ticketItem.showing.movie.title}"></c:out></td>
						<td><c:out value="${ticketItem.quantity}"></c:out></td>
						<td><c:out value="${ticketItem.showing.showroom.theatre.theatreName}"></c:out></td>
						<td><c:out value="${ticketItem.showing.showroom.showroomNumber}"></c:out></td>
						<td><c:out value="${ticketItem.showing.time}"></c:out></td>
						<td><c:out value="${ticketItem.showing.date}"></c:out></td>
						<td>
							<c:url var="cancelOrderURL" value="CancelOrder">
								<c:param name="OId" value="${order.orderID}"></c:param>
								<c:param name="TId" value="${ticketItem.ticketID}"></c:param>
							</c:url>
								
							<a href="${cancelOrderURL}">Cancel Order Item</a>
						</td>
					</tr>
				</c:otherwise>
			</c:choose>
		</c:forEach>
	</table>

	<div>
		<p>
			Order Total: $${order.totalCost}
		</p>
	</div>



</body>
</html>