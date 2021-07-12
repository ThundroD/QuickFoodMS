package quickFoodMS;

public class Restaurants {
	//restaurant attributes
	private String restaurantName;
	private String restaurantPhone;
	
	public Restaurants(String restaurantName, String restaurantPhone) {
		this.restaurantName = restaurantName;
		this.restaurantPhone = restaurantPhone;
	}
	
	public String restaurantInfo() {
		String contactRestaurant = "\n" + "If you need to contact " + this.restaurantName + ", their number is " + this.restaurantPhone + ".";
		return contactRestaurant;
	}
}
