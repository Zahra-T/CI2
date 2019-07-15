package game.asset;

import game.Location;
import game.Step;
import messageHandler.ObjectType;

public class Bomb implements Asset{
	int ID;
	int gamerID;
	boolean remove = false;  // it'll be checked in a function in game class in order to remove objects;
	Location location;
	int remainingStep = 50;
	Step step;
	ObjectType type;
	
	public Bomb(int ID, int gamerID, Location firstPlace) {
		this.ID = ID;
		this.gamerID = gamerID;
		initialize(firstPlace);
		location = firstPlace;
		this.type = ObjectType.BOMB;
		
	}
	
	void initialize(Location firstPlace){
		Location center = new Location(1920, 1030);
		double xStep = (center.getX() - firstPlace.getX())/remainingStep;
		double yStep = (center.getY()- firstPlace.getY())/remainingStep;
		step = new Step(xStep, yStep);
		
	}

	
	@Override
	public void move() {
		if(!remove) {
		location.x += step.getX();
		location.y += step.getY();
		remainingStep--;
		}
		
		if(remainingStep <= 0) {
			remove = true;
		}
	}
	
	@Override
	public boolean remove() {
		return remove;
	}
	
	@Override 
	public ObjectType getType() {
		return type;
	}
	
	@Override 
	public Location getLocation() {
		return location;
	}
	
	
	

}
