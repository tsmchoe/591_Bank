import java.util.ArrayList;
import java.time.format.DateTimeFormatter;  
import java.time.LocalDateTime;    
public class Customer extends User {
	DBConnector db;


	public Customer(int userID, String fistName, String lastName, String username, String password, double balance) {
		super(userID, fistName, lastName, username, password, balance);
		db = new DBConnector();
	}

	public void createNewCustomer(String fistName, String lastName, String username, String password) {
		Customer newCustomer = new Customer(Func.generate_id(), firstName, lastName, username, password, 0);
		db.insertNewCustomer(newCustomer);
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
		// DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");  
		// LocalDateTime now = LocalDateTime.now(); 
		// Loan newLoan = new Loan(Func.generate_id(), amount, collateral, now.toString(), );
		// DBConnector.insertNewLoan(newLoan);
	}


	//SELECT, This is purely for the purpose of displaying if user choose to display it
	public ArrayList<CheckingsAccount> getAllCheckings() {
		ArrayList<CheckingsAccount> checkingsAccount = new ArrayList<>();
		// load all checkingsAccount that belong to this user from database
			// query using this.userID
			// checkingsAccount.add(new CheckingsAccount(String accountID, double initial_deposit, String userID, Currency currency));

		return checkingsAccount;
	}

	public ArrayList<SavingsAccount> getAllSavings() {
		ArrayList<SavingsAccount> savingsAccount = new ArrayList<>();
		// load all savingsAccount that belong to this user from database
			// query using this.userID
			// savingsAccount.add(SavingsAccount(String accountID, double initial_deposit, String userID, Currency currency, double interest_rate));
		return savingsAccount;
	}

	public ArrayList<SecurityAccount> getAllSecurities() {
		ArrayList<SecurityAccount> securityAccounts = new ArrayList<>();
		// load all securityAccount that belong to this user from database
			//query using this.userID
			//securityAccounts.add(new SecurityAccount());
		return securityAccounts;
	}

	public ArrayList<Loan> getAllLoans() {
		ArrayList<Loan> loans = new ArrayList<>();
		// load all loans that belong to this user from database
			//query using this.userID
			//loans.add(new Loan(String loanId, String userId, double amount, String collateral, String loan_date, String payment_date));
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
		//update database;
	}


}
