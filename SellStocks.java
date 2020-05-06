//Allows user to sell stocks

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

 
public class SellStocks extends JDialog{
    private static final long serialVersionUID = 1L;
    private JPanel amount = new JPanel(new GridBagLayout());
    private JLabel script;
    private JTextField entry;
    private JButton submit;

    public SellStocks(Customer cust){
        super();
        GridBagConstraints cs = new GridBagConstraints();
        cs.fill = GridBagConstraints.HORIZONTAL;
        script = new JLabel("Enter Stock ID to Sell:");
        cs.gridx = 0;
        cs.gridy = 0;
        cs.gridwidth = 2;
        amount.add(script, cs);

        entry = new JTextField(40);
        cs.gridx = 1;
        cs.gridy = 1;
        cs.gridwidth = 3;
        amount.add(entry, cs);

        submit = new JButton("Sell Stock");
        cs.gridx = 1;
        cs.gridy = 2;
        cs.gridwidth = 1;
        amount.add(submit, cs);

        submit.addActionListener(
            new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    ArrayList<SecurityAccount> sec = cust.getAllSecurities();
                    ArrayList<BoughtStock> stonk = sec.get(0).getAllStocks();
                    for (int i=0; i < stonk.size(); i++){
                        if(stonk.get(i).getID() == (Integer.parseInt(entry.getText()))){
                            stonk.remove(i).getProfit(Integer.parseInt(entry.getText()));
                        }
                    }

                    
                }
            }
        );

        getContentPane().add(amount, BorderLayout.CENTER);
        setSize(400,400);
        setTitle("Sell Stocks");
    }
}