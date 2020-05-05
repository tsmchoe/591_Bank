package src.bank.views;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class CustCheckAccount extends JDialog {

    private static final long serialVersionUID = 1L;

    private JPanel balanceCheck = new JPanel();
    private JMenuBar mb = new JMenuBar(); 
    private JMenu m1 = new JMenu("Profile");
    private JMenu m3 = new JMenu("Loans");
    private JMenu m4 = new JMenu("Transactions");
    private JMenuItem deposit = new JMenuItem("Deposit");
    private JMenuItem withdrawl = new JMenuItem("Withdrawl");

    public CustCheckAccount(JPanel parent){
        super();
        setTitle("Bank App");
        deposit.addActionListener(
            new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    CustDeposit dep = new CustDeposit(parent);
                    setVisible(false);
                    dep.setVisible(true);
                }
            }
        );
        withdrawl.addActionListener(
            new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    CustWithdraw with = new CustWithdraw(parent);
                    setVisible(false);
                    with.setVisible(true);
                }
            }
        );

        mb.add(m1);
        mb.add(m3);
        mb.add(m4);
        m4.add(deposit);
        m4.add(withdrawl);
        JLabel user = new JLabel("Welcome:");
        JLabel balance = new JLabel("Here is your current balance:");
        balanceCheck.setLayout(new BoxLayout(balanceCheck, BoxLayout.PAGE_AXIS));
        Box account = Box.createVerticalBox();
        balanceCheck.add(account);
        account.add(user);
        account.add(balance);
        getContentPane().add(BorderLayout.PAGE_START, mb);
        getContentPane().add(BorderLayout.CENTER,balanceCheck);

        setSize(400,400);

    }
}
