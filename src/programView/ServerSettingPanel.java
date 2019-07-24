package programView;

import java.awt.Color;
import java.awt.Font;
import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Logger.Logger;

public class ServerSettingPanel extends JPanel{

	JTextField portTextField;
	JTextField maxPlayerTextField;
	JComboBox<Integer> levelNum;
	String address = "C:\\Users\\Markazi.co\\workspace\\Chicken Invaders\\src\\game\\resources\\";
	MainController mainController;
	Menu menu;
	Logger logger = Logger.getLogger();
	public ServerSettingPanel(MainController mainController, Menu menu) 
	{
		this.mainController = mainController;
		this.menu = menu;
		initialize();
	}

	private void initialize() 
	{
		Color blue = new Color(100,100,230); 
		this.setLayout(null);
		this.setBounds(720, 250, 500, 300);
		this.setBackground(Color.black);
		this.setBorder(BorderFactory.createLineBorder(Color.white));
		
		JLabel portLabel = new JLabel("Port:");
		portLabel.setBounds(20, 30, 300, 40);
		portLabel.setFont(new Font("Serif", Font.BOLD, 20));
		portLabel.setForeground(Color.WHITE);
		portLabel.setVisible(true);
		this.add(portLabel, 2, 0);
		
		JLabel maxPlayerLabel = new JLabel("Maximum number of players:");
		maxPlayerLabel.setBounds(20, 90, 300, 40);
		maxPlayerLabel.setFont(new Font("Serif", Font.BOLD, 20));
		maxPlayerLabel.setForeground(Color.WHITE);
		maxPlayerLabel.setVisible(true);
		this.add(maxPlayerLabel, 2, 0);
		
		JLabel waveNumber = new JLabel("Wave number: ");
		waveNumber.setBounds(20, 150, 300, 40);
		waveNumber.setFont(new Font("Serif", Font.BOLD, 20));
		waveNumber.setForeground(Color.WHITE);
		waveNumber.setVisible(true);
		this.add(waveNumber, 2, 0);


		portTextField = new JTextField();
		portTextField.setBackground(blue);
		portTextField.setBorder(BorderFactory.createLineBorder(Color.white));
		portTextField.setBounds(270,40, 200, 30);
		portTextField.setHorizontalAlignment(JLabel.CENTER);
		portTextField.setForeground(Color.white);
		portTextField.setFont(new Font("Serif", Font.BOLD, 20));
		this.add(portTextField);
		
		maxPlayerTextField = new JTextField();
		maxPlayerTextField.setBackground(blue);
		maxPlayerTextField.setBorder(BorderFactory.createLineBorder(Color.white));
		maxPlayerTextField.setBounds(270, 100, 200, 30);
		maxPlayerTextField.setHorizontalAlignment(JLabel.CENTER);
		maxPlayerTextField.setForeground(Color.white);
		maxPlayerTextField.setFont(new Font("Serif", Font.BOLD, 20));
		this.add(maxPlayerTextField);
		
		Integer[] waves = new Integer[] {1, 2, 3, 4};
		levelNum = new JComboBox<Integer>(waves);
		levelNum.setBounds(270,160, 200, 30);
		this.add(levelNum);
		
		setButtons();
		this.setVisible(true);
	}

	public void setButtons()
	{
		Button ok = new Button("ok", 20, 220, 220, 60);
		ok.addActionListener((e)->{
			String portString = portTextField.getText();
			String maxNumString = maxPlayerTextField.getText();
			if(!(isNumeric(portString) && !isPortInUse("127.0.0.1",Integer.parseInt(portString))) || 
																		!isNumeric(maxNumString)) {
				if(!(isNumeric(portString) && !isPortInUse("127.0.0.1",Integer.parseInt(portString))) && !isNumeric(maxNumString)) {
					portTextField.setBackground(Color.red);
					maxPlayerTextField.setBackground(Color.red);
				}
				else if(!isNumeric(maxNumString)) {
					portTextField.setBackground(new Color(100,100,230));
					maxPlayerTextField.setBackground(Color.red);
				}
				else if(!(isNumeric(portString) && !isPortInUse("127.0.0.1",Integer.parseInt(portString)))) {
					maxPlayerTextField.setBackground(new Color(100,100,230));
					portTextField.setBackground(Color.red);
				}
			}
			else
			{
				int port = Integer.parseInt(portString);
				int maxNum = Integer.parseInt(maxNumString);
				int levelNum = (int) this.levelNum.getSelectedItem();
				System.out.println(levelNum);
				mainController.createServer(port, maxNum, levelNum);
				mainController.startServer();
				
			}
			 
			

		});
		this.add(ok,1,0);

		Button cancel = new Button("cancel", 250, 220, 220, 60);
		cancel.addActionListener((e)->{
			menu.remove(this);
			menu.enablePanel();
			menu.revalidate();
		});
		this.add(cancel,1,0);
	}
	
	public boolean isNumeric(String str) { 
		try {  
			Integer.parseInt(str);  
			return true;
		} catch(NumberFormatException e){  
			return false;  
		}  
	}
	
	private boolean isPortInUse(String host, int port) {
		  // Assume no connection is possible.
		  boolean result = false;

		  try {
		    (new Socket(host, port)).close();
		    result = true;
		  }
		  catch(IOException e) {
		    // Could not connect.
		  }
		  System.out.println(result);
		  return result;
		}

}
