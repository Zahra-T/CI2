package client;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class ClientMain {

	public static void main(String[] args) {
		
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setBounds(0,0,screenSize.width, screenSize.height);
	
		Client client = new Client("127.0.0.1", 1111, "zahra");
		
		
		GamePanel gamePanel = new GamePanel(client);
		gamePanel.add(new JLabel("50"));
		
		
		client.addGamePanel(gamePanel);
		frame.add(gamePanel);
		
		client.start();
		frame.setVisible(true);
	}

}
