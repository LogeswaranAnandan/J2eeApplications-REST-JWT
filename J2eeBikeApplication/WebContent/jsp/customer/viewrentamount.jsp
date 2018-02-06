<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="customerheader.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="css/basicstyle.css" />
<link rel="stylesheet" type="text/css" href="css/rentamount.css" />
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<title>Rented Amount</title>
</head>
<body>
	<div class="rent-amount-container">
		<c:choose>
			<c:when test="${rentCalculatorBean.getPenaltyHours() > '0' }">
				Charge for ${rentCalculatorBean.getHoursRented() } hours is <b>${rentCalculatorBean.getActualCharge() } </b><br />
				Penalty for extra time of ${rentCalculatorBean.getPenaltyHours() } hour is <b>${rentCalculatorBean.getPenaltyCharge() } </b><br />
				The total charge is <b>${rentCalculatorBean.getTotalCharge() } </b><br />
				Advance paid is <b>${rentCalculatorBean.getAdvancePaid() } </b><br />
				<span class="rent-amount"><b>The amount to be paid is ${rentCalculatorBean.getRemainingPayment() }</b></span>
			</c:when>
			<c:otherwise>
				Charge for ${rentCalculatorBean.getHoursRented() } hours is <b>${rentCalculatorBean.getActualCharge() } </b><br />
				Advance paid is <b>${rentCalculatorBean.getAdvancePaid() } </b><br />
				<span class="rent-amount"><b>The amount to be paid is ${rentCalculatorBean.getRemainingPayment() }</b></span><br/>
				<c:choose>
					<c:when test="${status == 'Return this Bike' }">
						Thanks for returning the bike on time <br/>
					</c:when>
					<c:otherwise>
						Kindly return the bike within <b>${rentCalculatorBean.getRemainingRentTime() } hours</b> to avoid penalty charges.
					</c:otherwise>
				</c:choose>
			</c:otherwise>
		</c:choose>
	</div>
	<script src="javascript/basicscript.js"></script>
</body>
</html>



<%-- 
Charge for ${rentCalculatorBean.getHoursRented() } hours is ${rentCalculatorBean.getActualCharge } <br/>
			Penalty for extra time of ${rentCalculatorBean.getPenaltyHours() } hour is ${rentCalculatorBean.getPenaltyCharge() } <br/>
			The total charge is ${rentCalculatorBean.getTotalCharge() } <br/>
			Advance paid is ${rentCalculatorBean.getAdvancePaid() } <br/>
			The amount to be paid is ${rentCalculatorBean.getRemainingPayment() }
	 --%>

<%-- 
	 Charge for ${rentCalculatorBean.getHoursRented() } hours is ${rentCalculatorBean.getActualCharge() } <br/>
			Your still have ${rentCalculatorBean.getRemainingRentTime } hours left to return the bike.
			 --%>