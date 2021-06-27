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


<sql:setDataSource user="root" password="" driver="com.mysql.jdbc.Driver" url="jdbc:mysql://localhost:3306/shopping_portal" var="ds"/>

<sql:query var="result" dataSource="${ds }">
	select * from products
</sql:query>
<c:set var="i" value="0"></c:set>
<c:out value="${msg }"></c:out>
<c:forEach items="${result.rows }" var="row">
	<c:set var="i" value="${i+1 }"></c:set>
	<c:out value="${i }"></c:out>
	<c:out value="${row.name }"></c:out>-- Rs.<c:out value="${row.price }"></c:out>
	<a href="<%=request.getContextPath()%>/admin?page=delete&id=${row.id}" >Delete</a> | <a href="<%=request.getContextPath()%>/admin?page=edit&id=${row.id}">Edit</a><hr>
</c:forEach>

</form>
</body>
</html>