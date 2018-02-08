<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" type="text/css" href="css/basicstyle.css" />
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<title>Customer Header Page</title>
</head>
<body>
	<nav class="nav-form">
		<form action="CustomerServlet">
			<input type="submit" id="home" name="customer-functionality" value="HOME"/>
		</form>
		<form action="CustomerServlet">
			<input type="submit" name="customer-functionality" value="View All Bikes"/>
		</form>
		<form action="CustomerServlet">
			<input type="submit" name="customer-functionality" value="Return the Bike"/>
		</form>
		<form action="CustomerServlet">
			<input type="submit" name="customer-functionality" value="View Rent History"/>
		</form>
		<span id="user-name-container">
			<i class="fa fa-user fa-lg" aria-hidden="true"></i> Welcome, <span class="user-name">${sessionScope.userName }</span>		
		</span>
		<span id="menu-icon"><i class="fa fa-bars" aria-hidden="true"></i></span>
	</nav>
	<div id="logout-container" class="display-none">
		<form action="SessionServlet">
			<input type="submit" value="Logout" id="logout" name="submit-button"/>
		</form>
	</div>
	
	<script src="javascript/basicscript.js"></script>
</body>
</html>