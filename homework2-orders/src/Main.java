import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		
//		creating food piles for each different food	category
		ArrayStack<Product> tunaCanPile = new ArrayStack<Product>();
		ArrayStack<Product> cornCanPile = new ArrayStack<Product>();
		ArrayStack<Product> puddingPacketPile = new ArrayStack<Product>();
		ArrayStack<Product> noodlePacketPile = new ArrayStack<Product>();

//		auxiliary arrays
		Product[] tunaCanArray = new Product[30];
		Product[] cornCanArray = new Product[30];
		Product[] puddingPacketArray = new Product[30];
		Product[] noodlePacketArray = new Product[30];
		
//		OPERATIONS 1 AND 2
//		creating tuna can products and adding them to a pile
		for (int i  = 0; i < 30; i++) {
			TunaCan tunaCan = new TunaCan(i);
			tunaCanArray[i] = tunaCan;
		}
		
		for (int i  = 29; i >= 0; i--) {
			tunaCanPile.push(tunaCanArray[i]);
		}
		
//		creating corn can products and adding them to a pile
		for (int i  = 0; i < 30; i++) {
			CornCan cornCan = new CornCan(i);
			cornCanArray[i] = cornCan;
		}
		
		for (int i  = 29; i >= 0; i--) {
			cornCanPile.push(cornCanArray[i]);
		}
		
//		creating pudding packet products and adding them to a pile
		for (int i  = 0; i < 30; i++) {
			PuddingPacket puddingPacket = new PuddingPacket(i);
			puddingPacketArray[i] = puddingPacket;
		}
		
		for (int i  = 29; i >= 0; i--) {
			puddingPacketPile.push(puddingPacketArray[i]);
		}
			
//		creating noodle packet products and adding them to a pile
		for (int i  = 0; i < 30; i++) {
			InstantNoodlePacket noodlePacket = new InstantNoodlePacket(i);
			noodlePacketArray[i] = noodlePacket;
		}
		
		for (int i  = 29; i >= 0; i--) {
			noodlePacketPile.push(noodlePacketArray[i]);
		}
		
		
//		printing piles BEFORE processing the orders
		System.out.println("\nPILES OF FOOD PACKAGES BEFORE PROCESSING THE ORDERS");	
		
//		printing tuna can pile
		System.out.println("\n*** PILE OF TUNA CANS ***\n");
		print(tunaCanPile);	
		
//		printing corn can pile
		System.out.println("\n*** PILE OF CORN CANS ***\n");
		print(cornCanPile);		
		
//		printing pudding packet pile
		System.out.println("\n*** PILE OF PUDDING PACKETS ***\n");
		print(puddingPacketPile);
		
//		printing instant noodle packet pile
		System.out.println("\n*** PILE OF INSTANT NOODLE PACKETS ***\n");		
		print(noodlePacketPile);		

		
//		OPERATIONS 3 AND 4
//		reading orders.csv and adding orders to a queue
		
		ArrayQueue<Order> orders = new ArrayQueue<Order>();
		
		File file = new File("orders.csv");
		
		BufferedReader bReader = new BufferedReader(new FileReader(file));
		
		String line = null;
		
		while((line = bReader.readLine()) != null) {
			String[] splittedLine = line.split(",");
			int orderID = Integer.parseInt(splittedLine[0]);
			String orderDate = splittedLine[1];
			String[] foodCategories = {splittedLine[2], splittedLine[3], splittedLine[4]};
			Order order = new Order(orderID, orderDate, foodCategories);
			orders.enqueue(order);
		}
		
//		printing the waiting line of orders
		
		System.out.println("\nTHE WAITING LINE OF ORDERS\n");
//		System.out.println("(order id - order date - food categories)\n");
		for (int i = 0; i < 30; i++) {
			Order nextOrder = orders.dequeue();
			System.out.println(nextOrder.getOrderID() + "   " +
							   nextOrder.getOrderDate() + "   " +
							   nextOrder.getFoodCategories()[0] + "   " +
							   nextOrder.getFoodCategories()[1] + "   " +
							   nextOrder.getFoodCategories()[2] + "   ");
			orders.enqueue(nextOrder);
		}
			
		
