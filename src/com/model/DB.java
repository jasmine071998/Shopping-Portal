package com.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.beans.Products;

import com.beans.User;



public class DB {
	//Step 1: Create Variables
		String username = "root";
		String password = "";
		String dbname = "shopping_portal";
		String url = "jdbc:mysql://localhost:3306/" + dbname;
		String driver = "com.mysql.jdbc.Driver";
		//HashMap<String, String> map = new HashMap<>();
		Connection conn;
		private ArrayList<Products> featuredList = new ArrayList<>();
		private ArrayList<String> categoriesList = new ArrayList<>();
		private ArrayList<Products> allProductsList = new ArrayList<>();
		private ArrayList<Products> productsId = new ArrayList<>();

		private void dbConnect(){
			//Step 2: Load The Driver
			try{
				Class.forName(driver);//Register the driver
				conn = DriverManager.getConnection(url ,username, password);
			}
			catch(ClassNotFoundException e){
				e.printStackTrace();
			} 
			catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		
		private void dbClose(){
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		public void addUser(User user)throws SQLException{
			dbConnect();
			String sql = "insert into user (username,password,address,email,name) values(?,?,?,?,?)";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, user.getUsername());
			statement.setString(2, user.getPassword());
			statement.setString(3, user.getAddress());
			statement.setString(4,  user.getEmail());
			statement.setString(5,  user.getName());
			statement.executeUpdate();
			dbClose();
			
		}

		public boolean validate(String username, String password) throws SQLException {
			dbConnect();
			int count = 0;
			String sql = "SELECT * FROM user WHERE username=? AND password=?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			ps.setString(2, password);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				count = 1;
			}
			dbClose();
			if(count==1)
				return true;
			return false;
		}
		
		public boolean validateAdmin(String username, String password)throws SQLException {
			dbConnect();
			int count = 0;
			String sql = "SELECT * FROM admin WHERE username=? AND password=?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			ps.setString(2, password);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				count = 1;
			}
			dbClose();
			if(count==1)
				return true;
			return false;
		}
		
		public String getDetails(String username, String password) throws SQLException{
			dbConnect();
			String details="";
			String sql = "SELECT name,email,address FROM user WHERE username=? AND password=?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			ps.setString(2, password);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				details = rs.getString("name") + "%" + rs.getString("email") + "%" +rs.getString("address");
			}
			dbClose();
			return details;
			
		}
		
		public ArrayList<Products> getFeaturedProducts() throws SQLException{
			dbConnect();
			
			String sql = "SELECT * FROM products WHERE featured='true'";
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			
			while(rs.next()){
				Products p = new Products();
				p.setId(rs.getInt("id"));
				p.setName(rs.getString("name"));
				p.setPrice(rs.getString("price"));
				p.setCategory(rs.getString("category"));
				//System.out.println(p);
				featuredList.add(p);
				
			}
			dbClose();
			return featuredList;
			
		}
		
		public ArrayList<Products> getAllProducts(String category) throws SQLException{
			dbConnect();
			
			String sql = "SELECT id,name,price FROM products where category='"+category+"'";
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				Products p = new Products();
				p.setId(rs.getInt("id"));
				p.setName(rs.getString("name"));
				p.setPrice(rs.getString("price"));
				allProductsList.add(p);
			}
			
			dbClose();
			return allProductsList;
			
		}
		
		public ArrayList<String> getCategories() throws SQLException{
			dbConnect();
			String categories = "SELECT category FROM products GROUP BY category";
			PreparedStatement ps1 = conn.prepareStatement(categories);
			ResultSet rs1 = ps1.executeQuery();
			while(rs1.next()) {
				categoriesList.add(rs1.getString("category"));
			}
			dbClose();
			return categoriesList;
		}
		
		public ArrayList<Products> getProducts() throws SQLException{
			dbConnect();
			String sql = "SELECT * FROM products";
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Products p = new Products();
				p.setId(rs.getInt("id"));
				p.setName(rs.getString("name"));
				p.setPrice(rs.getString("price"));
				productsId.add(p);
			}
			dbClose();
			return productsId;
		}
		
		public void deleteProducts(int id) throws SQLException {
			dbConnect();
			String sql = "DELETE FROM products where id="+ id;
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.executeUpdate();
			dbClose();
		}
		public Products getProductById(int id) throws SQLException{
			dbConnect();
			
			String sql = "SELECT * FROM products where id="+id;
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			Products p = new Products();
			while(rs.next()) {
			p.setId(rs.getInt("id"));
			p.setName(rs.getString("name"));
			p.setPrice(rs.getString("price"));
			p.setFeatured(rs.getString("featured"));
			p.setRating(rs.getString("rating"));
			}
			dbClose();
			return p;
		}
		public void editProduct(Products p) throws SQLException{
			dbConnect();
			String sql = "UPDATE products set price=?, rating=? WHERE id=?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, p.getPrice());
			ps.setString(2, p.getRating());
			ps.setInt(3, p.getId());
			ps.executeUpdate();
			dbClose();
		}
}
