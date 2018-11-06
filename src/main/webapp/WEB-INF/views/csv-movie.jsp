<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<c:set var="contextPath" value="${pageContext.request.contextPath}"></c:set>
	
	<a href="${contextPath}/admin/">Home</a><br/>
	
	
	<form method="post" action="${contextPath}/csvMovie/insert.htm">
		Enter Csv File name <input type="text" name="csvFileName" /> 
		<input type="submit">
	</form>
	
	<c:set value="${requestScope.movies}" var="movies" />
	
	<c:if test="${not empty movies}">
		<br>
		<form method="post" action="${contextPath}/csvMovie/addToDB.htm">
			<input type="submit" value="Save to Database">
		</form>
		
		<form method="post" action="${contextPath}/csvMovie/insert.htm">
			<table>
				<tr>
					<th>Movie ID</th>
					<th>Movie Title</th>
					<th>Lead Actor</th>
					<th>Lead Actress</th>
					<th>Genre</th>
					<th>Year</th>
				</tr>
				<c:forEach items="${movies.values()}" var="movie">
					<tr>
						<td><input type=text name="${movie}" value="${movie.movieID}" /></td>
						<td><input type=text name="${movie}" value="${movie.movieTitle}"/></td>
						<td><input type=text name="${movie}" value="${movie.leadActor}"/></td>
						<td><input type=text name="${movie}" value="${movie.leadActress}"/></td>
						<td><input type=text name="${movie}" value="${movie.genre}"/></td>
						<td><input type=text name="${movie}" value="${movie.year}"/></td>
					</tr>
				</c:forEach>
			</table>
		</form>
	</c:if>
</body>
</html>