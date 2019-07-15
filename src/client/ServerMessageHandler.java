package client;

import java.util.Stack;

import messageHandler.ClientMessage;
import messageHandler.ServerMessage;

public class ServerMessageHandler extends Thread{
	Stack <ServerMessage>serverMessages = new Stack<ServerMessage>();
	@Override 
	public void run() {
		while(true) {
			synchronized(serverMessages) {
				while(!serverMessages.isEmpty()) {
					ServerMessage message = serverMessages.pop();
					handleMessage(message);
				}
			}

			try {
				Thread.sleep(30);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	private synchronized void handleMessage(ServerMessage message) {


	}

	public void setMessages(Stack<ServerMessage> messages) {
		synchronized(this.serverMessages) {
			this.serverMessages = messages;
		} 
		
	}
	
	public Stack<ServerMessage> getMessages(){
		return serverMessages;
	}
	

}
