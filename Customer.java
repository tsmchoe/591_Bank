
import java.util.*;
//Lets the user use the app as a customer
public class Customer extends User{
	public Customer(int id, String username, String password) {
		super(id, username, password);
	}

	public int acc_money;
	public ArrayList<Integer> loans;
	public Account accounts;
	
	public void playStocks() {
		if (acc_money > 5000) {
			//enter stock market
		}
	}
	
}
