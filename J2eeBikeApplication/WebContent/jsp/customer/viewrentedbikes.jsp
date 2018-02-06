<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>View Rented Bikes</title>
</head>
<body>
	<jsp:include page="customerheader.jsp" />
	<div>
		<table id="table">
			<thead>
				<tr>
					<th>Bike Name</th>
					<th>Registration Number</th>
					<th>Actual Charge</th>
					<th>Penalty Charge</th>
					<!-- <th>Total Charge</th> -->
					<th>Advance Paid</th>
					<th>Remaining Payment</th>
					<th>Return the Bike</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${bikeBeanList }" var="bikeBean">
					<form action="CustomerServlet" method="get">
						<tr>
							<td>${bikeBean.getManufacturer() } - ${bikeBean.getBikeName() }</td>
							<td>${bikeBean.getRegistrationNumber() }</td>
							<c:forEach items="${rentCalculatorBeanList }" var="rentCalculatorBean">
								<c:choose>
									<c:when test="${rentCalculatorBean.getRegistrationNumber()  == bikeBean.getRegistrationNumber() }">
										<c:choose>
											<c:when test="${rentCalculatorBean.getPenaltyHours() > '0' }">
												<td>Charge for ${rentCalculatorBean.getHoursRented() } hours is <b>&#8377;${rentCalculatorBean.getActualCharge() } </b></td>
												<td>Penalty for extra time of ${rentCalculatorBean.getPenaltyHours() } hour is <b>&#8377;${rentCalculatorBean.getPenaltyCharge() } </b></td>
												<%-- <td>The total charge is <b>&#8377;${rentCalculatorBean.getTotalCharge() } </b></td> --%>
												<td>Advance paid is <b>&#8377;${rentCalculatorBean.getAdvancePaid() } </b></td>
											</c:when>
											<c:otherwise>
												<td>Charge for ${rentCalculatorBean.getHoursRented() } hours is <b>&#8377;${rentCalculatorBean.getActualCharge() } </b></td>
												<td>Nil</td>
												<%-- <td>The total charge is <b>&#8377;${rentCalculatorBean.getActualCharge() } </b></td> --%>
												<td>Advance paid is <b>&#8377;${rentCalculatorBean.getAdvancePaid() } </b></td>
											</c:otherwise>
										</c:choose>
										<c:choose>
											<c:when test="${rentCalculatorBean.getRemainingPayment() < '0' }">
												<td><b>&#8377;${-rentCalculatorBean.getRemainingPayment() } has be refunded</b></td>
											</c:when>
											<c:otherwise>
												<td><b>&#8377;${rentCalculatorBean.getRemainingPayment() } has to be paid</b></td>
											</c:otherwise>
										</c:choose>
										<c:choose>
											<c:when test="${rentCalculatorBean.getPenaltyHours() > '0' }">
												<td>
													<input type="hidden" name="bike-id"	value="${bikeBean.getBikeId() }" />
													<input type="hidden" name="registration-number"	value="${bikeBean.getRegistrationNumber() }" />
													<input type="submit" name="customer-functionality" value="Return this Bike" class="red-button" />
												</td>
											</c:when>
											<c:otherwise>
												<td>
													<input type="hidden" name="bike-id"	value="${bikeBean.getBikeId() }" />
													<input type="hidden" name="registration-number"	value="${bikeBean.getRegistrationNumber() }" />
													<input type="submit" name="customer-functionality" value="Return this Bike" class="green-button"/>
												</td>
											</c:otherwise>
										</c:choose>
									</c:when>
								</c:choose>
							</c:forEach>
						</tr>
					</form>
				</c:forEach>
			</tbody>
		</table>
	</div>
</body>
</html>