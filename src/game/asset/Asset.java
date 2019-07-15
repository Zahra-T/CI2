package game.asset;

import game.Location;
import messageHandler.ObjectType;

public interface Asset {

	
	void move();
	
	boolean remove();
	
	ObjectType getType();
	
	Location getLocation();
}
