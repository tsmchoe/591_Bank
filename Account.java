//Based class shared by Savings, Checking, and Security accounts
public abstract class Account {
    protected int accountID;
    protected double balance;
    protected int userID;
    protected Currency currency;


    public Account(int accountID, double balance, int userID, Currency currency) {
        this.accountID = accountID;
        this.balance = balance;
        this.userID = userID;
        this.currency = currency;
    }

    //required methods for subclasses
    public abstract void deposit(double amt, Currency currency);

    public abstract void withdraw(double amt, Currency currency);

    public abstract void transfer(double amt, Currency currency, int accountID);


    //getters and setters

    public int getAccountID() {
        return accountID;
    }

    public double getBalance() {
        return balance;
    }

    public int getUserID() {
        return userID;
    }

    public String getCurrency() {
        return currency.toString();
    }
}
