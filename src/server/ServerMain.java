package server;

import java.io.IOException;

import Logger.Logger;

public class ServerMain {
	static Logger logger = Logger.getLogger();
	public static void main(String [] args) {
		
		Server server;
		try {
			server = new Server(9090);
			server.start();
			server.startGame();
			logger.debug("started in server main");
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
	
}
