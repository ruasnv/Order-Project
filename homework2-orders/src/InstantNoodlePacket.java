
public class InstantNoodlePacket extends Product {
	private int netWeight, simmerDuration;
	
	public InstantNoodlePacket(int productID) {
		super(productID);
		this.netWeight = 120;
		this.simmerDuration = 3;
	}

	public int getNetWeight() {
		return netWeight;
	}

	public void setNetWeight(int netWeight) {
		this.netWeight = netWeight;
	}

	public int getSimmerDuration() {
		return simmerDuration;
	}

	public void setSimmerDuration(int simmerDuration) {
		this.simmerDuration = simmerDuration;
	}
	
	public String getName() {
		return "noodle";
	}
	
}
