package src.bank;
import java.util.ArrayList;

public class Stock {
    private int stockid;
    private String name;
    private double currentPrice;
    private int total_share;

    public Stock(int stockid, String name, double currentPrice, int init_share) {
        this.stockid = stockid;
        this.name = name;
        this.currentPrice = currentPrice;
        this.total_share = init_share;
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
        //update the stock in the database with Stock(stockid, name currentPrice, total_share)
    }



    //Update Price
    public void updateStockPrice(double newPrice) {
        setCurrentPrice(newPrice);
        updatePriceChange(newPrice);
    }

    //update the subscribers of price change
    private void updatePriceChange(double newPrice) {
        ArrayList<SecurityAccount> subscribers = getAllSubscribers();
        for(SecurityAccount account: subscribers) {
            account.receive_updatePrice(this, newPrice);
        }
    }


    //Get all security accounts who bought the stock
    private ArrayList<SecurityAccount> getAllSubscribers() {
        ArrayList<SecurityAccount> ret = null;
        /// query database using this.stockid, 
        // add new SecurityAccount()
        ///
        return ret;
    }   



}