//		OPERATIONS 5 AND 6
//		creating cargo packets and adding them to a list (in a sorted way)		
		AList<CargoPacket> tempCargoList = new AList<CargoPacket>();
		AList<CargoPacket> cargoPacketsList = new AList<CargoPacket>();
		
		for (int i = 0; i < 30; i++) {
			Product product1 = null, product2 = null, product3 = null;
			Order nextOrder = orders.dequeue();
			int cargoID = nextOrder.getOrderID();
			String processDate = nextOrder.getOrderDate();
			String[] foodCategories = nextOrder.getFoodCategories();
			String foodCategory_1 = foodCategories[0].replaceAll("\\s","");
			String foodCategory_2 = foodCategories[1].replaceAll("\\s","");
			String foodCategory_3 = foodCategories[2].replaceAll("\\s","");
			
			if (foodCategory_1.equals("tuna")) {
				product1 = (TunaCan) tunaCanPile.pop();
			}
			else {
				product1 = (CornCan) cornCanPile.pop();
			}
			
			if (foodCategory_2.equals("corn")) {
				product2 = (CornCan) cornCanPile.pop();
			}
			else {
				product2 = (PuddingPacket) puddingPacketPile.pop();
			}
			
			if (foodCategory_3.equals("pudding")) {
				product3 = (PuddingPacket) puddingPacketPile.pop();
			}
			else {
				product3 = (InstantNoodlePacket) noodlePacketPile.pop();
			}
			
			
			CargoPacket cargoPacket = new CargoPacket(cargoID, processDate, product1, product2, product3);
			
			tempCargoList.add(cargoPacket);
		}
		
//		sorting the list of cargo packets	
		int temp = 0;
		while (temp < 30) {
			for (int i = 0; i < 30; i++) {
			if (tempCargoList.getEntry(i).getCargoID() == temp+1) {
				cargoPacketsList.add(tempCargoList.getEntry(i));
				temp++;
				}		
			}
		}
		
//		printing the list of cargo packets
		
		System.out.println("\nTHE LIST OF CARGO PACKETS\n");
//		System.out.println("(cargo id - process date - packaged food products)\n");
		for (int i = 0; i < 30; i++) {
			CargoPacket nextCargo = cargoPacketsList.getEntry(i);
			System.out.println(nextCargo.getCargoID() + " " + 
							   nextCargo.getProcessDate() + " " + 
					           nextCargo.getPackagedProducts().getEntry(0).getName() + " " + 
							   nextCargo.getPackagedProducts().getEntry(1).getName() + " " + 
					           nextCargo.getPackagedProducts().getEntry(2).getName());
		}
		

//		printing piles AFTER processing the orders
		System.out.println("\nPILES OF FOOD PACKAGES AFTER PROCESSING THE ORDERS");	
		
//		printing tuna can pile
		System.out.println("\n*** PILE OF TUNA CANS ***\n");
		print(tunaCanPile);
		
//		printing corn can pile
		System.out.println("\n*** PILE OF CORN CANS ***\n");
		print(cornCanPile);
		
//		printing pudding packets pile
		System.out.println("\n*** PILE OF PUDDING PACKETS ***\n");
		print(puddingPacketPile);	
		
//		printing instant noodle packet pile
		System.out.println("\n*** PILE OF INSTANT NOODLE PACKETS ***\n");		
		print(noodlePacketPile);	
		
		
		
//		OPERATION 8
//		printing the expiration dates of the packaged products of the cargo packet with ID 25
		for (int i = 0; i < 30; i++) {
			CargoPacket nextCargo = cargoPacketsList.getEntry(i);
			if (nextCargo.getCargoID() == 25) {
				System.out.println("\nTHE EXPIRATION DATES OF THE PACKAGED \nPRODUCTS OF THE CARGO PACKET WITH ID 25\n");
				for (int j = 0; j <3; j++) {
					System.out.println(j+1 + ". " + nextCargo.getPackagedProducts().getEntry(j).getExpirationDate()); 
				}
			}
		}
		
		
//		OPERATION 9
//		removing the cargo packet with ID 20 (which is index 19) from the cargo packets list 
//		and print the contents of the list again
		
		cargoPacketsList.remove(19);
		System.out.println("\nTHE LIST OF CARGO PACKETS \n(WITHOUT THE CARGO WITH ID 20)\n");
		for (int i = 0; i < 29; i++) {
			CargoPacket nextCargo = cargoPacketsList.getEntry(i);
			System.out.println(nextCargo.getCargoID() + " " + 
							   nextCargo.getProcessDate() + " " + 
					           nextCargo.getPackagedProducts().getEntry(0).getName() + " " + 
							   nextCargo.getPackagedProducts().getEntry(1).getName() + " " + 
					           nextCargo.getPackagedProducts().getEntry(2).getName());
		}
		
		bReader.close();
	}
	
	
//	printing the contents of the piles
	public static void print(ArrayStack<Product> pile) {
		ArrayStack<Product> tempPile = new ArrayStack<Product>();
		if (pile.isEmpty()) {
			System.out.println("The pile is empty.");
		}
		
		while (!pile.isEmpty()) {
			Product nextItem = pile.pop();
			System.out.println(nextItem.getName().toUpperCase() + " " + nextItem.getExpirationDate());
			tempPile.push(nextItem);
		}
		
		while (!tempPile.isEmpty()) {
			Product nextItem = tempPile.pop();
			pile.push(nextItem);
		}
	}
	
}
