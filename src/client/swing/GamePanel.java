package client.swing;

import java.awt.Graphics;
import java.awt.event.InputEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Stack;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Logger.Logger;
import client.Client;
import game.GamePictures;
import game.Location;
import messageHandler.ClientMessage;
import messageHandler.ClientMessageType;
import messageHandler.ObjectType;
import messageHandler.ServerMessage;

public class GamePanel extends JPanel{
	Stack<ServerMessage> shapes = new Stack<ServerMessage>();
	GamePictures gamePictures = GamePictures.getInstance();
	Client client;
	Logger logger = Logger.getLogger();
	Background background;
	public GamePanel(Client client) {
		this.client = client;
		initialize();
	}
	
	private void initialize() {
		background = new Background();
		setFocusable(true);
		requestFocusInWindow();
		addMouseMotionListener(new myMouseMotionListener(client));
		addMouseListener(new myMouseListener(client));
		
	}
	
	public void paint(Stack<ServerMessage> shapes){
		
		synchronized(shapes) {
			this.shapes = shapes;
		}
		repaint();
		
//		background.move();
	}


	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		background.paint(g);
		synchronized(shapes) {
			for(ServerMessage shape : shapes) {
				BufferedImage bufferedImage = (BufferedImage) gamePictures.get(shape.getType());
				Location location = shape.getLocation();
				g.drawImage(bufferedImage, (int)(location.x - bufferedImage.getWidth()/2), (int)(location.y - bufferedImage.getHeight()/2) , null);
			}
			
		}

	}
}

class myMouseMotionListener implements MouseMotionListener{
	Client client;
	Logger logger = Logger.getLogger();
	public myMouseMotionListener(Client client) {
		this.client = client;
	}

	
		@Override
		public void mouseDragged(MouseEvent e) {
			if(client.isMoving()) {
				client.addMessage(new ClientMessage(ClientMessageType.mouseDragged, e.getX(), e.getY()));
			}
		}
		@Override
		public void mouseMoved(MouseEvent e) {
			if(client.isMoving()) {
				client.addMessage(new ClientMessage(ClientMessageType.mouseMoved, e.getX(), e.getY()));
			}
		}
}

class myMouseListener implements MouseListener{

	Client client;
	Logger logger = Logger.getLogger();
	
	public myMouseListener(Client client) {
		this.client = client;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getModifiers() == MouseEvent.BUTTON3_MASK)
		{
				client.addMessage(new ClientMessage(ClientMessageType.rightClick, null));
		
		}
	}


	@Override
	public void mousePressed(MouseEvent e) {

		int modifiers = e.getModifiers();
		if ((modifiers & InputEvent.BUTTON1_MASK) == InputEvent.BUTTON1_MASK) {
			client.addMessage(new ClientMessage(ClientMessageType.mousePressed, null));

		}


	}

	@Override
	public void mouseReleased(MouseEvent e) {
		client.addMessage(new ClientMessage(ClientMessageType.mouseReleased, null));
	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}
	
	
}



