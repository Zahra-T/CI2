package programControl;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.JLabel;


public class UserLabel extends JLabel{

	String username;
	MainController mainController;
	public UserLabel(String userName, MainController mainController)
	{
		this.username = userName;
		this.mainController = mainController;
		initialize();

	}

	private void initialize()
	{
		//		isChoosed = false;
		setText(username);
		this.setPreferredSize(new Dimension(100, 50));//How?
		setFont(new Font("Serif", Font.BOLD, 40));
		setForeground(Color.white);
		setHorizontalAlignment(JLabel.CENTER);

		addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				mainController.choosePlayer(username);
				
			}

			@Override
			public void mousePressed(MouseEvent e) {


			}

			@Override
			public void mouseReleased(MouseEvent e) {
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				UserLabel.this.setBorder(BorderFactory.createLineBorder(Color.white));

			}


			@Override
			public void mouseExited(MouseEvent e) {
				if(!isChoosed())
				{
					UserLabel.this.setBorder(null);
				}

			}
		});
	}

	private boolean isChoosed() {
		return (this.username == mainController.getUsername());
	}

}
