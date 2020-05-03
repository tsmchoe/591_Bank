

public class CheckingsAccount extends Account {

    public CheckingsAccount(String accountID, double initial_deposit, String userID, Currency currency) {
        super(accountID, initial_deposit, userID, currency);
    }


    //There is no limitation of transactions in checkings account
    @Override
    public void deposit(double amt, Currency currency) {
        //convert the amt to deposit to checking accounts' default currency;
        double amtConverted = this.currency.convert(currency, amt);
        //add the amt to balance in account in database

        //Add the new Deposti() to trasaction table in dabatase

    }

    @Override
    public void withdraw(double amt, Currency currency) {
        //convert the amt to deposit to checking accounts' default currency;
        double amtConverted = this.currency.convert(currency, amt);
        if(getBalance() - amtConverted >= 0) {
            //decrease the balance in database by amt

            //Add the new Withdraw() to trasaction table in dabatase
        }


    }

    @Override
    public void transfer(double amt, Currency currency, String accountID) {
        //query to see if the accountID the user wants to transfer to exits, if so:


        double amtConvertedUser = this.currency.convert(currency, amt);
        if(getBalance() - amtConvertedUser >= 0) {
            //decrease this amount in user's balance in database
            //increase this amount in target's balance in database
            // double amtConvertedTarget = TARGETUSER.getCurrency().convert(currency, amt)

            //Add the new Transer() to trasaction table in dabatase
        }


    }

}
