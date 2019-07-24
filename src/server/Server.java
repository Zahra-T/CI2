package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

import Logger.Logger;
import game.Game;
import messageHandler.PaintMessage;



public class Server extends Thread {
	Stack <PaintMessage> serverPaintMessages = new Stack <PaintMessage>();
	
	Game game;
	int maxPlayer;
	ServerSocket serverSocket;
	int port;
	Logger logger = Logger.getLogger();
	ArrayList<Service> services;
	
	public Server(int port, Game game, int maxPlayer) {
		this.port = port;
		this.game = game;
		this.maxPlayer = maxPlayer;
	}
	
//	public void startGame() {
//		game.start();
//		logger.debug("started in startGame()");
//	}
	
	public void run() {
		System.out.println("server started");
		try {
			serverSocket = new ServerSocket(port);
			
			services = new ArrayList<>();
			logger.debug(maxPlayer+"");
			while(services.size() < maxPlayer) {
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
				logger.debug("service started");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
//	public void startServic() {
//		for (Service service:services) {
//			service.start();
//		}
//	}
	
	public void stopService() {
		for (Service service:services) {
			service.stop();
		}
	}
	
	
	
	
	
	
}
