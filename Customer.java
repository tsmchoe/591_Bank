import java.util.ArrayList;
import java.text.SimpleDateFormat;
import java.util.Calendar; 
public class Customer extends User {
	DBConnector db;




	public Customer(int userID,String firstName, String lastName, String username, String password, double balance) {
		super(userID, firstName, lastName, username, password, balance);
		db = new DBConnector();
	}


	public Customer(String firstName, String lastName, String username, String password, double balance) {
		super(Func.generate_id(), firstName, lastName, username, password, balance);
		db = new DBConnector();
		db.insertNewCustomer(this);
	}

	//Get all the transactions made by this user
	public ArrayList<Transaction> getTransactionsByDate(String date) {
		return db.getUserTransactions_Date(userID, date);
	}
	

	//creates a new checking account
	public void createNewCheckings(double initial_deposit, int userID, Currency currency) {
		CheckingsAccount newCheckings = new CheckingsAccount(Func.generate_id(), initial_deposit, userID, currency);
		db.insertNewAccount(newCheckings);

	}

	public void createNewSavings(double initial_deposit, int userID, Currency currency) {
		if(initial_deposit >= Fees.SAVINGS_MINIMUM_BALANCE) {
			//store savings account in database, include interest rate as a field, can use Fees.SAVINGS_INTEREST
			SavingsAccount newSavings = new SavingsAccount(Func.generate_id(), initial_deposit, userID, currency, Fees.SAVINGS_INTEREST);
			db.insertNewAccount(newSavings);
		}

	}

	public void createNewSecurity(double initial_deposit, int userID, Currency currency) {
		if(initial_deposit >= Fees.SECURITY_OPEN_LIMIT) {
			SecurityAccount newSecurity = new SecurityAccount(Func.generate_id(), initial_deposit, userID, currency);
			db.insertNewAccount(newSecurity);
		}
	}

	public void createNewLoan(int userId, double amount, String collateral) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		//Getting current date
		Calendar cal = Calendar.getInstance();
		Calendar calDue = Calendar.getInstance();
		calDue.add(Calendar.YEAR, 2);
		Loan newLoan = new Loan(Func.generate_id(), userId, amount, collateral, sdf.format(cal.getTime()), sdf.format(calDue.getTime()));
		db.insertNewLoan(newLoan);
	}



	public ArrayList<CheckingsAccount> getAllCheckings() {
		ArrayList<CheckingsAccount> checkingsAccount = db.getCheckingsAccountByUser(this.userID);
		return checkingsAccount;
	}

	public ArrayList<SavingsAccount> getAllSavings() {
		ArrayList<SavingsAccount> savingsAccount = db.getSavingsAccountByUser(this.userID);
		return savingsAccount;
	}

	public ArrayList<SecurityAccount> getAllSecurities() {
		ArrayList<SecurityAccount> securityAccounts = db.getSecutrityAccountByUser(this.userID);
		return securityAccounts;
	}

	public ArrayList<Loan> getAllLoans() {
		ArrayList<Loan> loans = db.getAllUserLoans(this.userID);
		return loans;
	}

	@Override
	public double getBalance() {
		balance = calculateTotalBalance();
		return balance;
	}

	private double calculateTotalBalance() {
		return calculateCheckingsBalance() + calculateSavingsBalance() + calculateSecurityBalance();
	}

	private double calculateCheckingsBalance() {
		double acc = 0;
		ArrayList<CheckingsAccount> checkingAccounts = getAllCheckings();
		for(CheckingsAccount c : checkingAccounts) {
			acc += c.getBalance();
		}
		return acc;
	}

	private double calculateSavingsBalance() {
		double acc = 0;
		ArrayList<SavingsAccount> savingsAccounts = getAllSavings();
		for(SavingsAccount s : savingsAccounts) {
			acc += s.getBalance();
		}
		return acc;

	}

	private double calculateSecurityBalance() {
		double acc = 0;
		ArrayList<SecurityAccount> securityAccounts = getAllSecurities();

		for(SecurityAccount s : securityAccounts) {
			acc += s.getBalance();
		}
		return acc;
	}

	//Called when a fee is charged(for example, closing/openning account)
	public void decreaseBalance(double amt) {
		balance -= amt;
	}


}
