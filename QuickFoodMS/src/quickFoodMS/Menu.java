package quickFoodMS;

public class Menu {
	//Menu attributes
	private String instructions;
	private String item1;
	private String item2;
	private String item3;
	private double itemPrice1;
	private double itemPrice2;
	private double itemPrice3;
	private double total;
	private int quantity1;
	private int quantity2;
	private int quantity3;
	private String restaurantCity;
	private String restaurantName;
	
	//Menu Constructor
	public Menu(String instructions, String item1, String item2, String item3, double itemPrice1, double itemPrice2,
			double itemPrice3, double total, int quantity1, int quantity2, int quantity3, String restaurantCity,
			String restaurantName) {
		this.instructions = instructions;
		this.item1 = item1;
		this.item2 = item2;
		this.item3 = item3;
		this.itemPrice1 = itemPrice1;
		this.itemPrice2 = itemPrice2;
		this.itemPrice3 = itemPrice3;
		this.total = total;
		this.quantity1 = quantity1;
		this.quantity2 = quantity2;
		this.quantity3 = quantity3;
		this.restaurantCity = restaurantCity;
		this.restaurantName = restaurantName;
	}
	
	//Method to display order to invoice
	public String restuarantDetails() {
		String foodDetails = "You have ordered the following from " + this.restaurantName + " in " + this.restaurantCity + ": \n" + "\n" +
				isZero(this.quantity1) + isNullEmpty(this.item1) + " " + doubleZero(this.itemPrice1) + "\n" +
				isZero(this.quantity2) + isNullEmpty(this.item2) + " " + doubleZero(this.itemPrice2) + "\n" +
				isZero(this.quantity3) + isNullEmpty(this.item3) + " " + doubleZero(this.itemPrice3) + "\n" + "\n" +

		"Special Instructions: " + this.instructions + "\n" + "\n" +

		"Total: R" + this.total + "\n";

		return foodDetails;
	}
	
	//Method to check if there int is Zero and return String value or empty String
	public static String isZero(int count) {
		String amount;
		if(count <= 0) {
			amount = "";
		}
		else {
			amount = Integer.toString(count);
		}
		return amount;
	}
	
	//Method to check if String is empty or null and return x + string if not 
	public static String isNullEmpty(String str) {
	    if (str == null) {
	      return str = "";
	    }

	    /* check if string is empty */
	    else if(str.isEmpty()){
	      return "";
	    }

	    else {
	      return " x " + str;
	    }
	}
	
	//Method to check if there double is Zero and return String value or empty String 
	public static String doubleZero(double price) {
		String amount;
		if(price <= 0) {
			amount = "";
		}
		else {
			amount = "R" + Double.toString(price);
		}
		return amount;
	}

}
