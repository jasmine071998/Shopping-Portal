<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="java.util.ArrayList"%>
	<%@ page import="com.beans.Products" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>MY SHOPPING PORTAL</title>
<link href="https://fonts.googleapis.com/css?family=Ubuntu+Condensed" rel="stylesheet">

</head>
<body>
	<h1 align="center" style="font-family: 'Ubuntu Condensed', sans-serif; font-size: 40px;">WELCOME TO MY SHOPPING PORTAL</h1>
	<div>
		
		<table id="featuredProducts" style="width:100%; float:left">
			<tr style="font-size: 30px" align="center">
				<td style="font-family: 'Ubuntu Condensed', sans-serif;">Featured Products</td>
			</tr>
			<tr style="font-size: 20px" align="center">
			
				<td>
				
				<%
				ArrayList<Products> list = (ArrayList<Products>)request.getAttribute("list");
				ArrayList<String> categoriesList = (ArrayList<String>)request.getAttribute("category");
				
				
				for(String category : categoriesList){
					out.println("<h3 style=\"font-family: 'Ubuntu Condensed', sans-serif;\"><a href=\""+"Controller?page=productForm&category="+category+"\""+">"+category+"</a></h3>");
					for(Products p : list){
						if(category.equals(p.getCategory()))
							out.println("<strong>"+p.getName()+"</strong>"+"\t"+"\t"+"Price: Rs."+p.getPrice()+ "<BR>"
									    );
						
							
						}
				}
				
				%>		
						
				</td>
			</tr>
				
			
		</table> 
		
	</div>
	
</body>
</html>