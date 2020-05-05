package bank;

public class Transfer extends Transaction {
    private String transferedToAccountID;

    public Transfer(String transactionID, String userid, String accountId, double amount, String currency,
            String date, String transferedToAccountID) {
        super(transactionID, userid, accountId, amount, currency, date);
        this.transferedToAccountID = transferedToAccountID;
    }   

    public String toString() {
        return userid + " transfer " + amount + " " + currency + " from " + accountId + " to " + transferedToAccountID + " on " + date;
    }

}