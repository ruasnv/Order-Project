
public class Order {
	private int orderID;
	private String orderDate;
	private String[] foodCategories;
	
	public Order(int orderID, String orderDate, String[] foodCategories) {
		this.orderID = orderID;
		this.orderDate = orderDate;
		this.foodCategories = foodCategories;
	}

	public int getOrderID() {
		return orderID;
	}

	public void setOrderID(int orderID) {
		this.orderID = orderID;
	}

	public String getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}

	public String[] getFoodCategories() {
		return foodCategories;
	}

	public void setFoodCategories(String[] foodCategories) {
		this.foodCategories = foodCategories;
	}
	
	
}
