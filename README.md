# QuickFoodMS
## Description

### QuickFoodMS is a Java app that allows customers to order from restaurants across South Africa. The user is greeted by Quickfoods and asked to either create a new order or look up an exisiting order with their invoice number. If the user wants to create a new order, the user enters their details, selects the restaurant and items they'd like to order. The app then will display the invoice as well as create a text file for the user. The app uses the JDBC via MySQL to add the Customers details to the database. The app also pulls information, such as resturant name, phone number, location, driver's name taking the delivery, etc to insert into the customers invoice. If the user decides to look up an existing orer, the customer simply has to type their invoice number invoice number in and their order is displayed.

## Table of Contents
* Installation
* Usage
* Credits
* License

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
### Creating a new Order
![Creating a New Order](https://github.com/ThundroD/QuickFoodMS/blob/main/QuickFoodMS%20IMG/Create%20Order.png)
### Displaying Invoice
![Displaying Invoice](https://github.com/ThundroD/QuickFoodMS/blob/main/QuickFoodMS%20IMG/Invoice.png)
### Look up Existing Order
![Look Up](https://github.com/ThundroD/QuickFoodMS/blob/main/QuickFoodMS%20IMG/LookUp.png)
### Logging out
![Logging out](https://github.com/ThundroD/QuickFoodMS/blob/main/QuickFoodMS%20IMG/Goodbye.png)
### Customer Table Update
![Customer Table Update](https://github.com/ThundroD/QuickFoodMS/blob/main/QuickFoodMS%20IMG/Customer%20Table.png)
### Invoice Table Update
![Logging out](https://github.com/ThundroD/QuickFoodMS/blob/main/QuickFoodMS%20IMG/Customer%20Table.png)

### For reference below is the EER diagram of how the database is set up
![Logging out](https://github.com/ThundroD/QuickFoodMS/blob/main/QuickFoodMS%20IMG/QuickFoodMS_EER_Diagram.png)

## Credits

### I woul like to thank [Hyperion Dev](https://www.hyperiondev.com/) for giving me this task to create as well as the guidance of my mentor Dayle Klinkhamer for assisting me throughout the 6 months of this course.

## License

### MIT License

### Copyright (c) [2021] [Daniel Montague]

### Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

### The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

### THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.



