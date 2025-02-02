//Displays the customers transactions
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;

import java.util.*;

public class CustTransactions extends JDialog {

    private static final long serialVersionUID = 1L;
    private JTable transactionList;
    private String[] columns = new String[]{
        "Transaction ID", "User ID", "Account ID", "Amount", "Currency", "Date"
    };
    private DBConnector trans = new DBConnector();
    private DefaultTableModel tableModel = new DefaultTableModel(columns,0);

    JPanel stockaccount = new JPanel();
    public CustTransactions(Customer cust){
        super();
        ArrayList<Transaction> newer = trans.getTransactionsByUser(cust.getUserID());
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
        getContentPane().add(transactionList);
        setSize(400,400);
        setTitle("Transactions");

    }
}