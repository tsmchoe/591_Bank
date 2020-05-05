package bank.control;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import bank.*;
import bank.views.*;

public class LoginControl {

	private LoginView Login;

	public LoginControl() {
		/*Login =  new LoginView();
		parentPanel = PanelData.getParentPanel();
		parentPanel.removeAll();
		parentPanel.revalidate();
		parentPanel.repaint();
		parentPanel.add(loginView, BorderLayout.CENTER);

		Login.setVisible(true);*/

		initController();
	}

	public void initController()
	{
		Login.getLogButton().addActionListener(x -> LoginAction());
		//loginView.getLoginManagerButton().addActionListener(l -> loginManager());
	}

	public void LoginAction()
	{
		//if (Login.text1.equals(User.getUsername()) && Login.text2.equals(User.getPassword()))
		//{
			//CustSaveAccount page=new CustSaveAccount(Login);
           // Login.setVisible(false);
          //  page.setVisible(true);
		//}
		//else
		//{
			//Login.setMsgLabel("Not a Verified User");
		//}
		
	}

}