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
    private JMenuItem request = new JMenuItem("Request");
    private JMenuItem list = new JMenuItem("See Other Accounts");
    private JMenuItem update = new JMenuItem("Update Profile");
    private JMenuItem view = new JMenuItem("See All");
    private JMenuItem deposit = new JMenuItem("Deposit");
    private JMenuItem withdrawl = new JMenuItem("Withdrawl");

    public CustCheckAccount(Customer cust){
        super();
        setTitle("Bank App");
        deposit.addActionListener(
            new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    CustDeposit dep = new CustDeposit(cust);
                    dep.setVisible(true);
                }
            }
        );
        withdrawl.addActionListener(
            new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    CustWithdraw with = new CustWithdraw(cust);
                    with.setVisible(true);
                }
            }
        );

        request.addActionListener(
            new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    LoanReq loan = new LoanReq(cust);
                    loan.setVisible(true);
                }
            }
        );

        mb.add(m1);
        m1.add(update);
        m1.add(list);
        mb.add(m3);
        m3.add(request);
        mb.add(m4);
        m4.add(deposit);
        m4.add(withdrawl);
        m4.add(view);
        JLabel user = new JLabel("Welcome: " + cust.firstName);
        JLabel balance = new JLabel("Here is your current balance: $" + String.valueOf(cust.balance));
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
