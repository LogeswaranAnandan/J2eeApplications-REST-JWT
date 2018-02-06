<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" type="text/css" href="css/modal.css" />
<title>View All Bikes</title>
</head>
<body>
	<jsp:include page="customerheader.jsp" />
	<div>
		<table id="table">
			<thead>
				<tr>
					<th class="flex-grow-1">Bike Id</th>
					<th>Manufacturer</th>
					<th>Bike Name</th>
					<th>Charge (per hour)</th>
					<th>Availability</th>
					<th>Rent the Bike</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${bikeBeanList }" var="bikeBean">
					<form action="CustomerServlet" method="get">
						<tr>
							<td class="flex-grow-1">${bikeBean.getBikeId() }</td>
							<td>${bikeBean.getManufacturer() }</td>
							<td>${bikeBean.getBikeName() }</td>
							<td>${bikeBean.getCharge() }</td>
							<c:choose>
								<c:when test="${bikeBean.getAvailability() > '0' }">
									<td>Available</td>
									<td>
										<input type="hidden" name="bike-id"	value="${bikeBean.getBikeId() }" />
										<input type="hidden" name="rent-duration" value="" id="rent-duration-${bikeBean.getBikeId() }"/>
										<input type="hidden" name="advance-paid" value="" id="advance-paid-${bikeBean.getBikeId() }"/>
										<input type="submit" name="customer-functionality" value="Rent this Bike" class="green-button" data-id="${bikeBean.getBikeId() }"/>
									</td>
								</c:when>
								<c:otherwise>
									<td>Not Available</td>
									<td>
										<input type="submit" value="Rent this Bike" class="red-button" disabled/>
									</td>
								</c:otherwise>
							</c:choose>
						</tr>
					</form>
				</c:forEach>
			</tbody>
		</table>
	</div>
	<div id="my-modal" class="my-modal">
		<div class="modal-container">
			
			<!-- Modal Header -->
			<div class="modal-header">
				<h1>Enter the Duration of Rent (in Hours)</h1>
				<span class="close">X</span>
			</div>
			
			<!-- Modal body -->
			<div class="modal-body">
				<input type="number" name="rent-duration" />
				<input type="button" name="rent-duration-submit" value="ok" />
			</div>
		</div>
	</div>
	<script src="javascript/rentbike.js"></script>
</body>
</html>