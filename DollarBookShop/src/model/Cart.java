package model;

public class Cart {
	private String userID;
	private String ProductID;
	private Integer qty;
	
	
	
	public Cart(String userID, String productID, Integer qty) {
		super();
		this.userID = userID;
		ProductID = productID;
		this.qty = qty;
	}
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	public String getProductID() {
		return ProductID;
	}
	public void setProductID(String productID) {
		ProductID = productID;
	}
	public Integer getQty() {
		return qty;
	}
	public void setQty(Integer qty) {
		this.qty = qty;
	}
	
	

	
}
