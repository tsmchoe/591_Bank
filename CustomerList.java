import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;


public class CustomerList extends JDialog {
    private static final long serialVersionUID = 1L;
    private JTable transactionList;
    JPanel stockaccount = new JPanel();
    private DBConnector db = new DBConnector();
    private String[] columns = new String[]{
        "Account"
    };
    private DefaultTableModel tableModel = new DefaultTableModel(columns,0);

    public CustomerList(Customer cust){
        super();
        ArrayList<CheckingsAccount> newer = db.getCheckingsAccountByUser(cust.getUserID());
        for(int i=0; i < newer.size(); i++){
            CheckingsAccount check = newer.get(i);
            Object[] data = {check};
            tableModel.addRow(data);
        }
        ArrayList<SavingsAccount> sec = db.getSavingsAccountByAccountID(cust.getUserID());
        for(int i=0; i < sec.size(); i++){
            SavingsAccount save = sec.get(i);
            Object[] data = {save};
            tableModel.addRow(data);
        }
        ArrayList<SecurityAccount> curity = db.getSecurityAccountByUser(cust.getUserID());
        for(int i=0; i < newer.size(); i++){
            SecurityAccount cure = curity.get(i);
            Object[] data = {cure};
            tableModel.addRow(data);
        }
        transactionList = new JTable(tableModel);
        getContentPane().add(transactionList);
  
        setSize(400,400);
        setTitle("List of Accounts");


    }
}
