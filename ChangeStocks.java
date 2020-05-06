//Page that allows manager to change stock prices
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

 
public class ChangeStocks extends JDialog{
    private static final long serialVersionUID = 1L;
    private JPanel amount = new JPanel(new GridBagLayout());
    private JLabel script, amounter;
    private JTextField entry, entitiy;
    private JButton submit;
    private DBConnector db = new DBConnector();
    public ChangeStocks(){
        super();
        GridBagConstraints cs = new GridBagConstraints();
        cs.fill = GridBagConstraints.HORIZONTAL;
        script = new JLabel("Enter Stock ID to Change");
        cs.gridx = 0;
        cs.gridy = 0;
        cs.gridwidth = 1;
        amount.add(script, cs);

        entry = new JTextField(40);
        cs.gridx = 1;
        cs.gridy = 0;
        cs.gridwidth = 2;
        amount.add(entry, cs);

        amounter = new JLabel("Enter Amount to Change");
        cs.gridx = 0;
        cs.gridy = 1;
        cs.gridwidth = 1;
        amount.add(amounter, cs);

        entitiy = new JTextField(40);
        cs.gridx = 1;
        cs.gridy = 1;
        cs.gridwidth = 3;
        amount.add(entitiy, cs);

        submit = new JButton("Change Stock");
        cs.gridx = 1;
        cs.gridy = 2;
        cs.gridwidth = 1;
        amount.add(submit, cs);

        submit.addActionListener(
            new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    ArrayList<Stock> newer = db.getAllAvailableStocks();
                    for (int i=0; i < newer.size(); i++){
                        if(newer.get(i).getId() == (Integer.parseInt(entry.getText()))){
                            newer.get(i).updateStockPrice(Double.parseDouble(entitiy.getText()));
                        }
                    }  
                }
            }
        );

        getContentPane().add(amount, BorderLayout.CENTER);
        setSize(400,400);
        setTitle("Change Stocks");
    }
}