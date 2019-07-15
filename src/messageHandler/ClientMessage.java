package messageHandler;

import game.Location;

public class ClientMessage{
	ClientMessageType messageType;
	Object info;
	
	public ClientMessage(ClientMessageType messageType, Object info){
		this.messageType = messageType;
		this.info = info;
	}
	
	public ClientMessage(ClientMessageType messageType, double x, double y){
		this.messageType = messageType;
		this.info = new Location(x, y);
	}
	
	public ClientMessageType getType() {
		return messageType;
	}

	public Location getLocation() {
		if(info instanceof Location) {
			return (Location)info;
		}
		return null;
	}
}
