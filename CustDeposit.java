//Form to let customers deposit money

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
 
public class CustDeposit extends JDialog{

    private static final long serialVersionUID = 1L;
    private JPanel amount = new JPanel(new GridBagLayout());
    private JLabel script;
    private JTextField entry;
    private JButton submit;
    private String[] payType = {"USD", "EURO", "CNY"};
    private String[] accountType = {"Checking", "Savings", "Security"};
    private JComboBox checkSave, newPower;
    private String choice,move;
    
    public CustDeposit(Customer cust){
        super();
        GridBagConstraints cs = new GridBagConstraints();
        cs.fill = GridBagConstraints.HORIZONTAL;
        script = new JLabel("Enter how much you want to deposit:");
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

        newPower = new JComboBox(accountType);
        cs.gridx = 1;
        cs.gridy = 3;
        cs.gridwidth = 2;
        amount.add(newPower, cs);

        submit = new JButton("Submit");
        cs.gridx = 1;
        cs.gridy = 4;
        cs.gridwidth = 1;
        amount.add(submit, cs);

        checkSave.addActionListener(
            new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    choice = (String)checkSave.getSelectedItem();
                }
            }
        );
        newPower.addActionListener(
            new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    move = (String)newPower.getSelectedItem();
                }
            }
        );


        submit.addActionListener(
            new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    double x = Double.parseDouble(entry.getText());
                    Currency newCurr = new Currency(choice);
                    ArrayList<SavingsAccount> saveCurr = cust.getAllSavings();
                    ArrayList<CheckingsAccount> checkCurr = cust.getAllCheckings();
                    ArrayList<SecurityAccount> secCurr = cust.getAllSecurities();
                    if(saveCurr.size() > 0 && move.equals("Saving")){
                        saveCurr.get(0).deposit(x, newCurr);
                    }
                    else if(checkCurr.size() > 0 && move.equals("Checking")){
                        checkCurr.get(0).deposit(x, newCurr);
                    }
                    else if(secCurr.size() > 0 && move.equals("Security")){
                        secCurr.get(0).deposit(x, newCurr);
                    }
                    else{
                        JOptionPane.showMessageDialog(null,"Nothing to deposit to");
                    }
                    //Create Deposit
                
                }
            }
        );

        getContentPane().add(amount, BorderLayout.CENTER);
        setSize(400,400);
        setTitle("Deposit");

    }
}
