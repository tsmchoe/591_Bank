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
    private JMenu m3 = new JMenu("Loans");
    private JMenu m4 = new JMenu("Transactions");
    private JMenuItem views = new JMenuItem("Views");
    private JMenuItem deposit = new JMenuItem("Deposit");
    private JMenuItem withdrawl = new JMenuItem("Withdrawl");
    private JTable customers = new JTable();

    public ManagerAccount(){

    }
    
}