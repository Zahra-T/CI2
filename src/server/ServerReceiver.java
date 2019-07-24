package server;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;
import java.util.Stack;
import messageHandler.ClientMessage;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import Logger.Logger;
import messageHandler.PaintMessage;

public class ServerReceiver extends Thread{
	InputStream inputStream;
	ClientMessageHandler clientMessageHandler;
	Logger logger = Logger.getLogger();
	public ServerReceiver(InputStream inputStream, ClientMessageHandler clientMessageHandler) {
		this.inputStream = inputStream;
		this.clientMessageHandler = clientMessageHandler;

	}

	@Override
	public void run() {
		clientMessageHandler.start();
		
		Scanner scanner = new Scanner(inputStream);
		Gson gson = new Gson();
		while(true) {
			if(scanner.hasNext()) {
				String message = scanner.nextLine();
				Stack<ClientMessage> messages = gson.fromJson(message, new TypeToken<Stack<ClientMessage>>() {}.getType());
				clientMessageHandler.addMessages(messages);
			}
			

			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
