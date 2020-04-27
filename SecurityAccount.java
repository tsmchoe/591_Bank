import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;



public class SecurityAccount extends Account {
    private HashMap<Stock, BoughtStock> allStocks = new HashMap<>();
    private double cash;
    private double unrealized_profit;

    public SecurityAccount(double cash) {
        this.setCash(cash);
    }

    public void buyStock(Stock stock, double cost, int share) {
        if (this.allStocks.containsKey(stock)) {
            this.allStocks.get(stock).buyMore(cost, share);

        } else {
            this.allStocks.put(stock, new BoughtStock(stock, cost, share));
        }

    }

    public void sellStock(Stock stock, int share) {
        BoughtStock currStock = allStocks.get(stock);
        if(share <= currStock.getShare()) {
            if(share == currStock.getShare()) {
                allStocks.remove(stock);
            }
            this.setCash(this.cash + currStock.getProfit(share));
            this.reset_unrealized_profit();
        } else {
            System.out.println("Do not have enough shares to sell!");
        }


    }

	public void receive_updatePrice(Stock stock, double newPrice) {
        this.allStocks.get(stock).updatePrice(newPrice);
        this.reset_unrealized_profit();
	}

    private void reset_unrealized_profit() {
        double accu = 0;
        Iterator<Map.Entry<Stock, BoughtStock>> iter = allStocks.entrySet().iterator();
        while(iter.hasNext()) {
            Map.Entry<Stock, BoughtStock> stock = (Map.Entry<Stock, BoughtStock>) iter.next();
            accu += ((BoughtStock) stock.getValue()).getTotalValue();
        }
        setUnrealized_profit(accu + cash);
    }


    public double getUnrealized_profit() {
        return unrealized_profit;
    }

    private void setUnrealized_profit(double unrealized_profit) {
        this.unrealized_profit = unrealized_profit;
    }

    public double getCash() {
        return cash;
    }

    private void setCash(double cash) {
        this.cash = cash;
    }

    public ArrayList<BoughtStock> getAllStocks() {
        return new ArrayList<>(allStocks.values());
    }

}
