
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.table.*;

public class DateReport extends JDialog {

    private static final long serialVersionUID = 1L;
    private DBConnector db = new DBConnector();
    private JTable transactionList;
    private String[] columns = new String[]{
        "Transaction ID", "User ID", "Account ID", "Amount", "Currency", "Date"
    };
    private DefaultTableModel tableModel = new DefaultTableModel(columns,0);


    public DateReport(String s) {
    ArrayList<Transaction> newer = db.getTransactions_Date(s);
    for(int i=0; i < newer.size(); i++){
        Transaction intro = newer.get(i);
        int newId = intro.transactionID;
        int useId = intro.userid;
        int account = intro.accountId;
        double setAmount = intro.amount;
        String curr = intro.currency;
        String dater = intro.date;
        Object[] data = {newId, useId, account, setAmount, curr, dater};
        tableModel.addRow(data);
    }
    transactionList = new JTable(tableModel);
    JScrollPane scroll = new JScrollPane();
    scroll.add(transactionList);
    getContentPane().add(scroll, BorderLayout.CENTER);
    setSize(400,400);
    setTitle("View User Transactions");
    }
}