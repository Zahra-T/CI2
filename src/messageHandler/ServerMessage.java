package messageHandler;

import java.util.Stack;

public class ServerMessage {
	ServerMessageType type;
	Stack<PaintMessage> paintMessages;
	OrderMessage orderMessage;
	
	public ServerMessage() {}
	
	public ServerMessage(ServerMessageType type, Stack<PaintMessage> paintMessages) {
		this.type = type;
		this.paintMessages = paintMessages;
	}
	
	public ServerMessage(ServerMessageType type, OrderMessage orderMessage) {
		this.type = type;
		this.orderMessage = orderMessage;
	}
	
	public ServerMessageType getType() {
		return type;
	}
	
	public Stack<PaintMessage> getPaintMessage(){
		return paintMessages;
	}
	
	public OrderMessage getOrderMessage() {
		return orderMessage;
	}

}
