package src.bank.views;

import java.awt.*;
import javax.swing.*;

 public class StockViewCust extends JDialog {
    private static final long serialVersionUID = 1L;
    
    private JButton buyStock = new JButton("Buy!");
    private JButton sellStock = new JButton("Sell!");
    private JTable stockList;
    public StockViewCust(){
        super();
        JPanel stockaccount = new JPanel();
            
    
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