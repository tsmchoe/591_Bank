
public class Transfer extends Transaction {
    private Account transferedToAccount;

    public Transfer(String userid, Account account, double amount, Currency currency, Account transferedToAccount) {
        super(userid, account, amount, currency);
        this.transferedToAccount = transferedToAccount;
    }

    public String toString() {
        return account + " transfer " + amount + " " + currency + " to " + transferedToAccount;
    }

}