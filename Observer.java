public interface Observer {
    public void receive_updatePrice(int stockId, double newPrice);
    public void receiveUpdateShare(int stockid, int newShare);
}