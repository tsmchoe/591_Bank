package src.bank;
import java.util.ArrayList;

public class Stock {
    private int id;
    private String name;
    private double currentPrice;
    private int total_share;
    private ArrayList<SecurityAccount> subscribers = new ArrayList<>();

    public Stock(int id, String name, double currentPrice, int init_share) {
        this.id = id;
        this.name = name;
        this.currentPrice = currentPrice;
        this.total_share = init_share;
    }

    public int getTotal_share() {
        return total_share;
    }

    public void decreaseShare(int amount) {
        this.total_share -= amount;
    }

    public void increaseShare(int amount) {
        this.total_share += amount;

	}


    public int getId() {
        return this.id;
    }

    public void setId(int new_id) {
        this.id = new_id;
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
    }

    public void subscribe(SecurityAccount account) {
        this.subscribers.add(account);
    } 

    public ArrayList<SecurityAccount> getAllSubscribers() {
        return this.subscribers;
    }


    //Update Price
    public void updateStockPrice(double newPrice) {
        setCurrentPrice(newPrice);
        updatePriceChange(newPrice);
    }

    private void updatePriceChange(double newPrice) {
        for(SecurityAccount account: subscribers) {
            account.receive_updatePrice(this, newPrice);
        }
    }



}