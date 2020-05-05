package src.bank;
//used for recording transactions
public abstract class Transaction {
    protected int userid;
    protected double amount;
    protected String currency;
    protected int accountId;
    protected String date;
    protected int transactionID;


<<<<<<< HEAD:Transaction.java
    //Add transaction type to database in transaction table, amount
    public Transaction(String transactionID, String userid, String accountId, double amount, String currency,
=======
    //Add transaction type to database in transaction table
    public Transaction(int transactionID, int userid, int accountId, double amount, String currency,
>>>>>>> 3a6a6d5ee78ce9ad97adb019a8fec44183624aa0:src/bank/Transaction.java
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

    public int getTransactionID() {
		return transactionID;
	}

	public void setTransactionID(int transactionID) {
		this.transactionID = transactionID;
	}

	public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public int getAccountId() {
        return accountId;
    }

    private void setAccountId(int accountId) {
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