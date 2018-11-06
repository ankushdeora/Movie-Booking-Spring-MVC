<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style>
.floating-box {
	float: left;
	width: 150px;
	height: 75px;
	margin: 10px;
	border: 3px solid #73AD21;
}

.after-box {
	clear: left;
	border: 3px solid red;
}
</style>
</head>
<body>
	<c:choose>
		<c:when test="${sessionScope.user.role == 'customer'}">
			<h4>Ticket Booking</h4>
			<h3>Welcome ${sessionScope.user.firstName}!</h3>
			<%-- <c:set var="i" value="${requestScope.latest}"></c:set> --%>
			<%-- <c:out value="${i}"></c:out> --%>
			<c:set var="contextPath" value="${pageContext.request.contextPath}" />
			<a href="${ contextPath }/movie/search" style="float: left">Search</a>
			<br style="clear: both" />
			<c:forEach var="latest" items="${requestScope.latest}">
				<div class="floating-box">
					Name: ${latest.movieTitle}<br> Actor: ${latest.leadActor}<br>
					Actress: ${latest.leadActress}<br> <a
						href="${contextPath}/movie/book?movieID=${latest.movieID}">Book
						ticket</a>
				</div>
			</c:forEach>
		</c:when>
		<c:otherwise>
			<h3>Please <a href="/finalPro/">Login</a> to access this page</h3>
		</c:otherwise>
	</c:choose>
</body>
</html>