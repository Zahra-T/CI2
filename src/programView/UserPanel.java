package programView;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.TreeMap;

import javax.swing.BorderFactory;
import javax.swing.JLayeredPane;

import Logger.Logger;
import game.Gamer;


public class UserPanel extends JLayeredPane{
	MainController mainController;
	TreeMap<String, UserLabel> labels;
	boolean choosableLabels;
	Logger logger = Logger.getLogger();
	public UserPanel(MainController mainController, boolean choosableLabels, int x, int y, int width, int height) 
	{
		this.mainController = mainController;
		this.choosableLabels = choosableLabels;
		initialize(x, y, width, height);
	}

	private void initialize(int x, int y, int width, int height)
	{

		setBounds(x, y, width, height);
		setLayout(new GridLayout());
		//		setLayout(new FlowLayout());
		//		setBorder(BorderFactory.createLineBorder(Color.white));
		setVisible(true);

		addMouseListener(new UserPanelMouseListener(this));
	}

	public void setLabels(TreeMap<String, UserLabel> labels) { //for start panel.
		removeLabels();
		this.labels = labels;
		addLabels();
		
		this.repaint();
		this.revalidate();
	}

	public void setLabels(ArrayList<Gamer> gamers) {
		TreeMap<String, UserLabel> labels = new TreeMap<String, UserLabel>();
		for(Gamer gamer : gamers) {
			labels.put(gamer.getUsername(), new UserLabel(gamer.getUsername()+"    Score:"+gamer.getScore()+"    Heart:"+gamer.getHeart(), false, mainController));
		}
		setLabels(labels);
		

	}



	public void removeLabels() {
		logger.debug(labels+"");
		if(labels != null)
			for(UserLabel label: labels.values()) {
				logger.debug(label.toString());
				this.remove(label);
			}
	}

	public void addLabels() {
		if(labels != null)
			for(UserLabel label: labels.values()) {
				this.add(label);
			}
	}

	public void removeLabel(String username) {
		this.remove(labels.get(username));
		labels.remove(username);
	}

	public void choosePlayer(String username) {
		for(UserLabel label : labels.values()) {
			label.setBorder(null);
		}

		labels.get(username).setBorder(BorderFactory.createLineBorder(Color.white));
	}

	public void addLabel(String username) 
	{
		UserLabel newUser = new UserLabel(username, choosableLabels,  mainController);
		this.add(newUser);
		labels.put(username,newUser);

	}


	//	public void removeUser(String username)
	//	{
	//		mainController.removePlayer(username);
	//		labels.remove(username);
	//
	//	}

}

class UserPanelMouseListener implements MouseListener{
	UserPanel userPanel;
	public UserPanelMouseListener(UserPanel userPanel) {
		this.userPanel = userPanel;
	}

	@Override
	public void mouseClicked(MouseEvent e) {

	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		userPanel.setBorder(BorderFactory.createLineBorder(Color.white));
	}


	@Override
	public void mouseExited(MouseEvent e) {
		userPanel.setBorder(null);
	}

}
