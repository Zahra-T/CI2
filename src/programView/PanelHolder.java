package programView;

import java.awt.Container;
import java.io.IOException;
import java.util.ArrayList;

import game.Triple;
import game.Gamer;
public class PanelHolder {
	MainController mainController;
	
	Container contentPane;
	
	StartPanel startPanel;
	Menu menu;
	SelectionPanel selectionPanel;
	ServerOrClientPanel serverOrClientPanel;
	SettingPanel settingPanel;
	ServerSettingPanel serverSettingPanel;
	ServerControlPanel serverControlPanel;
	ClientSettingPanel clientSettingPanel;
	ClientControlPanel clientControlPanel;
	public PanelHolder(MainController mainController) {
		this.mainController = mainController;
		MainFrame frame = new MainFrame();
		contentPane = frame.getContentPane();
		
		startPanel = new StartPanel(mainController);
		contentPane.add(startPanel);

		contentPane.repaint();
		contentPane.revalidate();
	}


	public void toMenu() {
		
		contentPane.removeAll();
		if(menu == null) menu = new Menu(mainController);
		contentPane.add(menu);
		contentPane.validate();
		contentPane.repaint();

	}

	public void toStartPanel() {
		contentPane.removeAll();
		if(startPanel == null) startPanel = new StartPanel(mainController);
		contentPane.add(startPanel);
		contentPane.validate();
		contentPane.repaint();

	}

	public void toSetting() {
		contentPane.removeAll();
		if(settingPanel == null) {
			try {
				settingPanel = new SettingPanel();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		contentPane.add(settingPanel);
		contentPane.repaint();
		contentPane.validate();

	}

	public void toSelectionPanel() {
		if(selectionPanel == null) {
			selectionPanel = new SelectionPanel(mainController, menu);
		}
		menu.disablePanel();
		menu.add(selectionPanel, 4, 0);
		menu.repaint(); 
	}

	public void toServerOrClientPanel() {
		menu.remove(selectionPanel);
		if(serverOrClientPanel == null) {
			serverOrClientPanel = new ServerOrClientPanel(mainController, menu);
		}
		menu.add(serverOrClientPanel, 4, 0);
		menu.repaint();
	}
	
	public void toServerSettingPanel() {
		menu.remove(serverOrClientPanel);
		if(serverSettingPanel == null) {
			serverSettingPanel = new ServerSettingPanel(mainController, menu);
		}
		menu.add(serverSettingPanel, 4, 0);
		menu.repaint();
	}
	
	public void toClientControlPanel() {
		if(clientControlPanel == null) {
			clientControlPanel = new ClientControlPanel(mainController);
		}
		if(menu != null)menu.removeAll();
		contentPane.removeAll();
		contentPane.add(clientControlPanel);
		if (clientControlPanel.getState() == Thread.State.WAITING) {
			clientControlPanel.notifyRequest();
		}
		contentPane.repaint();
		contentPane.revalidate();
	}
	
	public void toServerControlPanel(int port, int maxPlayer, int levelNum) {
		contentPane.removeAll();
		if(serverControlPanel == null) serverControlPanel = new ServerControlPanel(port, maxPlayer, levelNum, mainController);
		contentPane.add(serverControlPanel);
		contentPane.repaint();
		contentPane.revalidate();
	}
	
	public void toClientSettingPanel() {
		menu.remove(serverOrClientPanel);
		if(clientSettingPanel == null) {
			clientSettingPanel = new ClientSettingPanel(mainController, menu);
		}
		menu.add(clientSettingPanel, 4, 0);
		menu.repaint();
	}
	
	public void showInfo(Triple<ArrayList<Gamer>, Integer, Integer> info) {
		if(clientControlPanel == null) {
			clientControlPanel = new ClientControlPanel(mainController);
		}
		clientControlPanel.setGameInfo(info.getFirst(), info.getSecond(), info.getThird());
	}
	

	
	public void setLabels() {
		startPanel.setLabels();
	}
	
	public void choosePlayer(String username) {
		startPanel.choosePlayer(username);
	}
	
	public StartPanel getStartPanel() {
		return startPanel;
	}

}
