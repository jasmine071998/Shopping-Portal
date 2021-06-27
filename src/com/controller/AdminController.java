package com.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.beans.Products;
import com.model.DB;

/**
 * Servlet implementation class AdminController
 */
public class AdminController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String page = request.getParameter("page");
		if(page == null) {
			request.setAttribute("msg", "");
			request.getRequestDispatcher("admin/login.jsp").forward(request, response);
		}
		else
			doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String page = request.getParameter("page");
		if(page.equals("login-form")) {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		DB db = new DB();
		boolean x = false;
		try {
			x = db.validateAdmin(username, password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(x) {
			request.getRequestDispatcher("admin/index.jsp").forward(request, response);
			
		}
		else {
			request.setAttribute("msg", "Invalid Login");
			request.getRequestDispatcher("admin/login.jsp").forward(request, response);
		}
			
		}
		if(page.equals("delete")) {
			String id = request.getParameter("id");
			DB db = new DB();
			try {
				db.deleteProducts(Integer.parseInt(id));
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			request.setAttribute("msg", "Product Deleted");
			request.getRequestDispatcher("admin/index.jsp").forward(request, response);
		}
		if(page.equals("edit")) {
			String id = request.getParameter("id");
			DB db = new DB();
			try {
				Products p = db.getProductById(Integer.parseInt(id));
				request.setAttribute("p", p);
				
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			request.getRequestDispatcher("admin/edit-form.jsp").forward(request, response);
		}
		if(page.equals("edit-form")) {
			String id = request.getParameter("id");
			System.out.println(id);
			String price = request.getParameter("newPrice");
			String rating = request.getParameter("newRating");
			Products p = new Products();
			p.setId(Integer.parseInt(id));
			p.setPrice(price);
			p.setRating(rating);
			DB db = new DB();
			try {
				db.editProduct(p);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			request.getRequestDispatcher("admin/index.jsp").forward(request, response);
			
		}
	}

}
