public class Deposit extends Transaction {

    public Deposit(int transactionID, int userid, int accountId, double amount, String currency, String date) {
        super(transactionID, userid, accountId, amount, currency, date);
    }

    public Deposit(int transactionID, int userid, int accountId, double amount, String currency) {
        super(transactionID, userid, accountId, amount, currency);
    }

    public String toString() {
        return userid + " deposited " + amount + " " + currency + " into " + accountId + " on " + date;
    }

}