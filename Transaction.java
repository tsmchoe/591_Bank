//used for recording transactions
import java.text.SimpleDateFormat;
import java.util.Calendar; 
public abstract class Transaction {
    protected int userid;
    protected double amount;
    protected String currency;
    protected int accountId;
    protected String date;
    protected int transactionID;
    protected int transferedToAccountID;

    public Transaction(int transactionID, int userid, int accountId, double amount, String currency) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		//Getting current date
        Calendar cal = Calendar.getInstance();
        String date = sdf.format(cal.getTime());
        setTransactionID(transactionID);
        setUserid(userid);
        setAccountId(accountId);
        setAmount(amount);
        setCurrency(currency);
        setDate(date);
    }
    public Transaction(int transactionID, int userid, int accountId, double amount, String currency,
    String date) {
        this(transactionID, userid, accountId, amount, currency);
        setDate(date);
    }

    //getters and setters
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

    public int getTransferAccountID() {
        return transferedToAccountID;
    }

}