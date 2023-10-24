
public class PuddingPacket extends Product {
	private int netWeight;
	private String flavor;
	
	public PuddingPacket(int productID) {
		super(productID);
		this.netWeight = 120;
		this.flavor = "banana";
	}

	public int getNetWeight() {
		return netWeight;
	}

	public void setNetWeight(int netWeight) {
		this.netWeight = netWeight;
	}

	public String getFlavor() {
		return flavor;
	}

	public void setFlavor(String flavor) {
		this.flavor = flavor;
	}
	
	public String getName() {
		return "pudding";
	}
}
