
public class Withdraw extends Transaction {

    public Withdraw(String userid, Account account, double amount, Currency currency) {
        super(userid, account, amount, currency);
    }

    public String toString() {
        return account + " withdrawed " + amount + " " + currency;
    }

}