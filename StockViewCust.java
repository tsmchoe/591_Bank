
import java.awt.*;
import javax.swing.*;
import javax.swing.table.*;

import java.awt.event.*;
import java.util.ArrayList;

 public class StockViewCust extends JDialog {
    private static final long serialVersionUID = 1L;
    private JMenuBar mb = new JMenuBar(); 
    private JMenu m1 = new JMenu("Owned");
    private JMenu m2 = new JMenu("Profit");
    private JMenuItem see = new JMenuItem("See Stocks");
    private JMenuItem sell = new JMenuItem("Sell");
    private JMenuItem ureal = new JMenuItem("Unrealized");
    private JButton buyStock = new JButton("Buy!");
    private JTable stockList;
    private DBConnector db = new DBConnector();
    private JTable transactionList;
    private String[] columns = new String[]{
        "Stock ID", "Name", "Current Price", "Share"
    };
    private DefaultTableModel tableModel = new DefaultTableModel(columns,0);

    public StockViewCust(Customer cust){
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

        sell.addActionListener(
            new ActionListener(){
                public void actionPerformed(ActionEvent e){
                SellStocks seller = new SellStocks(cust);
                seller.setVisible(true);
                }
            }
        );
        ureal.addActionListener(
            new ActionListener(){
                public void actionPerformed(ActionEvent e){
                UnrealizedProfit urealer = new UnrealizedProfit(cust);
                urealer.setVisible(true);
                }
            }
        );
        buyStock.addActionListener(
            new ActionListener(){
                public void actionPerformed(ActionEvent e){
                BuyStocks buyer = new BuyStocks(cust);
                buyer.setVisible(true);
                }
            }
        );




        JPanel stockaccount = new JPanel();
        mb.add(m1);
        m1.add(see);
        m1.add(sell);
        mb.add(m2);
        m2.add(ureal);
        getContentPane().add(BorderLayout.PAGE_START, mb);
        stockaccount.setLayout(null);
        stockList = new JTable();
        stockaccount.add(stockList);
        buyStock.setBounds(100,300,150,40);
        stockaccount.add(buyStock);  
        getContentPane().add(stockaccount);     
        setSize(400,400);
        setTitle("Stock Market");
    
    }
    
    
}