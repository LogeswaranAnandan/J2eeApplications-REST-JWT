<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="css/table.css" />
<title>View All Users</title>
</head>
<body>
	<jsp:include page="adminheader.jsp" />
	<div>
		<table id="table">
			<thead>
				<tr>
					<th>User Id</th>
					<th>User Name</th>
					<th>Phone Number</th>
					<th>License Number</th>
					<th>Street</th>
					<th>Area</th>
					<th>City</th>
					<th>Pincode</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${userBeanList }" var="userBean">
					<tr>
						<td>${userBean.getUserId() }</td>
						<td>${userBean.getUserName() }</td>
						<td>${userBean.getPhoneNumber() }</td>
						<td>${userBean.getLicenseNumber() }</td>
						<td>${userBean.getStreet() }</td>
						<td>${userBean.getArea() }</td>
						<td>${userBean.getCity() }</td>
						<td>${userBean.getPincode() }</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	
</body>
</html>