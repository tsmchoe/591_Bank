
import java.util.*;
//Lets the user use the app as a customer
public class Customer extends User{
	private ArrayList<Loan> loans;
	private ArrayList<SecurityAccount> securityAccounts;
	private ArrayList<SavingsAccount> savingsAccounts;
	private ArrayList<CheckingsAccount> checkingAccounts;
	
	public Customer(String firstName, String lastName, int id, String username, String password) {
		super(firstName, lastName, id, username, password);
	}


	// public void playStocks() {
	// 	if (acc_money > 5000) {
	// 		//enter stock market
	// 	}
	// }
	
}
