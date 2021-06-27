<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Payment Successfull</title>
<link href="https://fonts.googleapis.com/css?family=Ubuntu+Condensed" rel="stylesheet">
</head>
<body style="text-align: center;">

<h1 align="center" style="font-family: 'Ubuntu Condensed', sans-serif; font-size: 40px;">Thank You!</h1>
Name: <%=request.getAttribute("name") %><br>
Total Cost: <%=request.getAttribute("price") %><br>
Email ID: <%=request.getAttribute("email") %><br>
Address: <%=request.getAttribute("address") %>


</body>
</html>