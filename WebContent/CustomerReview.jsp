<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="Styles//Base.css" rel="stylesheet" type="text/css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Customer Review</title>
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
			    var review = document.forms["reviewEntry"]["review"].value;

			    if(review.length > 300){
			    	alert("You may not enter more than 300 characters.");
			    	return false;
			    }
			    
			    if(review == ""){
			    	alert("You must enter a review.");
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
	
	<div>
		<h1>Customer Review</h1>
	</div>
	
	<div>
		<h3><c:out value="${movie.title}"></c:out></h3>
	</div>
	
	<div style="text-align:center;">
		<form style="display:inline-block" name="reviewEntry" onsubmit="return checkIfEmpty();" action="CustomerReviewHandler" method="post">
			<div class="textInput">
				<label class="label">Review: </label><br>
				<textarea class="textInput" name="review" cols="100" rows="10"></textarea>
			</div>
			
			<div>
				<input type="hidden" name="movieID" value="${movie.movieID}">
			</div>
			
			<div style="padding:10px">
				<label class="label">Stars: </label>
				
				<select name="stars">
					<option value="1">1</option>
					<option value="2">2</option>
					<option value="3">3</option>
					<option value="4">4</option>
					<option value="5">5</option>
				</select>
			</div>
			
			<div style="padding:10px;">
				<input class="button" type="submit">
			</div>
		</form>
		
		<form name="Cancel" action=ViewMovieDetailsAndSelection.jsp method="post">
				<input class="button" type="submit" value="Cancel">
		</form>
	</div>
</body>
</html>