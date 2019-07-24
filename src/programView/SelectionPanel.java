package programView;
import java.awt.Color;
import java.awt.Font;
import java.awt.Point;
import java.awt.image.BufferedImage;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import client.swing.Background;
import game.GamePictures;
import messageHandler.ObjectType;

public class SelectionPanel extends JPanel{
	MainController mainController;
	Menu menu;
	public SelectionPanel(MainController mainController, Menu menu) {
		this.mainController = mainController;
		this.menu = menu;
		initialize();
	}
	
	private void initialize() {
		Color blue = new Color(100,100,230); 
		this.setLayout(null);
		this.setBounds(720, 250, 500, 300);
		this.setBackground(Color.black);
		this.setBorder(BorderFactory.createLineBorder(Color.white));
		
		addButtons();
		
	
	}
	
	private void addButtons() {
		JButton cancel = new JButton(new ImageIcon(ButtonPictures.getInstance().get("multiplySign")));
		cancel.setBorder(null);
        cancel.setBorderPainted(false);
        cancel.setContentAreaFilled(false);
        cancel.setOpaque(false);
		cancel.setBounds(10, 10, 60, 60);
		cancel.addActionListener((e)->{
			menu.remove(this);
			menu.enablePanel();
			menu.revalidate();
		});
		this.add(cancel);
		
		Button singlePlayer = new Button("SinglePlayer", 50, 50, 400, 100);
		singlePlayer.addActionListener((e)->{
			mainController.singlePlayer();
		});
		this.add(singlePlayer,1,0);

		Button multiPlayer = new Button("MultiPlayer", 50, 150, 400, 100);
		multiPlayer.addActionListener((e)->{
			mainController.toServerOrClientPanel();
		});
		this.add(multiPlayer,1,0);
	}
	
}
