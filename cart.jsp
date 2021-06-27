<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib uri = "http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@taglib uri = "http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link href="https://fonts.googleapis.com/css?family=Ubuntu+Condensed" rel="stylesheet">
<link href="css/material.cs" rel="stylesheet">
<link href="css/material.min.css" rel="stylesheet">
<script type="text/javascript" src="js/material.js"></script>
<script type="text/javascript" src="js/material.min.js"></script>
<style>
	html, body {
    width: 100%;
	}
	table {
	    margin: 0 auto;
	}
</style>
</head>
<body>
	<h3 align="center" style="font-family: 'Ubuntu Condensed', sans-serif; font-size: 40px;">Your Cart</h3>
	
	
	<table>
	
		<tr>
			<c:set var="total" value="0"></c:set>
						<td align="center" >
							<h4 align="center" style="font-family: 'Ubuntu Condensed', sans-serif; font-size: 40px;">Products</h4>
							<br>
							<c:forEach items="${cartList }" var="i">
								<c:forEach items="${ productList }" var="c">
									<c:if test="${i == c.getId()}">
										<font size="6px;"><c:out value="${c.getName() }"/></font><br><br>
									</c:if>
								</c:forEach>
							</c:forEach>
							<br>
							
							<hr>
							<font size="5px;">Total Amount:</font>
						</td>
						<td> <font color="#ffffff">jnvoiafbvefbv</font> </td>
						<td align="center" >
							<h4 align="center" style="font-family: 'Ubuntu Condensed', sans-serif; font-size: 40px;">Prices</h4>
							<br>
							<c:forEach items="${cartList }" var="i">
								<c:forEach items="${ productList }" var="c">
									<c:if test="${i == c.getId()}">
										<font size="6px;"><c:out value="${c.getPrice() }"/></font>
										<a href="Controller?page=removeFromCart&id=${c.getId() }">Remove From Cart</a>
										<br><br>
										<c:set var="total" value="${total+c.getPrice() }"></c:set>
									</c:if>
								</c:forEach>
							</c:forEach>
							<br>
							<hr>
							<font size="5px;"><c:out value="${total }"></c:out></font>
							
						</td>
						
				
		</tr>
		
		<tr>
			<td align="center" colspan="4">
				<br>
				<form action="Controller">
					<!-- Raised button with ripple -->
					<input type="hidden" name="totalCost" value="${total }">
					<button class="mdl-button mdl-js-button mdl-button--raised mdl-js-ripple-effect" name="page" value="purchase">
						
					  PURCHASE
					</button>
				</form>
			</td>
		</tr>
		
		
	</table>

</body>
</html>