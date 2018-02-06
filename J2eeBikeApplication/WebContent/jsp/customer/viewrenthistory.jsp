<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>View Rent History</title>
</head>
<body>
	<jsp:include page="customerheader.jsp" />
	<div>
		<table id="table">
			<thead>
				<tr>
					<th>Bike Name</th>
					<th class="hide-in-mobile">Registration Number</th>
					<th>Rented Date & Time</th>
					<th>Rent Duration</th>
					<th class="hide-in-mobile">Actual Charge</th>
					<th class="hide-in-mobile">Penalty Duration</th>
					<th class="hide-in-mobile">Penalty Charge</th>
					<th>Total Charge</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${appBeanList }" var="appBean">
					<c:set var="bikeBean" value="${appBean.getBikeBean() }"/>
					<c:set var="rentBean" value="${appBean.getRentBean() }"/>
					<c:set var="revenueBean" value="${appBean.getRentCalculatorBean() }"/>
					<tr>
						<td>${bikeBean.getManufacturer() } - ${bikeBean.getBikeName() }</td>
						<td class="hide-in-mobile">${rentBean.getRegistrationNumber() }</td>
						<td>${rentBean.getRentedDateTime() }</td>
						<fmt:parseNumber var = "temp" integerOnly = "true" type = "number" value = "${revenueBean.getHoursRented()/24}" />
						<c:choose>
							<c:when test="${temp > '1' }">
								<td>${temp } days ${revenueBean.getHoursRented()%24 } hour</td>
							</c:when>
							<c:when test="${temp == '1' }">
								<td>${temp } day ${revenueBean.getHoursRented()%24 } hour</td>
							</c:when>
							<c:otherwise>
								<td>${revenueBean.getHoursRented() } hour</td>
							</c:otherwise>
						</c:choose>
						<td class="hide-in-mobile">&#8377; ${revenueBean.getActualCharge() }</td>
						<fmt:parseNumber var = "temp1" integerOnly = "true" type = "number" value = "${revenueBean.getPenaltyHours()/24}" />
						<c:choose>
							<c:when test="${revenueBean.getPenaltyHours() == '0' }">
								<td>Nil</td>
							</c:when>
							<c:when test="${temp1 > '1' }">
								<td>${temp1 } days ${revenueBean.getPenaltyHours()%24 } hour</td>
							</c:when>
							<c:when test="${temp1 == '1' }">
								<td>${temp1 } day ${revenueBean.getPenaltyHours()%24 } hour</td>
							</c:when>
							<c:otherwise>
								<td>${revenueBean.getPenaltyHours() } hour</td>
							</c:otherwise>
						</c:choose>
						<td class="hide-in-mobile">&#8377; ${revenueBean.getPenaltyCharge() }</td>
						<td>&#8377; ${revenueBean.getTotalCharge() }</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</body>
</html>