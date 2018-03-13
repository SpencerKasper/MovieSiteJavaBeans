<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>View Orders</title>
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
	
	<h1>Your Orders</h1>
	
	<div>
		<table>
			<tr>
				<th><h3>Order Number</h3></th>
				<th><h3>Status</h3></th>
				<th><h3>Total</h3></th>
				<th></th>
			</tr>
	
			<c:forEach var="order" items="${orders}" begin="0" end="${ordersSize}" step="1" varStatus="index">
				<c:choose>
					<c:when test="${index.index mod 2 == 0}">
						<tr>
							<td><c:out value="${order.orderID}"></c:out></td>
							<td><c:out value="${order.status}"></c:out></td>
							<td><c:out value="${order.totalCost}"></c:out></td>
							<td>
								<c:url var="manageOrderURL" value="ManageOrder">
									<c:param name="Id" value="${order.orderID}"></c:param>
								</c:url>
								
								<a href="${manageOrderURL}">Manage Order</a>
							</td>
						</tr>
					</c:when>
					
					<c:otherwise>
						<tr class="grayRow">
							<td><c:out value="${order.orderID}"></c:out></td>
							<td><c:out value="${order.status}"></c:out></td>
							<td><c:out value="${order.totalCost}"></c:out></td>
							<td>
								<c:url var="manageOrderURL" value="ManageOrder">
									<c:param name="Id" value="${order.orderID}"></c:param>
								</c:url>
								
								<a href="${manageOrderURL}">Manage Order</a>
							</td>
						</tr>
					</c:otherwise>
				</c:choose>
			</c:forEach>
		</table>
	</div>
</body>
</html>