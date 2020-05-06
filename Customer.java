import java.util.ArrayList;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Customer extends User {
	DBConnector db;


	//If were to load a customer
	public Customer(int userID,String firstName, String lastName, String username, String password, double balance) {
		super(userID, firstName, lastName, username, password, balance);
		db = new DBConnector();
	}

	//If were to create a new customer
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
		db.increaseBankBalance(Fees.OPENNING_FEE);

	}

	//creates a saving account
	public void createNewSavings(double initial_deposit, int userID, Currency currency) {
		if(initial_deposit >= Fees.SAVINGS_MINIMUM_BALANCE) {
			//store savings account in database, include interest rate as a field, can use Fees.SAVINGS_INTEREST
			SavingsAccount newSavings = new SavingsAccount(Func.generate_id(), initial_deposit, userID, currency, Fees.SAVINGS_INTEREST);
			db.insertNewAccount(newSavings);
			db.increaseBankBalance(Fees.OPENNING_FEE);
		}

	}

	//creates a securitys account
	public void createNewSecurity(double initial_deposit, int userID, Currency currency) {
		if(initial_deposit >= Fees.SECURITY_OPEN_LIMIT) {
			SecurityAccount newSecurity = new SecurityAccount(Func.generate_id(), initial_deposit, userID, currency);
			db.insertNewAccount(newSecurity);
			db.increaseBankBalance(Fees.OPENNING_FEE);
		}
	}
	
	//creates a new loan
	public void createNewLoan(int userId, double amount, String collateral) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		//Getting current date
		Calendar cal = Calendar.getInstance();
		Calendar calDue = Calendar.getInstance();
		calDue.add(Calendar.YEAR, 2);
		Loan newLoan = new Loan(Func.generate_id(), userId, amount, collateral, sdf.format(cal.getTime()), sdf.format(calDue.getTime()));
		db.insertNewLoan(newLoan);
	}


	//getters
	public ArrayList<CheckingsAccount> getAllCheckings() {
		ArrayList<CheckingsAccount> checkingsAccount = db.getCheckingsAccountByUser(this.userID);
		return checkingsAccount;
	}

	public ArrayList<SavingsAccount> getAllSavings() {
		ArrayList<SavingsAccount> savingsAccount = db.getSavingsAccountByUser(this.userID);
		return savingsAccount;
	}

	public ArrayList<SecurityAccount> getAllSecurities() {
		ArrayList<SecurityAccount> securityAccounts = db.getSecurityAccountByUser(this.userID);
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

	//Check all the loans that the user have, it is payment day, user pays loan interest
	public void pay_loan_increase() throws ParseException {
		for(Loan l: getAllLoans()) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String strloanDate = l.getLoanDate();
			Date loanDate = sdf.parse(strloanDate);
			Date currentDate = Calendar.getInstance().getTime();
			// we want the user to pay loan interest every 30 days, here we check if the diff between today and loan date is 30.
			if((currentDate.getTime() - loanDate.getTime()) % 30 < 1) {
				//bank gets fee
				double fee = l.getAmount() * Fees.LOAN_INTEREST;
				db.increaseBankBalance(fee);
				//decrease the money in user's checking's account
				getAllCheckings().get(0).withdraw(fee, new Currency(getAllCheckings().get(0).getCurrency()));

			}
		}

	}

	public String getFirstName(){
		return this.firstName;
	}

	public String getLastName(){
		return this.lastName;
	}


}
