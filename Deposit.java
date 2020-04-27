
public class Deposit extends Transaction {

    public Deposit(String userid, Account account, double amount, Currency currency) {
        super(userid, account, amount, currency);
    }

    public String toString() {
        return this.account + " deposited " + this.amount + " " + this.currency;
    }

}