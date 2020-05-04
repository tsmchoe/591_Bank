import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import 591_Bank.*;

public class LoginControl {

	private LoginView loginView;
	private JPanel parentPanel;

	public LoginControl() {
		loginView =  new LoginView();
		loginView.setVisible(true);
		parentPanel = PanelData.getParentPanel();
		parentPanel.removeAll();
		parentPanel.revalidate();
		parentPanel.repaint();
		parentPanel.add(loginView, BorderLayout.CENTER);

		initController();
	}

	public void initController()
	{
		loginView.getLoginButton().addActionListener(l -> Login());
		loginView.getLoginManagerButton().addActionListener(l -> loginManager());
	}

	public void Login()
	{
		Customer c = new Customer(loginView.text1.getText(), loginView.text2.getText());
		
		var loggedUser = Data.getBank().loginCustomer(c);
		//Data.getBank().setCurrentCustomer(loggedUser);
		if (loggedUser != null)
		{
			custSideView.setVisible(true);	
			PanelData.setSidePanel(custSideView);
			LoggedUser.setProfile(loggedUser);
			ProfileController proController = new ProfileController();
			proController.showUserProfile(loggedUser);
		}
		else
		{
			loginView.setMsgLabel("This user does not exist");
		}
		
	}

	public void loginManager()
	{
		managerSideView.setVisible(true);		
		PanelData.setSidePanel(managerSideView);
		
		ProfileController proController = new ProfileController();
		proController.ShowProfileList();
	}

}