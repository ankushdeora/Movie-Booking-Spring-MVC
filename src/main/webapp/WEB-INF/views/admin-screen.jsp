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
	<%-- c:out - <c:out value="${contextPath}"></c:out> --%>
	<c:choose>
		<c:when test="${sessionScope.user.role == 'admin'}">
			<a href="${contextPath}/admin/">Home</a>
			<br />

			<h2>Add a New Screen</h2>

			<form:form action="${contextPath}/admin/theatre/screen/add"
				method="post" commandName="screen">

				<table>
					<%-- <tr>
				<td>Posted By</td>
				<td>${sessionScope.user.username}</td>
				<td><form:hidden path="postedBy"
						value="${sessionScope.user.personID}" /></td>
			</tr> --%>

					<tr>
						<td>Theatre:</td>
						<td><form:select path="theatre" items="${theatres}"
								multiple="flase" required="required" /></td>
					</tr>

					<tr>
						<td>Screen Name:</td>
						<td><form:input type="text" path="name" size="30"
								required="required" /></td>
					</tr>

					<tr>
						<td>Movie:</td>
						<td><form:select path="movie" items="${movies}"
								multiple="false" required="required" /></td>
					</tr>

					<tr>
						<td colspan="2"><input type="submit" value="Add Screen" /></td>
					</tr>
				</table>

			</form:form>
		</c:when>
		<c:otherwise>
			<h3 style="color: red">Sorry You are not authorized to access
				this page!</h3>
		</c:otherwise>
	</c:choose>
</body>
</html>