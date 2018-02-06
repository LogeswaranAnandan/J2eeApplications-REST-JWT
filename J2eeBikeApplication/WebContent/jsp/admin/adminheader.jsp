<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" type="text/css" href="css/basicstyle.css" />
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<title>Admin header page</title>
</head>
<body>
	<nav class="nav-form">
		<form action="AdminServlet">
			<input type="submit" id="home" name="admin-functionality" value="HOME"/>
		</form>
		<form action="AdminServlet">
			<input type="submit" name="admin-functionality" value="View All Bikes"/>
		</form>
		<form action="FactoryServlet">
			<input type="submit" name="admin-functionality" value="View All Rented Bikes"/>
		</form>
		<form action="AdminServlet">
			<input type="submit" name="admin-functionality" value="Add New Bike"/>
		</form>
		<form action="AdminServlet">
			<input type="submit" name="admin-functionality" value="View Customer Details"/>
		</form>
		<span id="user-name-container">
			<i class="fa fa-user fa-lg" aria-hidden="true"></i> Welcome, <span class="user-name">${sessionScope.userName }</span>		
		</span>
		<span id="menu-icon"><i class="fa fa-bars" aria-hidden="true"></i></span>
	</nav>
	<div id="logout-container" class="display-none">
		<form action="SessionServlet">
			<input type="submit" name="submit-button" value="Logout" id="logout"/>
		</form>
	</div>
	
	<script src="javascript/basicscript.js"></script>	
</body>
</html>