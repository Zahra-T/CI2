package client;

import java.util.LinkedList;
import java.util.Stack;

import messageHandler.PaintMessage;

public class PaintLoop extends Thread{
	LinkedList<Stack<PaintMessage>> messages = new LinkedList<Stack<PaintMessage>>();
	Client client;

	public PaintLoop(Client client) {
		this.client = client;
	}

	@Override
	public void run() {
		while(true) {
			if(client.running() && !messages.isEmpty()) {
				synchronized(messages) {
					client.paint(messages.get(0));
					messages.removeFirst();
				}
			}

			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}


	public void addMessages(Stack<PaintMessage> message) {
		synchronized(messages) {
			this.messages.add(message);
		}
	}





}
