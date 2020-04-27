public abstract class Transaction {
    protected String userid;
    protected double amount;
    protected Currency currency;
    protected Account account;

    public Transaction(String userid, Account account, double amount, Currency currency) {
        setUserid(userid);
        setAccount(account);
        setAmount(amount);
        setCurrency(currency);
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public Account getAccount() {
        return account;
    }

    private void setAccount(Account account) {
        this.account = account;
    }

    public Currency getCurrency() {
        return currency;
    }

    private void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public double getAmount() {
        return amount;
    }

    private void setAmount(double amount) {
        this.amount = amount;
    }

}