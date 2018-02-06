<!DOCTYPE html>
<html>

<head>
	<meta charset="ISO-8859-1">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<link rel="stylesheet" type="text/css" href="../../css/signup.css" />
	<title>Sign Up Form</title>
</head>
<body onload="bootstrapping()">
	<div>
		<form action="SessionServlet" method="post">
			<div class="login-container">
				<a href="index.jsp"><span class="sign-in">SIGN IN</span></a>
				<a href="signup.jsp"><span class="sign-up">SIGN UP</span></a>
			</div>
			<label for="username">USERNAME</label>
			<input type="text" id="username" name="username" placeholder="Lokesh" required/><br/>
			<label for="street">STREET</label>
			<input type="text" id="street" name="street" placeholder="Sivan Kovil Street" required/><br/>
			<label for="area">AREA</label>
			<input type="text" id="area" name="area" placeholder="Mambalam" required/><br/>
			<label for="city">CITY</label>
			<input type="text" id="city" name="city" placeholder="Chennai" required/><br/>
			<label for="pincode">PINCODE</label>
			<input type="number" id="pincode" name="pincode" placeholder="600116" required/><br/>
			<label for="phonenumber">PHONE NUMBER</label>
			<input type="number" id="phonenumber" name="phonenumber" placeholder="9876543210" required/><br/>
			<label for="licensenumber">Driving License Number</label>
			<input type="text" id="licensenumber" name="licensenumber" placeholder="TN 10 20138475294"/><br/>
			<label for="userpassword">Password</label>
			<input type="password" id="userpassword" name="userpassword" placeholder="password"/><br/>
			<input type="submit" name="submit-button" value="SIGN UP"/>
		</form>
	</div>
	<script src="../../javascript/validation.js"></script>	
</body>

</html>