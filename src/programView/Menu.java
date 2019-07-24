package programView;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.Window.Type;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import game.GamePictures;
import messageHandler.ObjectType;
import sounds.SoundPlayer;

public class Menu extends JLayeredPane{
	MainController mainController;
	private JLabel background;
	private JLabel userLabel;
	SoundPlayer soundPlayer;

	public Menu(MainController mainController)
	{
		this.mainController = mainController;
		initialize();
	}

	private void initialize()
	{

		this.setLayout(null);
		background = new JLabel(new ImageIcon(GamePictures.getInstance().get(ObjectType.menuePanel)));
		background.setBounds(0,  0, 1920, 1030);
		this.add(background,1,0);

		userLabel = new JLabel("Hello, " + mainController.getUsername() + "!");
		userLabel.setFont(new Font("Serif", Font.BOLD, 40));
		userLabel.setForeground(Color.white);
		userLabel.setHorizontalAlignment(JLabel.CENTER);
		userLabel.setBounds(810, 370, 300, 50);
		this.add(userLabel, 3, 0);


		setButtons();  
	}



	public void setButtons()
	{

		Button newGame = new Button("newGame", 660, 430, 600, 100);
		newGame.addActionListener((e)->{
			mainController.toSelectionPanel();

		});
		newGame.setVisible(true);
		this.add(newGame,2,0);

		Button resume = new Button("resume", 660, 540, 600, 100);
		resume.addActionListener((e)->{
				mainController.resume();
		});
		this.add(resume,2,0);


		Button ranking = new Button("ranking", 660, 650, 600, 100);
		ranking.addActionListener((e)->{


		});
		this.add(ranking,2,0);


		Button setting = new Button("setting", 660, 760, 600, 100);
		setting.addActionListener((e)->{
			mainController.toSetting();
		});
		this.add(setting,2,0);


		Button quit = new Button("quit", 40, 870, 380, 100);
		quit.addActionListener((e)->{
			mainController.quit();
		});
		this.add(quit,2,0);

		Button back = new Button("back", 1480, 870, 380, 100);
		back.addActionListener((e)->{
			try {
				mainController.toStartPanel();
			}catch(Exception x)
			{

			}
		});
		this.add(back,2,0);
	}


	public JLabel getUserLabel() {
		return userLabel;
	}

	public void setUserLabel(JLabel userLabel) {
		this.userLabel = userLabel;
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
