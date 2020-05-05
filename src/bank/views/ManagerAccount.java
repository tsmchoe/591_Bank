package src.bank.views;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

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
                    CustomerList cust = new CustomerList();
                    cust.setVisible(true);
                }
            }
        );
        update.addActionListener(
            new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    StockViewManager stk = new StockViewManager();
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
        JLabel user = new JLabel("Welcome:");
        JLabel balance = new JLabel("Here are all your customers:");
        JTable customerList = new JTable();        
        JScrollPane scrollPane = new JScrollPane();
        Dimension d = customerList.getPreferredSize();
        scrollPane.setPreferredSize(new Dimension(d.width, customerList.getRowHeight()*6+1));
        //add fucntionality to table
        balanceCheck.setLayout(new BoxLayout(balanceCheck, BoxLayout.PAGE_AXIS));
        Box account = Box.createVerticalBox();
        balanceCheck.add(account);
        account.add(user);
        account.add(balance);
        account.add(scrollPane);
        getContentPane().add(BorderLayout.PAGE_START, mb);
        getContentPane().add(BorderLayout.CENTER,balanceCheck);

        setSize(400,400);

    }
}