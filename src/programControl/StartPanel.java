package programControl;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import Logger.Logger;
import game.GamePictures;
import messageHandler.ObjectType;
import sounds.SoundPlayer;
public class StartPanel extends JLayeredPane{
	private JLabel background;
	private JLabel userBackground;
	private ButtonPictures buttonPictures;
	private GamePictures gamePictures;
	UserPanel userPanel;
	MainController mainController;
	Logger logger = Logger.getLogger();
	public StartPanel(MainController mainController)
	{
		this.mainController = mainController;
		initialize();
	}

	private void initialize() 
	{
		this.gamePictures = GamePictures.getInstance();
		this.buttonPictures = ButtonPictures.getInstance();


		background = new JLabel(new ImageIcon(gamePictures.get(ObjectType.startPanel)));
		background.setBounds(0,  0, 1920, 1030);
		this.add(background,1,0);
		logger.debug("background added");
		this.setLayout(null);



		userBackground = new JLabel(new ImageIcon(gamePictures.get(ObjectType.userPanel)));
		userBackground.setBounds(500, 200, 950, 500);
		this.add(userBackground, 2,0);


		JLabel users = new JLabel("USERS");
		users.setBounds(900, 100, 200, 40);
		users.setFont(new Font("Serif", Font.BOLD, 40));
		users.setForeground(Color.WHITE);
		users.setVisible(true);
		this.add(users, 2, 0);


		userPanel = new UserPanel(mainController);
		this.add(userPanel, 2, 0);

		setButtons();

		this.setVisible(true);

		mainController.playSound("I Saved You");


	}

	private void setButtons() 
	{

		Button enter = new Button("enter", 390, 750, 380, 100);
		enter.addActionListener((e)->{
			if(mainController.getUsername() != null) {
				mainController.toMenu();
			}
		});
		this.add(enter,2,0);

		Button addUser = new Button("addUser", 790, 750, 380, 100);
		addUser.addActionListener((e)->{
			AddingPanel addingPanel = new AddingPanel(mainController, this);
			this.disablePanel();
			this.add(addingPanel, 4, 0);
			//StartPanel.getPanel().setEnabled(false);
			this.repaint();
			this.revalidate();
			mainController.revalidate();


		});
		this.add(addUser,2,0);

		Button removeUser = new Button("removeUser", 1190, 750, 380, 100);
		removeUser.addActionListener((e)->{

			RemovingPanel removingPanel;
			try {
				removingPanel = new RemovingPanel(mainController, this);
				this.disablePanel();
				this.add(removingPanel, 4, 0);
				//				StartPanel.getPanel().setEnabled(false);
				this.repaint();

			} catch (IOException e1) {
				e1.printStackTrace();
			}

		});
		this.add(removeUser,2,0);


		Button quit = new Button("quit", 40, 870, 380, 100);
		quit.addActionListener((e)->{

			mainController.saveInfo();

			System.exit(0);
		});
		this.add(quit,2,0);


	}
	public void removeLabel(String username) {
		userPanel.removeLabel(username);
	}

	public void addLabel(String username) {
		try {
			userPanel.addLabel(username);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void choosePlayer(String username) {
		userPanel.choosePlayer(username);
	}

	public void disablePanel()
	{

		for (Component c : this.getComponents()) {
			c.setEnabled(false);
		}
	}

	public void enablePanel()
	{
		for (Component c : this.getComponents()) {
			c.setEnabled(true);
		}
	}


}
