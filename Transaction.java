//used for recording transactions
public abstract class Transaction {
    protected String userid;
    protected double amount;
    protected String currency;
    protected String accountId;
    protected String date;
    protected String transactionID;


    //Add transaction type to database in transaction table, amount
    public Transaction(String transactionID, String userid, String accountId, double amount, String currency,
            String date) {
        setTransactionID(transactionID);
        setUserid(userid);
        setAccountId(accountId);
        setAmount(amount);
        setCurrency(currency);
        setDate(date);

    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTransactionID() {
		return transactionID;
	}

	public void setTransactionID(String transactionID) {
		this.transactionID = transactionID;
	}

	public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getAccountId() {
        return accountId;
    }

    private void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getCurrency() {
        return currency;
    }

    private void setCurrency(String currency) {
        this.currency = currency;
    }

    public double getAmount() {
        return amount;
    }

    private void setAmount(double amount) {
        this.amount = amount;
    }

}