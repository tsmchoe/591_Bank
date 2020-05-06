//Form allowing customer to update their profile
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class CustProfile extends JDialog {

    private static final long serialVersionUID = 1L;
    private final JPanel panel = new JPanel();
    private JLabel firstname = new JLabel("First Name");
    private JTextField firster = new JTextField(20);
    private JLabel lastname = new JLabel("Last Name");
    private JTextField laster = new JTextField(20);
    private JLabel address = new JLabel("Username");
    private JTextField addresser = new JTextField(40);

    private JLabel password = new JLabel("Password");
    private JTextField passworder = new JTextField(20);
    private JButton bu1 = new JButton("Update Profile");
    private JPanel buttons = new JPanel();

    public CustProfile(Customer cust) {
        super();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        firstname.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(Box.createRigidArea(new Dimension(0,20)));
        panel.add(firstname);
        firster.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(firster);
        lastname.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(lastname);
        laster.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(laster);
        address.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(address);
        addresser.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(addresser);
        password.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(password);
        passworder.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(passworder);
        buttons.add(bu1);

        bu1.addActionListener(
            new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    cust.setFirstname(firster.getText());
                    cust.setLastname(laster.getText());
                    cust.setUsername(addresser.getText());
                    cust.setPassword(passworder.getText());
                }
            }
        );


        getContentPane().add(panel);
        getContentPane().add(buttons, BorderLayout.PAGE_END);
        setSize(400,400);
        setTitle("Update Account");
    }
}