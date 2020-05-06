public class SavingsAccount extends Account {
    private double interest_rate;
    DBConnector db;

    public SavingsAccount(int accountID, double initial_deposit, int userID, Currency currency,
            double interest_rate) {
        super(accountID, initial_deposit, userID, currency);
        setInterest_rate(interest_rate);
        db = new DBConnector();

    }

    public double getInterest_rate() {
        return interest_rate;
    }

    private void setInterest_rate(double interest_rate) {
        this.interest_rate = interest_rate;
    }



    @Override
    public void deposit(double amt, Currency currency) {
        //convert the amt to deposit to savings accounts' default currency;
        double amtConverted = this.currency.convert(currency, amt);
        double newBalance = balance + amtConverted;
        //add the amt to balance in account in database
        db.updateBalanceSavings(accountID, newBalance);
        db.insertTransaction(new Deposit(Func.generate_id(), userID, accountID, amtConverted, currency.toString()), "DEPOSIT");
        db.increaseBankBalance(Fees.TRANSACTION_FEE);

    }


    //Customer must maitain a minimum balance in savings acount
    @Override
    public void withdraw(double amt, Currency currency) {
        //convert the amt to deposit to savings accounts' default currency;
        double amtConverted = this.currency.convert(currency, amt);
        double newBalance = getBalance() - amtConverted;
        if(newBalance >= Fees.SAVINGS_MINIMUM_BALANCE) {
            //decrease the balance in database by amtConverted
            db.updateBalanceSavings(accountID, newBalance);
            db.insertTransaction(new Withdraw(Func.generate_id(), userID, accountID, amtConverted, currency.toString()), "WITHDRAW");
            db.increaseBankBalance(Fees.TRANSACTION_FEE);
        }


    }

    //Customer must maitain a minimum balance in savings acount
    @Override
    public void transfer(double amt, Currency currency, int accountID) {
        double amtConvertedUser = this.currency.convert(currency, amt);
        double newBalance = getBalance() - amtConvertedUser;
        if(newBalance >= Fees.SAVINGS_MINIMUM_BALANCE) { 
            db.updateBalanceSavings(this.accountID, newBalance);
            SavingsAccount target = db.getSavingsAccountByAccountID(this.accountID).get(0);
            target.deposit(amt, currency);
            Transfer t = new Transfer(Func.generate_id(), userID, this.accountID, amtConvertedUser, currency.toString(), accountID);
            db.insertTransaction(t);
            db.increaseBankBalance(Fees.TRANSACTION_FEE);
        }




    }

}
