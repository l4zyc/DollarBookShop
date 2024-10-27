package model;

public class Product {
	
	private String ProductID;
	private String Name;
	private String Genre;
	private Integer Stock;
	private Integer Price;
	
	public Product(String productID, String name, String genre, Integer stock, Integer price) {
		super();
		ProductID = productID;
		Name = name;
		Genre = genre;
		Stock = stock;
		Price = price;
	}
	
	public String getProductID() {
		return ProductID;
	}
	public void setProductID(String productID) {
		ProductID = productID;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getGenre() {
		return Genre;
	}
	public void setGenre(String genre) {
		Genre = genre;
	}
	public Integer getStock() {
		return Stock;
	}
	public void setStock(Integer stock) {
		Stock = stock;
	}
	public Integer getPrice() {
		return Price;
	}
	public void setPrice(Integer price) {
		Price = price;
	}
}
