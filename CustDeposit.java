import javax.swing.*;
import java.awt.*;
 
public class CustDeposit extends JDialog{

    private static final long serialVersionUID = 1L;
    private JPanel amount = new JPanel(new GridBagLayout());
    private JLabel script;
    private JTextField entry;
    private JButton submit;
    
    public CustDeposit(JPanel parent){
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

        submit = new JButton("Submit");
        cs.gridx = 1;
        cs.gridy = 2;
        cs.gridwidth = 1;
        amount.add(submit, cs);

        getContentPane().add(amount, BorderLayout.CENTER);
        setSize(400,400);
        setTitle("Deposit");

    }
}
