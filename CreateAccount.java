import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
 
public class CreateAccount extends JDialog {

    private static final long serialVersionUID = 1L;
    private final JPanel panel = new JPanel();
    private JLabel firstname = new JLabel("First Name");
    private JTextField firster = new JTextField(20);
    private JLabel lastname = new JLabel("Last Name");
    private JTextField laster = new JTextField(20);
    private JLabel address = new JLabel("Username");
    private JTextField addresser = new JTextField(40);

    private JLabel amount = new JLabel("Starting Deposit Amount in USD");
    private JTextField amounter = new JTextField(40);
    private JLabel password = new JLabel("Password");
    private JTextField passworder = new JTextField(20);
    private JButton bu1 = new JButton("Create Checking Account");
    private JButton bu2 = new JButton("Create Savings Account");
    private JPanel buttons = new JPanel();

    private Customer customer;

    public CreateAccount(Frame parent){
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
        amount.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(amount);
        amounter.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(amounter);
        password.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(password);
        passworder.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(passworder);


        bu1.addActionListener(
            new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    //Create Custo
                    //customer = new Customer(Integer.parseInt(user.getText()), firster.getText(), laster.getText(), addresser.getText(), passworder.getText(), Double.parseDouble(amounter.getText()));
                    //Customer.createNewCustomer(firster.getText(), laster.getText(), addresser.getText(), passworder.getText());
                    customer = new Customer(firster.getText(), laster.getText(), addresser.getText(), passworder.getText(), Double.parseDouble(amounter.getText()));
                    customer.createNewCheckings(Double.parseDouble(amounter.getText()), customer.getUserID(), new Currency("USD"));
                    CustCheckAccount dep = new CustCheckAccount(customer);
                    setVisible(false);
                    dep.setVisible(true);
                }
            }
        );

        bu2.addActionListener(
            new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    //Create Customer
                    customer = new Customer(firster.getText(), laster.getText(), addresser.getText(), passworder.getText(), Double.parseDouble(amounter.getText()));
                    customer.createNewSavings(Double.parseDouble(amounter.getText()), customer.getUserID(), new Currency("USD"));
                    CustSaveAccount dep = new CustSaveAccount(customer);
                    setVisible(false);
                    dep.setVisible(true);
                }
            }
        );


        buttons.add(bu1);
        buttons.add(bu2);



        getContentPane().add(panel);
        getContentPane().add(buttons, BorderLayout.PAGE_END);
        setSize(500,500);
        setTitle("Create Account");


        
    }
}