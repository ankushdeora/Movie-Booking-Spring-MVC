<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<c:choose>
		<c:when test="${sessionScope.user.role == 'admin'}">
			<h1>Please enter the details below:</h1>
			<c:set var="a" value="${requestScope.movie}"></c:set>
			<c:set var="contextPath" value="${pageContext.request.contextPath}" />
			<a href="${contextPath}/csvMovie/insert.htm">Upload Csv</a>

			<form:form commandName="movie">
            Movie Title: <form:input path="movieTitle"></form:input>
				<br />
            Lead Actor: <form:input path="leadActor"></form:input>
				<br />
            Lead Actress: <form:input path="leadActress"></form:input>
				<br />
            Genre: <form:input path="genre"></form:input>
				<br />
            Year: <form:input path="year"></form:input>
				<br />
				<input type="submit" value="Add Movie" />
			</form:form>
		</c:when>
		<c:otherwise>
			<h3 style="color: red">Sorry You are not authorized to access
				this page!</h3>
		</c:otherwise>
	</c:choose>
</body>
</html>