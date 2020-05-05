package views;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ManagerLogin extends JDialog implements ActionListener {

    private static final long serialVersionUID = 1L;
    public JPanel Login = new JPanel(new GridBagLayout());
    private JButton Submit = new JButton("Submit");
    private JTextField text1, text2;

    public ManagerLogin(Frame parent) {
        super();
        GridBagConstraints cs = new GridBagConstraints();
        cs.fill = GridBagConstraints.HORIZONTAL;

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
        cs.gridy = 01;
        cs.gridwidth = 2;
        Login.add(text2, cs);


        JPanel panel = new JPanel();
        Submit.addActionListener(this);
        panel.add(Submit);
        getContentPane().add(Login, BorderLayout.CENTER);
        getContentPane().add(panel, BorderLayout.PAGE_END);
        setSize(400,400);
        setTitle("Manager LOGIN FORM");

    }

    public void actionPerformed(ActionEvent ae){
        String value1=text1.getText();
        String value2=text2.getText();
        if (value1.equals("roseindia") && value2.equals("roseindia")) {
            NextPage page=new NextPage();
            JLabel label = new JLabel("Welcome:"+value1);
            page.getContentPane().add(label);
            page.setVisible(true);
        }
        else{
            JLabel label = new JLabel("Failed");
        }
    }
}