<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h3>${sessionScope.user}'s Bookings</h3>
	<c:set var="contextPath" value="${pageContext.request.contextPath}" />
	<a href="${contextPath}/user/dashboard">Dashboard</a>
	<br/>
	<c:forEach items="${requestScope.bookingList}" var="booking">
	    Movie:   		${booking.movie} <br/>
		Theatre: 		${booking.theatre} <br/>
		Screen:  		${booking.screen} <br/>
		Booking Date:	${booking.bookingDate}<br/>
		<hr>
	</c:forEach>
</body>
</html>