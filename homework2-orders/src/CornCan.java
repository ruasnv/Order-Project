
public class CornCan extends Product{
	private int netWeight, drainedWeight;
	private String productionCountry;
	
	public CornCan(int productID) {
		super(productID);
		this.netWeight = 220;
		this.drainedWeight = 132;
		this.productionCountry = "Turkey";
	}

	public int getNetWeight() {
		return netWeight;
	}

	public void setNetWeight(int netWeight) {
		this.netWeight = netWeight;
	}

	public int getDrainedWeight() {
		return drainedWeight;
	}

	public void setDrainedWeight(int drainedWeight) {
		this.drainedWeight = drainedWeight;
	}

	public String getProductionCountry() {
		return productionCountry;
	}

	public void setProductionCountry(String productionCountry) {
		this.productionCountry = productionCountry;
	}
	
	public String getName() {
		return "corn";
	}
}
