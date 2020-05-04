import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class CustCheckAccount extends JDialog implements ActionListener {

    private static final long serialVersionUID = 1L;
    private JPanel account = new JPanel();
    private JLabel info = new JLabel("Welcome!");
    private JMenuBar mb = new JMenuBar();
    private JMenu m1 = new JMenu("Profile");
    private JMenu m2 = new JMenu("Stocks");
    private JMenu m3 = new JMenu("Loans");
    private JMenu m4 = new JMenu("Transactions");
    public CustCheckAccount() {
        mb.add(m1);
        mb.add(m2);
        mb.add(m3);
        mb.add(m4);
        account.add(BorderLayout.NORTH, mb);
        
    }

    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub

    }
}
