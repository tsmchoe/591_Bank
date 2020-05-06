//Allows manger to see the current stock market
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.table.*;

public class StockViewManager extends JDialog {
    private static final long serialVersionUID = 1L;
    private DBConnector db = new DBConnector();
    private JTable transactionList;
    private String[] columns = new String[]{
        "Stock ID", "Name", "Current Price", "Share"
    };
    private DefaultTableModel tableModel = new DefaultTableModel(columns,0);

    public StockViewManager(){
        super();
        ArrayList<Stock> newer = db.getAllAvailableStocks();
        for(int i=0; i < newer.size(); i++){
            Stock intro = newer.get(i);
            int newId = intro.getId();
            String stockName = intro.getName();
            double account = intro.getCurrentPrice();
            double sharing = intro.getTotal_share();

            Object[] data = {newId, stockName, account, sharing};
            tableModel.addRow(data);
        }
        transactionList = new JTable(tableModel);
        getContentPane().add(transactionList);
  
        setSize(400,400);
        setTitle("Stock Market");


    }
    
}