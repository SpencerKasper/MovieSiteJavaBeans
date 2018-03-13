<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="Styles//Base.css" rel="stylesheet" type="text/css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Registration</title>
</head>

		<script>
			function validateForm() {
			    var user = document.forms["userForm"]["userName"].value;
			    var password = document.forms["userForm"]["password"].value;
			    var confirm = document.forms["userForm"]["password2"].value;
			    if (user == "" && (password == "" || confirm == "")) {
			        alert("Username and passwords must be filled out.");
			        return false;
			    }
			    
			    else if(user == ""){
			    	alert("Username must be filled out.")
			    }
			    
			    else if (password == "" || confirm == "") {
			        alert("Password must be filled out.");
			        return false;
			    }
			    if (password != confirm){
			    	alert("Passwords must match.");
			    	return false;
			    }
			}
	   </script>
<body>

	
<div style="text-align:center;">

	<h1>Movie Manager Registration</h1>
	<br>
	<form name="userForm" action=RegistrationHandler onsubmit="return validateForm();" method="post" style="text-align:center;">		
		<div>
			<p class="label" >Username: </p>
			<input class="textInput" type="text" name="userName"> <br>
		</div>
		
		<div>
			<p class="label" >Password: </p>
			<input class="textInput" type="password" name="password">
		</div>
		
		<div>
			<p class="label" >Re-enter Password: </p>
			<input class="textInput" type="password" name="password2">
		</div>
		
		<div>
			<p class="label"> First Name: </p>
			<input class="textInput" type="text" name="firstName">
		</div>
		
		<div>
			<p class="label" >Last Name: </p>
			<input class="textInput" type="text" name="lastName">
		</div>
		
		<div>
			<p class="label" >Address: </p>
			<input class="textInput"  type="text" name="address">
		</div>
		
		<div>
			<p class="label" >City: </p>
			<input class="textInput"  type="text" name="city">
		</div>
		
		<div>
			<p class="label">State: </p>
			<input class="textInput"  type="text" name="state">
		</div>
		
		<div>
			<p class="label" >Zip: </p>
			<input class="textInput"type="text" name="zip">
		</div>
		
		<div>
			<p class="label" >Country: </p>
			<input class="textInput" type="text" name="country">
		</div>
		
		<div>
			<p class="label" >Phone Number: </p>
			<input class="textInput" type="text" name="phoneNumber">
		</div>
		
		
		
		<input class="button" type=submit value=Register>	
	</form>
</div>

</body>
</html>