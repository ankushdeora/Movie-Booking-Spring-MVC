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
	<c:choose>
		<c:when test="${sessionScope.user.role == 'customer'}">
			<h4>Movie Ticket Booking</h4>
			<h3>Welcome ${sessionScope.user.firstName}!</h3>

			<c:set var="contextPath" value="${pageContext.request.contextPath}" />
			<a href="${ contextPath }/movie/browse" style="float: left">Browse
				Movies</a>
			<br />
			<a href="${ contextPath }/movie/myBookings" style="float: left">My Bookings</a>
			<br>
			<hr>
			<a href="${ contextPath }/user/logout">Logout</a>
		</c:when>
		<c:otherwise>
			<h3>
				Please <a href="/finalPro/">Login</a> to access this page
			</h3>
		</c:otherwise>
	</c:choose>
</body>
</html>