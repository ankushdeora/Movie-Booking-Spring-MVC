<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	
	<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
	<%-- <c:out value="${ sessionScope.user }"></c:out> --%>
	<c:choose>
		<c:when test="${sessionScope.user.role == 'admin'}">
			<h1>Hello, Admin!</h1>
			<a href="${contextPath}/admin/theatre/add">Add Theatre</a> <br />
			<a href="${contextPath}/admin/theatre/screen/add">Add Screens</a> <br />
			<a href="${contextPath}/admin/movie/add">Add Movies</a> <br />
			
			<hr>
			<a href="${ contextPath }/user/logout">Logout</a>
		</c:when>
		<c:otherwise>
			<h3 style="color:red">Sorry You are not authorized to access this page!</h3>
			<a href="/finalPro/">Login</a>
		</c:otherwise> 
	</c:choose>
</body>
</html>