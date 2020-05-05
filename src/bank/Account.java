package bank;

import java.util.ArrayList;
public abstract class Account {
    protected String accountID;
    protected double balance;
    protected String userID;
    protected Currency currency;

    public Account(String accountID, double balance, String userID, Currency currency) {
        this.accountID = accountID;
        this.balance = balance;
        this.userID = userID;
        this.currency = currency;
    }

    //required methods
    public abstract void deposit(double amt, Currency currency);

    public abstract void withdraw(double amt, Currency currency);

    public abstract void transfer(double amt, Currency currency, String accountID);

    //view transaction made by this account on a particular date
    public ArrayList<Transaction> getAllTransactions(String date) {
        ArrayList<Transaction> allTransactions = new ArrayList<>();
        //query the database for transactions made by accountID
        // if (transactionType == TransactionType.WITHDRAW) {
        //     allTransactions.add(new Withdraw(transactionID, userid, accountId, amount, currency, date));
        // } else if (transactionType == TransactionType.DEPOSIT) {
        //     allTransactions.add(new Deposit(transactionID, userid, accountId, amount, currency, date))
        // } else {
        //     allTransactions.add(new Transfer(transactionID, userid, accountId, amount, currency, date, transferedToAccountID));
        // }
        return allTransactions;
    }

    //getters and setters

    public String getAccountID() {
        return accountID;
    }

    public double getBalance() {
        return balance;
    }

    public String getUserID() {
        return userID;
    }

    public String getCurrency() {
        return currency.toString();
    }
}
