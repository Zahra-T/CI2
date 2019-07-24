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
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import Logger.Logger;
import messageHandler.ClientMessage;
import messageHandler.OrderMessage;
import messageHandler.OrderType;
import messageHandler.PaintMessage;
import messageHandler.ServerMessage;
import messageHandler.ServerMessageType;
import game.Game;
import game.Gamer;
import game.Rocket;
import game.asset.Asset;
import game.Triple;
public class Service{
	int ID;
	ServerReceiver receiver;
	ServerSender sender;
	ClientMessageHandler messageHandler;
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
		receiver = new ServerReceiver(inputStream, new ClientMessageHandler(game, ID, this));
		sender = new ServerSender(outputStream, this);
		messageHandler = new ClientMessageHandler(game, ID, this);
		logger.debug(userName);
		Gamer gamer = new Gamer(ID, userName);
		Rocket rocket = new Rocket(game.getRocketsSize(), ID);
		game.addGamer(gamer);
		game.addRocket(rocket);
	}
	
	public void start() {
		receiver.start();
		sender.start();
		messageHandler.start();
	}
	
	public void stop() {
		receiver.stop();
		sender.stop();
		messageHandler.stop();
	}
	
	public Stack<PaintMessage> getServerMessages() {
		
		Stack<PaintMessage> serverMessages = new Stack<PaintMessage>();
				synchronized(game.getRockets()) {
					for(Rocket rocket : game.getRockets()) {
						serverMessages.add(new PaintMessage(rocket.getType(), rocket.getLocation()));
					}
				}

				synchronized(game.getAssets()) {
					for(Asset asset : game.getAssets()) {
						serverMessages.add(new PaintMessage(asset.getType(), asset.getLocation()));
					}
				}

				
				return serverMessages;                                                                                               
	}
	
	public void sendGameInfo() {
		Triple<ArrayList<Gamer>, Integer, Integer> info = game.getInfo();
		sender.addOrderMessage(new ServerMessage(ServerMessageType.ORDER, new OrderMessage(OrderType.GameInfo, info)));
	}



}
