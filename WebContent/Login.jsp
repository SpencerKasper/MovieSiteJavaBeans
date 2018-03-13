<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login</title>
<link href="Styles//Base.css" rel="stylesheet" type="text/css">
</head>

<script>
			function checkIfEmpty() {
			    var user = document.forms["loginForm"]["username"].value;
			    var password = document.forms["loginForm"]["password"].value;

			    if(user == "" && password == ""){
			    	alert("Both fields must be filled out.");
			    	return false;
			    }
			    
			    else if (user == "") {
			        alert("Username must be filled out.");
			        return false;
			    }
			    
			    else if (password == "") {
			        alert("Password must be filled out.");
			        return false;
			    }
			}
</script>

<body>
	<h1>Movie Manager Login</h1>
	
	<div style="text-align:center;">
		<form name="loginForm" action="LoginHandler" onsubmit="return checkIfEmpty();" method="post">
			<div class="textInput">
				<label class="label">Username: </label>
				<input type="text" name="username"> <br>
			</div>
		
			<div class="textInput">
				<label class="label">Password: </label>
				<input type="password" name="password">
			</div>
			<br>
			<input class="button" type="submit"> <br>
			<br>
			<a href="Registration.jsp">New user? Register now.</a>
		</form>
	</div>
</body>
</html>