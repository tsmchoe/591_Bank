package src.bank;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
 
public class LoginDemo extends JDialog{

    private static final long serialVersionUID = 1L;
    private JFrame panel = new JFrame("Bank Demo");
    private JPanel buttons = new JPanel(new GridLayout());
    private JButton customer = new JButton("Customer");
    private JButton manager = new JButton("Manger");
    private JLabel head = new JLabel("Please Choose Sign-in Option");

    public LoginDemo(Frame parent){
        super();

        GridBagConstraints cs = new GridBagConstraints();
        cs.fill = GridBagConstraints.HORIZONTAL;
        buttons.add(customer);
        buttons.add(manager);
        head.setFont(new Font("Verdana",1,20));
        getContentPane().add(head, BorderLayout.CENTER);
        getContentPane().add(buttons, BorderLayout.PAGE_END);
        setSize(400,400);


        customer.addActionListener(
            new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    LoginView login = new LoginView(panel);
                    panel.setVisible(false);
                    login.setVisible(true);
                    }
                }
            );
       /*  manager.addActionListener(
            new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    ManagerLogin mLog = new ManagerLogin(panel);
                    panel.setVisible(false);
                    mLog.setVisible(true);
                    }
                }
            ); */

    }

}