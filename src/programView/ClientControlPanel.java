package programView;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;

import Logger.Logger;
import game.GamePictures;
import game.Gamer;
import messageHandler.ClientMessage;
import messageHandler.ClientMessageType;
import messageHandler.ObjectType;

public class ClientControlPanel extends JLayeredPane{
	MainController mainController;
	UserPanel userPanel;
	RequestSender requestSender; //wait it when you remove panel and notify it when add panel again.
	JLabel levelLabel;
	JLabel waveLabel;
	Logger logger = Logger.getLogger();
	public ClientControlPanel(MainController mainController) {
		this.mainController = mainController;
		initialize();
	}
	
	private void initialize() {
		mainController.addClientMessage(new ClientMessage(ClientMessageType.GameInfo));
		JLabel background = new JLabel(new ImageIcon(GamePictures.getInstance().get(ObjectType.startPanel)));
		background.setBounds(0,  0, 1920, 1030);
		this.add(background,1,0);
		this.setLayout(null);
		
		
		JLabel players = new JLabel("Connected players");
		players.setBounds(1190, 70, 500, 40);
		players.setFont(new Font("Serif", Font.BOLD, 30));
		players.setForeground(Color.WHITE);
		players.setVisible(true);
		this.add(players, 2, 0);
		
		userPanel = new UserPanel(mainController, false, 800, 120, 1000, 800);
		userPanel.setBorder(BorderFactory.createLineBorder(Color.white));
		this.add(userPanel, 2, 0);
		
		JLabel userPanelBack = new JLabel(new ImageIcon(GamePictures.getInstance().get(ObjectType.serverUserPanel)));
		userPanelBack.setBounds(800,  120, 1000, 800);
		this.add(userPanelBack,1,0);
		
		levelLabel = new JLabel("Level: ");
		levelLabel.setBounds(50, 100, 500, 40);
		levelLabel.setFont(new Font("Serif", Font.BOLD, 30));
		levelLabel.setForeground(Color.WHITE);
		levelLabel.setVisible(true);
		this.add(levelLabel, 2, 0);
		
		waveLabel = new JLabel("Wave: ");
		waveLabel.setBounds(50, 150, 500, 40);
		waveLabel.setFont(new Font("Serif", Font.BOLD, 30));
		waveLabel.setForeground(Color.WHITE);
		waveLabel.setVisible(true);
		this.add(waveLabel, 2, 0);
		
		this.setVisible(true);
		requestSender = new RequestSender(mainController);
		requestSender.start();
	
		
		
		
		
	}
	
	public void setGameInfo(ArrayList<Gamer> gamers, int level, int wave) {
		logger.debug(level+"");
		setLevel(level);
		setWave(wave);
		userPanel.setLabels(gamers);
	}
	
	
	
	public void setLevel(int level) {
		levelLabel.setText("Level: "+ level);
	}
	
	public void setWave(int wave) {
		waveLabel.setText("Wave:"+ wave);
	}
	
	public void waitRequest() {
		try {
			requestSender.wait();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void notifyRequest() {
		requestSender.notify();
	}
	
	public Thread.State getState(){
		return requestSender.getState();
	}
	
	
	
	
	

	
	
	
}

class RequestSender extends Thread{
	MainController mainController;
	Logger logger = Logger.getLogger();
	public RequestSender(MainController mainController) {
		this.mainController = mainController;
	}
	
	@Override 
	public void run() {
		while(true) {
			mainController.addClientMessage(new ClientMessage(ClientMessageType.GameInfo));
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
