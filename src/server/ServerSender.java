package server;

import java.io.OutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Stack;

import messageHandler.Pair;
import messageHandler.ServerMessage;
import messageHandler.PaintMessage;
import messageHandler.ServerMessageType;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import Logger.Logger;

public class ServerSender extends Thread{
	ArrayList<ServerMessage> serverOrderMessages = new ArrayList<ServerMessage>();
	OutputStream outputStream;
	Service service;
	Logger logger = Logger.getLogger();

	public ServerSender(OutputStream outputStream, Service service) {
		this.outputStream = outputStream;
		this.service = service;
	}

	@Override
	public void run() {
		PrintWriter printer = new PrintWriter(outputStream, true);
		Gson gson = new Gson();

		while(true) {
			
			ServerMessage serverPaintMessage = new ServerMessage(ServerMessageType.PAINT, service.getServerMessages());
			String paintMessage = gson.toJson(serverPaintMessage, new TypeToken<ServerMessage>(){}.getType());
			printer.println(paintMessage);
			
			synchronized(serverOrderMessages) {
				for(ServerMessage serverOrderMessage : serverOrderMessages) {
					String orderMessage = gson.toJson(serverOrderMessage, new TypeToken<ServerMessage>(){}.getType());
					printer.println(orderMessage);
				}
			}

			printer.flush();

			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}



		}
	}
	
	public void addOrderMessage(ServerMessage message) {
		synchronized(serverOrderMessages) {
			serverOrderMessages.add(message);
			logger.debug("add message:"+message);
		}
	}

}
