import java.util.ArrayList;
//Represents stock in the stock market
public class Stock implements Subject {
    private int stockid;
    private String name;
    private double currentPrice;
    private int total_share;
    DBConnector db;

    public Stock(int stockid, String name, double currentPrice, int init_share) {
        this.stockid = stockid;
        this.name = name;
        this.currentPrice = currentPrice;
        this.total_share = init_share;
        this.db = new DBConnector();
    }

    public int getTotal_share() {
        return total_share;
    }

    public void setShare(int newShare) {
        this.total_share = newShare;
        UpdateStock();

    }

    public int getId() {
        return this.stockid;
    }

    public void setId(int new_id) {
        this.stockid = new_id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String newName) {
        this.name = newName;
    }

    public double getCurrentPrice() {
        return this.currentPrice;
    }

    public void setCurrentPrice(double newPrice) {
        this.currentPrice = newPrice;
        UpdateStock();
    }


    //Called after a field is updated, need to update the database
    private void UpdateStock() {
        db.updateStockInStock_Market(stockid, currentPrice, total_share);
    }



    //Update Price
    public void updateStockPrice(double newPrice) {
        setCurrentPrice(newPrice);
        updatePriceChange(newPrice);
    }

    //update the subscribers of price change
    //@Override
    public void updatePriceChange(double newPrice) {
        ArrayList<SecurityAccount> subscribers = getAllSubscribers();
        for(SecurityAccount account: subscribers) {
            account.receive_updatePrice(this.stockid, newPrice);
        }
    }

    //update the subscribers of share change
    //@Override
    public void updateShareChange(int newShare) {
        ArrayList<SecurityAccount> subscribers = getAllSubscribers();
        for(SecurityAccount account: subscribers) {
            account.receiveUpdateShare(this.stockid, newShare);
        }
    }

    //Get all security accounts who bought the stock
    private ArrayList<SecurityAccount> getAllSubscribers() {
        ArrayList<SecurityAccount> ret = db.getSecurityAccountByStockID(stockid);
        return ret;
    }   



}