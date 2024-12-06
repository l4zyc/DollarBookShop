package model;

public class CartItem {

	private String ProductId;
	private String Name;
	private String genre;
	private Integer Price;
	private Integer qty;
	private Integer Total;
	
	public CartItem(String productId, String name, String genre, Integer price, Integer qty, Integer total) {
		super();
		ProductId = productId;
		Name = name;
		this.genre = genre;
		Price = price;
		this.qty = qty;
		Total = total;
	}

	public String getProductId() {
		return ProductId;
	}

	public void setProductId(String productId) {
		ProductId = productId;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public Integer getPrice() {
		return Price;
	}

	public void setPrice(Integer price) {
		Price = price;
	}

	public Integer getQty() {
		return qty;
	}

	public void setQty(Integer qty) {
		this.qty = qty;
	}

	public Integer getTotal() {
		return Total;
	}

	public void setTotal(Integer total) {
		Total = total;
	}
	
	
	
	
}
