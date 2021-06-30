# QuickFoodMS
## Description

### QuickFoodMS is a Java app that allows customers to order from restaurants across South Africa. The user is greeted by Quickfoods and asked to either create a new order or look up an exisiting order with their invoice number. If the user wants to create a new order, the user enters their details, selects the restaurant and items they'd like to order. The app then will display the invoice as well as create a text file for the user. The app uses the JDBC via MySQL to add the Customers details to the database. The app also pulls information, such as resturant name, phone number, location, driver's name taking the delivery, etc to insert into the customers invoice. If the user decides to look up an existing orer, the customer simply has to type their invoice number invoice number in and their order is displayed.

## Installation
### Download the files from Github. You'll need a Java IDE, to place the Java Files as well as a relational database vendor, such as MySQL to place the tables in. Set up the connection by replacing your sever information in all try catch blocks example below:
```
  try {
			Connection connection = DriverManager.getConnection(
			"jdbc:mysql://localhost:3306/QuickFoodMS",
			"root",
			"password"
			);
```
### Once complete hit run and QuickFoodMS should begain.

## Usage
### Answer all the questions when prompted and you should end up with a invoice in a text file as well as your invoice on the console. You can go back and check your order by view your tables as well selecting 1 in the begining of the application and input your invoice number. You're order will display on the console. Below are a few examples of how it works:
https://github.com/ThundroD/QuickFoodMS/blob/main/QuickFoodMS%20IMG/Create%20Order.png

