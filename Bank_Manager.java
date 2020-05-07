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

	//Gets all the pending request from the bank
	public ArrayList<Request> SeeAllPendingRequest() {
		return bank.getAllRequest();
	}

	//Approves a request
	public void ApproveRequest(Request r) {
		r.approveRequest();
	}


	//Sees the balance in bank
	@Override
	public double getBalance() {
		return bank.getBalance();
	}

}
