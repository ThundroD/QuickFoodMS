package quickFoodMS;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
			boolean exit = false;
			while(exit == false) {
				Scanner input = new Scanner(System.in);
				System.out.println("Hello! Welcome to QuickFoods \n"
						+ "Input the number of what you'd like to do \n"
						+ "1. Look up an exisiting order. \n"
						+ "2. Create a new order. \n"
						+ "3. Exit \n");
				String welcome = input.nextLine();
				
				//If statement to go through different commands depending on answer of user
				if(welcome.equals("1")) {
					Functions.lookUpOrder();
				}
				else if(welcome.equals("2")) {
					//Create Invoice
					Invoice.createInvoice();
			
					//Create new customer
					Customer newCustomer = Functions.createCustomer();
			
					//Create new Restaurant
					Restaurants newRestaurant = Functions.createRestaurant();
			
					//Create new Menu
					Menu newMenu = Functions.showMenu();
			
					//Create new Driver
					Driver newDriver = Functions.assignDriver();
			
					//Print invoice on console
					System.out.println("\n"
							+ "Please see your invoice below. \n"
							+ "One will also be printed for you for your records. \n");
					String printCustomer = newCustomer.customerDetails();
					System.out.println(printCustomer);
					String printMenu = newMenu.restuarantDetails();
					System.out.println(printMenu);
					String printDriver = newDriver.displayDriver();
					System.out.println(printDriver);
					String printRestaurant = newRestaurant.restaurantInfo();
					System.out.println(printRestaurant);
			
					//Print invoice in text file
					File invoice;
					PrintWriter writer;
					try {
						invoice = new File("invoice.txt");
						writer = new PrintWriter(invoice);
				
						writer.println(printCustomer);
						writer.println(printMenu);
						writer.println(printDriver);
						writer.println(printRestaurant );
				
						writer.close();
					} 
					catch(IOException e) {
						e.printStackTrace();
						System.out.println("Cannot find file");
					}
			
				}
				else if(welcome.equals("3")) {
					System.out.println("Goodbye, see you next time");
					exit = true;
				}
				else {
					System.out.println("Sorry I didn't recognize that command. Please try again \n");
				}
			}
		}
}

