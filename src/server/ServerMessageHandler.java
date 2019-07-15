package server;

import java.util.ArrayList;
import java.util.Stack;

import game.Game;
import game.Rocket;
import game.asset.Asset;
import messageHandler.ServerMessage;

public class ServerMessageHandler {
	Game game;
	

	public ServerMessageHandler(Game game) {
		this.game = game;
	}

	public Stack<ServerMessage> getMessages() {
		Stack<ServerMessage> serverMessages = new Stack<ServerMessage>();
				synchronized(game.getRockets()) {
					for(Rocket rocket : game.getRockets()) {
						serverMessages.add(new ServerMessage(rocket.getType(), rocket.getLocation()));
					}
				}

				synchronized(game.getAssets()) {
					for(Asset asset : game.getAssets()) {
						serverMessages.add(new ServerMessage(asset.getType(), asset.getLocation()));
					}
				}

				
				return serverMessages;                                                                                               
	}


}
