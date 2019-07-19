package client.swing;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

import Logger.Logger;
import game.Location;

public class Background{
	BufferedImage bufferedImage;
	String address ="C:\\Users\\Markazi.co\\workspace\\Chicken Invaders\\src\\game\\resources\\";
	Location location = new Location(0 , 0);
	Logger logger = Logger.getLogger();
	
	public Background() {
		
		try {
			bufferedImage = ImageIO.read(new File(address+"background.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void paint(Graphics g) {
		  g.drawImage(bufferedImage, (int)location.x,(int) location.y, null);
		  logger.debug("in paint");
	}
	
	public void move()
	{
		location.y += 20;
		if(location.y >= 1030)
		{
			location.y = - 1030;
		}
	}
	
	
}
