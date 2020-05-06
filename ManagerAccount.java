

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.table.*;


public class ManagerAccount extends JDialog {

    private static final long serialVersionUID = 1L;

    private JPanel balanceCheck = new JPanel();
    private JMenuBar mb = new JMenuBar(); 
    private JMenu m1 = new JMenu("Profile");
    private JMenu m2 = new JMenu("Stocks");
    private JMenu m3 = new JMenu("Daily Report");
    private JMenuItem search = new JMenuItem("Search Customer");
    private JMenuItem list = new JMenuItem("Stock List");
    private JMenuItem update = new JMenuItem("Update Stock Prices");
    private JMenuItem report = new JMenuItem("Show Report");

    public ManagerAccount(JPanel parent){
        super();
        setTitle("Bank App");
        report.addActionListener(
            new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    ManagerReport man = new ManagerReport();
                    man.setVisible(true);
                }
            }
        );
        list.addActionListener(
            new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    StockViewManager cust = new StockViewManager();
                    cust.setVisible(true);
                }
            }
        );
        update.addActionListener(
            new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    ChangeStocks stk = new ChangeStocks();
                    stk.setVisible(true);
                }
            }
        );
        search.addActionListener(
            new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    ManagerSearch sear = new ManagerSearch();
                    sear.setVisible(true);
                }
            }
        );


        mb.add(m1);
        m1.add(search);
        mb.add(m2);
        m2.add(list);
        m2.add(update);
        mb.add(m3);
        m3.add(report);
        JLabel user = new JLabel("Welcome back Manager!");

        balanceCheck.setLayout(new BoxLayout(balanceCheck, BoxLayout.PAGE_AXIS));
        Box account = Box.createVerticalBox();
        balanceCheck.add(account);
        account.add(user);
        getContentPane().add(BorderLayout.PAGE_START, mb);
        getContentPane().add(BorderLayout.CENTER,balanceCheck);

        setSize(400,400);

    }
}