package src.bank.views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import src.bank.control.*;
 
public class SignLog {
    final static JFrame frame = new JFrame("Bank Demo");
    final static JLabel welcome = new JLabel("Welcome to the Bank App");
    
    public static void main(String arg[]) {
        try {
            final JButton log = new JButton("Login");
            final JButton create = new JButton("Create Account");
            welcome.setFont(new Font("Verdana",1,20));
            welcome.setBounds(50,100,400,100);
            log.addActionListener(
                new ActionListener(){
                    public void actionPerformed(ActionEvent e){
                    LoginControl logger = new LoginControl();
                    frame.setVisible(false);
                    }
                }
            );
            log.setBounds(50,300,150,40);
            create.setBounds(200,300,150,40);
            create.addActionListener(
                new ActionListener(){
                    public void actionPerformed(ActionEvent e){
                    CreateAccount cAcc = new CreateAccount(frame);
                    frame.setVisible(false);
                    cAcc.setVisible(true);
                    }
                }
            );
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(400, 400);
            frame.setLayout(null);
            frame.add(welcome);
            frame.add(log);
            frame.add(create);
            frame.setVisible(true);
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());}
        }

}