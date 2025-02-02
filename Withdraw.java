public class Withdraw extends Transaction {
    public Withdraw(int transactionID, int userid, int accountId, double amount, String currency,
            String date) {
        super(transactionID, userid, accountId, amount, currency, date);
    }

    public Withdraw(int transactionID, int userid, int accountId, double amount, String currency) {
        super(transactionID, userid, accountId, amount, currency);
    }

    public String toString() {
        return userid + " withdrawed " + amount + " " + currency + " from " + accountId + " on " + date;
    }

}