<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

</head>
<body>
	<%-- <c:out value="${requestScope.movie.movieTitle}"></c:out> --%>

	<%-- <c:set var="Theatres" target="${requestScope.listOfTheatres}"></c:set> --%>
	<%-- <c:out value="${requestScope.listOfTheatres}"></c:out> --%>
	<%-- <c:out value="${requestScope.listOfTheatres}"></c:out> --%>
	<c:set var="contextPath" value="${pageContext.request.contextPath}" />
	<c:choose>
		<c:when test="${not empty requestScope.listOfTheatres}">
			<a href="${contextPath}/user/">Home</a>
			<br />

			<h2>${requestScope.movie.movieTitle}</h2>
			<h2>Select a theatre!</h2>
			<form action="${contextPath}/movie/screen/book" method="post">
				<input type="hidden" value="${requestScope.movie.movieTitle}"
					name="movie" /> <select name="theatre">
					<c:forEach items="${requestScope.listOfTheatres}" var="theatre">
						<option value="${theatre.name}">${theatre.name}</option>
					</c:forEach>
				</select> <br> <br> <input type="submit" value="Next>>">
			</form>
		</c:when>
		<c:otherwise>
			<h3>No shows available for this movie: ${requestScope.movie.movieTitle}</h3>
			<a href="/finalPro/user/dashboard">Dashboard</a>
		</c:otherwise>
	</c:choose>
</body>
</html>