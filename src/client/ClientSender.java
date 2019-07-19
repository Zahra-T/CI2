package client;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;
import java.util.Stack;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import Logger.Logger;
import messageHandler.ClientMessage;

public class ClientSender extends Thread{
	OutputStream outputStream;
	transient Stack<ClientMessage> clientMessages;
	Logger logger = Logger.getLogger();
	public ClientSender(OutputStream outputStream, Stack<ClientMessage> clientMessages) {
		this.outputStream = outputStream;
		this.clientMessages = clientMessages;
	}

	@Override
	public void run() {

		PrintStream printer = new PrintStream(outputStream);


		Gson gson = new Gson();

		//		printer.println(userName);
		while(true) {

			synchronized(clientMessages) {
				if(!clientMessages.isEmpty()) {
					String message = gson.toJson(clientMessages, new TypeToken<Stack<ClientMessage>>(){}.getType());
					printer.println(message);
					printer.flush();
					clientMessages.clear();
				}
			}

			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}


		}





	}



}	





