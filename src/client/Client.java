package client;

import java.awt.Window.Type;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;
import java.util.Vector;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import Logger.Logger;
import game.Location;
import messageHandler.ClientMessage;
import messageHandler.ServerMessage;

public class Client extends Thread{

	String IP;
	int port;
	String userName;
	Stack <ClientMessage>clientMessages = new Stack<ClientMessage>();
	ServerMessageHandler serverMessageHandler;
	private transient Gson gson = new Gson();
	boolean moving;
	GamePanel gamePanel;
	Logger logger = Logger.getLogger();
	public Client(String IP, int port, String userName) {
		this.IP = IP;
		this.port = port;
		this.userName = userName;
	}


	@Override
	public void run() {


		try {
			Socket socket = new Socket(IP, port);


			PrintStream printer = new PrintStream(socket.getOutputStream());
			Scanner scanner = new Scanner(socket.getInputStream());

			System.out.println("client connected");

			//		printer.println(userName);
			while(true) {

				synchronized(clientMessages) {
					if(!clientMessages.isEmpty()) {
						logger.debug("client sent");
						String message = gson.toJson(clientMessages, new TypeToken<Stack<ClientMessage>>(){}.getType());
						printer.println(message);
						printer.flush();
						logger.debug("client flushed");
						clientMessages.clear();
					}
				}
				if(scanner.hasNext()) {
					logger.debug(scanner.nextLine());
					logger.debug(scanner.hasNext()+"");
				}
				

				//				if(scanner.hasNext()) {
//					String message = scanner.nextLine();
//					logger.debug(message);
//
//
//					logger.debug("in client loop:"+message);
//					Stack<ServerMessage> messages = gson.fromJson(message, new TypeToken<Stack<ServerMessage>>() {}.getType());
//					gamePanel.paint(messages);
//				}

				try {
					Thread.sleep(30);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

			}

		} catch (IOException e1) {
			e1.printStackTrace();
		}

	}	

	public void addGamePanel(GamePanel gamePanel) {
		this.gamePanel = gamePanel;
	}

	public void addMessage(ClientMessage message) {
		logger.debug("message added");
		synchronized(clientMessages) {
			clientMessages.add(message);
		}
	}

	public boolean isMoving() {
		return moving;
	}
}
