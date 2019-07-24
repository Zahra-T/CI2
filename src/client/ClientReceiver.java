package client;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import java.util.Stack;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import Logger.Logger;
import messageHandler.ClientMessage;
import messageHandler.Pair;
import messageHandler.PaintMessage;
import messageHandler.ServerMessageType;
import messageHandler.ServerMessage;
public class ClientReceiver extends Thread{

	InputStream inputStream;
	Client client;
	Logger logger = Logger.getLogger();
	public ClientReceiver(InputStream inputStream, Client client) {
		this.inputStream = inputStream;
		this.client = client;
	}

	@Override
	public void run() {

		Gson gson = new Gson();
		Scanner scanner = new Scanner(inputStream);
		System.out.println("client connected");

		//		printer.println(userName);


		while(true) {

			if(scanner.hasNext()) {
				String messageString = scanner.nextLine();
				ServerMessage message = gson.fromJson(messageString, new TypeToken<ServerMessage>(){}.getType());
				switch(message.getType()) {
				case PAINT:{
					client.addPaintMessage(message.getPaintMessage());
					break;
				}
				case ORDER:{
					client.addOrderMessage(message.getOrderMessage());
					break;
				}
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
