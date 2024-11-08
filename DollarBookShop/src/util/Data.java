package util;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert.AlertType;
import model.Cart;
import model.Product;
import model.User;

public class Data {
	
	private Connect connect = Connect.getInstance();
	
	public void insertUser(User user) {
	    String query = String.format("INSERT INTO users VALUES ('%s', '%s', '%s', '%s', '%s', '%s')",
	            user.getUserID(), user.getEmail(), user.getUsername(),
	            user.getPassword(), user.getDate().toString(), user.getRole());
		
		connect.execUpdate(query);
		func.showAlert(AlertType.INFORMATION, "User", "User Added!");
	}
	
	public User getUserData(String email, String password) throws NullPointerException { 
		
		String query = "SELECT * FROM users";
		
		connect.rs = connect.execQuery(query);
		User user = null;
		
		try {
			while(connect.rs.next()) {
				String UserID = connect.rs.getString("UserID");
				String Email = connect.rs.getString("Email");
				String Username = connect.rs.getString("Username");
				String Password = connect.rs.getString("Password");
				Date DOB = connect.rs.getDate("DOB");
				String Role = connect.rs.getString("Role");
			
				if(Email.equals(email) && password.equals(password)) {
					user = new User(UserID, Email, Username, Password, DOB, Role);
					
					return user;
				}
			}
		} catch (Exception e) {
			System.out.println("User Doesnt exist");
		}
		
		return null;
	}
	
	public ArrayList<User> getUserListData() {
		String query = "SELECT * FROM users";
		
		
		ArrayList<User> users = new ArrayList<User>();
		connect.rs = connect.execQuery(query);
		
		try {
			while(connect.rs.next()) {
				String ID = connect.rs.getString("UserID");
				String email = connect.rs.getString("Email");
				String username = connect.rs.getString("Username");
				String passwd = connect.rs.getString("Password");
				Date date = connect.rs.getDate("DOB");
				String role = connect.rs.getString("Role");
				
				users.add(new User(ID, email, username, passwd, date, role));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return users;
	}
	
	public String setNewUserID() {
		String ID = null;
		String query = "SELECT UserID FROM users "
				+ "ORDER BY UserID "
				+ "DESC LIMIT 1";
		
		connect.rs = connect.execQuery(query);
		
		try {
			if((!connect.rs.next())) {
				return "US001";
			}
			
			ID = connect.rs.getString("UserID");
			Integer num = Integer.parseInt(ID.substring(2));
			
			ID = String.format("US%03d", (num + 1));
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return ID;
	}
	
	public String setNewProductID() {
		String ID = null;
		String query = "SELECT ProductID FROM products"
				+ " ORDER BY ProductID DESC "
				+ "LIMIT 1";
		
		connect.rs = connect.execQuery(query);
		
		try {
			if(!connect.rs.next()) {
				return "PD001";
			}
			
			ID = connect.rs.getString("ProductID");
			Integer num = Integer.parseInt(ID.substring(2));
			
			ID = String.format("PD%03d", (num + 1));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return ID;
	}
	
	public ObservableList<Product> getProductListData() {
		String query = "SELECT * FROM products";
		ObservableList<Product> products = FXCollections.observableArrayList();
		
		connect.rs = connect.execQuery(query);
		
		try {
			while(connect.rs.next()) {
				String ID = connect.rs.getString("ProductID");
				String Name = connect.rs.getString("Name");
				String Genre = connect.rs.getString("Genre");
				Integer Stock = connect.rs.getInt("Stock");
				Integer Price = connect.rs.getInt("Price");
				
				products.add(new Product(ID, Name, Genre, Stock, Price));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return products;
	}
	
	public User getUserInstanceFromEmail(String email) {
		try {
			ArrayList<User> users = getUserListData();
			for (User user : users) {
				if(user.getEmail().equals(email)) {
					return user;
				}
			}
		} catch(NullPointerException e) {
			System.out.println("User not Found");
		}
		return null;
	}
	
	public void insertItemtoCart(User user, Product product, Integer qty) {
		String query = String.format(
				"INSERT INTO carts VALUES ("
				+ " '%s', '%s', %d "
				+ ")", user.getUserID(), product.getProductID(), qty);
		
		connect.execUpdate(query);
	}
	
	public ArrayList<Cart> getUserCartData(User user) {
		String query = String.format(
				"SELECT * FROM carts WHERE"
				+ " UserID = '%s'", user.getUserID());
		
		connect.rs = connect.execQuery(query);
		ArrayList<Cart> carts = new ArrayList<>();
	
		
		try {
			while(connect.rs.next()) {
				String userID = connect.rs.getString("UserID");
				String productID = connect.rs.getString("ProductID");
				Integer qty = connect.rs.getInt("Quantity");
				
				carts.add(new Cart(userID, productID, qty));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return carts;
	}

	
	public Integer checkIteminCart(User user, Product product) {
		ArrayList<Cart> user_items = getUserCartData(user);
		
		for (Cart item : user_items) {
			if(product.getProductID().equals(item.getProductID())) {
				return item.getQty();
			}
		}
		
		return 0;
	}
	
	
	public void checkStock(Product product) {
		
	}
}
