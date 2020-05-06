

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

 public class StockViewCust extends JDialog {
    private static final long serialVersionUID = 1L;
    private JMenuBar mb = new JMenuBar(); 
    private JMenu m1 = new JMenu("Owned");
    private JMenu m2 = new JMenu("Profit");
    private JMenuItem see = new JMenuItem("See Stocks");
    private JMenuItem sell = new JMenuItem("Sell");
    private JMenuItem real = new JMenuItem("Realized");
    private JMenuItem ureal = new JMenuItem("Unrealized");
    private JButton buyStock = new JButton("Buy!");
    private JTable stockList;
    public StockViewCust(){
        super();
        sell.addActionListener(
            new ActionListener(){
                public void actionPerformed(ActionEvent e){
                SellStocks seller = new SellStocks();
                seller.setVisible(true);
                }
            }
        );
        real.addActionListener(
            new ActionListener(){
                public void actionPerformed(ActionEvent e){
                RealizedProfit realer = new RealizedProfit();
                realer.setVisible(true);
                }
            }
        );
        ureal.addActionListener(
            new ActionListener(){
                public void actionPerformed(ActionEvent e){
                UnrealizedProfit urealer = new UnrealizedProfit();
                urealer.setVisible(true);
                }
            }
        );
        buyStock.addActionListener(
            new ActionListener(){
                public void actionPerformed(ActionEvent e){
                BuyStocks buyer = new BuyStocks();
                buyer.setVisible(true);
                }
            }
        );




        JPanel stockaccount = new JPanel();
        mb.add(m1);
        m1.add(see);
        m1.add(sell);
        mb.add(m2);
        m2.add(real);
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
    
    public void setTable(JTable stockList) {
        JScrollPane scrollPane = new JScrollPane(stockList);
        Dimension d = stockList.getPreferredSize();
        scrollPane.setPreferredSize(
                new Dimension(d.width,stockList.getRowHeight()*6+1));
        //this.stocklist = stocklist;
        add(scrollPane, BorderLayout.CENTER);
    }
    
}