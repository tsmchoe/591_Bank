package bank;

public class Deposit extends Transaction {

    public Deposit(String transactionID, String userid, String accountId, double amount, String currency, String date) {
        super(transactionID, userid, accountId, amount, currency, date);
    }

    public String toString() {
        return userid + " deposited " + amount + " " + currency + " into " + accountId + " on " + date;
    }

}