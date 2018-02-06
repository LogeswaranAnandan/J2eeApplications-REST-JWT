<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="css/table.css" />
<title>View All Bikes</title>
</head>
<body>
	<jsp:include page="adminheader.jsp" />
	<div>
		<table id="table">
			<thead>
				<tr>
					<th>Bike Id</th>
					<th>Manufacturer</th>
					<th>Bike Name</th>
					<th>Charge (per hour)</th>
					<th>Availability</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${bikeBeanList }" var="bikeBean">
					<tr>
						<td name="bike-id" value="${bikeBean.getBikeId() }">${bikeBean.getBikeId() }</td>
						<td>${bikeBean.getManufacturer() }</td>
						<td>${bikeBean.getBikeName() }</td>
						<td>${bikeBean.getCharge() }</td>
						<td>
							<c:choose>
								<c:when test="${bikeBean.getAvailability() > '0' }">
									Available
								</c:when>
								<c:otherwise>
									Not Available
								</c:otherwise>
							</c:choose>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</body>
</html>