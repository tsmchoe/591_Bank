
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;

public class CustTransfer extends JDialog {
    private static final long serialVersionUID = 1L;
    private JPanel amount = new JPanel(new GridBagLayout());
    private JLabel script;
    private JTextField entry;
    private JButton submit;
    private String[] payType = {"USD", "EURO", "CNY"};
    private JComboBox checkSave;
    private String choice;

    public CustTransfer(Customer cust){
        super();
        GridBagConstraints cs = new GridBagConstraints();
        cs.fill = GridBagConstraints.HORIZONTAL;
        script = new JLabel("Enter how much you want to transfer to new Account:");
        cs.gridx = 0;
        cs.gridy = 0;
        cs.gridwidth = 2;
        amount.add(script, cs);

        entry = new JTextField(40);
        cs.gridx = 1;
        cs.gridy = 1;
        cs.gridwidth = 3;
        amount.add(entry, cs);

        checkSave = new JComboBox(payType);
        cs.gridx = 1;
        cs.gridy = 2;
        cs.gridwidth = 2;
        amount.add(checkSave, cs);

        submit = new JButton("Submit");
        cs.gridx = 1;
        cs.gridy = 3;
        cs.gridwidth = 1;
        amount.add(submit, cs);

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
                    double x = Double.parseDouble(entry.getText());
                    Currency newCurr = new Currency(choice);
                    ArrayList<SavingsAccount> saveCurr = cust.getAllSavings();
                    SavingsAccount saver = saveCurr.get(0);
                    if(saver.balance >= 2500){
                        saver.withdraw(x, newCurr);
                        cust.createNewSecurity(x, cust.userID, newCurr);
                    }
                    else{
                        JOptionPane.showMessageDialog(null,"Nothing to deposit to");
                    }
                    //Create Deposit
                   //Account acc = new Deposit(transactionID, Login.getUserId(), accountId, Double.parseDouble(entry.getText()), choice, date);
                    setVisible(false);
                }
            }
        );

        getContentPane().add(amount, BorderLayout.CENTER);
        setSize(400,400);
        setTitle("Transfer");

    }
    //create a new security account
}