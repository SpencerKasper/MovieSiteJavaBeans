<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="javax.servlet.http.HttpSession"%>
<%@ page import="java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="Styles//Base.css" rel="stylesheet" type="text/css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Movie Manager</title>
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
	
	

	<h1>Movie Manager Homepage</h1>
	
	<div style="text-align:center;">
		<form action=TheatreAndMovieSearchQueryHandler method="post">
			<div class="textInput">
				<label class="label">Theatre: </label>
				<select name="theater">
					<c:forEach var="theatre" items="${theatreNames}">
						<option value="${theatre}">${theatre}</option>
					</c:forEach>
				</select>
			</div>
			
			<div class="textInput">
				<label class="label">Movie or Keyword:</label>
				<input type="text" name=searchTerm>
			</div>
			
			<div class="textInput">
				<label class="label">Date:</label>
				<select name="monthSelect">
					<c:forEach items="${months}" var="month">
						<option>${month}</option>
					</c:forEach>
				</select>
				/
				<select name="daySelect">
					<c:forEach items="${days}" var="day">
						<option value="${day}">${day}</option>
					</c:forEach>
				</select>
				/
				<select name="yearSelect">
					<c:forEach var="year" items="${years}">
						<option value="${year}">${year}</option>
					</c:forEach>
				</select>
			</div>
				
			<input type="submit" value="Search">
		</form>
		
		<form action=TheatreAndMovieSearchQueryHandler method="post">
			<input type="submit" value="Get All Showings">
			<input name="getAll" type="hidden" value="true">
		</form>
	</div>
</body>
</html>