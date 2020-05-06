public class CheckingsAccount extends Account {
    DBConnector db;

    public CheckingsAccount(int accountID, double initial_deposit, int userID, Currency currency) {
        super(accountID, initial_deposit, userID, currency);
        db = new DBConnector();
    }


    //There is no limitation of transactions in checkings account
    @Override
    public void deposit(double amt, Currency currency) {
        //convert the amt to deposit to checking accounts' default currency;
        double amtConverted = this.currency.convert(currency, amt);
        double newBalance = getBalance() + amtConverted;
        //add the amt to balance in account in database
        db.updateBalanceCheckings(accountID, newBalance);
        db.insertTransaction(new Deposit(Func.generate_id(), userID, accountID, amtConverted, currency.toString()), "DEPOSIT");
    }

    @Override
    public void withdraw(double amt, Currency currency) {
        //convert the amt to deposit to checking accounts' default currency;
        double amtConverted = this.currency.convert(currency, amt);
        double newBalance = getBalance() - amtConverted;
        if(newBalance >= 0) {
            db.updateBalanceCheckings(accountID, newBalance);
            db.insertTransaction(new Withdraw(Func.generate_id(), userID, accountID, amtConverted, currency.toString()), "WITHDRAW");
        }
    }

    @Override
    public void transfer(double amt, Currency currency, int accountID) {
        //query to see if the accountID the user wants to transfer to exits, if so:
        double amtConverted = this.currency.convert(currency, amt);
        double newBalance = getBalance() - amtConverted;
        if(newBalance >= 0) {
            db.updateBalanceCheckings(this.accountID, newBalance);
            //increase this amount in target's balance in database
            CheckingsAccount target = db.getCheckingsAccountByAccountID(accountID).get(0);
            target.deposit(amt, currency);
            Transfer t = new Transfer(Func.generate_id(), userID, this.accountID, amtConverted, currency.toString(), accountID);
            db.insertTransaction(t);
        }


    }

}
