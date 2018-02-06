<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="css/table.css" />
<title>View Rented Bikes</title>
</head>
<body>
	<jsp:include page="adminheader.jsp" />
	<div>
		<table id="table">
			<thead>
				<tr>
					<th>Transaction Id</th>
					<th>User Id</th>
					<th>Bike Id</th>
					<th>Registration Number</th>
					<th>Rented Date&Time</th>
					<th>Duration</th>
					<th>Estimated Amount</th>
					<th>Advance Paid</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${rentBeanList }" var="rentBean">
					<tr>
						<td>${rentBean.getTransactionId() }</td>
						<td>${rentBean.getUserId() }</td>
						<td>${rentBean.getBikeId() }</td>
						<td>${rentBean.getRegistrationNumber() }</td>
						<td>${rentBean.getRentedDateTime() }</td>
						<td>${rentBean.getDuration() }</td>
						<td>${rentBean.getEstimatedAmount() }</td>
						<td>${rentBean.getAdvancePaid() }</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>

</body>
</html>