package programControl;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.TreeMap;

import javax.swing.BorderFactory;
import javax.swing.JLayeredPane;

import Logger.Logger;


public class UserPanel extends JLayeredPane{
	MainController mainController;
	TreeMap<String, UserLabel> labels;
	Logger logger = Logger.getLogger();
	public UserPanel(MainController mainController) 
	{
		this.mainController = mainController;
		setLabels();
		initialize();
	}

	private void initialize()
	{
		
		setBounds(500, 200, 950, 500);
		setLayout(new GridLayout());
		setLabels();

		setVisible(true);

		addMouseListener(new UserPanelMouseListener(this));
	}
	
	public void setLabels() {
		removeLabels();
		labels = mainController.getLabels();
		addLabels();
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

	public void addLabel(String username) throws IOException
	{
			UserLabel newUser = new UserLabel(username, mainController);
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
