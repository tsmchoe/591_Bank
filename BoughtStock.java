public class BoughtStock {
    private int stockid;
    private double avgCost;
    private int share;
    private double equity;
    private double currentPrice;
    private double totalValue;
    private String name;
    private int accountID;
    private DBConnector db;

    public BoughtStock(int stockid, double cost, int share, String name, int accountID) {
        this.setStock(stockid);
        this.setAvgCost(cost);
        this.setShare(share);
        this.setEquity(avgCost * share);
        this.setName(name);
        this.setAccount(accountID);
        this.db = new DBConnector();
    }

    //If the account already owns a stock, we need to increase the share and updare avgCost
    public void buyMore(double cost, int share) {
        this.equity += cost * share;
        this.share += share;
        this.avgCost = this.equity/this.share;
        this.totalValue = this.currentPrice * this.share;
        updateBoughtStock();
    }

	public void updatePrice(double newPrice) {
        this.currentPrice = newPrice;
        updateBoughtStock();
    }
    
    public void updateShare(int newShare) {
        this.share = newShare;
        updateBoughtStock();
    }

    //Calculate profit if were to sell this many share at the current price
	public double getProfit(int shareToSell) {
        return currentPrice * shareToSell;
    }
    
    //Update the database with newPrice/share
    public void updateBoughtStock() {
        db.updateBoughtStock(stockid, currentPrice, share);

    }


    //Getters and Setters
    public double getAvgCost() {
        return avgCost;
    }

    private void setAvgCost(double avgCost) {
        this.avgCost = avgCost;
    }

    public int getShare() {
        return share;
    }

    private void setShare(int share) {
        this.share = share;
    }

    public double getEquity() {
        return equity;
    }

    private void setEquity(double equity) {
        this.equity = equity;
    }

    public int getStock() {
        return stockid;
    }

    private void setStock(int stockid) {
        this.stockid = stockid;
    }

	public double getTotalValue() {
		return this.totalValue;
    }
    
    public int getID(){
        return this.stockid;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return this.name;
    }

    public double getCurrrent_price(){
        return this.currentPrice;
    }

    public void setAccount(int accountID){
        this.accountID = accountID;
    }

    public int getAccount(){
        return this.accountID;
    }



}
