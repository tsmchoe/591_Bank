//Allows customer to see unrealized profit
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

 
public class UnrealizedProfit extends JDialog{
    private static final long serialVersionUID = 1L;
    private JPanel amount = new JPanel(new GridBagLayout());
    private JLabel script;
    private JTextField entry;
    private JButton submit;
    private JLabel lab;

    public UnrealizedProfit(Customer cust){
        super();
        GridBagConstraints cs = new GridBagConstraints();
        cs.fill = GridBagConstraints.HORIZONTAL;
        script = new JLabel("Enter Stock ID to see unrealized profit:");
        cs.gridx = 0;
        cs.gridy = 0;
        cs.gridwidth = 2;
        amount.add(script, cs);

        entry = new JTextField(40);
        cs.gridx = 1;
        cs.gridy = 1;
        cs.gridwidth = 3;
        amount.add(entry, cs);

        submit = new JButton("See Stock");
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
                            lab = new JLabel(String.valueOf(stonk.get(i).getProfit(Integer.parseInt(entry.getText()))));
                        }
                    }
                    System.out.println(lab);

                    
                }
            }
        );

        getContentPane().add(amount, BorderLayout.CENTER);
        setSize(400,400);
        setTitle("Sell Stocks");
    }
}