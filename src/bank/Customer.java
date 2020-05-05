package src.bank;
import java.util.ArrayList;

public class Customer extends User {


	public Customer(String userID, String fistName, String lastName, String username, String password, double balance) {
		super(userID, fistName, lastName, username, password, balance);
	}

	// CREATE
	public void createNewCheckings(double initial_deposit, String userID, Currency currency) {
		//store checking account in database

	}

	public void createNewSavings(double initial_deposit, String userID, Currency currency) {
		if(initial_deposit >= Fees.SAVINGS_MINIMUM_BALANCE) {
			//store savings account in database, include interest rate as a field, can use Fees.SAVINGS_INTEREST
		}

	}

	public void createNewSecurity(double initial_deposit, String userID, Currency currency) {
		if(initial_deposit >= Fees.SECURITY_OPEN_LIMIT) {
			//store security account int database table
		}
	}

	public void createNewLoan(String userId, double amount, String collateral, String loan_date, String payment_date) {
		//store loan in database, need to communicate with front on on input format of the date
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
