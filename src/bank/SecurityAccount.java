package src.bank;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;



public class SecurityAccount extends Account {
    private double unrealized_profit;


    public SecurityAccount(int accountID, double balance, int userID, Currency currency) {
        super(accountID, balance, userID, currency);
    }


    // customer buys share of a stock with each costing cost
    public void buyStock(int stockId, double cost, int share) {
        // Stock market_stock = select stock by stockid
        // if(market_stock.gettTotalShare() <= share) {
            // double total_cost = cost*share;
            // if(total_cost <= balance) {
                // if(BoughtStock exists) {
                //     Select BoughtStock in database,
                //     boughtStock.buyMore(cost, share);
                // } else {
                    //Create new BoughtStock(int stockid, String name, double currentPrice, int share, double cost);
                // }
                // stock.setShare(market_stock.getTotalShare()-share);
                // this.balance -= total_cost;
                // updateAccount();
            // }
        // }

    }

    public void sellStock(int stockId, int share) {
        // BoughtStock stock = select Boughtstock by stockid
        // int shareOwned = stock.getShare();
        // if(sharedOwned >= share) {
            // double totalProfit = stock.getProfit(share);
            // Stock marketStock = select Stock by stockid
            // marketStock.setShare(marketStock.getTotalShare() + share);
            // this.balance += totalProfit;
            // if(share < stock.getShare()) {
            //     stock.setShare(stock.getShare() - share);
            // } else if(share == stock.getShare()) {
            //     // remove BoughtStock with stockId in database
            // } 
            // updateAccount();

        // }
    }

	public void receive_updatePrice(int stockId, double newPrice) {
        //Select the boughtStock with stockid
        //boughtStock.setPrice(newPrice);
	}



    public double getUnrealized_profit() {
        double ret = balance;
        ArrayList<BoughtStock> ownedStock = getAllStocks();
        for(BoughtStock stock: ownedStock) {
            ret += stock.getProfit(stock.getShare());
        }
        return ret;
    }

 

    public ArrayList<BoughtStock> getAllStocks() {
        ArrayList<BoughtStock> ret = null;
        //query the database to get all BoughtStock owned by accountid
        return ret;

    }

    /* In real life, there would be restrictions on how many times a security account can transfer/withdraw, but since it's not
    required in the assignment, we have it the same as in the checkings account(not restriction)
    */
    @Override
    public void deposit(double amt, Currency currency) {
        //convert the amt to deposit to security accounts' default currency;
        double amtConverted = this.currency.convert(currency, amt);
        //add the amt to balance in account in database

        //Add the new Deposti() to trasaction table in dabatase

    }

    @Override
    public void withdraw(double amt, Currency currency) {
        //convert the amt to deposit to secuirty accounts' default currency;
        double amtConverted = this.currency.convert(currency, amt);
        if(getBalance() - amtConverted >= 0) {
            //decrease the balance in database by amt

            //Add the new Withdraw() to trasaction table in dabatase

        }


    }

    @Override
    public void transfer(double amt, Currency currency, int accountID) {
        //query to see if the accountID the user wants to transfer to exits, if so:

        
        double amtConvertedUser = this.currency.convert(currency, amt);
        if(getBalance() - amtConvertedUser >= 0) {
            // decrease this amount in user's balance in database
            // increase this amount in target's balance in database
            // double amtConvertedTarget = TARGETUSER.getCurrency().convert(currency, amt)

            // Add the new Transer() to trasaction table in dabatase

        }



    }



}
