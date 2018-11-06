<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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

<script>
function ajaxEvent(contextPath) {

    var xmlHttp;
    try // Firefox, Opera 8.0+, Safari
    {
        xmlHttp = new XMLHttpRequest();
    } catch (e) {
        try // Internet Explorer
        {
            xmlHttp = new ActiveXObject("Msxml2.XMLHTTP");
        } catch (e) {
            try {
                xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
            } catch (e) {
                alert("Your browser does not support AJAX!");
                return false;
            }
        }
    }

    xmlHttp.onreadystatechange = function() {
        if (xmlHttp.readyState == 4) {
        	console.log(xmlHttp.responseText);
        	myObj = JSON.parse(xmlHttp.responseText);
        	console.log(myObj);
            /* document.getElementById("movies").innerHTML = xmlHttp.responseText; */
            txt = "";
            console.log("contextPAth"+contextPath);
            for(key in myObj){
            	txt += " \
            	<div class='floating-box'>\
    				Name:  		"+myObj[key].movieTitle+"<br>\
    				Actor: 		"+myObj[key].leadActor+"<br>\
    				Actress: 	"+myObj[key].leadActress+"<br>\
    				<a href='"+contextPath+"/movie/book?movieID="+myObj[key].movieID+"'>Book ticket</a>\
    			</div>"
            }
            document.getElementById("movies").innerHTML = txt;
        }
    }
    
    var keyword = document.getElementsByName("keyword")[0].value;
    var searchVar = document.getElementsByName("searchBy");
    var searchBy = "";
    for(var i = 0; i < searchVar.length; i++){
    	console.log(searchVar[i].checked)
    	if(searchVar[i].checked){
    		searchBy = searchVar[i].value;
    	}
    }
	console.log(keyword);
	console.log(searchBy);
    xmlHttp.open("POST", "../service.htm?keyword="+keyword+"&searchBy="+searchBy, true);
    xmlHttp.send();
}
</script>
</head>
<body>
	<c:set var="contextPath" value="${pageContext.request.contextPath}" />
	<c:out value="${contextPath}"></c:out>
	<h1>Search Movies!</h1>
	<form action="${contextPath}/search" method="GET">
		Keyword: <input type="text" name="keyword" onkeyup="ajaxEvent('${contextPath}')"/><br /> 
		<input type="radio" name="searchBy" value="movieTitle" checked="checked">Title<br /> 
		<input type="radio" name="searchBy" value="leadActor">Actor<br /> 
		<input type="radio" name="searchBy" value="leadActress">Actress<br /> 
		<input type="submit" value="Search Movies" />
	</form>
	<div id = "movies">
		
	</div>
</body>
</html>