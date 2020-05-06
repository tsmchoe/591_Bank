package bank.views;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class StockViewManager extends JDialog {
    private static final long serialVersionUID = 1L;
    private JTable stockList;
    public StockViewManager(){
        super();
        JPanel stockaccount = new JPanel();
        stockList = new JTable();
        stockaccount.setLayout(null);
        stockaccount.add(stockList);
        getContentPane().add(stockaccount);     
        setSize(400,400);
        setTitle("Stock Market");


    }
}