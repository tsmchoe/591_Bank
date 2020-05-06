import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class CustSaveAccount extends JDialog {

    private static final long serialVersionUID = 1L;

    private JPanel balanceCheck = new JPanel();
    private JMenuBar mb = new JMenuBar(); 
    private JMenu m1 = new JMenu("Profile");
    private JMenu m2 = new JMenu("Stocks");
    private JMenu m3 = new JMenu("Loans");
    private JMenu m4 = new JMenu("Transactions");
    private JMenuItem update = new JMenuItem("Update Profile");
    private JMenuItem transfer = new JMenuItem("Transfer to Savings");
    private JMenuItem request = new JMenuItem("Request");
    private JMenuItem stock = new JMenuItem("See Stock Market");
    private JMenuItem view = new JMenuItem("See All");
    private JMenuItem deposit = new JMenuItem("Deposit");
    private JMenuItem withdrawl = new JMenuItem("Withdrawl");

    public CustSaveAccount(JPanel parent){
        super();
        setTitle("Bank App");
        deposit.addActionListener(
            new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    CustDeposit dep = new CustDeposit(parent);
                    dep.setVisible(true);
                }
            }
        );
        withdrawl.addActionListener(
            new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    CustWithdraw with = new CustWithdraw(parent);
                    with.setVisible(true);
                }
            }
        );

        request.addActionListener(
            new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    LoanReq loan = new LoanReq(parent);
                    loan.setVisible(true);
                }
            }
        );
        update.addActionListener(
            new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    CustProfile up = new CustProfile();
                    up.setVisible(true);
                }
            }
        );
        transfer.addActionListener(
            new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    CustTransfer tra = new CustTransfer();
                    tra.setVisible(true);
                }
            }
        );
        stock.addActionListener(
            new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    StockViewCust st = new StockViewCust();
                    st.setVisible(true);
                }
            }
        );
        view.addActionListener(
            new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    CustTransactions st = new CustTransactions();
                    st.setVisible(true);
                }
            }
        );


        mb.add(m1);
        m1.add(update);
        mb.add(m2);
        m2.add(stock);
        m2.add(transfer);
        mb.add(m3);
        m3.add(request);
        mb.add(m4);
        m4.add(deposit);
        m4.add(withdrawl);
        m4.add(view);
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
