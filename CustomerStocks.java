//Customer's owned stocks

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

 
public class CustomerStocks extends JDialog{
    private static final long serialVersionUID = 1L;

    private DBConnector db = new DBConnector();
    private JTable transactionList;
    private String[] columns = new String[]{
        "Stock ID", "Name", "Current Price", "Share"
    };
    private DefaultTableModel tableModel = new DefaultTableModel(columns,0);

    public CustomerStocks(Customer cust){
        super();
        ArrayList<BoughtStock> newer = db.getStockByAccountID(cust.getAllSecurities().get(0).accountID);
        for(int i=0; i < newer.size(); i++){
            BoughtStock intro = newer.get(i);
            int newId = intro.getID();
            String stockName = intro.getName();
            double account = intro.getCurrrent_price();
            double sharing = intro.getShare();

            Object[] data = {newId, stockName, account, sharing};
            tableModel.addRow(data);
        }
        transactionList = new JTable(tableModel);
        getContentPane().add(transactionList);
  
        setSize(400,400);
        setTitle("Customer Stocks");


    }
}