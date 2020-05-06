public class Transfer extends Transaction {

    public Transfer(int transactionID, int userid, int accountId, double amount, String currency,
            String date, int transferedToAccountID) {
        super(transactionID, userid, accountId, amount, currency, date);
        this.transferedToAccountID = transferedToAccountID;
    }   

    public String toString() {
        return userid + " transfer " + amount + " " + currency + " from " + accountId + " to " + transferedToAccountID + " on " + date;
    }

}