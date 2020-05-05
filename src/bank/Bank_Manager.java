package src.bank;
import java.util.ArrayList;

public class Bank_Manager extends User {
	public Bank_Manager(int userID, String fistName, String lastName, String username, String password,
			double balance) {
		super(userID, fistName, lastName, username, password, balance);
	}

	// Get all the transactions that happend on a particular date
	public ArrayList<Transaction> getDailyReports(String date) {
		ArrayList<Transaction> transactions = new ArrayList<>();
		//query all trasactions with the date
			//transactions.add(new Transaction(String transactionID, String userid, Account account, double amount, Currency currency, String date))
		return transactions;
	}

	@Override
	public double getBalance() {
		return balance;
	}

}
