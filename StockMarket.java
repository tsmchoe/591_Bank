import java.util.ArrayList;

public class StockMarket {
    private static StockMarket stockMarket;
    private int total_stock_num = 0;
    private DBConnector db;

    private StockMarket() {
        this.db = new DBConnector();
    }

    //singleton for creating a new market
    public static StockMarket createStockMarket() {
        if (stockMarket == null) {
            stockMarket = new StockMarket();
        }
        return stockMarket;
    }   

    //See what stocks are availble in the market
    public ArrayList<Stock> get_allStock() {
        return db.getAllAvailableStocks();
    }

    //creates new stock.
    public void createStock(String name, double price, int share) {
        Stock newStock = new Stock(Func.generate_id(), name, price, share);
        // Store newStock in database
        db.addStockToStockMarket(newStock);
        total_stock_num++;
    }


    //Update the stock price
    public void updateStockPrice(int stockId, double newPrice) {
        Stock seletected = db.getAvailableStockByID(stockId);
        seletected.setCurrentPrice(newPrice);
        //get all secuirty accounts who owns the stock
        ArrayList<SecurityAccount> secAccounts = db.getSecurityAccountByStockID(stockId);
        for(SecurityAccount s: secAccounts) {
             s.receive_updatePrice(stockId, newPrice);
        }

    }


    //Update share of the stock
    public void updateShare(int stockId, int newShare) {
        Stock seletected = db.getAvailableStockByID(stockId);
        seletected.setShare(newShare);
        //get all secuirty accounts who owns the stock
        ArrayList<SecurityAccount> secAccounts = db.getSecurityAccountByStockID(stockId);
        for(SecurityAccount s: secAccounts) {
             s.receiveUpdateShare(stockId, newShare);
        }
    }



    public int getTotal_stock_num() {
        return total_stock_num;
    }





}