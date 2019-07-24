package programView;

import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JRadioButton;

import game.GamePictures;
import messageHandler.ObjectType;

public class ServerControlPanel extends JLayeredPane{
	
	MainController mainController;
	UserPanel userPanel;
	JRadioButton viewer;
	JRadioButton player;

	public ServerControlPanel(int port, int maxPlayer, int levelNum, MainController mainController) {
		this.mainController = mainController;
		initialize(port, maxPlayer, levelNum);
	}
	
	private void initialize(int port, int maxPlayer, int levelNum) {
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
		
		
		JLabel portLabel = new JLabel("Port: "+port);
		portLabel.setBounds(50, 100, 500, 40);
		portLabel.setFont(new Font("Serif", Font.BOLD, 30));
		portLabel.setForeground(Color.WHITE);
		portLabel.setVisible(true);
		this.add(portLabel, 2, 0);
		
		JLabel maxPlayerLabel = new JLabel("Maximum number of players: "+maxPlayer);
		maxPlayerLabel.setBounds(50, 150, 500, 40);
		maxPlayerLabel.setFont(new Font("Serif", Font.BOLD, 30));
		maxPlayerLabel.setForeground(Color.WHITE);
		maxPlayerLabel.setVisible(true);
		this.add(maxPlayerLabel, 2, 0);
		
		JLabel levelNumLabel = new JLabel("Number of levels: "+levelNum);
		levelNumLabel.setBounds(50, 200, 500, 40);
		levelNumLabel.setFont(new Font("Serif", Font.BOLD, 30));
		levelNumLabel.setForeground(Color.WHITE);
		levelNumLabel.setVisible(true);
		this.add(levelNumLabel, 2, 0);
		
		viewer = new JRadioButton("  Viewer");
		viewer.getModel().setEnabled(false);
		viewer.setBounds(50, 800, 500, 40);
		viewer.setForeground(Color.white);
		viewer.setFont(new Font("Serif", Font.BOLD, 30));
		viewer.setContentAreaFilled(false);
		viewer.setBorderPainted(false);
		viewer.setOpaque(false);
		
		player = new JRadioButton("  Player");
		player.setSelected(true);
		player.setBounds(50, 850, 500, 40);
		player.setForeground(Color.white);
		player.setFont(new Font("Serif", Font.BOLD, 30));
		player.setContentAreaFilled(false);
		player.setBorderPainted(false);
		player.setOpaque(false);
		
		ButtonGroup group = new ButtonGroup();
		group.add(viewer);
		group.add(player);
		this.add(viewer, 2, 0);
		this.add(player, 2, 0);
		
		Button startGame = new Button("StartGame", 100, 800, 600, 100);
		startGame.addActionListener((e)->{
			
		});
		this.add(startGame, 2, 0);
		
	}
	
	public void enableViewer(boolean enabled) {
		viewer.getModel().setEnabled(enabled);
	}
	
	public void addLabel(String username) {
		userPanel.addLabel(username);
	}
	
	public void removeLabel(String username) {
		userPanel.removeLabel(username);
	}
	
	

}
