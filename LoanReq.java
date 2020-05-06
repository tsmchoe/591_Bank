

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
 
public class LoanReq extends JDialog{

    private static final long serialVersionUID = 1L;
    private JPanel amount = new JPanel();
    private JLabel script;
    private JTextField entry;
    private JButton submit;
    private String[] accountType = {"USD", "EURO", "CNY"};
    private JComboBox checkSave;
    private String choice;
    
    public LoanReq(Customer cust){
        super();
        amount.setLayout(new BoxLayout(amount, BoxLayout.Y_AXIS));
        script = new JLabel("Submit a Loan request amount:");
        script.setAlignmentX(Component.CENTER_ALIGNMENT);
        amount.add(Box.createRigidArea(new Dimension(0,10)));
        amount.add(script);
        entry = new JTextField(40);
        entry.setAlignmentX(Component.CENTER_ALIGNMENT);
        amount.add(entry);
        JLabel col = new JLabel("Enter Collateral:");
        col.setAlignmentX(Component.CENTER_ALIGNMENT);
        amount.add(col);
        JTextField kac = new JTextField(40);
        kac.setAlignmentX(Component.CENTER_ALIGNMENT);
        amount.add(kac);
        checkSave = new JComboBox(accountType);
        checkSave.setAlignmentX(Component.CENTER_ALIGNMENT);
        amount.add(checkSave);


        submit = new JButton("Submit");

        checkSave.addActionListener(
            new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    choice = (String)checkSave.getSelectedItem();
                }
            }
        );

        submit.addActionListener(
            new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    String x = entry.getText();
                    Double loanAmount = Double.parseDouble(x);
                    cust.createNewLoan(cust.userID, loanAmount, kac.getText());
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