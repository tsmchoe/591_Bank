package bank.control;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import bank.views.*;

public class CreateControl {

	private LoginView loginView;
	private JPanel parentPanel;

	public CreateControl() {
		loginView =  new LoginView();
		parentPanel.add(loginView, BorderLayout.CENTER);

		initController();
	}

	public void initController()
	{
		//loginView.getLogButton().addActionListener(l -> Login());
	}
}