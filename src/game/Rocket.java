package game;

import messageHandler.ObjectType;

public class Rocket {
	int ID;
	int gamerID;
	Location location;
	ObjectType type;
	
	public Rocket() {}
	public Rocket(int ID, int gamerID) {
		this.ID = ID;
		this.gamerID = gamerID;
		this.type = ObjectType.ROCKET;
		this.location = new Location(960, 515);
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
		return this.location;
	}
	public int getGamerID() {
		return gamerID;
	}
	
	public ObjectType getType() {
		return type;
	}
}
