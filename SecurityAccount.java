import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;



public class SecurityAccount extends Account implements Observer{
    private double unrealized_profit;
    private double cash;
    DBConnector db;

    public SecurityAccount(int accountID, double balance, int userID, Currency currency) {
        super(accountID, balance, userID, currency);
        db = new DBConnector();
    }


    // customer buys share of a stock with each costing cost
    public void buyStock(int stockId, double cost, int share) {
        //If the share that user want to buy is valid
        Stock market_stock = db.getAvailableStockByID(stockId);
        int shares_in_market = market_stock.getTotal_share();
        if(shares_in_market <= share) {
            double total_cost = cost*share;
            //If user has enough money to buy that much stock
            if(total_cost <= balance) {
                //If user already has the stock, increase the share
                BoughtStock boughtStock = db.getBoughtStockByStockIDAccountID(stockId, accountID);
                if(boughtStock != null) {
                    boughtStock.buyMore(cost, share);
                } else {
                    //Otherwise, add new BoughtStock to user's BoughtStock table
                    BoughtStock newBoughtStock = new BoughtStock(stockId, cost, share, market_stock.getName(), accountID);
                    db.insertNewBoughtStock(newBoughtStock);
                }
                //decrease the total share in the market stock
                market_stock.setShare(market_stock.getTotal_share()-share);
                //decrease the total balance of the account
                this.balance -= total_cost;
                //update balance in the database
                db.updateBalanceSecurity(accountID, balance);
            }
        }

    }

    public void sellStock(int stockId, int share) {
        BoughtStock boughtStock = db.getBoughtStockByStockIDAccountID(stockId, accountID);
        int shareOwned = boughtStock.getShare();
        //If the share that user wants to sell is valid
        if(shareOwned >= share) {
            //Calculate the profit user can earn
            double totalProfit = boughtStock.getProfit(share);
            //increase share in the market stock
            Stock marketStock = db.getAvailableStockByID(stockId);
            marketStock.setShare(marketStock.getTotal_share() + share);
            //increase balance of the account
            this.balance += totalProfit;
            //If user sold all shares, remove the BoughtStock, otherwise, just decrease the share.
            if(share < boughtStock.getShare()) {
                boughtStock.updateShare(boughtStock.getShare() - share);
            } else if(share == boughtStock.getShare()) {
                db.deleteStockByAccountStock(stockId, accountID);
            } 
            //update the balance in the dataabase
            db.updateBalanceSecurity(accountID, balance);

        }
    }

    @Override
    //After a stock in the market price change, user's boughtStock receives update
	public void receive_updatePrice(int stockId, double newPrice) {
        BoughtStock boughtStock = db.getBoughtStockByStockIDAccountID(stockId, accountID);
        boughtStock.updatePrice(newPrice);
	}

    @Override
    //After a stock in the market share change, user's boughtStock receives update
    public void receiveUpdateShare(int stockid, int newShare) {
        BoughtStock boughtStock = db.getBoughtStockByStockIDAccountID(stockid, accountID);
        boughtStock.updateShare(newShare);
    }

    //Calculate unrealized profit
    public double getUnrealized_profit() {
        double ret = balance;
        ArrayList<BoughtStock> ownedStock = getAllStocks();
        for(BoughtStock stock: ownedStock) {
            ret += stock.getProfit(stock.getShare());
        }
        return ret;
    }

    
    //get all stock owned by the user
    public ArrayList<BoughtStock> getAllStocks() {
        ArrayList<BoughtStock> ret = db.getStockByAccountID(accountID);
        return ret;

    }

    //Getters and Setters
    public void setUnrealized_profits(double unrealized){
        this.unrealized_profit = unrealized;
    }

    public void setCash(double cashAmount){
        this.cash = cashAmount;
    }

    

    /* In real life, there would be restrictions on how many times a security account can transfer/withdraw, but since it's not
    required in the assignment, we have it the same as in the checkings account(not restriction)
    */
    @Override
    public void deposit(double amt, Currency currency) {
        //convert the amt to deposit to security accounts' default currency;
        double amtConverted = this.currency.convert(currency, amt);
        double newBalance = getBalance() + amtConverted;
        db.updateBalanceSecurity(accountID, newBalance);
        db.insertTransaction(new Deposit(Func.generate_id(), userID, accountID, amtConverted, currency.toString()), "DEPOSIT");
        db.increaseBankBalance(Fees.TRANSACTION_FEE);

    }

    @Override
    public void withdraw(double amt, Currency currency) {
        //convert the amt to deposit to secuirty accounts' default currency;
        double amtConverted = this.currency.convert(currency, amt);
        double newBalance = getBalance() - amtConverted;
        if(newBalance >= 0) {
            db.updateBalanceSecurity(accountID, newBalance);
            db.insertTransaction(new Withdraw(Func.generate_id(), userID, accountID, amtConverted, currency.toString()), "WITHDRAW");
            db.increaseBankBalance(Fees.TRANSACTION_FEE);
        }
    }

    @Override
    public void transfer(double amt, Currency currency, int accountID) {
        double amtConvertedUser = this.currency.convert(currency, amt);
        double newBalance = getBalance() - amtConvertedUser;
        if(newBalance >= 0) {
            // decrease this amount in user's balance in database
            db.updateBalanceSecurity(accountID, newBalance);
            SecurityAccount target = db.getSecurityAccountByAccountID(accountID).get(0);
            target.deposit(amt, currency);
            Transfer t = new Transfer(Func.generate_id(), userID, this.accountID, amtConvertedUser, currency.toString(), accountID);
            db.insertTransaction(t);
            db.increaseBankBalance(Fees.TRANSACTION_FEE);
        }



    }





}
