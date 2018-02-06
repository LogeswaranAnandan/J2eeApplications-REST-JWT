<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add New Bike</title>
</head>
<body>
	<jsp:include page="adminheader.jsp" />
	<section>
		<br/><br/>
		<form action="AdminServlet" method="get">
		<label for="bikename">Select a Bike : </label>
		<select name="bikename">
			<option value="null">Please select a bike</option>
			<c:forEach items="${bikeBeanList }" var="bikeBean">
				<option value="${bikeBean.getBikeId() }">${bikeBean.getBikeName() }</option>
			</c:forEach>
		</select>
		<input type="button" name="display-add-form" value="Ok"/>
			<div id='container'></div>
		</form>
	</section>
	<script type="text/javascript" src="javascript/addbike.js"></script>
</body>
</html>