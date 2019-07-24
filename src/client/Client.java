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
import client.swing.GamePanel;
import game.Gamer;
import game.Location;
import game.Triple;
import messageHandler.ClientMessage;
import messageHandler.OrderMessage;
import messageHandler.PaintMessage;
import messageHandler.ServerMessage;
import programView.MainController;

public class Client{

	String IP;
	int port;
	String userName;
	Socket socket;
	Stack <ClientMessage>clientMessages = new Stack<ClientMessage>();
	ClientSender clientSender;
	ClientReceiver clientReceiver;
	PaintLoop paintLoop = new PaintLoop(this); //start when game starts.
	OrderHandler orderHandler = new OrderHandler(this);
	boolean moving;
	boolean running;
	GamePanel gamePanel;
	MainController mainController;
	Logger logger = Logger.getLogger();
	public Client(String IP, int port, String userName, MainController mainController) {
		this.IP = IP;
		this.port = port;
		this.userName = userName;
		this.mainController = mainController;
		initialize();
	}
	
	public Client(String IP, int port, MainController mainController) {
		this.IP = IP;
		this.port = port;
		this.mainController = mainController;
		initialize();
	}
	
	private void initialize() {
		
	}


	public void start() {
		try {
			socket = new Socket(IP, port);
			orderHandler.start();
			clientSender = new ClientSender(socket.getOutputStream(), clientMessages);
			clientSender.start();
			clientReceiver = new ClientReceiver(socket.getInputStream(), this);
			clientReceiver.start();
			System.out.println("client connected");
			

		} catch (IOException e) {
			e.printStackTrace();
		}
			
	}
	
	public void startGame() { //call when we receive a start game message from server.

			paintLoop.start();
			moving = true;
			//running

	}
	
	public void addPaintMessage(Stack<PaintMessage> messages) {
		paintLoop.addMessages(messages);
	}
	
	public void addOrderMessage(OrderMessage message) {
		orderHandler.addMessage(message);
	}
	
	public void paint(Stack<PaintMessage> messages) {
		gamePanel.paint(messages);
	}
	
	public void addGamePanel(GamePanel gamePanel) {
		this.gamePanel = gamePanel;
	}

	public void addClientMessage(ClientMessage message) {
		synchronized(clientMessages) {
			clientMessages.add(message);
		}
	}
	
	public void showInfo(Triple <ArrayList<Gamer>, Integer, Integer> gameInfo) {
		mainController.showInfo(gameInfo);
	}
	

	public boolean isMoving() {
		return moving;
	}
	
	public boolean running() {
		return running;
	}
	
	public void setRunning(boolean running) { //call with server order.
		this.running = running;
	}
}
