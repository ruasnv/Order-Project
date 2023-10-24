
public class TunaCan extends Product {
	private int netWeight, drainedWeight;
	private String[] ingredients = {"tuna", "sunflower oil", "salt"};
	
	
	public TunaCan(int productID) {
		super(productID);
		this.netWeight = 75;
		this.drainedWeight = 50;
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

	public String[] getIngredients() {
		return ingredients;
	}

	public void setIngredients(String[] ingredients) {
		this.ingredients = ingredients;
	}

	public String getName() {
		return "tuna";
	}
}
