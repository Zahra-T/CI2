package messageHandler;

import com.google.gson.Gson;

import game.Location;

public class ServerMessage {
	ObjectType objectType;
	Location location;
	public ServerMessage() {};
	public ServerMessage(ObjectType objectType, Location location) {
		this.objectType = objectType;
		this.location = location;
	}
	
	public ObjectType getType() {
		return objectType;
	}
	
	public Location getLocation() {
		return location;
	}
	
	@Override
	public String toString() {
		return (new Gson()).toJson(this);
	}

}
