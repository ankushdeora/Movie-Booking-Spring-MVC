<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Final Project</title>
</head>
<body>
	<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
	<c:set value="${ sessionScope.user }" var="user"></c:set>
	<c:if test="${ not empty user}">

	</c:if>
	<c:choose>
		<c:when test="${ not empty user}">
			<c:choose>
				<c:when test="${user.role == 'admin'}">
					<c:redirect url="${ contextPath }/admin/"></c:redirect>
				</c:when>
				<c:otherwise>
					<c:redirect url="/user/dashboard"></c:redirect>
				</c:otherwise>
			</c:choose>
		</c:when>
		<c:otherwise>
			<a href="user/register.htm">Register a new User</a>
			<br />

			<h2>Login</h2>
			<form action="user/login" method="post">

				<table>
					<tr>
						<td>User Name:</td>
						<td><input name="username" size="30" required="required" /></td>
					</tr>

					<tr>
						<td>Password:</td>
						<td><input type="password" name="password" size="30"
							required="required" /></td>
					</tr>

					<tr>
						<td colspan="2"><input type="submit" value="Login" /></td>
					</tr>

				</table>

			</form>
		</c:otherwise>
	</c:choose>

</body>
</html>