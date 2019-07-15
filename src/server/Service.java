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

public class Service extends Thread{
	int ID;
	InputStream inputStream;
	OutputStream outputStream;

	Game game = new Game();
	ClientMessageHandler clientMessageHandler;
	ServerMessageHandler serverMessageHandler;
	Logger logger = Logger.getLogger();
	private transient Gson gson = new Gson();

	public Service(Game game, int gamerID, String userName, InputStream inputStream, OutputStream outputStream) {
		this.ID = gamerID;
		this.inputStream = new BufferedInputStream(inputStream);
		this.outputStream = new BufferedOutputStream(outputStream);

		initialize(ID, userName);
	}

	void initialize(int gamerID, String userName) {
		logger.debug(userName);
		Gamer gamer = new Gamer(gamerID, userName);
		Rocket rocket = new Rocket(game.getRocketsSize(), gamerID);
		clientMessageHandler = new ClientMessageHandler(game, gamerID);
		serverMessageHandler = new ServerMessageHandler(game);
		game.addGamer(gamer);
		game.addRocket(rocket);
		clientMessageHandler.start();

	}

	@Override
	public void run() {
		while(true) {
			
			Scanner scanner = new Scanner(inputStream);
			PrintStream printer = new PrintStream(outputStream);

			String serverMessage = gson.toJson(serverMessageHandler.getMessages());
			printer.print(serverMessage);
			printer.flush();
			
			logger.debug("service: "+scanner.hasNext());
			
//			while(scanner.hasNext()) {
//				String message = scanner.nextLine();
//				logger.debug("in service loop:"+message);
//				Stack<ClientMessage> messages = gson.fromJson(message, new TypeToken<Stack<ServerMessage>>() {}.getType());
//				synchronized(clientMessageHandler.getMessages()) {
//					clientMessageHandler.getMessages().addAll(messages);
//
//				}
//			}
			scanner.close();

			try {
				Thread.sleep(30);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}



		}
	}


}
