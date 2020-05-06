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
        total_stock_num++;
    }

    public void updateStockPrice(int stockId, double newPrice) {
        //Stock seletec = getStockwithid(stockId);
        //selected.setCurrenPrice(newPrice)
        //get all secuirty accounts who owns the stock
        //securityaccount.receiveUpdate(stockid, newPrice)
    }

    public void updateShare(int stockId, int newShare) {
        //Stock seletec = getStockwithid(stockId);
        //selected.setShare(newShare)
        //
    }



    public int getTotal_stock_num() {
        return total_stock_num;
    }





}