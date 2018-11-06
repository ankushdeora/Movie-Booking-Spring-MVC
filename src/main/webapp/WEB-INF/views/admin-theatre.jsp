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
	<%-- contextpath: <c:out value="${contextPath}"></c:out> --%>
	<c:choose>
		<c:when test="${sessionScope.user.role == 'admin'}">
			<a href="${contextPath}/admin/">Home</a>
			<br />

			<h2>Add a New Theatre</h2>


			<form:form action="${contextPath}/admin/theatre/add" method="post"
				commandName="theatre">

				<table>
					<tr>
						<td>Theatre Name:</td>
						<td><form:input path="name" size="30" required="required" />
							<font color="red"><form:errors path="name" /></font></td>
					</tr>

					<%-- <tr>
				<td>Number of screens:</td>
				<td><form:input path="name" size="30" required="required" /> <font color="red"><form:errors path="name" /></font></td>
			</tr> --%>

					<tr>
						<td colspan="2"><input type="submit" value="Create Theatre" /></td>
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