import java.util.ArrayList;
//This class represent the bank
public class Bank {
    private double balance;
    DBConnector db = new DBConnector();
    ArrayList<Request> pendingRequest;

    public double getBalance() {
        this.balance = db.getBankBalance();
        return balance;
    }
 
    public void increaseBankBalance(double amt) {
        db.increaseBankBalance(amt);
    }
    
    public void decreaseBankBalance(double amt) {
        db.decreaseBankBalance(amt);
    }
 
    public void addRequest(Request r) {
        this.pendingRequest.add(r);
    }

    public ArrayList<Request> getAllRequest() {
        return this.pendingRequest;
    }
}