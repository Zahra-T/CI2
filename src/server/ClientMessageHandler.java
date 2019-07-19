package server;

import java.util.Stack;

import Logger.Logger;
import game.Game;
import game.Gamer;
import messageHandler.ClientMessage;

public class ClientMessageHandler extends Thread{
	Game game;
	int gamerID;
	Stack<ClientMessage> clientMessages = new Stack<ClientMessage>();
	Logger logger = Logger.getLogger();

	public ClientMessageHandler(Game game, int gamerID) {
		this.game = game;
		this.gamerID = gamerID;
	}

	@Override 
	public void run() {
		while(true) {
			synchronized(clientMessages) {
				if(game.running()) {
					while(!clientMessages.isEmpty()) {
						ClientMessage message = clientMessages.pop();
						handleMessage(message);
						
					}
				}
			}

			try {
				Thread.sleep(30);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}
	public void addMessages(Stack<ClientMessage> messages) {
		synchronized(messages) {
			clientMessages.addAll(messages);
		}
	}

	private synchronized void handleMessage(ClientMessage message) {
		switch(message.getType()) {

		case rightClick:{
			game.throwBomb(gamerID, message.getLocation());
			break;
		}
		case mousePressed:{
			game.setMousePressed(gamerID, true);
			break;
		}
		case mouseReleased:{
			game.setMousePressed(gamerID, false);
			break;
		}
		case mouseDragged:{
			game.setLocation(gamerID, message.getLocation());
			break;
		}
		case mouseMoved:{
			game.setLocation(gamerID, message.getLocation());
			break;
		}
		case ESCAPE:{
			game.pause();
			break;
		}

		}

	}

	public Stack<ClientMessage> getMessages(){
		return clientMessages;
	}



}
