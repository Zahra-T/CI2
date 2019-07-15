package game;

import messageHandler.ObjectType;

public class Rocket {
	int ID;
	int gamerID;
	Location location;
	ObjectType type;
	
	public Rocket(int ID, int gamerID) {
		this.ID = ID;
		this.gamerID = gamerID;
		this.type = ObjectType.ROCKET;
	}
	
	boolean mousePressed = false;
	
	double getX() {
		return location.getX();
	}
	double getY() {
		return location.getY();
	}
	
	void setMousePressed(boolean mousePressed) {
		this.mousePressed = mousePressed;
	}
	
	public void setLocation(Location location) {
		this.location = location;
	}
	
	public Location getLocation() {
		return location;
	}
	public int getGamerID() {
		return gamerID;
	}
	
	public ObjectType getType() {
		return type;
	}
}
