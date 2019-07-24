package programView;


import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Logger.Logger;

public class ServerOrClientPanel extends JPanel{
	MainController mainController;
	Menu menu;
	public ServerOrClientPanel(MainController mainController, Menu menu) {
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
		
		Button server = new Button("WorkAsServer", 50, 50, 400, 100);
		server.addActionListener((e)->{
			mainController.workAsServer();
		});
		this.add(server,1,0);

		Button client = new Button("WorkAsClient", 50, 150, 400, 100);
		client.addActionListener((e)->{
			mainController.workAsClient();
		});
		this.add(client,1,0);
	}
	
}
