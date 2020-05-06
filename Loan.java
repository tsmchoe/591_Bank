public class Loan {
	protected int loanId;
	protected int userId;
	protected double amount;
	protected String collateral;
	protected String loan_date;
	protected String payment_date;

	public Loan(int loanId, int userId, double amount, String collateral, String loan_date, String payment_date) {
		this.loanId = loanId;
		this.userId = userId;
		this.amount = amount;
		this.collateral = collateral;
		this.loan_date = loan_date;
		this.payment_date = payment_date;
	}

	//Setter and getter
	public int getUser() {
		return userId;
	}

	private void setUser(int userId) {
		this.userId = userId;
	}
	
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public String getCollateral() {
		return collateral;
	}
	private void setCollateral(String collateral) {
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

	public int getLoanID(){
		return this.loanId;
	}

	public String toString(){
		String ret = "loandId: " + loanId + " " + "userIdL: "+ userId + " " + "amount: " + amount + " " + "colalteral: " + collateral + " " + "loan_date" + loan_date + " " + "payment_date:" + payment_date;
		return ret;
	}
}
