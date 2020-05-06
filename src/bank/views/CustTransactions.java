package bank.views;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import bank.*;
import java.util.*;

public class CustTransactions extends JDialog {

    private static final long serialVersionUID = 1L;
    private JTable transactionList;
    private DBConnector trans = new DBConnector();
    private Transaction[] data;
    private LoginView Login = new LoginView();

    JPanel stockaccount = new JPanel();
    public CustTransactions(){
        super();
        String[] columns = new String[]{
            "ID", "User ID", "Account ID", "Amount", "Currency", "Date"
        };
        ArrayList<Transaction> newer = trans.getTransactionsByUser(Login.getUserId());
        for(int i=0; i < newer.size(); i++){
            data.add(newer.get(i));
        }
        transactionList = new JTable(columns,newer);

    }
}