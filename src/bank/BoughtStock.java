package src.bank;
public class BoughtStock {
    private Stock stock;
    private double avgCost;
    private int share;
    private double equity;
    private double currentPrice;
    private double totalValue;

    public BoughtStock(Stock stock, double cost, int share) {
        this.setStock(stock);
        this.setAvgCost(cost);
        this.setShare(share);
        this.setEquity(avgCost * share);
    }

    public void buyMore(double cost, int share) {
        this.equity += cost * share;
        this.share += share;
        this.avgCost = this.equity/this.share;
        updateTotalValue();;
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

    public Stock getStock() {
        return stock;
    }

    private void setStock(Stock stock) {
        this.stock = stock;
    }

	public double getTotalValue() {
		return this.totalValue;
	}

	public void updateTotalValue() {
        this.totalValue = this.currentPrice * this.share;
	}

	public void updatePrice(double newPrice) {
        this.currentPrice = newPrice;
	}

	public double getProfit(int shareToSell) {
        
        return currentPrice * shareToSell;

	}


}
