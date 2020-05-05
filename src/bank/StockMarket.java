package src.bank;

import java.util.ArrayList;

public class StockMarket {
    private static StockMarket stockMarket;
    private int total_stock_num = 0;

    private StockMarket() {
    }


    public static StockMarket createStockMarket() {
        if (stockMarket == null) {
            stockMarket = new StockMarket();
        }
        return stockMarket;
    }

    public ArrayList<Stock> get_allStock() {
        // get all stock from database
        //
        //
        return null;
    }

    public void createStock(String name, double price, int share) {
        // Store newStock in database
        //
        //
        //
        setTotal_stock_num(getTotal_stock_num() + 1);
    }

    public void updateStock(int stockId, double newPrice) {
        //Update the price in database to newPrice
        //
        //
    }

    public void customer_buyStock(SecurityAccount account, Stock stock, int share, double cost) {
        account.buyStock(stock, cost, share);
        stock.decreaseShare(share);
    }

    public void customer_sellStock(SecurityAccount account, Stock stock, int share) {
        account.sellStock(stock, share);
        stock.increaseShare(share);
    }


    public int getTotal_stock_num() {
        return total_stock_num;
    }

    public void setTotal_stock_num(int total_stock_num) {
        this.total_stock_num = total_stock_num;
    }
}