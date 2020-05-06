package src.bank;
import java.util.ArrayList;

public class Bank_Manager extends User {
	DBConnector db;

	public Bank_Manager(int userID, String fistName, String lastName, String username, String password,
			double balance) {
		super(userID, fistName, lastName, username, password, balance);
		db = new DBConnector();
	}

	// Get all the transactions that happend on a particular date
	public ArrayList<Transaction> getDailyReports(String date) {
		ArrayList<Transaction> transactions = db.getTransactions_Date(date);
		return transactions;
	}

	@Override
	public double getBalance() {
		return balance;
	}

}
