<%@page import="java.util.ResourceBundle"%>
<%@page import="java.util.Locale"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="css/admin.css" />
<title>Welcome Admin</title>
</head>
<body>
	<jsp:include page="adminheader.jsp" />
	<fmt:bundle basename="MessagesBundle">
	<section>
		<div class="main-container">
			<h1><fmt:message key="TITLE"/></h1>
			<h2><fmt:message key="QUOTES"/></h2>
		</div>	
	</section>
	</fmt:bundle>
</body>
</html>