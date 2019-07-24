package messageHandler;

import com.google.gson.Gson;

import Logger.Logger;
import game.Location;

public class ClientMessage{
	ClientMessageType messageType;
	Location location;
	transient Logger logger = Logger.getLogger();
	
	public ClientMessage() {}
	public ClientMessage(ClientMessageType messageType, Location location){
		this.messageType = messageType;
		this.location = location;
	}
	
	public ClientMessage(ClientMessageType type) {
		this.messageType = type;
	}
	
	public ClientMessage(ClientMessageType messageType, double x, double y){
		this.messageType = messageType;
		this.location = new Location(x, y);
	}
	
	public ClientMessageType getType() {
		return messageType;
	}

	public Location getLocation() {
			return location;
	}
	
	@Override
	public String toString() {
		return (new Gson()).toJson(this);
	}
}
