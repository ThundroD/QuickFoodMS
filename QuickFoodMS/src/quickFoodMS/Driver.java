package quickFoodMS;

public class Driver {
	//Driver attributes
	private String driverName;
	private String customerAddress;
	
	//Driver Constructor
	public Driver(String driverName, String customerAddress) {
		this.driverName = driverName;
		this.customerAddress = customerAddress;
	}
	
	//Method to print Driver details to invoice 
	public String displayDriver() {
		String output= this.driverName +" is nearest to the restaurant and so s/he will be delivering your order to you at:";
		output+="\n"+ this.customerAddress;
		return output;
	}

}
