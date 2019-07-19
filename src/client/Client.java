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
import game.Location;
import messageHandler.ClientMessage;
import messageHandler.ServerMessage;

public class Client extends Thread{

	String IP;
	int port;
	String userName;
	Stack <ClientMessage>clientMessages = new Stack<ClientMessage>();
	ClientSender clientSender;
	ClientReceiver clientReceiver;
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
			
			clientSender = new ClientSender(socket.getOutputStream(), clientMessages);
			clientSender.start();
			clientReceiver = new ClientReceiver(socket.getInputStream(), this);
			clientReceiver.start();
			
			moving = true;
			System.out.println("client connected");
			
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
	}	

	public void addGamePanel(GamePanel gamePanel) {
		this.gamePanel = gamePanel;
	}

	public void addMessage(ClientMessage message) {
		synchronized(clientMessages) {
			clientMessages.add(message);
		}
	}
	
	public void paint(Stack <ServerMessage> messages) {
		gamePanel.paint(messages);
	}

	public boolean isMoving() {
		return moving;
	}
}
