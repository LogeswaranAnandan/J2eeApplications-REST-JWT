<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>

<head>
	<meta charset="ISO-8859-1">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<link rel="stylesheet" type="text/css" href="css/index.css" />
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
	<title>Bike Renting Application</title>
</head>
<body>
	<div class="container">
		<form action="SessionServlet" method="post">
			<div class="login-container">
				<span class="welcome">Welcome</span>
			</div>
			<div class="form-label-container">
				<label for="username">USERNAME or PHONENUMBER</label>
				<input type="text" id="username" name="username" placeholder="Enter your username or phonenumber..." required autofocus /><br/>
				<label for="password">PASSWORD</label>
				<input type="password" id="password" name="password" placeholder="Enter your password..." required autofocus /><br/>
				<c:if test="${loginStatus eq 'invalid' }">
					<font><i class="fa fa-info-circle" aria-hidden="true"></i>Invalid Username or Password</font>
				</c:if>
				<input type="submit" name="submit-button" value="LOGIN"/>
			</div>
			<div class="form-line"></div>
				<a href="jsp/customer/signup.jsp" class="create-account">Create an account...</a>
		</form>
	</div>
</body>

</html>