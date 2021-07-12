package quickFoodMS;

public class Customer {
	//Customer Attributes */
	private int invoiceNumber;
	private String name;
	private String city;
	private String email;
	private String phone;

	public Customer (int invoiceNumber, String name, String city, String email, String phone) {
		/** Customer Constructor*/
		this.invoiceNumber = invoiceNumber;
		this.name = name;
		this.city = city;
		this.email = email;
		this.phone = phone;
	}

	//Method to Display Customer Details
	public String customerDetails() {
		String details = "Order number " + this.invoiceNumber + "\n" +
				"Customer: " + this.name + "\n" +
				"Email: " + this.email + "\n" +
				"Phone number: " + this.phone + "\n" +
				"Location: " + this.city + "\n";
		
		return details;
	}
}
	

	
	

