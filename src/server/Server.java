package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

import Logger.Logger;
import game.Game;
import messageHandler.ServerMessage;



public class Server extends Thread {
	Stack <ServerMessage> serverMessages = new Stack <ServerMessage>();
	Game game = new Game();
	ServerSocket serverSocket;
	int port;
	Logger logger = Logger.getLogger();
	ArrayList<Service> services;
	
	public Server(int port) throws IOException {
		this.port = port;
	}
	
	public void startGame() {
		game.start();
		logger.debug("started in startGame()");
	}
	
	public void run() {
		System.out.println("server started");
		try {
			serverSocket = new ServerSocket(port);
			
			services = new ArrayList<>();
			while(true) {
				Socket socket = serverSocket.accept();
				
				Scanner scanner = new Scanner(socket.getInputStream());
//				String userName = scanner.next();
//				logger.debug(userName);
				Service newService = 
						new Service(
								game,
								services.size(), "zahra",
								socket.getInputStream(), 
								socket.getOutputStream());
				
				services.add(newService);
				newService.start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void stopService() {
		for (Service thread:services) {
			thread.stop();
		}
	}
	
	
}
