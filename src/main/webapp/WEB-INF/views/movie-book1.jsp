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
	<c:set var="contextPath" value="${pageContext.request.contextPath}" />

	<a href="${contextPath}/user/">Home</a><br/>
	<h2>Select a Screen!</h2>
	<form:form action = "${contextPath}/movie/bookFinal" method="post" commandName="booking">
		<table>
			<tr>
				<td>Movie</td>
				<td>${requestScope.movie}</td>
				<td><form:hidden path="movie" value="${requestScope.movie}" /></td>
			</tr>

			<tr>
				<td>Theatre</td>
				<td>${requestScope.theatre}</td>
				<td><form:hidden path="theatre" value="${requestScope.theatre}" /></td>
			</tr>

			<tr>
				<td>User</td>
				<td>${sessionScope.user}</td>
				<td><form:hidden path="user" value="${sessionScope.user}" /></td>
			</tr>

			<tr>
				<td>Screen:</td>
				<td><form:select path="screen" items="${theatreScreen}" multiple="false" required="required" /></td>
			</tr>
			
			<tr>
				<td colspan="2"><input type="submit" value="Book Ticket" /></td>
			</tr>
		</table>
	</form:form>
</body>
</html>