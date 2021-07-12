package quickFoodMS;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Functions {
	
	// Method to Create Customer
	public static Customer createCustomer() {
		//create scanner
		Scanner input = new Scanner(System.in);
		
		//initiate variables used
		int customerId;
		int invoiceNumber;
		String name;
		String city;
		String address;
		String email;
		String phone;
		int min = 1000;
		int max = 999999;
		
		//create customer id
		customerId = ((int) (Math.random() * (max - min + 1) + min));
		
		//Create Order Number
		invoiceNumber = Invoice.getInvoiceNumber();
	
		//get name value
		System.out.println("Please enter your full name");
		name = input.nextLine();
		
		//get city value
		System.out.println("What city are you in?");
		city = input.nextLine();
		
		//get address value
		System.out.println("Please enter your address. -Example: '123 Main St'");
		address = input.nextLine();
		
		//get email value 
		System.out.println("Please enter your email");
		email = input.nextLine();
		
		//get phone value
		System.out.println("Please enter your phone number");
		phone = input.nextLine();
		
		//try - catch for connection
		try {
			Connection connection = DriverManager.getConnection(
			"jdbc:mysql://localhost:3306/QuickFoodMS",
			"root",
			"thunderD1"
			);
			
			//Create a direct line to the database for running our queries
			Statement statement = connection.createStatement();
			
			//Add invoice number to invoice table 
			statement.executeUpdate("INSERT INTO Invoice (`Order Number`) VALUES ('" + invoiceNumber + "')");
			// add in all values to row for customer table
			statement.executeUpdate("INSERT INTO Customer VALUES ('" + customerId + "','" + name + "','" + email + "','" + phone + "','" + address + "','" + city + "','" + invoiceNumber + "') ");
			//add in customer id to invoice row
			statement.executeUpdate("UPDATE Invoice SET `Customer ID` ='" + customerId + "'WHERE (`Order Number` ='" + invoiceNumber + "')");
			
			//Close up our connections
			statement.close();
			connection.close();
			
		} catch (SQLException e) {
			//We only want to catch a SQLException - anything else is off-limits for now. 
			e.printStackTrace();
			}
		//create new customer and return new customer 
		Customer newCustomer = new Customer (invoiceNumber, name, city, email, phone);
		return newCustomer;
	}
	
	//Method to creat Restaurant
	public static Restaurants createRestaurant() {
		//initate variables for Restaurant Object
		String restaurantName = null;
		String restaurantPhone = null;
		
		//decalre scanner
		Scanner input = new Scanner(System.in);
		
		//try - catch for connection
		try {
			Connection connection = DriverManager.getConnection(
			"jdbc:mysql://localhost:3306/QuickFoodMS",
			"root",
			"thunderD1"
			);
			
			//Create a direct line to the database for running our queries 
			Statement statement = connection.createStatement();
			//declare result sets
			ResultSet restaurantDisplay;
			ResultSet id;
			ResultSet city;
			ResultSet name;
			ResultSet phone;
			
			//get invoice number 
			int orderNumber = Invoice.getInvoiceNumber();
			
			//get city location
			city = statement.executeQuery("SELECT `City` FROM Customer WHERE `Invoice_Order Number` = '" + orderNumber + "'");
			String restCity = null;
			while(city.next()) {
				restCity = city.getString("City");
			}
			
			//Display options for customer to choose restaurant
			System.out.println("Please enter the number of the restaurant you wish to order from");
			restaurantDisplay = statement.executeQuery("SELECT * FROM Restaurant WHERE City='" + restCity +"'");
			while(restaurantDisplay.next()) {
				System.out.print(restaurantDisplay.getString("Value") + " ");
				System.out.print(restaurantDisplay.getString("Name") + "\n");
			}
			
			//get selected restaurant value 
			String selectedValue = input.nextLine();
			
			//get restaurant id 
			int restID = 0;
			id = statement.executeQuery("SELECT Restaurant_ID FROM Restaurant WHERE Value='" + selectedValue + "' AND CITY= '" + restCity + "'");
			while(id.next()) {
			restID = id.getInt("Restaurant_ID");
			}
			
			//Update Invoice Table with Restaurant ID 
			statement.executeUpdate("UPDATE Invoice SET `Restaurant_Restaurant_ID` = '" + restID + "'WHERE (`Order Number` ='" + orderNumber + "')");
			
			//get restaurant name 
			String restName = null;
			name = statement.executeQuery("SELECT `Name` FROM Restaurant WHERE Value='" + selectedValue + "'");
			while(name.next()) {
				restName = name.getString("Name");
			}
			
			//get restuarant phone 
			String restPhone = null;
			phone = statement.executeQuery("SELECT `Phone` FROM Restaurant WHERE Value='" + selectedValue + "'");
			while(phone.next()) {
				restPhone = phone.getString("Phone");
			}
			
			//take in values for restaurant constructor
			restaurantName = restName;
			restaurantPhone = restPhone;
			
			//Close up our connections 
			statement.close();
			connection.close();
			
			
			
		} catch (SQLException e) {
			//We only want to catch a SQLException
			e.printStackTrace();
			}
		
		//create new restaurant and return new restaurant
		Restaurants newRestaurant = new Restaurants(restaurantName, restaurantPhone);
		return newRestaurant;
	}
	
	//Method to return driver for order 
	public static Driver assignDriver() {
		//declare variables for driver constructor
		String driverName = null;
		String customerAddress = null;
		
		//try - catch for connection
		try {
			Connection connection = DriverManager.getConnection(
			"jdbc:mysql://localhost:3306/QuickFoodMS",
			"root",
			"thunderD1"
			);
			
			Statement statement = connection.createStatement();
			
			//assign orderNumber 
			int orderNumber = Invoice.getInvoiceNumber();
			
			//find city from database 
			ResultSet city = statement.executeQuery("SELECT `City` FROM Customer WHERE `Invoice_Order Number` = '" + orderNumber + "'");
			String restCity = null;
			while(city.next()) {
				restCity = city.getString("City");
			}
			
			//find customer address 
			ResultSet address = statement.executeQuery("SELECT `Address` FROM Customer WHERE `Invoice_Order Number` = '" + orderNumber + "'");
			String custAddress = null;
			while(address.next()) {
				custAddress = address.getString("Address");
			}
			
			//find driver with lowest trip count
			int trips = 0;
			ResultSet findTrips = statement.executeQuery("SELECT min(`Deliveries`) FROM Drivers WHERE `City` = '" + restCity + "'");
			while(findTrips.next()) {
				trips = findTrips.getInt("min(`Deliveries`)");
			}
			
			//select driver name with lowest trip count in city
			String selectedDriver = null;
			ResultSet findDriver = statement.executeQuery("SELECT `Name` FROM Drivers WHERE `Deliveries` = '" + trips + "' AND `City` = '" + restCity + "'");
			while(findDriver.next()) {
				selectedDriver = findDriver.getString("Name");
			}
			
			//update database 
			statement.executeUpdate("UPDATE Invoice SET `Driver_Name` = '" + selectedDriver + "'WHERE (`Order Number` ='" + orderNumber + "')");
			statement.executeUpdate("UPDATE Drivers SET `Deliveries` = '" + trips + "' + 1 WHERE (`Name` ='" + selectedDriver + "' AND `City` = '" + restCity + "')");
			
			//take in values for driver constructor 
			customerAddress = custAddress;
			driverName = selectedDriver;
			
			//Close up our connections 
			statement.close();
			connection.close();
			
		} catch (SQLException e) {
			//We only want to catch a SQLException 
			e.printStackTrace();
			}
		
		//create new driver, return new driver
		Driver newDriver = new Driver(driverName, customerAddress);
		return newDriver;
	}
	
	//Method to create new Menu
	public static Menu showMenu() {
		//declare scanner
		Scanner input = new Scanner(System.in);
		
		//initate variables for Menu constructor
		String instructions = null;
		String item1 = "";
		String item2 = "";
		String item3 = "";
		double itemPrice1 = 0;
		double itemPrice2 = 0;
		double itemPrice3 = 0;
		double total = 0;
		String restaurantName = null;
		String restaurantCity = null;
		int quantity1 = 0;
		int quantity2 = 0;
		int quantity3 = 0;
		
		//try - catch for connection
		try {
			Connection connection = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/QuickFoodMS",
					"root",
					"thunderD1"
			);
			//Create a direct line to the database for running our queries
			 
			Statement statement = connection.createStatement();
			
			// Declare Result Sets 
			ResultSet menuDisplay;
			ResultSet id;
			ResultSet name;
			ResultSet city;
			ResultSet dish1;
			ResultSet dish2;
			ResultSet dish3;
			ResultSet price1;
			ResultSet price2;
			ResultSet price3;
					
			//Create Restaurant and pull data from Restaurant
			int orderNumber = Invoice.getInvoiceNumber();
			
			//Get Restaurant ID to select correct Restaurant Menu for display later
			int restID = 0;
			id = statement.executeQuery("SELECT `Restaurant_Restaurant_ID` FROM Invoice WHERE (`Order Number` ='" + orderNumber + "')");
			while(id.next()) {
				restID = id.getInt("Restaurant_Restaurant_ID");
			}
			
			//Select Restaurant Name from database for Menu Constructor 
			String restName = null;
			name = statement.executeQuery("SELECT `Name` FROM Restaurant WHERE `Restaurant_ID` = '" + restID + "'");
			while(name.next()) {
				restName = name.getString("Name");
			}
			
			//Select City from database for Menu Constructor 
			String restCity = null;
			city = statement.executeQuery("SELECT `City` FROM Restaurant WHERE `Restaurant_ID` = '" + restID + "'");
			while(city.next()) {
				restCity = city.getString("City");
			}
			
			//Select Item1 from database for display 
			String order1 = null;
			dish1 = statement.executeQuery("SELECT `Item1` FROM Menus WHERE `Restaurant_Restaurant ID` = '" + restID + "'");
			while(dish1.next()) {
				order1 = dish1.getString("Item1");
				}
			
			//Select Item2 from database for display
			String order2 = null;
			dish2 = statement.executeQuery("SELECT `Item2` FROM Menus WHERE `Restaurant_Restaurant ID` = '" + restID + "'");
			while(dish2.next()) {
				order2 = dish2.getString("Item2");
				}
			
			//Select Item3 from database for display
			String order3 = null;
			dish3 = statement.executeQuery("SELECT `Item3` FROM Menus WHERE `Restaurant_Restaurant ID` = '" + restID + "'");
			while(dish3.next()) {
				order3 = dish3.getString("Item3");
				}
			
			//take item prices from database and turn them into variables
			double orderPrice1 = 0.00;
			price1 = statement.executeQuery("SELECT `Item1_Price` FROM Menus WHERE `Restaurant_Restaurant ID` = '" + restID + "'");
			while(price1.next()) {
				orderPrice1 = price1.getDouble("Item1_Price");
				}
			
			double orderPrice2 = 0.00;
			price2 = statement.executeQuery("SELECT `Item2_Price` FROM Menus WHERE `Restaurant_Restaurant ID` = '" + restID + "'");
			while(price2.next()) {
				 orderPrice2 = price2.getDouble("Item2_Price");
				}
			
			double orderPrice3 = 0.00;
			price3 = statement.executeQuery("SELECT `Item3_Price` FROM Menus WHERE `Restaurant_Restaurant ID` = '" + restID + "'");
			while(price3.next()) {
				orderPrice3 = price3.getDouble("Item3_Price");
				}
			
			//create counters
			double count1 = 0;
			double count2 = 0;
			double count3 = 0;
			
			//boolean for exiting menu display 
			boolean orderMore = true;
			
			//Display menu for user to order 
			while(orderMore == true) {
				System.out.println("Please enter the number of the item you'd like to order. Press 4 when finished \n");
				
				menuDisplay = statement.executeQuery("SELECT* FROM Menus WHERE `Restaurant_Restaurant ID` = '" + restID + "'");
				while(menuDisplay.next()) {
					System.out.print("1." + " " + menuDisplay.getString("Item1") + "\t");
					System.out.print(menuDisplay.getString("Item1_Price") + "\n");
					System.out.print("2." + " " + menuDisplay.getString("Item2") + "\t");
					System.out.print(menuDisplay.getString("Item2_Price") + "\n");
					System.out.print("3." + " " + menuDisplay.getString("Item3") + "\t");
					System.out.print(menuDisplay.getString("Item3_Price") + "\n");
					System.out.println("4. I'm finished with my order");
				}
				String menuSelection = input.nextLine();
				
				//If statements for order, if selected the database is updated and variables update
				if(menuSelection.equals("1")) {
					statement.executeUpdate("UPDATE Invoice SET `Item1` ='" + order1 + "'WHERE (`Order Number` ='" + orderNumber + "')");
					statement.executeUpdate("UPDATE Invoice SET `Item1_Price` ='" + orderPrice1 + "' WHERE (`Order Number` ='" + orderNumber + "')");
					item1 = order1;
					itemPrice1 = orderPrice1;
					count1++;
				}
				else if(menuSelection.equals("2")) {
					statement.executeUpdate("UPDATE Invoice SET `Item2` ='" + order2 + "'WHERE (`Order Number` ='" + orderNumber + "')");
					statement.executeUpdate("UPDATE Invoice SET `Item2_Price` ='" + orderPrice2 + "' WHERE (`Order Number` ='" + orderNumber + "')");
					item2 = order2;
					itemPrice2 = orderPrice2;
					count2++;
				}
				else if(menuSelection.equals("3")) {
					statement.executeUpdate("UPDATE Invoice SET `Item3` ='" + order3 + "'WHERE (`Order Number` ='" + orderNumber + "')");
					statement.executeUpdate("UPDATE Invoice SET `Item3_Price` ='" + orderPrice3 + "' WHERE (`Order Number` ='" + orderNumber + "')");
					item3 = order3;
					itemPrice3 = orderPrice3;
					count3++;
				}
				else if(menuSelection.equals("4")) {
					System.out.println("Thank you for your order!"
							+ "\n");
					orderMore = false;
				}
				else {
					System.out.println("Incorrect input, please try again \n");
				}
			}
			
			//take in special instructions and add to database 
			System.out.println("Do you have any special requests or instructions?");
			instructions = input.nextLine();
			statement.executeUpdate("UPDATE Invoice SET `Special_Instructions` ='" + instructions + "'WHERE (`Order Number` ='" + orderNumber + "')");
			
			//change counts into int to add to database
			int intCount1 = (int)count1;
			int intCount2 = (int)count2;
			int intCount3 = (int)count3;
			
			//update database with quantities needed 
			statement.executeUpdate("UPDATE Invoice SET `Quantity1` ='" + intCount1 + "'WHERE (`Order Number` ='" + orderNumber + "')");
			statement.executeUpdate("UPDATE Invoice SET `Quantity2` ='" + intCount2 + "'WHERE (`Order Number` ='" + orderNumber + "')");
			statement.executeUpdate("UPDATE Invoice SET `Quantity3` ='" + intCount3 + "'WHERE (`Order Number` ='" + orderNumber + "')");
			
			//calculate total and update database 
			double orderTotal = ((count1 * orderPrice1) + (count2 * orderPrice2) + (count3 * orderPrice3));
			statement.executeUpdate("UPDATE Invoice SET `Total` ='" + orderTotal + "'WHERE (`Order Number` ='" + orderNumber + "')");
			
			//give values for the restaurant object 
			total = Math.round(orderTotal * 100.00) / 100.00;
			restaurantName = restName;
			restaurantCity = restCity;
			quantity1 = (int)count1;
			quantity2 = (int)count2;
			quantity3 = (int)count3;
	
			//Close up our connections 
			statement.close();
			connection.close();
			
		} catch (SQLException e) {
			//We only want to catch a SQLException
			e.printStackTrace();
			}
		//create new Menu and return new menu 
		Menu newMenu = new Menu(instructions, item1, item2, item3, itemPrice1, itemPrice2, itemPrice3, total, quantity1, quantity2, quantity3, restaurantCity, restaurantName);
		return newMenu;
	}
	
	//Method to look up an existing order using invoice number
	 public static void lookUpOrder() {
		//Find invoice number
		Scanner input = new Scanner(System.in);
		System.out.println("Please enter the Invoice Number for the order you'd like to look up? \n");
		int invoiceNumber = input.nextInt();
		
		
		//try - catch for connection 
		try {
			Connection connection = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/QuickFoodMS",
					"root",
					"thunderD1"
			);
			
			//Create a direct line to the database for running our queries
			Statement statement = connection.createStatement();
			ResultSet id;
			ResultSet restName;
			ResultSet order;
			
			//Find restaurant id
			int restaurantID = 0;
			id = statement.executeQuery("SELECT `Restaurant_Restaurant_ID` FROM Invoice WHERE (`Order Number` ='" + invoiceNumber + "')");
			while(id.next()) {
				restaurantID = id.getInt("Restaurant_Restaurant_ID");
			}
			
			//Find restaurant name id
			String restaurantName = "";
			restName = statement.executeQuery("SELECT `Name` FROM Restaurant WHERE `Restaurant_ID` = '" + restaurantID + "'");
			while(restName.next()) {
				restaurantName = restName.getString("Name");
			}
			
			//Print out order
			order = statement.executeQuery("SELECT Item1, Quantity1, Item1_Price, Item2, Quantity2, Item2_Price, Item3, Quantity3, Item3_Price, Total FROM Invoice WHERE"
					+ "(`Order Number` ='" + invoiceNumber + "')");
			System.out.println("You order from " + restaurantName + " is below: \n");
			while(order.next()) {
				System.out.print("Item: " + order.getString("Item1") + "\t");
				System.out.print("Quantity: " + order.getInt("Quantity1") + "\t");
				System.out.print("Price: " + order.getFloat("Item1_Price") + "\n");
				System.out.print("Item: " + order.getString("Item2") + "\t");
				System.out.print("Quantity: " + order.getInt("Quantity2") + "\t");
				System.out.print("Price: " + order.getFloat("Item2_Price") + "\n");
				System.out.print("Item: " + order.getString("Item3") + "\t");
				System.out.print("Quantity: " + order.getInt("Quantity3") + "\t");
				System.out.print("Price: " + order.getFloat("Item3_Price") + "\n");
				System.out.print("Total: " + order.getFloat("Total") + "\n \n");
			}
			
			//Close up our connections 
			statement.close();
			connection.close();
			
		} catch (SQLException e) {
			// We only want to catch a SQLException. 
			e.printStackTrace();
			}
		
	}
	
}
