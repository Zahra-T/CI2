package programView;

import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Logger.Logger;

public class UsernamePanel extends JPanel{

	private JTextField username;
	private String address = "C:\\Users\\Markazi.co\\workspace\\Chicken Invaders\\src\\game\\resources\\";
	MainController mainController;
	Menu menu;
	Logger logger = Logger.getLogger();
	public UsernamePanel(MainController mainController, Menu menu) 
	{
		this.mainController = mainController;
		this.menu = menu;
		initialize();
	}

	private void initialize() 
	{
		Color blue = new Color(100,100,230); 
		this.setLayout(null);
		this.setBounds(720, 250, 500, 300);
		this.setBackground(Color.black);
		this.setBorder(BorderFactory.createLineBorder(Color.white));

		username = new JTextField();
		username.setBackground(blue);
		username.setBorder(BorderFactory.createLineBorder(Color.white));
		username.setBounds(50,50, 400, 80);
		username.setHorizontalAlignment(JLabel.CENTER);
		username.setForeground(Color.white);
		username.setFont(new Font("Serif", Font.BOLD, 40));

		this.add(username);
		setButtons();
		this.setVisible(true);
	}

	public void setButtons()
	{
		Button ok = new Button("ok", 20, 200, 220, 60);
		ok.addActionListener((e)->{
			String name = username.getText();
			boolean added = mainController.addPlayer(name);
			logger.debug(added+"");
			if(added) {
				startPanel.addLabel(name);	
				startPanel.remove(this);
				startPanel.enablePanel();
				startPanel.revalidate();
			}
			else
			{
				System.out.println("Please enter another username: ");
			}
		});
		this.add(ok,1,0);

		Button cancel = new Button("cancel", 250, 200, 220, 60);
		cancel.addActionListener((e)->{
			startPanel.remove(this);
			startPanel.enablePanel();
			startPanel.revalidate();
		});
		this.add(cancel,1,0);
	}

}

