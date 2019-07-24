package programView;

import java.awt.Color;
import java.awt.Font;
import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;

import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Logger.Logger;

public class ClientSettingPanel extends JPanel{

	JTextField portTextField;
	JTextField IPTextField;
	JComboBox<Integer> levelNum;
	String address = "C:\\Users\\Markazi.co\\workspace\\Chicken Invaders\\src\\game\\resources\\";
	MainController mainController;
	Menu menu;
	Logger logger = Logger.getLogger();
	public ClientSettingPanel(MainController mainController, Menu menu) 
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
		
		JLabel IPLabel = new JLabel("IP:");
		IPLabel.setBounds(100, 30, 300, 40);
		IPLabel.setFont(new Font("Serif", Font.BOLD, 20));
		IPLabel.setForeground(Color.WHITE);
		IPLabel.setVisible(true);
		this.add(IPLabel, 2, 0);
		
		JLabel portLabel = new JLabel("Port:");
		portLabel.setBounds(100, 90, 300, 40);
		portLabel.setFont(new Font("Serif", Font.BOLD, 20));
		portLabel.setForeground(Color.WHITE);
		portLabel.setVisible(true);
		this.add(portLabel, 2, 0);


		IPTextField = new JTextField();
		IPTextField.setBackground(blue);
		IPTextField.setBorder(BorderFactory.createLineBorder(Color.white));
		IPTextField.setBounds(200, 40, 200, 30);
		IPTextField.setHorizontalAlignment(JLabel.CENTER);
		IPTextField.setForeground(Color.white);
		IPTextField.setFont(new Font("Serif", Font.BOLD, 20));
		this.add(IPTextField);
		
		portTextField = new JTextField();
		portTextField.setBackground(blue);
		portTextField.setBorder(BorderFactory.createLineBorder(Color.white));
		portTextField.setBounds(200,100, 200, 30);
		portTextField.setHorizontalAlignment(JLabel.CENTER);
		portTextField.setForeground(Color.white);
		portTextField.setFont(new Font("Serif", Font.BOLD, 20));
		this.add(portTextField);

		
		setButtons();
		this.setVisible(true);
	}

	public void setButtons()
	{
		Button ok = new Button("ok", 20, 220, 220, 60);
		ok.addActionListener((e)->{
			String IP = IPTextField.getText();
			String portString = portTextField.getText();
			
			if(!(isNumeric(portString) && availablePort("127.0.0.1",Integer.parseInt(portString))) ) {
					portTextField.setBackground(Color.red);
			}
			else
			{
				int port = Integer.parseInt(portString);
				
				mainController.createClient(IP, port);
				mainController.startClient();
			
				
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
	private boolean availablePort(String host, int port) {
		  // Assume port is not available.
		  boolean result = false;

		  try {
		    (new Socket(host, port)).close();

		    // Successful connection means the port is taken.
		    result = true;
		  }
		  catch(IOException e) {
		    // Could not connect.
		  }

		  return result;
		}

}

