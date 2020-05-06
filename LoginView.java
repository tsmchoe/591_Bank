
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;

public class LoginView extends JDialog implements ActionListener{

    private static final long serialVersionUID = 1L;
    private JPanel Login = new JPanel(new GridBagLayout());
    private JLabel script = new JLabel("Sign in Valued Customer!");
    private JButton Submit = new JButton("Submit");
    protected JTextField text1, text2, text3;
    private String[] accountType = {"Checking", "Savings"};
    private JComboBox checkSave;
    private String choice;



    public LoginView() {
        super();
        GridBagConstraints cs = new GridBagConstraints();
        cs.fill = GridBagConstraints.HORIZONTAL;
        script.setFont(new Font("Verdana",1,20));

        JLabel label1 = new JLabel("Username: ");
        cs.gridx = 0;
        cs.gridy = 0;
        cs.gridwidth = 1;
        Login.add(label1, cs);
        
        text1 = new JTextField(15);
        cs.gridx = 1;
        cs.gridy = 0;
        cs.gridwidth = 2;
        Login.add(text1, cs);

        JLabel label3 = new JLabel("User ID: ");
        cs.gridx = 0;
        cs.gridy = 1;
        cs.gridwidth = 1;
        Login.add(label3, cs);

        text3 = new JTextField(15);
        cs.gridx = 1;
        cs.gridy = 1;
        cs.gridwidth = 2;
        Login.add(text3, cs);


        JLabel label2 = new JLabel("Password: ");
        cs.gridx = 0;
        cs.gridy = 2;
        cs.gridwidth = 1;
        Login.add(label2, cs);

        text2 = new JTextField(15);
        cs.gridx = 1;
        cs.gridy = 2;
        cs.gridwidth = 2;
        Login.add(text2, cs);

        checkSave = new JComboBox(accountType);
        cs.gridx = 1;
        cs.gridy = 3;
        cs.gridwidth = 2;
        Login.add(checkSave, cs);

        checkSave.addActionListener(
            new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    choice = (String)checkSave.getSelectedItem();
                }
            }
        );

        JPanel panel = new JPanel();
        Submit.addActionListener(this);
        panel.add(Submit);
        getContentPane().add(script, BorderLayout.PAGE_START);
        getContentPane().add(Login, BorderLayout.CENTER);
        getContentPane().add(panel, BorderLayout.PAGE_END);
        setSize(400,400);
        setTitle("Login");

    }
    public String getUsername(){
        return text1.getText();
    }
    public String getPassword(){
        return text2.getText();
    }
    public int getUserId(){
        return Integer.parseInt(text3.getText());
    }


    public void actionPerformed(ActionEvent ae){
        String value1=text1.getText();
        String value2=text2.getText();
        int value3 = Integer.parseInt(text3.getText());
        DBConnector dbc = new DBConnector();
        boolean x = dbc.checkUserByUsername(value1);
        Customer cust = dbc.getCustomerByUserID(value3);
        ArrayList<CheckingsAccount> custCheck = cust.getAllCheckings();
        ArrayList<SavingsAccount> custSave = cust.getAllSavings();
        //ArrayList<SecurityAccount> custSec = cust.getAllSecurities(); 
        if (x == true) {
            System.out.println("Logged in");
            if (choice.equals("Savings") && custSave.size() > 0){
                CustSaveAccount page=new CustSaveAccount(cust);
                page.setVisible(true);
            }
            else if(choice.equals("Checking") && custCheck.size() > 0){
                CustCheckAccount page=new CustCheckAccount(cust);
                page.setVisible(true);
            }
            Login.setVisible(false);
        }
        else{
            System.out.println("Failed");
            JOptionPane.showMessageDialog(null, "not authorized");
        }
    }

}