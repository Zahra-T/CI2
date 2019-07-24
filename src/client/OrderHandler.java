package client;

import java.util.LinkedList;

import Logger.Logger;
import messageHandler.OrderMessage;

public class OrderHandler extends Thread{
	LinkedList<OrderMessage> messages = new LinkedList<OrderMessage>();
	Client client;
	Logger logger = Logger.getLogger();
	public OrderHandler(Client client) {
		this.client = client;
	}


	@Override
	public void run() {
		while(true) {
			if(!messages.isEmpty()) {
				synchronized(messages){
					for(OrderMessage message: messages) {

						handleOrder(message);
						logger.debug("in while:"+message);
					}
				}
			}
			try {
				Thread.sleep(20);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void handleOrder(OrderMessage message) {
		switch(message.getType()) {
		case GameInfo:{
			client.showInfo(message.getGameInfo());
			logger.debug("message handled");
		}
		}
	}

	public void addMessage(OrderMessage message) {
		synchronized(messages) {
			messages.add(message);
		}
	}


}
