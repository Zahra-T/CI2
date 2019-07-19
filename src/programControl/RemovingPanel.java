package programControl;

import java.awt.Color;
import java.awt.Font;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class RemovingPanel extends JPanel{

	private JTextField username;
	private String address = "C:\\Users\\Markazi.co\\workspace\\Chicken Invaders\\src\\game\\resources\\";
	MainController mainController;
	StartPanel startPanel;
	public RemovingPanel(MainController mainController, StartPanel startPanel) throws IOException
	{
		this.mainController = mainController;
		this.startPanel = startPanel;
		initialize();
	}

	private void initialize() throws IOException
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

	public void setButtons() throws IOException
	{
		Button remove = new Button("remove", 20, 200, 220, 60);
		remove.addActionListener((e)->{
				String name = username.getText();
				if(mainController.containsPlayer(name)) {
					mainController.removePlayer(name);
					startPanel.removeLabel(name);
					startPanel.remove(this);
					startPanel.enablePanel();
					startPanel.revalidate();
				}
		});
		this.add(remove,2,0);

		Button cancel = new Button("cancel", 250, 200, 220, 60);
		cancel.addActionListener((e)->{
				startPanel.remove(this);
				startPanel.enablePanel();
				startPanel.revalidate();
		});
		this.add(cancel,2,0);


	}




}
