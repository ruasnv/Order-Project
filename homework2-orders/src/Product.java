
public class Product {
	private String expirationDate;
	private int productID;
	
	public Product(int productID) {
		this.setProductID(productID);
		expirationDate = setExpDates()[productID];
	}

	
//	setting expiration dates of the products
	public static String[] setExpDates() {
		int year = 22;
		String[] expirationDates = new String[30];
		String[] months = {"Jul", "Aug", "Sep", "Oct", "Nov", "Dec", "Jan", "Feb", "Mar", "Apr", "May", "Jun"};
		for(int i = 0; i < 30; i++) {
			if (months[i%12].equals("Jan")){
				year++;
			}
			expirationDates[i] = months[i%12] + "-" + year;
		}
		
		return expirationDates;
	}
	
	
	public String getExpirationDate() {
		return expirationDate;
	}
	
	
	public void setExpirationDate(String expirationDate) {
		this.expirationDate = expirationDate;
	}
	
	
	public String getName() {
		return null;
	}

	
	public int getProductID() {
		return productID;
	}

	
	public void setProductID(int productID) {
		this.productID = productID;
	}
	
}
