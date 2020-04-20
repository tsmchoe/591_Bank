import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;



public class SecurityAccount {
    private HashMap<Stock, Position> allStocks = new HashMap<>();
    private double cash;
    private double unrealized_profit;

    public SecurityAccount(double cash) {
        this.setCash(cash);
    }

    public void buyStock(Stock stock, double cost, int share) {
        if (this.allStocks.containsKey(stock)) {
            this.allStocks.get(stock).buyMore(cost, share);

        } else {
            this.allStocks.put(stock, new Position(cost, share));
        }

    }

    public void sellStock(Stock stock) {
        Position position = allStocks.remove(stock);
        this.setCash(this.cash + position.getTotalValue());
        this.reset_unrealized_profit();

    }

	public void receive_updatePrice(Stock stock, double newPrice) {
        this.allStocks.get(stock).updatePrice(newPrice);
        this.reset_unrealized_profit();
	}

    private void reset_unrealized_profit() {
        double accu = 0;
        Iterator<Map.Entry<Stock, Position>> iter = allStocks.entrySet().iterator();
        while(iter.hasNext()) {
            Map.Entry<Stock, Position> stock = (Map.Entry<Stock, Position>) iter.next();
            accu += ((Position) stock.getValue()).getTotalValue();
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

    public HashMap<Stock, Position> getAllStocks() {
        return this.allStocks;
    }

}
