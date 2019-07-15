package client;

import java.awt.Graphics;
import java.awt.event.InputEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.util.Stack;

import javax.swing.JPanel;

import Logger.Logger;
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
	public GamePanel(Client client) {
		this.client = client;
		initialize();
	}
	
	private void initialize() {
		setFocusable(true);
		requestFocusInWindow();
		addMouseMotionListener();
		addMouseListener();
	}

	private void addMouseMotionListener() {
		addMouseMotionListener(new MouseMotionListener() {
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
		});
	}

	private void addMouseListener() {
		addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//				gamer.shelik();
				logger.debug("mouse clicked");
				if (e.getModifiers() == MouseEvent.BUTTON3_MASK)
				{
					logger.debug("in if");
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
		});
	}
	
	public void paint(Stack<ServerMessage> shapes){
		synchronized(shapes) {
			this.shapes = shapes;
		}
		repaint();
	}


	@Override
	public void paintComponent(Graphics g) {
		synchronized(shapes) {
			for(ServerMessage shape : shapes) {
				BufferedImage bufferedImage = (BufferedImage) gamePictures.get(shape.getType());
				Location location = shape.getLocation();
				g.drawImage(bufferedImage, (int)(location.x - bufferedImage.getWidth()/2), (int)(location.y - bufferedImage.getHeight()/2) , null);
			}
		}
	}
}
