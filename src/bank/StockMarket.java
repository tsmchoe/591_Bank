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

    public void updateStockPrice(int stockId, double newPrice) {
        //Stock seletec = getStockwithid(stockId);
        //selected.setCurrenPrice(newPrice)
        //
    }

    public void updateShare(int stockId, int newShare) {
        //Stock seletec = getStockwithid(stockId);
        //selected.setShare(newShare)
        //
    }

    public void customer_buyStock(int accountId, Stock stock, int share, double cost) {
        //select the account with accountid
        // account.buyStock(stock, cost, share);
        // selected.setShare(selected.getShare()-share);
    }

    public void customer_sellStock(int accountId, Stock stock, int share) {
        //select the account with accountid
        // account.sellStock(stock, share);
        // selected.setShare(selected.getShare()-share);
    }


    public int getTotal_stock_num() {
        return total_stock_num;
    }

    public void setTotal_stock_num(int total_stock_num) {
        this.total_stock_num = total_stock_num;
    }
}