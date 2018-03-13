<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="movies.beans.*"%>
<%@ page import="movies.dbAccess.*"%>
<%@ page import="java.util.*" %>
<%@ page import="java.text.DecimalFormat" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="Styles//Base.css" rel="stylesheet" type="text/css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Shopping Cart</title>
</head>

<body>
	<%
		HttpSession sess = request.getSession();
	
		if(sess.getAttribute("loggedIn") != "True"){
			response.sendRedirect("Login.jsp");
		}
	%>
	
	<script>
			function checkIfEmpty() {
			    var total = document.forms["Checkout"]["total"].value;
			    
			    if(total == 0){
			    	alert("There is nothing in your cart!");
			    	return false;
			    }
			}
	</script>
	
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
	
	<h1>Shopping Cart</h1>
	
	<div>
		<table>
			<tr>
				<th></th>
				<th><h3>Movie Name</h3></th>
				<th><h3>Theatre Multiplex</h3></th>
				<th><h3>Theatre Number</h3></th>
				<th><h3>Showtime</h3></th>
				<th><h3>Date</h3></th>
				<th><h3>Number of Tickets</h3></th>
				<th><h3>Total Price</h3></th>
				<th></th>
			</tr>
			
			<c:if test="${shoppingCart != null}">
			<c:forEach var="shoppingCartItem" items="${shoppingCart}" begin="0" end="${shoppingCartSize}" step="1" varStatus="index">				
				<c:choose>
					<c:when test="${index.index mod 2 == 0}">
						<tr>
							<td style="height:auto; width:auto;"><img src="${shoppingCartItem.showing.movie.poster}"></img></td>
							<td><c:out value="${shoppingCartItem.showing.movie.title}"></c:out></td>
							<td><c:out value="${shoppingCartItem.showing.showroom.theatre.theatreName}"></c:out></td>
							<td><c:out value="${shoppingCartItem.showing.showroom.showroomNumber}"></c:out></td>
							<td><c:out value="${shoppingCartItem.showing.time}"></c:out></td>
							<td><c:out value="${shoppingCartItem.showing.date}"></c:out></td>
							<td><c:out value="${shoppingCartItem.quantity}"></c:out></td>
							<td><c:out value="${shoppingCartItem.showing.price}"></c:out></td>
							<td>	
								<form style="display:inline-block;padding:10px;" name="Delete" action=UpdateShoppingCartHandler method="post">
									<input style="display:inline-block" class="button" type="submit" value="Remove from cart"></input>
									<input type="hidden" name="type" value="delete">
									<input type="hidden" name="cartID" value="${index.index}">
									<input type="hidden" name="movieShowingID" value="${shoppingCartItem.showing.movieShowingID}">
								</form>
							</td>
						</tr>
					</c:when>
					
					<c:otherwise>
						<tr class="grayRow">
							<td style="height:auto; width:auto;"><img src="${shoppingCartItem.showing.movie.poster}"></img></td>
							<td><c:out value="${shoppingCartItem.showing.movie.title}"></c:out></td>
							<td><c:out value="${shoppingCartItem.showing.showroom.theatre.theatreName}"></c:out></td>
							<td><c:out value="${shoppingCartItem.showing.showroom.showroomNumber}"></c:out></td>
							<td><c:out value="${shoppingCartItem.showing.time}"></c:out></td>
							<td><c:out value="${shoppingCartItem.showing.date}"></c:out></td>
							<td><c:out value="${shoppingCartItem.quantity}"></c:out></td>
							<td><c:out value="${shoppingCartItem.showing.price}"></c:out></td>
							<td>	
								<form style="display:inline-block;padding:10px;" name="Delete" action=UpdateShoppingCartHandler method="post">
									<input style="display:inline-block" class="button" type="submit" value="Remove from cart"></input>
									<input type="hidden" name="type" value="delete">
									<input type="hidden" name="cartID" value="${index.index}">
									<input type="hidden" name="movieShowingID" value="${shoppingCartItem.showing.movieShowingID}">
								</form>
							</td>
						</tr>
					</c:otherwise>
				</c:choose>
				</c:forEach>
			</c:if>
		</table>
	</div>
	
	<c:if test="${shoppingCartSize == 0 || shoppingCart == null}">
		<h3 style="color:lightblue;background:darkblue"><c:out value="${cartMessage}"></c:out></h3>
	</c:if>
	
	<c:if test="${shoppingCartSize != 0}">
		<div style="text-align:center">
			<p style="font-size:30px;">
				Total Cost: <c:out value="${shoppingCartPrice}"></c:out>
			</p>
		
			<form name="Checkout" action=UpdateShoppingCartHandler onsubmit="return checkIfEmpty();" method="post">
				<input class="button" type="submit" value="Checkout"></input>
				<input type="hidden" name="type" value="checkout">
			</form>
		</div>
	</c:if>
	
	
	
	<br>
</body>
</html>