import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;



public class SecurityAccount extends Account {
    private double unrealized_profit;
    private double cash;
    DBConnector db;

    public SecurityAccount(int accountID, double balance, int userID, Currency currency) {
        super(accountID, balance, userID, currency);
        db = new DBConnector();
    }


    // customer buys share of a stock with each costing cost
    public void buyStock(int stockId, double cost, int share) {
        Stock market_stock = db.getAvailableStockByID(stockId);
        int shares_in_market = market_stock.getTotal_share();
        // if(shares_in_market <= share) {
        //     double total_cost = cost*share;
        //     if(total_cost <= balance) {
        //     //     if(BoughtStock exists) {
        //     //         Select BoughtStock in database,
        //     //         boughtStock.buyMore(cost, share);
        //     //     } else {
        //     //         Create new BoughtStock(int stockid, String name, double currentPrice, int share, double cost);
        //     //     }
        //     //     stock.setShare(market_stock.getTotalShare()-share);
        //     //     this.balance -= total_cost;
        //     //     db.updateBalanceSecurity(accountID, balance);
        //     // }
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
                // db.updateBalanceSecurity(accountID, balance);

        // }
    }

	public void receive_updatePrice(int stockId, double newPrice) {
        //Select the boughtStock with stockid
        //boughtStock.setPrice(newPrice);
	}

    public void receiveUpdateShare(int stockid, double newShare) {

    }

    public void setUnrealized_profits(double unrealized){
        this.unrealized_profit = unrealized;
    }

    public void setCash(double cashAmount){
        this.cash = cashAmount;
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
        ArrayList<BoughtStock> ret = db.getStockByAccountID(accountID);
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
        
        //Add the new Deposit() to trasaction table in dabatase

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
        double amtConvertedUser = this.currency.convert(currency, amt);
        if(getBalance() - amtConvertedUser >= 0) {
            // decrease this amount in user's balance in database
            SecurityAccount target = db.getSecurityAccountByAccountID(accountID).get(0);
            target.deposit(amt, currency);
            // Add the new Transer() to trasaction table in dabatase

        }



    }





}
