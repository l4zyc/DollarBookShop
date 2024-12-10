package util;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert.AlertType;
import model.Cart;
import model.CartItem;
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
	
	public String transactionDate() {
		LocalDate currentDate = LocalDate.now();
		return currentDate.toString();
	}
	
	public void insertTransaction(User user, CartItem carts) {
		String ID = setNewTransactionID();
		
		String transactionDate = transactionDate();
		
		String query = String.format("INSERT INTO transaction_headers VALUES ('%s', '%s','%s')", ID, user.getUserID(), transactionDate);
		connect.execUpdate(query);
		
		String query2 = String.format("INSERT INTO transaction_details VALUES ('%s','%s',%d)", ID,carts.getProductId(),carts.getQty());
		connect.execUpdate(query2);
			
		String updateStock = String.format("UPDATE products SET Stock = Stock - %d WHERE ProductID = '%s'", carts.getQty(),carts.getProductId());
			
		connect.execUpdate(updateStock);
		
		

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
	
	public String setNewTransactionID() {
		String ID = null;
		String query = "SELECT TransactionID FROM transaction_headers ORDER BY TransactionID DESC LIMIT 1";
		
		connect.rs = connect.execQuery(query);
		
		try {
			if(!connect.rs.next()) {
				return "TR001";
			}
			
			ID = connect.rs.getString("TransactionID");
			
			Integer num = Integer.parseInt(ID.substring(2));
			
			ID = String.format("TR%03d", num + 1);
			
			
			
		} catch (SQLException e) {
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
	
	public ObservableList<CartItem> getCartListData(User user){
	    String query = "SELECT c.ProductID, p.Name, p.Genre, p.Price, c.Quantity, (p.Price * c.Quantity) AS TotalPrice " +
                "FROM carts c JOIN products p ON c.ProductID = p.ProductID WHERE c.UserID = '" + user.getUserID() + "'";
	    ObservableList<CartItem> cartItems =  FXCollections.observableArrayList();
	    
	    connect.rs = connect.execQuery(query);
	    
	    try {
	    	while (connect.rs.next()) {
	    		   String productId = connect.rs.getString("ProductID");
	               String name = connect.rs.getString("Name");
	               String genre = connect.rs.getString("Genre");
	               int price = connect.rs.getInt("Price");
	               int qty = connect.rs.getInt("Quantity");
	               int total = connect.rs.getInt("TotalPrice");
	               
	               cartItems.add(new CartItem(productId, name, genre, price, qty, total));
				
			}
         
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	    
	    
	    return cartItems;
	    
	    
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

	
	public Integer getItemQty(User user, Product product) {
		ArrayList<Cart> user_items = getUserCartData(user);
		
		for (Cart item : user_items) {
			if(product.getProductID().equals(item.getProductID())) {
				return item.getQty();
			}
		}
		
		return 0;
	}
	
	public void updateCartQty(Cart cart) {
		String query = String.format("UPDATE carts "
				+ "SET Quantity = %d WHERE UserID = '%s' AND ProductID = '%s'",
				cart.getQty(), cart.getUserID(), cart.getProductID());
		
		connect.execUpdate(query);
	}
	
	public void updateCartItemQty(String ProductID, User user, int newQty) {
		String query = String.format("UPDATE carts SET quantity = %d WHERE UserID = '%s' AND ProductID = '%s'",
				newQty, user.getUserID(), ProductID);
		
		connect.execUpdate(query);
	}

	
	public void setQtyCart(User user,String ProductID, int newQty) {
		ObservableList<CartItem> cartItems = getCartListData(user);
		for(CartItem item : cartItems) {
			if(item.getProductId().equals(ProductID)) {
				item.setQty(newQty);
				
				int newTotal = item.getPrice()*newQty;
				updateCartItemQty(ProductID, user, newQty);
			}
			
		}
	}

	public void deleteCartItem(CartItem carts, User user) {
		String query = String.format("DELETE FROM carts WHERE UserID = '%s' AND ProductID = '%s'",user.getUserID(),carts.getProductId());
		
		connect.execUpdate(query);
	}
	
	public boolean inCart(User user, Product product) {
		ArrayList<Cart> carts = getUserCartData(user);
		
		for (Cart item : carts) {
			if(item.getProductID().equals(product.getProductID())) {
				return true;
			}
		}
		
		return false;
	}
	
	
	
	
	public void addNewProduct(Product product) {
//		String ID = setNewProductID();
		String query = String.format("INSERT INTO products VALUES ("
				+ "'%s', '%s', '%s', '%d', '%d')", product.getProductID(), product.getName(), product.getGenre(), product.getStock(), product.getPrice());
		
		connect.execUpdate(query);
	}
	
	public void updateProduct(Product product) {
		String query = String.format("UPDATE products "
				+ "SET Name = '%s', Genre = '%s', Stock = %d, Price = %d "
				+ "WHERE ProductID = '%s'", product.getName(), product.getGenre(), product.getStock(), product.getPrice(), product.getProductID());
		
		connect.execUpdate(query);
	}
	
	public void removeProductFromSomeoneCart(Product product) {
		String query = String.format("DELETE FROM carts WHERE ProductID = '%s'",product.getProductID());
		
		connect.execUpdate(query);
	}
	
	public void removeProduct(Product product) {
		String query = String.format("DELETE FROM products WHERE ProductID = '%s'", product.getProductID());
		
		connect.execUpdate(query);
	}
	
	
}