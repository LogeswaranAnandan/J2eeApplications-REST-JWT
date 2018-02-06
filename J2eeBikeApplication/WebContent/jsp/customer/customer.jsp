<%@page import="java.util.ResourceBundle"%>
<%@page import="java.util.Locale"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="css/customer.css" />
<title>Welcome Home</title>
</head>
<body>
<jsp:include page="customerheader.jsp" />
	<fmt:bundle basename="MessagesBundle">
	<section>
		<div class="main-container">
			<h1><fmt:message key="TITLE"/></h1>
			<h2><fmt:message key="QUOTES"/></h2>
			<form action="CustomerServlet">
				<input type="submit" name="customer-functionality" value="Rent a Bike" class="rent-btn"/>
			</form>
		</div>		
	</section>
	</fmt:bundle>		
</body>
</html>