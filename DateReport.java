

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class DateReport extends JDialog {

    public DateReport(String s){
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
    }
}