package Test;

import java.io.IOException;

import Logger.Logger;
import programView.MainController;

public class ServerMain {
	static Logger logger = Logger.getLogger();
	public static void main(String [] args) {
		MainController mainController = new MainController();
		mainController.createServer(9090, 5, 4);
		mainController.startServer();
//		Server server;
//		try {
//			server = new Server(9090);
//			server.start();
//			server.startGame();
//			
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
		
		
	}
	
}
