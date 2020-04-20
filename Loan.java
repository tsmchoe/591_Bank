
public class Loan {
	protected int id;
	protected int userId;
	protected double amount;
	protected boolean collateral;
	protected String loan_date;
	protected String payment_date;
	public Loan(int id, int userId, double amount, boolean collateral, String loan_date, String payment_date) {
		this.id = id;
		this.userId = userId;
		this.amount = amount;
		this.collateral = collateral;
		this.loan_date = loan_date;
		this.payment_date = payment_date;
	}
	//Setter and getter
	public int getLoanID() {
		return id;
	}
	public void setLoanID(int id) {
		this.id = id;
	}
	public int getUser() {
		return userId;
	}
	public void setUser(int userId) {
		this.userId = userId;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public boolean getCollateral() {
		return collateral;
	}
	public void setCollateral(boolean collateral) {
		this.collateral = collateral;
	}
	public String getLoanDate() {
		return loan_date;
	}
	public void setLoanDate(String loan_date) {
		this.loan_date = loan_date;
	}
	public String getPaymentDate() {
		return payment_date;
	}
	public void setPaymentDate(String payment_date) {
		this.payment_date = payment_date;
	}
}
