package bank.views;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import bank.*;

public class LoginView extends JDialog implements ActionListener{

    private static final long serialVersionUID = 1L;
    private JPanel Login = new JPanel(new GridBagLayout());
    private JLabel script = new JLabel("Sign in Valued Customer!");
    private JButton Submit = new JButton("Submit");
    protected JTextField text1, text2;

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


        JLabel label2 = new JLabel("Password: ");
        cs.gridx = 0;
        cs.gridy = 1;
        cs.gridwidth = 1;
        Login.add(label2, cs);

        text2 = new JTextField(15);
        cs.gridx = 1;
        cs.gridy = 1;
        cs.gridwidth = 2;
        Login.add(text2, cs);


        JPanel panel = new JPanel();
        Submit.addActionListener(this);
        panel.add(Submit);
        getContentPane().add(script, BorderLayout.PAGE_START);
        getContentPane().add(Login, BorderLayout.CENTER);
        getContentPane().add(panel, BorderLayout.PAGE_END);
        setSize(400,400);
        setTitle("Login");

    }

    /*public JButton getLogButton(){
        return Submit;
    }
    public String getUsername(){
        return text1.getText();
    }
    public String getPassword(){
        return text2.getText();
    }*/


    public void actionPerformed(ActionEvent ae){
        String value1=text1.getText();
        String value2=text2.getText();
        DBConnector dbc = new DBConnector();
		Boolean x =	dbc.checkUserByUsername(value1);
        if (x == true) {
            System.out.println("Logged in");
            CustSaveAccount page=new CustSaveAccount(Login);
            Login.setVisible(false);
            page.setVisible(true);
        }
        else{
            System.out.println("Failed");
            JOptionPane.showMessageDialog(null, "not authorized");
        }
    }
    /*private boolean validated(String text1, String text2){
        User u = new User("null","null", "null", text1, text2, 0.0);
        if (u.getUsername().equals(text1) && u.getPassword().equals(text2)) {
            return true;
        }
        return false;

    }*/

}