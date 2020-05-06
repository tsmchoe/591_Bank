

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
 
public class LoanReq extends JDialog{

    private static final long serialVersionUID = 1L;
    private JPanel amount = new JPanel(new GridBagLayout());
    private JLabel script;
    private JTextField entry;
    private JButton submit;
    private Customer cust;
    private LoginView login = new LoginView();
    
    public LoanReq(JPanel parent){
        super();
        GridBagConstraints cs = new GridBagConstraints();
        cs.fill = GridBagConstraints.HORIZONTAL;
        script = new JLabel("Submit a Loan request amount:");
        cs.gridx = 0;
        cs.gridy = 0;
        cs.gridwidth = 2;
        amount.add(script, cs);

        entry = new JTextField(40);
        cs.gridx = 1;
        cs.gridy = 0;
        cs.gridwidth = 2;
        amount.add(entry, cs);

        JLabel col = new JLabel("Enter Collateral:");
        cs.gridx = 0;
        cs.gridy = 1;
        cs.gridwidth = 1;
        amount.add(col, cs);

        JTextField kac = new JTextField(40);
        cs.gridx = 1;
        cs.gridy = 1;
        cs.gridwidth = 2;
        amount.add(kac, cs);

        submit = new JButton("Submit");

        submit.addActionListener(
            new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    String x = entry.getText();
                    Double loanAmount = Double.parseDouble(x);
                    cust.createNewLoan("userId", loanAmount, kac.getText(), "loan_date", "payment_date");
                    setVisible(false);
                }
            }
        );
        
        getContentPane().add(amount, BorderLayout.CENTER);
        getContentPane().add(submit, BorderLayout.PAGE_END);
        setSize(400,400);
        setTitle("Loan Request");

    }
}