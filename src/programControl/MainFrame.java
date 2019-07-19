package programControl;

import javax.swing.*;

public class MainFrame extends JFrame {
    
    public  MainFrame() 
    {
    	initialize();
    }
    
    private void initialize()
    {
    	setTitle("Swing");
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setBounds(0,0, 1920, 1030);
//		setExtendedState(JFrame.MAXIMIZED_BOTH); 
//		setUndecorated(true);
		setVisible(true);
       
    }
    

}
