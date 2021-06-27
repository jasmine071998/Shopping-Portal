<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<form method="get" action="admin">
	<input type="hidden" name="page" value="edit-form">
	<input type="hidden" name="id" value="${p.getId() }">
	<strong> Name: <c:out value="${p.getName() }"></c:out><br></strong>
	Price: <c:out value="${p.getPrice() }"></c:out><br>
	Ratings: <c:out value="${p.getRating() }"></c:out><hr>
	
	
	Enter New Price: <input type="text" name="newPrice"><br>
	
	Enter The New Rating of the Product: <input type="text" name="newRating"><br>
	<input type="submit" value="Edit Changes">
</form>

</body>
</html>