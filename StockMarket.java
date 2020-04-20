import java.util.ArrayList;

public class StockMarket {
    private static StockMarket stockMarket;
    private int total_stock_num = 0;
    
    private StockMarket() {}

    public static StockMarket createStockMarket() {
        if(stockMarket == null) {
            stockMarket = new StockMarket();
        }
        return stockMarket;
    }

    public ArrayList<Stock> get_allStock() {
        //TODO get all stock from database
        return null;
    }

    public void createStock(String name, double price, int share) {
        Stock newStock = new Stock(total_stock_num++, name, price, share);
        //TODO Store newStock in database
    }

    public void updateStock(Stock stock, double newPrice) {
        int id = stock.getId();
        //TODO Update the price in database to newPrice
    }

    public void customer_buyStock(SecurityAccount account, Stock stock, int share, double cost) {
        account.buyStock(stock, cost, share);
        stock.decreaseShare(share);
    }

    public void customer_sellStock(SecurityAccount account, Stock stock, int share) {
        account.sellStock(stock);
        stock.increaseShare(share);
    }
}