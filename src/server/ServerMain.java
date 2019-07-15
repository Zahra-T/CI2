package server;

import java.io.IOException;

public class ServerMain {
	public static void main(String [] args) {
		
		Server server;
		try {
			
			server = new Server(1111);
			server.start();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
	
}
