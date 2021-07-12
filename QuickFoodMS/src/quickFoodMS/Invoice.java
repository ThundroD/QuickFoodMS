package quickFoodMS;

public class Invoice {
	//invoice attributes
	private static int invoiceNumber;
	
	//getter for invoice number
	public static int getInvoiceNumber() {
		return invoiceNumber;
	}
	
	//setter for invoice number
	public Invoice(int invoiceNumber) {
		Invoice.invoiceNumber = invoiceNumber;
	}
	
	//constructor for invoice
	public static void setInvoiceNumber(int invoiceNumber) {
		Invoice.invoiceNumber = invoiceNumber;
	}
	
	//Method to create Invoice Number
	public static void createInvoice() {
		int min = 1000;
		int max = 999999;
		invoiceNumber = ((int) (Math.random() * (max - min + 1) + min));
	}
}

