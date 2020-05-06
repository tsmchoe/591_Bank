import java.util.ArrayList;

public class StockMarket {
    private static StockMarket stockMarket;
    private int total_stock_num = 0;
    private DBConnector db;

    private StockMarket() {
        this.db = new DBConnector();
    }


    public static StockMarket createStockMarket() {
        if (stockMarket == null) {
            stockMarket = new StockMarket();
        }
        return stockMarket;
    }

    public ArrayList<Stock> get_allStock() {
        
        return db.getAllAvailableStocks();
    }

    public void createStock(String name, double price, int share) {
        Stock newStock = new Stock(Func.generate_id(), name, price, share);
        // Store newStock in database
        db.addStockToStockMarket(newStock);
        total_stock_num++;
    }

    public void updateStockPrice(int stockId, double newPrice) {
        Stock seletected = db.getAvailableStockByID(stockId);
        seletected.setCurrentPrice(newPrice);
        //get all secuirty accounts who owns the stock
        // ArrayList<SecurityAccount> secAccounts = 
        //securityaccount.receiveUpdate(stockid, newPrice)
    }

    public void updateShare(int stockId, int newShare) {
        Stock seletected = db.getAvailableStockByID(stockId);
        seletected.setShare(newShare);
        // ArrayList<SecurityAccount> secAccounts = 
        //securityaccount.receiveUpdateShare(stockid, newShare)
    }



    public int getTotal_stock_num() {
        return total_stock_num;
    }





}