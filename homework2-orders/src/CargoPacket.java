
public class CargoPacket {
	private int cargoID;
	private String processDate;
	private AList<Product> packagedProducts = new AList<Product>();
	
	public CargoPacket(int cargoID, String processDate, Product product1, Product product2, Product product3) {
		this.cargoID = cargoID;
		this.processDate = processDate;
		packagedProducts.add(product1);
		packagedProducts.add(product2);
		packagedProducts.add(product3);
	}

	public int getCargoID() {
		return cargoID;
	}

	public void setCargoID(int cargoID) {
		this.cargoID = cargoID;
	}

	public String getProcessDate() {
		return processDate;
	}

	public void setProcessDate(String processDate) {
		this.processDate = processDate;
	}

	public AList<Product> getPackagedProducts() {
		return packagedProducts;
	}

	public void setPackagedProducts(AList<Product> products) {
		this.packagedProducts = products;
	}
	
	
	

}
