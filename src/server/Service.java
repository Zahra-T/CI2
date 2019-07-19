package server;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
import java.util.Stack;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import Logger.Logger;
import messageHandler.ClientMessage;
import messageHandler.ServerMessage;
import game.Game;
import game.Gamer;
import game.Rocket;
import game.asset.Asset;

public class Service extends Thread{
	int ID;
	ServerReceiver receiver;
	ServerSender sender;
	
	InputStream inputStream;
	OutputStream outputStream;
	Game game; // a common class between services;
	Logger logger = Logger.getLogger();
	private transient Gson gson = new Gson();

	public Service(Game game, int gamerID, String userName, InputStream inputStream, OutputStream outputStream) {
		this.ID = gamerID; 
		this.inputStream = new BufferedInputStream(inputStream);
		this.outputStream = new BufferedOutputStream(outputStream);
		this.game = game;
		initialize(userName);
	}

	void initialize(String userName) {
		logger.debug(userName);
		Gamer gamer = new Gamer(ID, userName);
		Rocket rocket = new Rocket(game.getRocketsSize(), ID);
		game.addGamer(gamer);
		game.addRocket(rocket);

		
	}
	
	@Override 
	public void run() {
		receiver = new ServerReceiver(inputStream, new ClientMessageHandler(game, ID));
		receiver.start();
		sender = new ServerSender(outputStream, this);
		sender.start();
		
	}
	
	public Stack<ServerMessage> getServerMessages() {
		
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
