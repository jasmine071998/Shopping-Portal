<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h1>Admin Login</h1>
<%=request.getAttribute("msg") %>
<form method="post" action="admin">
<input type="hidden" name="page" value="login-form">
	Username: <input name="username" type="text"><BR><BR>
	Password: <input name="password" type="password">
	<input type="submit" value="Login">
</form>
</body>
</html>