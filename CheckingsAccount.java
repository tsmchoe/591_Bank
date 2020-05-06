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

        //Add the new Deposit() to trasaction table in dabatase


    }

    @Override
    public void withdraw(double amt, Currency currency) {
        //convert the amt to deposit to checking accounts' default currency;
        double amtConverted = this.currency.convert(currency, amt);
        double newBalance = getBalance() - amtConverted;
        if(newBalance >= 0) {
            db.updateBalanceCheckings(accountID, newBalance);
            //Add the new Withdraw() to trasaction table in dabatase
        }


    }

    @Override
    public void transfer(double amt, Currency currency, int accountID) {
        //query to see if the accountID the user wants to transfer to exits, if so:
        double amtConverted = this.currency.convert(currency, amt);
        double newBalance = getBalance() - amtConverted;
        if(newBalance >= 0) {
            db.updateBalanceCheckings(accountID, newBalance);
            //increase this amount in target's balance in database

            // TARGETUSER.deposit(amt, currency);
            //Add the new Transer() to trasaction table in dabatase
        }


    }

}
