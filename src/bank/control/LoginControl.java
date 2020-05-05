package bank.control;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import bank.*;
import bank.views.*;

public class LoginControl {

	private LoginView Login;
	private JPanel parent;

	public LoginControl() {
		Login = new LoginView();
		parent = new JPanel();
		parent.add(Login);
		Login.setVisible(true);

		initController();
	}

	public void initController()
	{
		Login.getLogButton().addActionListener(x -> LoginAction());
	}

	public void LoginAction()
	{
		String user_name = Login.getUsername();
		String password = Login.getPassword();
		DBConnector dbc = new DBConnector();
		boolean x =	dbc.checkUserByUsername(user_name);
		if (x == true){
			CustSaveAccount page=new CustSaveAccount(parent);
            parent.setVisible(false);
            page.setVisible(true);
		}
		else{

		}

	}

}