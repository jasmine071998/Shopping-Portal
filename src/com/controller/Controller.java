package com.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.beans.Products;
import com.beans.User;
import com.model.DB;

/**
 * Servlet implementation class Controller
 */
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    ArrayList<Products> featuredList = new ArrayList<>();
    ArrayList<String> categoriesList = new ArrayList<>();
    static ArrayList<String> cartList = new ArrayList<>();
    ArrayList<Products> allProductsList = new ArrayList<>();
    String totalCost = "";
    ArrayList<Products> products = new ArrayList<>();
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
String page = request.getParameter("page");//null
		
		if(page == null){  
			request.setAttribute("msg", "");
			
			DB db = new DB();
			try {
				featuredList = db.getFeaturedProducts();
				categoriesList = db.getCategories();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			request.setAttribute("list", featuredList);
			request.setAttribute("category", categoriesList);
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}
		else{
				doPost(request,response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String page = request.getParameter("page");
		if(page.equals("sign-up")){
			request.getRequestDispatcher("sign-up.jsp").forward(request, response);
		}
		if(page.equals("sign-up-form")){
			String name = request.getParameter("name");
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			String email = request.getParameter("email");
			String address = request.getParameter("address");
			System.out.println(name + " " + username + " " + password + " " + email + " " + address);
			User user =new User();
			user.setName(name);
			user.setUsername(username);
			user.setPassword(password);
			user.setAddress(address);
			user.setEmail(email);
			//access database
			DB db = new DB();
			try {
				db.addUser(user);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			request.setAttribute("msg", "Sign Up Successfull");
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}
		if(page.equals("login-form")){
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			
			DB db = new DB();
			String details="";
			boolean x = false;
			try {
				x = db.validate(username, password);
				details = db.getDetails(username, password);
					
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(x == true){
				String [] x1 = details.split("\\%");
				request.setAttribute("name", x1[0]);
				request.setAttribute("email", x1[1]);
				request.setAttribute("address", x1[2]);
				request.setAttribute("price", totalCost);
				request.getRequestDispatcher("final.jsp").forward(request, response);
			}
			else{
				//HttpSession session = request.getSession();
				request.setAttribute("msg", "Not Valid User");
				request.getRequestDispatcher("login.jsp").forward(request, response);
			}
			
		}
		if(page.equals("productForm")) {
			String category = request.getParameter("category");
			DB db = new DB();
			try {
				allProductsList = db.getAllProducts(category);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			request.setAttribute("productList", allProductsList);
			request.setAttribute("category", categoriesList);
			request.getRequestDispatcher("products.jsp").forward(request, response);
		}
		if(page.equals("add-to-cart")) {
			String id = request.getParameter("id");
			if(cartList.contains(id)) {
				request.setAttribute("msg", "Product Already in cart");
			}
			else {
				cartList.add(id);
				request.setAttribute("msg", "Product Added");
			}
			
			request.setAttribute("cartList", cartList);
			request.setAttribute("productList", allProductsList);
			request.setAttribute("category", categoriesList);
			request.getRequestDispatcher("products.jsp").forward(request, response);
		}
		if(page.equals("Laptops")) {
			DB db = new DB();
			try {
				allProductsList = db.getAllProducts("Laptops");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			request.setAttribute("productList", allProductsList);
			request.setAttribute("cartList", cartList);
			request.setAttribute("category", categoriesList);
			request.getRequestDispatcher("products.jsp").forward(request, response);
		}
		if(page.equals("HeadPhones")) {
			DB db = new DB();
			try {
				allProductsList = db.getAllProducts("HeadPhones");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			request.setAttribute("productList", allProductsList);
			request.setAttribute("cartList", cartList);
			request.setAttribute("category", categoriesList);
			request.getRequestDispatcher("products.jsp").forward(request, response);
		}
		if(page.equals("Mobiles")) {
			DB db = new DB();
			try {
				allProductsList = db.getAllProducts("Mobiles");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			request.setAttribute("productList", allProductsList);
			request.setAttribute("category", categoriesList);
			request.setAttribute("cartList", cartList);
			request.getRequestDispatcher("products.jsp").forward(request, response);
		}
		if(page.equals("price-sort")){
			String price = request.getParameter("price");
			Products p = new Products();
			if(price.equals("low-high"))
				allProductsList = p.sortLowHigh(allProductsList);
			else
				allProductsList = p.sortHighLow(allProductsList);
			request.setAttribute("productList", allProductsList);
			request.setAttribute("cartList", cartList);
			request.setAttribute("category", categoriesList);
			request.getRequestDispatcher("products.jsp").forward(request, response);
		}
		if(page.equals("showCart")) {
			DB db = new DB();
			try {
				products = db.getProducts();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			request.setAttribute("productList", products);
			request.setAttribute("cartList", cartList);
			request.getRequestDispatcher("cart.jsp").forward(request, response);
		}
		if(page.equals("purchase")){
			totalCost = request.getParameter("totalCost");
			request.setAttribute("msg", "");
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}
		if(page.equals("removeFromCart")) {
			String id = request.getParameter("id");
			cartList.remove(id);
			request.setAttribute("productList", products);
			request.setAttribute("cartList", cartList);
			request.getRequestDispatcher("cart.jsp").forward(request, response);
		}
	}

}
