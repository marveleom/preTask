<%@ page language="java" contentType="text/html; charset=UTF-8"
		pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Home</title>
</head>
<body>
<h1>
	Hello world!  - 테스형!
</h1>

<P>  The time on the server is ${serverTime}. </P>

<ul>
    <c:forEach var="vo" items="${resultMap}">
    <li>${vo.id}. ${vo.todo} - ${vo.rdate}</li>
    </c:forEach>
</ul>


</body>
</html>
