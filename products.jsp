<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib uri = "http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@taglib uri = "http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
    <%@ page import="java.util.ArrayList"%>
	<%@ page import="com.beans.Products" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Products</title>
<link href="https://fonts.googleapis.com/css?family=Ubuntu+Condensed" rel="stylesheet">

</head>
<body>
	<div>
		<h1 align="center" style="font-family: 'Ubuntu Condensed', sans-serif; font-size: 40px;">Shop Your Products</h1>
		<div style="text-align:right; vertical-align: top;">
			<c:set var="x" value="0"></c:set>
			<c:forEach items="${cartList }" var="i">
				<c:set var="x" value="${x+1 }"></c:set>
			</c:forEach>
			<a href="Controller?page=showCart">cart(<c:out value="${x }"/>)</a>
			<c:out value="${msg}"></c:out>
		</div>
	</div>
	
	
	<table style="width:80%; padding-left: 400px;">
		<tr style="font-size: 20px">
			<td  align="center">
				<h3>Categories</h3>
				
				<%
				ArrayList<String> categoriesList = (ArrayList<String>)request.getAttribute("category");
				for(String category : categoriesList)
					out.println("<h4><a href=\""+"Controller?page="+category+"\">"+category+"</a></h4>");
				%><br><br>
				Sort By:
				<form method = "post" action = "Controller">
				<input type = "hidden" name = "page" value = "price-sort">
				<select name = "price">
				<option value= "low-high">price low-high</option>
				<option value= "high-low">price high-low</option>
				</select>
				<input type = "submit" value = "GO">
				</form>
			</td>	
				
			
			<td align="center">
				<h3>Products</h3>
				
				<%
				ArrayList<Products> products = (ArrayList<Products>)request.getAttribute("productList");
				for(Products p : products){
					out.print(p);
					out.println("<a href=\"Controller?page=add-to-cart&id="+p.getId()+"\">Add To Cart</a>"+"<BR><BR>");
				}
				%>
			</td>
		</tr>
	</table>
</body>
</html>